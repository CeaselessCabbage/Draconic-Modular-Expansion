package com.ceaselesscabbage.draconicmodularexpansion.core.init;

import static com.brandon3055.brandonscore.api.TechLevel.DRACONIUM;
import static com.brandon3055.brandonscore.api.TechLevel.WYVERN;
import static net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus.MOD;

import java.util.ArrayList;

import com.brandon3055.brandonscore.lib.TechPropBuilder;
import com.brandon3055.draconicevolution.items.equipment.ModularAxe;
import com.brandon3055.draconicevolution.items.equipment.ModularChestpiece;
import com.brandon3055.draconicevolution.items.equipment.ModularHoe;
import com.brandon3055.draconicevolution.items.equipment.ModularPickaxe;
import com.brandon3055.draconicevolution.items.equipment.ModularShovel;
import com.brandon3055.draconicevolution.items.equipment.ModularStaff;
import com.brandon3055.draconicevolution.items.equipment.ModularSword;
import com.ceaselesscabbage.draconicmodularexpansion.DraconicModularExpansion;
import com.ceaselesscabbage.draconicmodularexpansion.core.itemgroup.DMEItemGroup;
import com.ceaselesscabbage.draconicmodularexpansion.items.ModifiedModularAxe;
import com.ceaselesscabbage.draconicmodularexpansion.items.ModifiedModularChestpiece;
import com.ceaselesscabbage.draconicmodularexpansion.items.ModifiedModularHoe;
import com.ceaselesscabbage.draconicmodularexpansion.items.ModifiedModularPickaxe;
import com.ceaselesscabbage.draconicmodularexpansion.items.ModifiedModularShovel;
import com.ceaselesscabbage.draconicmodularexpansion.items.ModifiedModularStaff;
import com.ceaselesscabbage.draconicmodularexpansion.items.ModifiedModularSword;

import net.minecraft.item.Item;
import net.minecraft.item.Rarity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

@Mod.EventBusSubscriber(modid = DraconicModularExpansion.MOD_ID, bus = MOD)
@ObjectHolder(DraconicModularExpansion.MOD_ID)
public class DMEContent {
	
	@ObjectHolder("draconium_energy_core")         public static Item                      energy_core_draconium;
	
	@ObjectHolder("draconium_pickaxe")             public static ModularPickaxe            pickaxe_draconium;
	@ObjectHolder("draconium_axe")                 public static ModularAxe                axe_draconium;
	@ObjectHolder("draconium_shovel")              public static ModularShovel             shovel_draconium;
	@ObjectHolder("draconium_sword")               public static ModularSword              sword_draconium;
	@ObjectHolder("draconium_hoe")                 public static ModularHoe                hoe_draconium;
	@ObjectHolder("draconium_staff")               public static ModularStaff              staff_draconium;
	@ObjectHolder("wyvern_staff")                  public static ModularStaff              staff_wyvern;
	
	@ObjectHolder("draconium_chestpiece")          public static ModularChestpiece         chestpiece_draconium;
	
	public static transient ArrayList<ResourceLocation> ITEM_REGISTRY_ORDER = new ArrayList<>();
	public static TechPropBuilder draconiumTools = new TechPropBuilder(DRACONIUM).maxStackSize(1).group(DMEItemGroup.DMEItems).rarity(Rarity.COMMON).maxDamage(-1);
	public static TechPropBuilder wyvernTools = new TechPropBuilder(WYVERN).maxStackSize(1).group(DMEItemGroup.DMEItems).rarity(Rarity.UNCOMMON).maxDamage(-1);
	
	@SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
		
		registerItem(event, new Item(new Item.Properties().tab(DMEItemGroup.DMEItems)).setRegistryName("draconium_energy_core"));
		
		registerItem(event, new ModifiedModularPickaxe(draconiumTools).setRegistryName("draconium_pickaxe"));
		registerItem(event, new ModifiedModularAxe(draconiumTools).setRegistryName("draconium_axe"));
		registerItem(event, new ModifiedModularShovel(draconiumTools).setRegistryName("draconium_shovel"));
		registerItem(event, new ModifiedModularSword(draconiumTools).setRegistryName("draconium_sword"));
		registerItem(event, new ModifiedModularHoe(draconiumTools).setRegistryName("draconium_hoe"));
		registerItem(event, new ModifiedModularStaff(draconiumTools, 4, 4).setRegistryName("draconium_staff"));
		registerItem(event, new ModifiedModularStaff(wyvernTools, 5, 5).setRegistryName("wyvern_staff"));
		
		registerItem(event, new ModifiedModularChestpiece(draconiumTools).setRegistryName("draconium_chestpiece"));
		
	}

	private static void registerItem(RegistryEvent.Register<Item> event, Item item) {
        event.getRegistry().register(item);
        ITEM_REGISTRY_ORDER.add(item.getRegistryName());
    }
	
}
