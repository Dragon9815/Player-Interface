package net.stegr.plim.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public abstract class TileEntityBase extends TileEntity
{
    public abstract void readFromSyncNBT(NBTTagCompound tag);
    public abstract void writeToSyncNBT(NBTTagCompound tag);

    public abstract void updateEntityServer();
    public abstract void updateEntityClient();

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if(!worldObj.isRemote)
            this.updateEntityServer();
        else
            this.updateEntityClient();
    }

    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
    {
        readFromSyncNBT(pkt.func_148857_g());
    }

    @Override
    public Packet getDescriptionPacket()
    {
        NBTTagCompound tag = new NBTTagCompound();

        writeToSyncNBT(tag);

        return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, tag);
    }
}
