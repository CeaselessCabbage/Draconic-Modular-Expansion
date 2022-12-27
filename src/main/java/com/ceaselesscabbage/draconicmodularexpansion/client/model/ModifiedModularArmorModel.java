package com.ceaselesscabbage.draconicmodularexpansion.client.model;

import java.util.Locale;

import org.lwjgl.opengl.GL11;

import com.brandon3055.brandonscore.api.TechLevel;
import com.brandon3055.draconicevolution.client.model.ModularArmorModel;
import com.ceaselesscabbage.draconicmodularexpansion.DraconicModularExpansion;

import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

public class ModifiedModularArmorModel extends ModularArmorModel{
	
	final RenderState.DiffuseLightingState DIFFUSE_LIGHTING = new RenderState.DiffuseLightingState(true);
	final RenderState.LightmapState LIGHTMAP = new RenderState.LightmapState(true);
	final RenderState.OverlayState NO_OVERLAY = new RenderState.OverlayState(false);
	final RenderState.OverlayState OVERLAY = new RenderState.OverlayState(true);

	public ModifiedModularArmorModel(float size, TechLevel techLevel, boolean onArmor) {
		super(size, techLevel, onArmor);
		
		String levelName = techLevel.name().toLowerCase(Locale.ENGLISH);
		modelType = RenderType.create("modelType", DefaultVertexFormats.BLOCK, GL11.GL_TRIANGLES, 256, true, false, RenderType.State.builder()
                .setTextureState(new RenderState.TextureState(new ResourceLocation(DraconicModularExpansion.MOD_ID, "textures/item/equipment/" + levelName + "_chestpiece.png"), false, false))
                .setDiffuseLightingState(DIFFUSE_LIGHTING)
                .setLightmapState(LIGHTMAP)
                .createCompositeState(true));
		shaderParentType = RenderType.create("shaderGemType", DefaultVertexFormats.BLOCK, GL11.GL_TRIANGLES, 256, RenderType.State.builder()
                .setDiffuseLightingState(DIFFUSE_LIGHTING)
                .setTextureState(new RenderState.TextureState(new ResourceLocation(DraconicModularExpansion.MOD_ID, "textures/item/equipment/shader_fallback_" + levelName + ".png"), false, false))
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
                .createCompositeState(false));
	}

}
