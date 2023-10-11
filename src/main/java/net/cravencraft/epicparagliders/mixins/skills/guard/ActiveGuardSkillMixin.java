package net.cravencraft.epicparagliders.mixins.skills.guard;

import net.cravencraft.epicparagliders.EPModCfg;
import net.cravencraft.epicparagliders.capabilities.PlayerMovementInterface;
import net.cravencraft.epicparagliders.utils.MathUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import tictim.paraglider.capabilities.PlayerMovement;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.skill.guard.ParryingSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.HurtEvent;

@Mixin(ParryingSkill.class)
public abstract class ActiveGuardSkillMixin extends GuardSkill {

    private float penalty;
    private float impact;
    private PlayerPatch playerPatch;

    public ActiveGuardSkillMixin(Builder builder) {
        super(builder);
    }

    @Inject(method = "guard", at = @At("HEAD"), remap = false)
    private void getPlayerPatch(SkillContainer container, CapabilityItem itemCapability, HurtEvent.Pre event, float knockback, float impact, boolean advanced, CallbackInfo ci) {
        this.playerPatch = event.getPlayerPatch();
        this.impact = impact;
    }

    @ModifyVariable(method = "guard", at = @At(value = "STORE"), ordinal = 2, remap = false)
    private float penalty(float penalty) {
        this.penalty = penalty;
        return penalty;
    }

    /**
     * Modifies the 'stamina' variable in the 'guard' method of the ActiveGuardSkill class.
     * Takes in values retrieved from the above methods that simply store the penalty and impact
     * from the same method to factor into this method's new stamina consumption factor.
     *
     * Calculates the stamina cost based on player armor weight, impact, and block penalty.
     * If active guard is successful (penalty > 0.1), then less stamina is drained
     * (vs. negating all stamina being drained initially), else even more stamina is drained
     * from the block. Making this a high risk high reward system to choose of regular guard.
     *
     * @param stamina
     * @return
     */
    @SuppressWarnings("InvalidInjectorMethodSignature")
    @ModifyVariable(method = "guard", at = @At(value = "STORE"), ordinal = 3, remap = false)
    private float stamina(float stamina) {
        PlayerMovement playerMovement = PlayerMovement.of(playerPatch.getOriginal());
        PlayerMovementInterface serverPlayerMovement = ((PlayerMovementInterface) playerMovement);

//        float poise = Formulars.getStaminarConsumePenalty(this.playerPatch.getWeight(), 1, this.playerPatch) * 0.1F;
        float poise = 0.0f;
        float currentStamina = playerMovement.getStamina();
        float missingStamina = playerMovement.getMaxStamina() - currentStamina;
        int guardConsumption;
        if (this.penalty > 0.1f) {
            guardConsumption = (int) ((getConsumption() + (penalty * 5) + (impact * 12)) * (1 - poise) * EPModCfg.baseBlockStaminaConsumption());
        }
        else {
            guardConsumption = -(int) MathUtils.calculateModifiedTriangularRoot(missingStamina, 0.3f);
        }

        ((PlayerMovementInterface) playerMovement).setActionStaminaCostServerSide(guardConsumption);
        ((PlayerMovementInterface) playerMovement).performingActionServerSide(true);

        if (playerMovement.isDepleted()) {
            return -0.1f;
        }
        else {
            return 0.0f;
        }
    }
}