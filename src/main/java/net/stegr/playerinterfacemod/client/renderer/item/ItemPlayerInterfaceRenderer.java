package net.stegr.playerinterfacemod.client.renderer.item;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class ItemPlayerInterfaceRenderer implements IItemRenderer {
    private TileEntitySpecialRenderer renderer;
    private TileEntity tileEntity;

    public ItemPlayerInterfaceRenderer(TileEntitySpecialRenderer renderer, TileEntity entity) {
        this.renderer = renderer;
        this.tileEntity = entity;
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type) {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
        if (type == ItemRenderType.ENTITY) {
            GL11.glTranslatef(-0.5F, 0.0F, -0.5F);
        }

        if (type == ItemRenderType.INVENTORY) {
            GL11.glTranslatef(0.0F, -0.9F, -1.0F);
            GL11.glRotatef(270F, 0.0F, 1.0F, 0.0F);
        }

        this.renderer.renderTileEntityAt(this.tileEntity, 0.0D, 0.0D, 0.0D, 0.0F);
    }
}
