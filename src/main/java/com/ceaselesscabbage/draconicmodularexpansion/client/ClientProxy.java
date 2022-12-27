package com.ceaselesscabbage.draconicmodularexpansion.client;

import static com.brandon3055.brandonscore.api.TechLevel.DRACONIUM;
import static com.brandon3055.brandonscore.api.TechLevel.WYVERN;

import com.brandon3055.draconicevolution.CommonProxy;
import com.brandon3055.draconicevolution.DEConfig;
import com.brandon3055.draconicevolution.client.handler.ClientEventHandler;
import com.ceaselesscabbage.draconicmodularexpansion.client.render.item.ModifiedRenderModularAxe;
import com.ceaselesscabbage.draconicmodularexpansion.client.render.item.ModifiedRenderModularBow;
import com.ceaselesscabbage.draconicmodularexpansion.client.render.item.ModifiedRenderModularChestpiece;
import com.ceaselesscabbage.draconicmodularexpansion.client.render.item.ModifiedRenderModularHoe;
import com.ceaselesscabbage.draconicmodularexpansion.client.render.item.ModifiedRenderModularPickaxe;
import com.ceaselesscabbage.draconicmodularexpansion.client.render.item.ModifiedRenderModularShovel;
import com.ceaselesscabbage.draconicmodularexpansion.client.render.item.ModifiedRenderModularStaff;
import com.ceaselesscabbage.draconicmodularexpansion.client.render.item.ModifiedRenderModularSword;
import com.ceaselesscabbage.draconicmodularexpansion.core.init.DMEContent;

import codechicken.lib.model.ModelRegistryHelper;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientProxy extends CommonProxy {
	
	public static ModelRegistryHelper modelHelper = new ModelRegistryHelper();
	
	@Override
    public void clientSetup(FMLClientSetupEvent event) {
        super.clientSetup(event);
        if (DEConfig.fancyToolModels) {
        	modelHelper.register(new ModelResourceLocation(DMEContent.pickaxe_draconium.getRegistryName(), "inventory"), new ModifiedRenderModularPickaxe(DRACONIUM));
        	modelHelper.register(new ModelResourceLocation(DMEContent.axe_draconium.getRegistryName(), "inventory"), new ModifiedRenderModularAxe(DRACONIUM));
        	modelHelper.register(new ModelResourceLocation(DMEContent.shovel_draconium.getRegistryName(), "inventory"), new ModifiedRenderModularShovel(DRACONIUM));
        	modelHelper.register(new ModelResourceLocation(DMEContent.sword_draconium.getRegistryName(), "inventory"), new ModifiedRenderModularSword(DRACONIUM));
        	modelHelper.register(new ModelResourceLocation(DMEContent.hoe_draconium.getRegistryName(), "inventory"), new ModifiedRenderModularHoe(DRACONIUM));
        	modelHelper.register(new ModelResourceLocation(DMEContent.staff_draconium.getRegistryName(), "inventory"), new ModifiedRenderModularStaff(DRACONIUM));
        	modelHelper.register(new ModelResourceLocation(DMEContent.staff_wyvern.getRegistryName(), "inventory"), new ModifiedRenderModularStaff(WYVERN));
        	//TODO non-3D bow doesn't have a draw animation
        	modelHelper.register(new ModelResourceLocation(DMEContent.bow_draconium.getRegistryName(), "inventory"), new ModifiedRenderModularBow(DRACONIUM));
        	
        	//TODO VBOArmorLayer has a cast to ModularChestpiece that silences my modifications and looks under DE files
        	modelHelper.register(new ModelResourceLocation(DMEContent.chestpiece_draconium.getRegistryName(), "inventory"), new ModifiedRenderModularChestpiece(DRACONIUM));
        }
        
        MinecraftForge.EVENT_BUS.register(new ClientEventHandler());
	}
	
	

}
