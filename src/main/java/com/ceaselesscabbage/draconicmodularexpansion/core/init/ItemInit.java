package com.ceaselesscabbage.draconicmodularexpansion.core.init;

import java.util.ArrayList;

import com.ceaselesscabbage.draconicmodularexpansion.DraconicModularExpansion;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ItemInit {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, DraconicModularExpansion.MOD_ID);	
	
	//public static final RegistryObject<Item> DRACONIUM_PICKAXE = ITEMS.register("draconium_pickaxe", () -> myPickAxe);
	
	
	
	/*
	public static transient ArrayList<ResourceLocation> ITEM_REGISTRY_ORDER = new ArrayList<>();
	
	@ObjectHolder("wyvern_pickaxe")	public static ModularPickaxe pickaxe_draconium;
	
	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
		TechPropBuilder draconiumTools = new TechPropBuilder(DRACONIUM).maxStackSize(1).group(ItemGroup.TAB_TOOLS).rarity(Rarity.COMMON).maxDamage(-1);
		registerItem(event, new ModularPickaxe(draconiumTools).setRegistryName("draconium_pickaxe"));
		
	}
	
	private static void registerItem(RegistryEvent.Register<Item> event, Item item) {
        event.getRegistry().register(item);
        ITEM_REGISTRY_ORDER.add(item.getRegistryName());
    }
    */
}
