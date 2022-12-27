package com.ceaselesscabbage.draconicmodularexpansion.client.render.item;

import java.util.Locale;

import org.lwjgl.opengl.GL11;

import com.brandon3055.brandonscore.api.TechLevel;
import com.brandon3055.draconicevolution.client.render.item.ToolRenderBase;
import com.ceaselesscabbage.draconicmodularexpansion.DraconicModularExpansion;
import com.mojang.blaze3d.matrix.MatrixStack;

import codechicken.lib.render.CCRenderState;
import codechicken.lib.vec.Matrix4;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderState;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public abstract class ModifiedToolRenderBase extends ToolRenderBase {

	public ModifiedToolRenderBase(TechLevel techLevel, String tool) {
		
		super(techLevel, tool);
		
		final RenderState.DiffuseLightingState DIFFUSE_LIGHTING = new RenderState.DiffuseLightingState(true);
		final RenderState.LightmapState LIGHTMAP = new RenderState.LightmapState(true);
		final RenderState.OverlayState NO_OVERLAY = new RenderState.OverlayState(false);
		final RenderState.OverlayState OVERLAY = new RenderState.OverlayState(true);
		String levelName = techLevel.name().toLowerCase(Locale.ENGLISH);
		
		modelType = RenderType.create("modelType", DefaultVertexFormats.BLOCK, GL11.GL_TRIANGLES, 256, true, false, RenderType.State.builder()
                .setTextureState(new RenderState.TextureState(new ResourceLocation(DraconicModularExpansion.MOD_ID, "textures/item/equipment/" + levelName + "_" + tool + ".png"), false, false))
                .setDiffuseLightingState(DIFFUSE_LIGHTING)
                .setLightmapState(LIGHTMAP)
              //.texturing(new RenderState.TexturingState("lighting", RenderSystem::disableLighting, SneakyUtils.none()))
                .createCompositeState(true));
	
		modelGuiType = RenderType.create("modelGuiType", DefaultVertexFormats.BLOCK, GL11.GL_TRIANGLES, 256, RenderType.State.builder()
                .setTextureState(new RenderState.TextureState(new ResourceLocation(DraconicModularExpansion.MOD_ID, "textures/item/equipment/" + levelName + "_" + tool + ".png"), false, false))
                .setLightmapState(LIGHTMAP)
                .setOverlayState(NO_OVERLAY)
//                .texturing(new RenderState.TexturingState("lighting", RenderSystem::disableLighting, SneakyUtils.none()))
                .createCompositeState(false)
        );
		
		shaderParentType = RenderType.create("shaderGemType", DefaultVertexFormats.BLOCK, GL11.GL_TRIANGLES, 256, RenderType.State.builder()
                .setTextureState(new RenderState.TextureState(new ResourceLocation(DraconicModularExpansion.MOD_ID, "textures/item/equipment/shader_fallback_" + levelName + ".png"), false, false))
                .setLightmapState(LIGHTMAP)
                .setOverlayState(OVERLAY)
                .createCompositeState(false)
        );
		
	}
		
	public abstract void renderTool(CCRenderState ccrs, ItemStack stack, TransformType transform, Matrix4 mat, MatrixStack mStack, IRenderTypeBuffer getter, boolean gui, int packedLight);

}
