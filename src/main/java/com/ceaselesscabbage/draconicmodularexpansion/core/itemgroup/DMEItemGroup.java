package com.ceaselesscabbage.draconicmodularexpansion.core.itemgroup;

import com.ceaselesscabbage.draconicmodularexpansion.core.init.DMEContent;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class DMEItemGroup extends ItemGroup {
	
	public static final DMEItemGroup DMEItems = new DMEItemGroup(ItemGroup.getGroupCountSafe(), "draconic_modular_expansion");

	public DMEItemGroup(int index, String label) {
		super(index, label);
	}

	@Override
	public ItemStack makeIcon() {
		return new ItemStack(DMEContent.pickaxe_draconium);
		//return new ItemStack(new ModifiedModularPickaxe(DMEContent.draconiumTools));
	}

}
