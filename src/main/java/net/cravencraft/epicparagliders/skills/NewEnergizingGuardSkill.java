package net.cravencraft.epicparagliders.skills;

import com.google.common.collect.Lists;
import net.cravencraft.epicparagliders.EpicParaglidersMod;
import net.cravencraft.epicparagliders.capabilities.UpdatedServerPlayerMovement;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.utils.AttackResult;
import yesman.epicfight.api.utils.ExtendedDamageSource;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Skills;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.CapabilityItem.Styles;
import yesman.epicfight.world.capabilities.item.CapabilityItem.WeaponCategories;
import yesman.epicfight.world.entity.eventlistener.HurtEvent;

import java.util.List;

public class NewEnergizingGuardSkill extends NewGuardSkill {

	private final UpdatedServerPlayerMovement updatedServerPlayerMovement;
	public static Builder createBuilder(ResourceLocation resourceLocation) {
		return NewGuardSkill.createBuilder(resourceLocation)
				.addAdvancedGuardMotion(WeaponCategories.LONGSWORD, (item, player) -> Animations.LONGSWORD_GUARD_HIT)
				.addAdvancedGuardMotion(WeaponCategories.SPEAR, (item, player) -> item.getStyle(player) == Styles.TWO_HAND ? Animations.SPEAR_GUARD_HIT : null)
				.addAdvancedGuardMotion(WeaponCategories.TACHI, (item, player) -> Animations.LONGSWORD_GUARD_HIT)
				.addAdvancedGuardMotion(WeaponCategories.GREATSWORD, (item, player) -> Animations.GREATSWORD_GUARD_HIT);
	}

	public NewEnergizingGuardSkill(Builder builder, UpdatedServerPlayerMovement serverPlayerMovement) {
		super(builder, serverPlayerMovement);
		this.updatedServerPlayerMovement = serverPlayerMovement;
	}
	
	@Override
	public void guard(SkillContainer container, CapabilityItem itemCapapbility, HurtEvent.Pre event, float knockback, float impact, boolean advanced) {
		boolean canUse = this.isHoldingWeaponAvailable(event.getPlayerPatch(), itemCapapbility, BlockType.ADVANCED_GUARD);
		
		if (event.getDamageSource().isExplosion()) {
			impact = event.getAmount();
		}
		
		super.guard(container, itemCapapbility, event, knockback, impact, canUse);
	}
	
	@Override
	public void dealEvent(PlayerPatch<?> playerpatch, HurtEvent.Pre event) {
		boolean isSpecialSource = isSpecialDamageSource(event.getDamageSource());
		EpicParaglidersMod.LOGGER.info("Stamina amount? " + event.getAmount());
		event.setAmount(isSpecialSource ? event.getAmount() * 0.4F : 0.0F); //TODO: Test more. Maybe update how stamina is drained in the base guard skill. Update text as well.
		event.setResult(isSpecialSource ? AttackResult.ResultType.SUCCESS : AttackResult.ResultType.BLOCKED);
		
		if (event.getDamageSource() instanceof ExtendedDamageSource) {
			((ExtendedDamageSource)event.getDamageSource()).setStunType(ExtendedDamageSource.StunType.NONE);
		}
		
		event.setCanceled(true);
		Entity directEntity = event.getDamageSource().getDirectEntity();
		LivingEntityPatch<?> entitypatch = (LivingEntityPatch<?>)directEntity.getCapability(EpicFightCapabilities.CAPABILITY_ENTITY).orElse(null);
		
		if (entitypatch != null) {
			entitypatch.onAttackBlocked(event, playerpatch);
		}
	}
	
	@Override
	protected boolean isBlockableSource(DamageSource damageSource, boolean advanced) {
		return (!damageSource.isBypassArmor() || damageSource.msgId.equals("indirectMagic")) && (advanced || super.isBlockableSource(damageSource, false)) && !damageSource.isBypassInvul();
	}
	
	@Override
	public float getPenaltyMultiplier(CapabilityItem itemCap) {
		return this.advancedGuardMotions.containsKey(itemCap.getWeaponCategory()) ? 0.4F : 0.6F;
	}
	
	private static boolean isSpecialDamageSource(DamageSource damageSource) {
		return (damageSource.isExplosion() || damageSource.isMagic() || damageSource.isFire() || damageSource.isProjectile()) && !(damageSource.isBypassArmor() && !damageSource.msgId.equals("indirectMagic"));
	}
	
	@Override
	public Skill getPriorSkill() {
		return Skills.GUARD;
	}
	
	@Override
	protected boolean isAdvancedGuard() {
		return true;
	}
	
	@OnlyIn(Dist.CLIENT)
	@Override
	public List<Object> getTooltipArgs() {
		List<Object> list = Lists.<Object>newArrayList();
		list.add(String.format("%s, %s, %s, %s", WeaponCategories.GREATSWORD, WeaponCategories.LONGSWORD, WeaponCategories.SPEAR, WeaponCategories.TACHI).toLowerCase());
		
		return list;
	}
}