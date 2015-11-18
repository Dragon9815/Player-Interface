package net.dragon9815.playerinterfacemod.tileentity;

import net.dragon9815.dragoncore.tileentity.TileEntityBase;
import net.dragon9815.dragoncore.util.Platform;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public abstract class GridTileEntity extends TileEntityBase {
    protected boolean[] connectedSides;
    protected GridTileEntity[] connectedTiles;
    private boolean initialized;

    public GridTileEntity() {
        connectedSides = new boolean[ForgeDirection.VALID_DIRECTIONS.length];
        connectedTiles = new GridTileEntity[ForgeDirection.VALID_DIRECTIONS.length];
        initialized = false;
    }

    @Override
    public void updateEntity() {
        super.updateEntity();

        if(!initialized)
            init();
    }

    public void initServer() {
        for(ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
            TileEntity te = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
            if(te instanceof GridTileEntity) {
                connectedSides[dir.ordinal()] = true;
                connectedTiles[dir.ordinal()] = (GridTileEntity) te;
            }
        }
    }

    public void initClient() {

    }

    public void init() {
        if(Platform.isServer())
            initServer();
        else
            initClient();

        initialized = true;
    }

    public boolean isSideConnected(ForgeDirection dir) {
        if(dir == null || dir.ordinal() < 0 || dir.ordinal() > ForgeDirection.VALID_DIRECTIONS.length)
            return false;

        return connectedSides[dir.ordinal()];
    }

    public GridTileEntity getConnectedTile(ForgeDirection dir) {
        if(dir == null || dir.ordinal() < 0 || dir.ordinal() > ForgeDirection.VALID_DIRECTIONS.length)
            return null;

        return connectedTiles[dir.ordinal()];
    }

    public boolean[] getConnectedSides() {
        return connectedSides;
    }

    public GridTileEntity[] getConnectedTiles() {
        return connectedTiles;
    }

    @Override
    public void writeToNBT(NBTTagCompound tag) {

    }
}
