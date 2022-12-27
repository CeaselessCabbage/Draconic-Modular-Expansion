package com.ceaselesscabbage.draconicmodularexpansion.items;

import static com.brandon3055.draconicevolution.api.config.ConfigProperty.DecimalFormatter.PLUS_PERCENT_0;
import static com.brandon3055.draconicevolution.init.ModuleCfg.removeInvalidModules;

import java.util.function.Supplier;

import javax.annotation.Nullable;

import com.brandon3055.brandonscore.lib.TechPropBuilder;
import com.brandon3055.draconicevolution.DEConfig;
import com.brandon3055.draconicevolution.api.config.DecimalProperty;
import com.brandon3055.draconicevolution.api.modules.ModuleCategory;
import com.brandon3055.draconicevolution.api.modules.ModuleTypes;
import com.brandon3055.draconicevolution.api.modules.data.JumpData;
import com.brandon3055.draconicevolution.api.modules.data.SpeedData;
import com.brandon3055.draconicevolution.api.modules.lib.ModuleHostImpl;
import com.brandon3055.draconicevolution.items.equipment.ModularChestpiece;
import com.ceaselesscabbage.draconicmodularexpansion.client.model.ModifiedModularArmorModel;

import codechicken.lib.util.SneakyUtils;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

/*
 * This subclass exists to override certain offending methods in the ModularPickaxe class.
 * This is necessary because DE doesn't fully support draconium-tier gear.
 */

public class ModifiedModularChestpiece extends ModularChestpiece{

	public ModifiedModularChestpiece(TechPropBuilder props) {
		super(props);
	}
	
	/*
	 * Override needed to customize the grid size for draconium gear.
	 * This is necessary because the DE enum doesn't have a case for draconium tier (defaults to 1x1).
	 */
	@Override
	public ModuleHostImpl createHost(ItemStack stack) {
        ModuleHostImpl host = new ModuleHostImpl(getTechLevel(), 4, 3, "chestpiece", removeInvalidModules);
        host.addCategories(ModuleCategory.CHESTPIECE);
        host.addPropertyBuilder(props -> {
            SpeedData speed = host.getModuleData(ModuleTypes.SPEED);
            if (speed != null) {
                Supplier<Double> speedGetter = () -> {
                    SpeedData data = host.getModuleData(ModuleTypes.SPEED);
                    double maxSpeed = data == null ? 0 : data.getSpeedMultiplier();
                    if (DEConfig.armorSpeedLimit != -1) {
                        maxSpeed = Math.min(maxSpeed, DEConfig.armorSpeedLimit);
                    }
                    return maxSpeed;
                };

                props.add(new DecimalProperty("walk_speed", 0).min(0).max(speedGetter).setFormatter(PLUS_PERCENT_0));
                props.add(new DecimalProperty("run_speed", speedGetter.get()).min(0).max(speedGetter).setFormatter(PLUS_PERCENT_0));
            }

            JumpData jump = host.getModuleData(ModuleTypes.JUMP_BOOST);
            if (jump != null) {
                Supplier<Double> jumpGetter = () -> {
                    JumpData data = host.getModuleData(ModuleTypes.JUMP_BOOST);
                    return data == null ? 0 : data.getMultiplier();
                };

                props.add(new DecimalProperty("jump_boost_run", 0).min(0).max(jumpGetter).setFormatter(PLUS_PERCENT_0));
                props.add(new DecimalProperty("jump_boost", jumpGetter.get()).min(0).max(jumpGetter).setFormatter(PLUS_PERCENT_0));
            }
        });
        return host;
    }
	
	@OnlyIn(Dist.CLIENT)
    private BipedModel<?> model;

    @OnlyIn(Dist.CLIENT)
    private BipedModel<?> model_on_armor;
	
	@Nullable
    @Override
    @OnlyIn(Dist.CLIENT)
    public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, A _default) {
        if (model == null) {
            model = new ModifiedModularArmorModel(1F, getTechLevel(), false);
        }
        return SneakyUtils.unsafeCast(model);
    }
	
	@OnlyIn(Dist.CLIENT)
	@Override
    public <A extends BipedModel<?>> A getChestPieceModel(LivingEntity entityLiving, ItemStack itemStack, EquipmentSlotType armorSlot, boolean onArmor) {
        if (model == null || model_on_armor == null) {
            model = new ModifiedModularArmorModel(1F, getTechLevel(), false);
            model_on_armor = new ModifiedModularArmorModel(1F, getTechLevel(), true);
        }

        return SneakyUtils.unsafeCast(onArmor ? model_on_armor : model);
    }

}
