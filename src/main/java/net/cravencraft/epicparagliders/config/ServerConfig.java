package net.cravencraft.epicparagliders.config;

import com.google.common.collect.ImmutableList;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import java.util.Collections;
import java.util.List;

public class ServerConfig {

    /**
     * Melee Attacks
     */
    private final ForgeConfigSpec.DoubleValue baseMeleeStaminaMultiplier;
    private final ForgeConfigSpec.DoubleValue daggerStaminaMultiplier;
    private final ForgeConfigSpec.DoubleValue swordStaminaMultiplier;
    private final ForgeConfigSpec.DoubleValue longSwordStaminaMultiplier;
    private final ForgeConfigSpec.DoubleValue greatSwordStaminaMultiplier;
    private final ForgeConfigSpec.DoubleValue uchigatanaStaminaMultiplier;
    private final ForgeConfigSpec.DoubleValue tachiStaminaMultiplier;
    private final ForgeConfigSpec.DoubleValue spearStaminaMultiplier;
    private final ForgeConfigSpec.DoubleValue knuckleStaminaMultiplier;
    private final ForgeConfigSpec.DoubleValue axeStaminaMultiplier;

    /**
     * Ranged Attacks
     */
    private final ForgeConfigSpec.DoubleValue baseRangedStaminaMultiplier;

    /**
     * Skills
     */
    private final ForgeConfigSpec.DoubleValue baseBlockStaminaMultiplier;
    private final ForgeConfigSpec.IntValue baseRollStaminaCost;
    private final ForgeConfigSpec.IntValue baseStepStaminaCost;
    private final ForgeConfigSpec.DoubleValue baseDodgeStaminaMultiplier;
    private final ForgeConfigSpec.IntValue baseDemolitionLeapStaminaCost;
    private final ForgeConfigSpec.DoubleValue demolitionLeapStaminaMultiplier;
    private final ForgeConfigSpec.DoubleValue parryPenaltyMultiplier;
    private final ForgeConfigSpec.IntValue parryPercentModifier;
    private final ForgeConfigSpec.BooleanValue parryDrain;
    private final ForgeConfigSpec.IntValue technicianPercentModifier;
    private final ForgeConfigSpec.BooleanValue technicianDrain;
    private final ForgeConfigSpec.IntValue staminaPillagerPercentModifier;
    private final ForgeConfigSpec.DoubleValue forbiddenStrengthMultiplier;
    private final ForgeConfigSpec.DoubleValue meteorSlamMultiplier;
    private final ForgeConfigSpec.DoubleValue meteorSlamFallDamageMitigator;
    private final ForgeConfigSpec.DoubleValue hyperVitalityMultiplier;
    private final ForgeConfigSpec.DoubleValue enduranceMultiplier;

    /**
     * Status Effects & Weapon Configs
     */
    private final ForgeConfigSpec.ConfigValue<List<? extends Integer>> depletionEffectList;
    private final ForgeConfigSpec.ConfigValue<List<? extends Integer>> depletionEffectStrengthList;
    private final ForgeConfigSpec.BooleanValue eldenStaminaSystem;

    public double baseMeleeStaminaMultiplier() {
        return baseMeleeStaminaMultiplier.get();
    }

    public double daggerStaminaMultiplier() {
        return daggerStaminaMultiplier.get();
    }

    public double swordStaminaMultiplier() {
        return swordStaminaMultiplier.get();
    }

    public double longSwordMultiplier() {
        return longSwordStaminaMultiplier.get();
    }

    public double greatSwordStaminaMultiplier() {
        return greatSwordStaminaMultiplier.get();
    }

    public double uchigatanaStaminaMultiplier() {
        return uchigatanaStaminaMultiplier.get();
    }

    public double tachiStaminaMultiplier() {
        return tachiStaminaMultiplier.get();
    }

    public double spearStaminaMultiplier() {
        return spearStaminaMultiplier.get();
    }

    public double knuckleStaminaMultiplier() {
        return knuckleStaminaMultiplier.get();
    }

    public double axeStaminaMultiplier() {
        return axeStaminaMultiplier.get();
    }

	public double baseRangedStaminaMultiplier() {
		return baseRangedStaminaMultiplier.get();
	}

	public double baseBlockStaminaMultiplier() {
		return baseBlockStaminaMultiplier.get();
	}

    public int baseRollStaminaCost() { return baseRollStaminaCost.get(); }
    public int baseStepStaminaCost() { return baseStepStaminaCost.get(); }
    public double meteorSlamMultiplier() { return meteorSlamMultiplier.get(); }
    public double meteorSlamFallDamageMitigator() { return meteorSlamFallDamageMitigator.get(); }

	public double baseDodgeStaminaMultiplier() {
		return baseDodgeStaminaMultiplier.get();
	}

    public double parryPenaltyMultiplier() {
        return parryPenaltyMultiplier.get();
    }
    public int parryPercentModifier() { return parryPercentModifier.get(); }
    public boolean parryDrain() { return parryDrain.get(); }
    public int technicianPercentModifier() { return technicianPercentModifier.get(); }
    public boolean technicianDrain() { return technicianDrain.get(); }
    public int staminaPillagerPercentModifier() { return staminaPillagerPercentModifier.get(); }
    public double forbiddenStrengthMultiplier() { return forbiddenStrengthMultiplier.get(); }
    public int baseDemolitionLeapStaminaCost() { return baseDemolitionLeapStaminaCost.get(); }
    public double demolitionLeapStaminaMultiplier() { return demolitionLeapStaminaMultiplier.get(); }
    public double hyperVitalityMultiplier() { return hyperVitalityMultiplier.get(); }
    public double enduranceMultiplier() { return enduranceMultiplier.get(); }

    public List<Integer> depletionEffectList() { return (List<Integer>) depletionEffectList.get(); }
    public List<Integer> depletionEffectStrengthList() { return (List<Integer>) depletionEffectStrengthList.get(); }

    public boolean eldenStaminaSystem() { return eldenStaminaSystem.get(); }

    public ServerConfig(ForgeConfigSpec.Builder server) {
        server.push("stamina");
        baseRangedStaminaMultiplier = server.defineInRange("weapons.base_ranged_stamina_multiplier", 1.0, 0.0, 10.0);
        baseMeleeStaminaMultiplier = server.comment("Base multiplier for all melee based attacks.").defineInRange("weapons.base_melee_stamina_multiplier", 1.0, 0.0, 10.0);
        daggerStaminaMultiplier = server.defineInRange("weapons.dagger_stamina_multiplier", 2.5, 0.0, 10.0);
        swordStaminaMultiplier = server.defineInRange("weapons.sword_stamina_multiplier", 2.5, 0.0, 10.0);
        longSwordStaminaMultiplier = server.defineInRange("weapons.longsword_stamina_multiplier", 2.5, 0.0, 10.0);
        greatSwordStaminaMultiplier = server.defineInRange("weapons.greatsword_stamina_multiplier", 1.5, 0.0, 10.0);
        uchigatanaStaminaMultiplier = server.defineInRange("weapons.uchigatana_stamina_multiplier", 2.0, 0.0, 10.0);
        tachiStaminaMultiplier = server.defineInRange("weapons.tachi_stamina_multiplier", 2.0, 0.0, 10.0);
        spearStaminaMultiplier = server.defineInRange("weapons.spear_stamina_multiplier", 2.5, 0.0, 10.0);
        knuckleStaminaMultiplier = server.defineInRange("weapons.knuckle_stamina_multiplier", 2.0, 0.0, 10.0);
        axeStaminaMultiplier = server.defineInRange("weapons.axe_stamina_multiplier", 2.5, 0.0, 10.0);

        baseBlockStaminaMultiplier = server.defineInRange("skills.block.block_stamina_multiplier", 7.5, 0.0, 10.0);

        baseRollStaminaCost = server.defineInRange("skills.dodge.roll_stamina_cost", 18, 0, 100);
        baseStepStaminaCost = server.defineInRange("skills.dodge.step_stamina_cost", 16, 0, 100);
        baseDodgeStaminaMultiplier = server.defineInRange("skills.dodge.dodge_stamina_multiplier", 1.0, 0.0, 10.0);

        baseDemolitionLeapStaminaCost = server.defineInRange("skills.dodge.step_stamina_cost", 18, 0, 100);
        demolitionLeapStaminaMultiplier = server.defineInRange("skills.demolition_leap.demolition_leap_multiplier", 1.0, 0.0, 10.0);
        hyperVitalityMultiplier = server.defineInRange("skills.passive.hyper_vitality_multiplier", 1.2, 0.0, 10.0);
        enduranceMultiplier = server.defineInRange("skills.passive.hyper_vitality_multiplier", 1.0, 0.0, 10.0);

        meteorSlamMultiplier = server.comment("This will be multiplier by the stamina cost of the meteor slam skill\n" +
                        "(which is equal to a basic attack with the given weapon).")
                .defineInRange("skills.identity.base_meteor_slam_cost", 1.2, 0.0, 10.0);
        meteorSlamFallDamageMitigator = server.comment("How much fall damage will be mitigated based on the player's remaining stamina\n" +
                        "when performing the meteor slam skill. By default it is set to 0.01 or 1% of remaining stamina. A higher value here\n" +
                        "will mitigate more damage, and a lower value will mitigate less damage.\n" +
                        "NOTE: This value represents percentage of remaining stamina (0.01 = 1% of remaining stamina). So, these values are VERY sensitive.\n" +
                        "I would recommend playing around with small increments at a time if you're not happy with the base amount of fall damage mitigation.")
                .defineInRange("skills.identity.base_meteor_slam_fall_damage_mitigator", 0.01, 0.0, 1.0);

        forbiddenStrengthMultiplier = server.comment("How much health will be drained when using a skill that consumes stamina.\n" +
                        "This value is multiplied by the stamina consumption of a weapon. So, a weapon that consumes more stamina\n" +
                        "will consume more hearts. I would HIGHLY suggest keeping this below 1.0, because anything higher could insta-kill you.")
                .defineInRange("skills.passive.forbidden_strength_multiplier", 0.1, 0.0, 10.0);

        parryPenaltyMultiplier = server.comment("How much stamina will be drained on a failed parry.\n" +
                                                  "Make the same as block_stamina_multiplier for no penalty.")
                .defineInRange("skills.parry.parry_penalty_multiplier", 7.5, 0.0, 10.0);

        parryDrain = server.comment("If true, stamina drain will be reduced based on the percentage defined below.\n" +
                                     "If false, missing stamina will be replenished based on the percentage defined below.")
                .define("skills.parry.parry_drain",false);

        parryPercentModifier = server.comment("How much stamina reduction will be applied on a successful parry,\n" +
                                                "or how much missing stamina will be replenished on a successful parry.")
                .defineInRange("skills.parry.parry_percent_modifier", 50, 0, 100);

        technicianDrain = server.comment("If true, stamina drain will be reduced based on the percentage defined below.\n" +
                                          "If false, missing stamina will be replenished based on the percentage defined below.")
                .define("skills.technician.technician_drain",false);

        technicianPercentModifier = server.comment("How much stamina reduction will be applied on a successful dodge,\n" +
                                                     "or how much missing stamina will be replenished on a successful dodge.")
                .defineInRange("skills.technician.technician_percent_modifier", 100, 0, 100);

        staminaPillagerPercentModifier = server.comment("How much missing stamina will be replenished on killing an enemy.")
                .defineInRange("skills.stamina_pillager.stamina_pillager_percent_modifier", 25, 0, 100);

        server.pop();

        server.push("depletion_effects");
        depletionEffectList = server
                .comment("The effect ID that will be applied when a player runs out of stamina (default is Mining Fatigue and Weakness respectively).\n" +
                         "Refer to https://minecraft.fandom.com/wiki/Effect#Effect_list for a list of the effects and their corresponding IDs")
                .defineListAllowEmpty(Collections.singletonList("effects"), () -> ImmutableList.of(4, 18), o -> true);
        depletionEffectStrengthList = server
                .comment("The strength applied to the depletion effect above (The default is 2 and 7. A value such as 4 would apply Weakness IV).\n" +
                         "If no value is set here, and an extra effect is added above, then the effect strength will default to 1.")
                .defineListAllowEmpty(Collections.singletonList("effects_strength"), () -> ImmutableList.of(5, 1), o -> true);
        server.pop();

        server.push("custom_stamina_systems");
        eldenStaminaSystem = server.comment("The stamina system will function like Elden Ring's IF the paraglider's 'runningAndSwimmingConsumesStamina' config\n" +
                "is set to true. The player will only drain stamina from running or swimming if they are in combat, or have recently been in combat.\n" +
                "Makes a good middle ground between the traditional RPG stamina system and one that solely focuses on combat.")
                        .define("elden_ring_stamina_system", false);

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, server.build());
    }
}