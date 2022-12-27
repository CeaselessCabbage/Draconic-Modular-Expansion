package com.ceaselesscabbage.draconicmodularexpansion.items;

import static com.brandon3055.draconicevolution.init.ModuleCfg.removeInvalidModules;

import com.brandon3055.brandonscore.lib.TechPropBuilder;
import com.brandon3055.draconicevolution.api.modules.lib.ModuleHostImpl;
import com.brandon3055.draconicevolution.items.equipment.ModularSword;

import net.minecraft.item.ItemStack;

/*
 * This subclass exists to override certain offending methods in the ModularPickaxe class.
 * This is necessary because DE doesn't fully support draconium-tier gear.
 */

public class ModifiedModularSword extends ModularSword{

	public ModifiedModularSword(TechPropBuilder props) {
		super(props);
	}
	
	/*
	 * Override needed to customize the grid size for draconium gear.
	 * This is necessary because the DE enum doesn't have a case for draconium tier (defaults to 1x1).
	 */
	@Override
	public ModuleHostImpl createHost(ItemStack stack) {
        return new ModuleHostImpl(this.getTechLevel(), 4, 3, "sword", removeInvalidModules);
    }

}
