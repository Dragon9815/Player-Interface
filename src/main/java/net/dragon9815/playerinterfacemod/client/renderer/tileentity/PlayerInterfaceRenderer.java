package net.dragon9815.playerinterfacemod.client.renderer.tileentity;

import cofh.api.transport.IItemDuct;
import net.minecraft.block.Block;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Facing;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.ForgeDirection;
import net.dragon9815.playerinterfacemod.client.model.ModelPlayerInterface;
import net.dragon9815.playerinterfacemod.helpers.BlockHelper;
import net.dragon9815.playerinterfacemod.reference.Reference;
import org.lwjgl.opengl.GL11;

public class PlayerInterfaceRenderer extends TileEntitySpecialRenderer {
    private static final ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/PlayerInterface.png");

    private ModelPlayerInterface model;

    public PlayerInterfaceRenderer() {
        this.model = new ModelPlayerInterface();
    }

    @Override
    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f) {
        this.model.backConnected = this.model.frontConnected = this.model.leftConnected = this.model.rightConnected = false;

        if (tileEntity.hasWorldObj()) {
            boolean connect;
            int x1 = tileEntity.xCoord;
            int y1 = tileEntity.yCoord;
            int z1 = tileEntity.zCoord;

            for (ForgeDirection direction : ForgeDirection.VALID_DIRECTIONS) {
                connect = false;
                if (direction == ForgeDirection.UP || direction == ForgeDirection.DOWN) {
                    continue;
                }

                Block block = tileEntity.getWorldObj().getBlock(x1 + direction.offsetX, y1 + direction.offsetY, z1 + direction.offsetZ);
                TileEntity te = tileEntity.getWorldObj().getTileEntity(x1 + direction.offsetX, y1 + direction.offsetY, z1 + direction.offsetZ);

                if (block == null) {
                    continue;
                }

                if (block.isSideSolid(tileEntity.getWorldObj(), x1 + direction.offsetX, y1 + direction.offsetY, z1 + direction.offsetZ, ForgeDirection.getOrientation(ForgeDirection.OPPOSITES[direction.ordinal()]))) {
                    connect = true;
                }

                if (block instanceof BlockHopper && BlockHopper.getDirectionFromMetadata(tileEntity.getWorldObj().getBlockMetadata(x1 + direction.offsetX, y1 + direction.offsetY, z1 + direction.offsetZ)) == Facing.oppositeSide[direction.ordinal()]) {
                    connect = true;
                }

                if (te instanceof IItemDuct || BlockHelper.isBlockPipe(te, direction)) {
                    connect = true;
                }

                if (connect) {
                    switch (direction) {
                    case NORTH:
                        model.frontConnected = true;
                        break;
                    case SOUTH:
                        model.backConnected = true;
                        break;
                    case EAST:
                        model.rightConnected = true;
                        break;
                    case WEST:
                        model.leftConnected = true;
                        break;
                    }
                }
            }
        }

        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        GL11.glRotatef(180, 0F, 0F, 1F);

        this.bindTexture(texture);

        GL11.glPushMatrix();
        this.model.renderModel(0.0625F);
        this.model.renderSkull(0.0625F);
        GL11.glPopMatrix();
        GL11.glPopMatrix();
    }
}
