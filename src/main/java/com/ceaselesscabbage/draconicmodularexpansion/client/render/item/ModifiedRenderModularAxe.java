package com.ceaselesscabbage.draconicmodularexpansion.client.render.item;

import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.brandon3055.brandonscore.api.TechLevel;
import com.brandon3055.draconicevolution.DEConfig;
import com.brandon3055.draconicevolution.DraconicEvolution;
import com.mojang.blaze3d.matrix.MatrixStack;

import codechicken.lib.render.CCModel;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.OBJParser;
import codechicken.lib.vec.Matrix4;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.ItemCameraTransforms.TransformType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

/**
 * Created by brandon3055 on 22/5/20.
 */
public class ModifiedRenderModularAxe extends ModifiedToolRenderBase {

    public ModifiedRenderModularAxe(TechLevel techLevel) {
        super(techLevel, "axe");
        Map<String, CCModel> model = OBJParser.parseModels(new ResourceLocation(DraconicEvolution.MODID, "models/item/equipment/axe.obj"), GL11.GL_TRIANGLES, null);
        baseModel = model.get("handle").backfacedCopy();
        materialModel = model.get("head").backfacedCopy();
        traceModel = model.get("trace").backfacedCopy();
        bladeModel = model.get("blade").backfacedCopy();
        gemModel = model.get("gem").backfacedCopy();

        initBaseVBO();
        initMaterialVBO();
        initTraceVBO();
        initBladeVBO();
        initGemVBO();
    }

    @Override
    public void renderTool(CCRenderState ccrs, ItemStack stack, TransformType transform, Matrix4 mat, MatrixStack mStack, IRenderTypeBuffer getter, boolean gui, int packedLight) {
        transform(mat, 0.25, 0.25, 0.5, gui ? 1.05 : 1.125);
        if (gui) {
            getter.getBuffer(guiBaseVBOType.withMatrix(mat).withLightMap(packedLight));
        } else {
            getter.getBuffer(baseVBOType.withMatrix(mat).withLightMap(packedLight));
        }

        if (techLevel == TechLevel.CHAOTIC && DEConfig.toolShaders) {
            getter.getBuffer(materialChaosVBOType.withMatrix(mat).withLightMap(packedLight).withState(getShaderType(chaosType, chaosShader)));
        } else {
            if (gui) {
                getter.getBuffer(guiMaterialVBOType.withMatrix(mat).withLightMap(packedLight));
            } else {
                getter.getBuffer(materialVBOType.withMatrix(mat).withLightMap(packedLight));
            }
        }

        if (DEConfig.toolShaders) {
            getter.getBuffer(traceVBOType.withMatrix(mat).withLightMap(packedLight).withState(getShaderType(shaderParentType, techLevel, traceShader)));
            getter.getBuffer(bladeVBOType.withMatrix(mat).withLightMap(packedLight).withState(getShaderType(shaderParentType, techLevel, bladeShader)));
            getter.getBuffer(gemVBOType.withMatrix(mat).withLightMap(packedLight).withState(getShaderType(shaderParentType, techLevel, gemShader)));
        } else {
            getter.getBuffer(traceVBOType.withMatrix(mat).withLightMap(packedLight));
            getter.getBuffer(bladeVBOType.withMatrix(mat).withLightMap(packedLight));
            getter.getBuffer(gemVBOType.withMatrix(mat).withLightMap(packedLight));
        }
    }
}