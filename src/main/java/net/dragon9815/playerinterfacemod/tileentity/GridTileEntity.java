package net.dragon9815.playerinterfacemod.tileentity;

import net.dragon9815.playerinterfacemod.grid.IGridNode;
import net.minecraft.tileentity.TileEntity;

public abstract class GridTileEntity extends TileEntity implements IGridNode {
    @Override
    public int getX() {
        return xCoord;
    }

    @Override
    public int getY() {
        return yCoord;
    }

    @Override
    public int getZ() {
        return zCoord;
    }
}
