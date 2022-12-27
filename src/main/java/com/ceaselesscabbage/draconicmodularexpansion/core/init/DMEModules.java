package com.ceaselesscabbage.draconicmodularexpansion.core.init;

import static com.brandon3055.brandonscore.api.TechLevel.DRACONIUM;
import static com.brandon3055.draconicevolution.api.modules.ModuleTypes.FLIGHT;
import static com.brandon3055.draconicevolution.api.modules.ModuleTypes.PROJ_MODIFIER;
import static com.brandon3055.draconicevolution.api.modules.ModuleTypes.SHIELD_BOOST;
import static com.brandon3055.draconicevolution.api.modules.ModuleTypes.SHIELD_CONTROLLER;
import static com.brandon3055.draconicevolution.api.modules.ModuleTypes.UNDYING;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import com.brandon3055.draconicevolution.api.modules.Module;
import com.brandon3055.draconicevolution.api.modules.ModuleRegistry;
import com.brandon3055.draconicevolution.api.modules.data.AOEData;
import com.brandon3055.draconicevolution.api.modules.data.AutoFeedData;
import com.brandon3055.draconicevolution.api.modules.data.DamageData;
import com.brandon3055.draconicevolution.api.modules.data.DamageModData;
import com.brandon3055.draconicevolution.api.modules.data.EnergyData;
import com.brandon3055.draconicevolution.api.modules.data.FlightData;
import com.brandon3055.draconicevolution.api.modules.data.JumpData;
import com.brandon3055.draconicevolution.api.modules.data.ModuleData;
import com.brandon3055.draconicevolution.api.modules.data.NoData;
import com.brandon3055.draconicevolution.api.modules.data.ProjectileData;
import com.brandon3055.draconicevolution.api.modules.data.ShieldControlData;
import com.brandon3055.draconicevolution.api.modules.data.ShieldData;
import com.brandon3055.draconicevolution.api.modules.data.SpeedData;
import com.brandon3055.draconicevolution.api.modules.data.UndyingData;
import com.brandon3055.draconicevolution.api.modules.lib.BaseModule;
import com.brandon3055.draconicevolution.api.modules.lib.IDamageModifier;
import com.brandon3055.draconicevolution.api.modules.lib.ModuleImpl;
import com.brandon3055.draconicevolution.api.modules.lib.ModuleItem;
import com.brandon3055.draconicevolution.init.ModuleCfg;
import com.brandon3055.draconicevolution.modules.ProjectileVelocityModule;
import com.ceaselesscabbage.draconicmodularexpansion.DraconicModularExpansion;
import com.ceaselesscabbage.draconicmodularexpansion.core.itemgroup.DMEItemGroup;

import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = DraconicModularExpansion.MOD_ID, bus = MOD)
@ObjectHolder(DraconicModularExpansion.MOD_ID)
public class DMEModules {

	private static transient ArrayList<ResourceLocation> ITEM_REGISTRY_ORDER = new ArrayList<>();
    public static transient Map<BaseModule<?>, Item> moduleItemMap = new LinkedHashMap<>();
    public static ForgeRegistry<Module<?>> MODULE_REGISTRY;
    
    @ObjectHolder("draconium_proj_velocity")           public static Module<ProjectileData>    draconiumProjVelocity;
    @ObjectHolder("draconium_proj_accuracy")           public static Module<ProjectileData>    draconiumProjAccuracy;
    @ObjectHolder("draconium_proj_grav_comp")          public static Module<ProjectileData>    draconiumProjGravComp;
    @ObjectHolder("draconium_proj_penetration")        public static Module<ProjectileData>    draconiumProjPenetration;
    @ObjectHolder("draconium_proj_damage")             public static Module<ProjectileData>    draconiumProjDamage;
    @ObjectHolder("draconium_shield_control")          public static Module<NoData>            draconiumShieldControl;
    @ObjectHolder("draconium_shield_capacity")         public static Module<ShieldData>        draconiumShieldCapacity;
    @ObjectHolder("draconium_large_shield_capacity")   public static Module<ShieldData>        draconiumLargeShieldCapacity;
    @ObjectHolder("draconium_shield_recovery")         public static Module<ShieldData>        draconiumShieldRecovery;
    @ObjectHolder("draconium_flight")                  public static Module<FlightData>        draconiumFlight;
    @ObjectHolder("draconium_undying")                 public static Module<NoData>            draconiumUndying;
    
    private static void registerModules() {
    	
    	register(new ProjectileVelocityModule(PROJ_MODIFIER, DRACONIUM,         projVelocityData(0.08F, .8F)).setMaxInstall(8),      "draconium_proj_velocity");   // (1 + (0.08 * 8) * 60) = 98.4 m/s max
    	register(new ModuleImpl<>(PROJ_MODIFIER,             DRACONIUM,         projAccuracyData(0.08F, 1.5F), 2, 1),                "draconium_proj_accuracy");
    	register(new ModuleImpl<>(PROJ_MODIFIER,             DRACONIUM,         projAntiGravData(0.10F, 1.5F), 2, 1),                "draconium_proj_grav_comp");
    	register(new ModuleImpl<>(PROJ_MODIFIER,             DRACONIUM,         projPenetrationData(0.15F, 1.5F), 2, 2),             "draconium_proj_penetration");
    	register(new ModuleImpl<>(PROJ_MODIFIER,             DRACONIUM,         projDamageData(0.1F, 1.5F)),                         "draconium_proj_damage");
    	register(new ModuleImpl<>(SHIELD_CONTROLLER,         DRACONIUM,         shieldControl(60.0)),                                "draconium_shield_control");
    	register(new ModuleImpl<>(SHIELD_BOOST,              DRACONIUM,         shieldData(10,  0.05)),                              "draconium_shield_capacity");
    	register(new ModuleImpl<>(SHIELD_BOOST,              DRACONIUM,         shieldData(10*5,  0.0D), 2, 2),                      "draconium_large_shield_capacity");
    	register(new ModuleImpl<>(SHIELD_BOOST,              DRACONIUM,         shieldData(2,   .5)),                                "draconium_shield_recovery");
    	register(new ModuleImpl<>(FLIGHT,                    DRACONIUM,         flightData(true, false, .3), 2, 2),                  "draconium_flight");
    	register(new ModuleImpl<>(UNDYING,                   DRACONIUM,         undyingData(4F,  15F, 10*20,  6*30*20, 2000000, 1)), "draconium_undying");
    	
    }
    
    private static Function<Module<EnergyData>, EnergyData> energyData(long defCapacity, long defTransfer) {
        return e -> {
            long capacity = ModuleCfg.getModuleLong(e, "capacity", defCapacity);
            long transfer = ModuleCfg.getModuleLong(e, "transfer", defTransfer);
            return new EnergyData(capacity, transfer);
        };
    }

    private static Function<Module<ShieldData>, ShieldData> shieldData(int defCapacity, double defRechargePerSecond) {
        return e -> {
            int capacity = ModuleCfg.getModuleInt(e, "capacity", defCapacity);
            double recharge = ModuleCfg.getModuleDouble(e, "recharge", defRechargePerSecond / 20); //Convert to per-tick
            return new ShieldData(capacity, recharge);
        };
    }

    private static Function<Module<ShieldControlData>, ShieldControlData> shieldControl(double defSeconds) {
        return e -> {
            int ticks = ModuleCfg.getModuleInt(e, "cool_down_ticks", (int) (defSeconds * 20D));
            return new ShieldControlData(ticks);
        };
    }

    private static Function<Module<SpeedData>, SpeedData> speedData(double defMultiplier) {
        return e -> new SpeedData(ModuleCfg.getModuleDouble(e, "speed_boost", defMultiplier));
    }

    private static Function<Module<DamageData>, DamageData> damageData(double defDamage) {
        return e -> new DamageData(ModuleCfg.getModuleDouble(e, "damage_boost", defDamage));
    }

    private static Function<Module<AOEData>, AOEData> aoeData(int defAOE) {
        return e -> new AOEData(ModuleCfg.getModuleInt(e, "aoe", defAOE));
    }

    private static Function<Module<JumpData>, JumpData> jumpData(double defMultiplier) {
        return e -> new JumpData(ModuleCfg.getModuleDouble(e, "jump_boost", defMultiplier));
    }

    private static Function<Module<FlightData>, FlightData> flightData(boolean elytra, boolean creative, double defSpeed) {
        return e -> {
            double speed = ModuleCfg.getModuleDouble(e, "elytra_boost_speed", defSpeed);
            return new FlightData(elytra, creative, speed);
        };
    }

    private static Function<Module<UndyingData>, UndyingData> undyingData(float defHealthBoost, float defShieldBoost, int shieldBoostTime, int defChargeTime, long defChargeEnergy, double defInvulnSeconds) {
        return e -> {
            float health = (float) ModuleCfg.getModuleDouble(e, "health_boost", defHealthBoost);
            float shield = (float) ModuleCfg.getModuleDouble(e, "shield_boost", defShieldBoost);
            int shieldTime = ModuleCfg.getModuleInt(e, "shield_boost_time", shieldBoostTime);
            int charge = ModuleCfg.getModuleInt(e, "charge_ticks", defChargeTime);
            long energy = ModuleCfg.getModuleLong(e, "charge_energy", defChargeEnergy);
            double invuln = ModuleCfg.getModuleDouble(e, "invulnerable_time", defInvulnSeconds);
            return new UndyingData(health, shield, shieldTime, charge, energy, (int) (invuln * 20));
        };
    }

    private static Function<Module<AutoFeedData>, AutoFeedData> autoFeedData(float defFoodStorage) {
        return e -> {
            float foodStorage = (float) ModuleCfg.getModuleDouble(e, "food_storage", defFoodStorage);
            return new AutoFeedData(foodStorage);
        };
    }

    private static Function<Module<DamageModData>, DamageModData> dmgModData(IDamageModifier modifier) {
        return e -> {
            return new DamageModData(modifier);
        };
    }

    private static Function<Module<ProjectileData>, ProjectileData> projectileData(float defVelocityModifier, float defAccuracyModifier, float defAntiGravModifier, float defPenetrationModifier, float defDamageModifier) {
        return e -> {
            float velocityModifier = (float) ModuleCfg.getModuleDouble(e, "velocity_modifier", defVelocityModifier);
            float accuracyModifier = (float) ModuleCfg.getModuleDouble(e, "accuracy_modifier", defAccuracyModifier);
            float antiGravModifier = (float) ModuleCfg.getModuleDouble(e, "anti_grav_modifier", defAntiGravModifier);
            float penetrationModifier = (float) ModuleCfg.getModuleDouble(e, "penetration_modifier", defPenetrationModifier);
            float damageModifier = (float) ModuleCfg.getModuleDouble(e, "damage_modifier", defDamageModifier);
            return new ProjectileData(velocityModifier, accuracyModifier, antiGravModifier, penetrationModifier, damageModifier);
        };
    }

    private static Function<Module<ProjectileData>, ProjectileData> projVelocityData(float velocityModifier, float penaltyModifier) {
        float accuracyPenalty = velocityModifier * -0.125F * penaltyModifier;
        float penetrationBoost = velocityModifier * 0.25F * (1 - penaltyModifier);
        return projectileData(velocityModifier, accuracyPenalty, 0, penetrationBoost, 0);
    }

    private static Function<Module<ProjectileData>, ProjectileData> projAccuracyData(float accuracyModifier, float penaltyModifier) {
        float velocityPenalty = accuracyModifier * -0.25F * penaltyModifier;
        float penetrationPenalty = velocityPenalty * 0.25F;
        return projectileData(velocityPenalty, accuracyModifier, 0, penetrationPenalty, 0);
    }

    private static Function<Module<ProjectileData>, ProjectileData> projAntiGravData(float antiGravModifier, float penaltyModifier) {
        float velocityPenalty = antiGravModifier * (-0.0625f) * penaltyModifier;
        float accuracyPenalty = antiGravModifier * -0.125F * penaltyModifier;
        float damagePenalty = antiGravModifier * -0.15F * penaltyModifier;
        float penetrationPenalty = velocityPenalty * 0.25F;
        return projectileData(velocityPenalty, accuracyPenalty, antiGravModifier, penetrationPenalty, damagePenalty);
    }

    private static Function<Module<ProjectileData>, ProjectileData> projPenetrationData(float penetrationModifier, float penaltyModifier) {
        float accuracyPenalty = penetrationModifier * -0.25F * penaltyModifier;
        return projectileData(0, accuracyPenalty, 0, penetrationModifier, 0);
    }

    private static Function<Module<ProjectileData>, ProjectileData> projDamageData(float damageModifier, float penaltyModifier) {
        float accuracyPenalty = damageModifier * -0.125F * penaltyModifier;
        return projectileData(0, accuracyPenalty, 0, 0, damageModifier);
    }

    private static Function<Module<NoData>, NoData> noData() {
        return e -> new NoData();
    }
    
    
    @SubscribeEvent
    public static void createRegistries(RegistryEvent.NewRegistry event) {
    	/*
        MODULE_REGISTRY = SneakyUtils.unsafeCast(new RegistryBuilder<>()//
                .setName(new ResourceLocation(DraconicModularExpansion.MOD_ID, "modules"))//
                .setType(SneakyUtils.unsafeCast(Module.class))//
                .disableSaving()//
                .create()//
        );
        */
    }
    
    

    private static void register(ModuleImpl<?> module, String name) {
        ModuleItem<?> item = new ModuleItem<>(new Properties().tab(DMEItemGroup.DMEItems), module);
        item.setRegistryName(name + "_module");
        module.setRegistryName(name);
        module.setModuleItem(item);
        moduleItemMap.put(module, item);
    }

    private static <T extends ModuleData<T>> void register(ModuleImpl<T> module, ModuleItem<T> item, String name) {
        item.setRegistryName(name + "_module");
        item.setModule(module);
        module.setRegistryName(name);
        module.setModuleItem(item);
        moduleItemMap.put(module, item);
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        moduleItemMap.clear();
        registerModules();
        moduleItemMap.keySet().forEach(BaseModule::reloadData);
        moduleItemMap.values().forEach(e -> event.getRegistry().register(e));
        ModuleCfg.saveStateConfig();
    }

    @SubscribeEvent
    public static void registerModules(RegistryEvent.Register<Module<?>> event) {
        moduleItemMap.keySet().forEach(e -> event.getRegistry().register(e));
        //MODULE_REGISTRY = ModuleRegistry.getRegistry();
        //moduleItemMap.keySet().forEach(e -> MODULE_REGISTRY.register( e));
    }
	
}
