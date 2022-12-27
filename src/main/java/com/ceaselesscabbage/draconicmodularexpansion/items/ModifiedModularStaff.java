package com.ceaselesscabbage.draconicmodularexpansion.items;

import static com.brandon3055.draconicevolution.init.ModuleCfg.removeInvalidModules;

import com.brandon3055.brandonscore.lib.TechPropBuilder;
import com.brandon3055.draconicevolution.api.modules.lib.ModuleHostImpl;
import com.brandon3055.draconicevolution.items.equipment.ModularStaff;

import net.minecraft.item.ItemStack;

/*
 * This subclass exists to override certain offending methods in the ModularPickaxe class.
 * This is necessary because DE doesn't fully support draconium-tier gear.
 */

public class ModifiedModularStaff extends ModularStaff{

	int width;
	int height;
	
	public ModifiedModularStaff(TechPropBuilder props, int width, int height) {
		super(props);
		this.width = width;
		this.height = height;
	}
	
	/*
	 * Override needed to customize the grid size for draconium gear.
	 * This is necessary because the DE enum doesn't have a case for draconium tier (defaults to 1x1).
	 */
	@Override
	public ModuleHostImpl createHost(ItemStack stack) {
        return new ModuleHostImpl(this.getTechLevel(), this.width, this.height, "staff", removeInvalidModules);
    }

}
