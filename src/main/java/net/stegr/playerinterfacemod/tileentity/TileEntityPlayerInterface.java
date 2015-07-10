package net.stegr.playerinterfacemod.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.server.MinecraftServer;
import net.stegr.playerinterfacemod.item.upgrade.IUpgrade;
import net.stegr.playerinterfacemod.reference.UpgradeNames;
import net.stegr.playerinterfacemod.utility.UpgradeRegistry;
import net.stegr.playerinterfacemod.utility.WrappedInventory;

import java.util.Iterator;
import java.util.UUID;

public class TileEntityPlayerInterface extends TileEntityUpgradeable implements ISidedInventory
{
    private EntityPlayer boundPlayer;

    public ItemStack[] bufferSlots;

    private Boolean bindPlayer;
    private String uuid;

    private boolean changed;

    protected WrappedInventory playerInventoryPrev;

    public TileEntityPlayerInterface()
    {
        super();

        boundPlayer = null;

        bindPlayer = false;
        uuid = "";

        bufferSlots = new ItemStack[9];
        playerInventoryPrev = new WrappedInventory(40);

        validUpgrades.put(UpgradeRegistry.getUpgrade(UpgradeNames.BUFFER).getUpgradeID(), 1);
        validUpgrades.put(UpgradeRegistry.getUpgrade(UpgradeNames.TRANSFER).getUpgradeID(), 1);
        validUpgrades.put(UpgradeRegistry.getUpgrade(UpgradeNames.COMPERATOR).getUpgradeID(), 1);
    }

    @Override
    public void updateEntityServer()
    {
        if(this.hasUpgrade(UpgradeNames.BUFFER) && this.hasUpgrade(UpgradeNames.TRANSFER))
        {
            int var1 = firstStackInBuffer();
            if (var1 != -1)
            {
                ItemStack stack;

                stack = bufferSlots[var1].copy();

                if (stack.stackSize > 0)
                {
                    stack.stackSize = 1;

                    if(boundPlayer != null)
                    {
                        if (boundPlayer.inventory.addItemStackToInventory(stack))
                        {
                            bufferSlots[var1].stackSize--;
                        }
                    }
                }

                if (bufferSlots[var1].stackSize <= 0)
                {
                    bufferSlots[var1] = null;
                }
            }
        }

        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);

        if(changed)
        {
            worldObj.notifyBlockOfNeighborChange(xCoord - 1, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
            worldObj.notifyBlockOfNeighborChange(xCoord + 1, yCoord, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
            worldObj.notifyBlockOfNeighborChange(xCoord, yCoord - 1, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
            worldObj.notifyBlockOfNeighborChange(xCoord, yCoord + 1, zCoord, worldObj.getBlock(xCoord, yCoord, zCoord));
            worldObj.notifyBlockOfNeighborChange(xCoord, yCoord, zCoord - 1, worldObj.getBlock(xCoord, yCoord, zCoord));
            worldObj.notifyBlockOfNeighborChange(xCoord, yCoord, zCoord + 1, worldObj.getBlock(xCoord, yCoord, zCoord));
            changed = false;
        }

        if(boundPlayer != null)
        {
            if(!WrappedInventory.fromIInventory(boundPlayer.inventory).equals(playerInventoryPrev))
            {
                changed = true;
                playerInventoryPrev = WrappedInventory.fromIInventory(boundPlayer.inventory);
            }
        }
    }

    @Override
    public void updateEntityClient()
    {

    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();

        if(bindPlayer)
        {
            Iterator it = MinecraftServer.getServer().getConfigurationManager().playerEntityList.iterator();
            EntityPlayer p;

            while (it.hasNext())
            {
                p = (EntityPlayer) it.next();

                if (p.getUniqueID().equals(UUID.fromString(uuid)))
                {
                    bindPlayer(p);
                    uuid = "";
                    bindPlayer = false;
                    worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
                    changed = true;
                }
            }
        }
    }

    private int firstStackInBuffer()
    {
        if(this.hasUpgrade(UpgradeNames.BUFFER))
        {
            for (int i = 0; i < this.bufferSlots.length; ++i)
            {
                if (this.bufferSlots[i] != null)
                {
                    return i;
                }
            }
        }

        return -1;
    }

    @Override
    public int getSizeInventory()
    {
        int size = 0;

        if(boundPlayer != null && this.hasUpgrade(UpgradeNames.TRANSFER))
        {
            size += boundPlayer.inventory.getSizeInventory();
        }

        if(this.hasUpgrade(UpgradeNames.BUFFER))
        {
            size += this.bufferSlots.length;
        }

        return size;
    }

    @Override
    public ItemStack getStackInSlot(int slot)
    {
        ItemStack var1 = null;

        if(slot < 0)
        {
            throw new IllegalArgumentException("Slot number can't be negative!");
        }

        if(slot < this.bufferSlots.length && this.hasUpgrade(UpgradeNames.BUFFER))
        {
            var1 = bufferSlots[slot];
        }
        else
        {
            if (boundPlayer != null && this.hasUpgrade(UpgradeNames.TRANSFER))
            {
                var1 = boundPlayer.inventory.getStackInSlot(slot - ((this.hasUpgrade(UpgradeNames.BUFFER)) ? this.bufferSlots.length : 0));
            }
        }

        return var1;
    }

    @Override
    public ItemStack decrStackSize(int slot, int par2)
    {
        ItemStack[] itemStacks = this.bufferSlots;

        if(slot < this.bufferSlots.length && this.hasUpgrade(UpgradeNames.BUFFER))
        {
            if(itemStacks[slot] != null)
            {
                ItemStack stack;

                if(itemStacks[slot].stackSize <= par2)
                {
                    stack = itemStacks[slot];
                    itemStacks[slot] = null;
                    return stack;
                }
                else
                {
                    stack = itemStacks[slot].splitStack(par2);

                    if(itemStacks[slot].stackSize == 0)
                    {
                        itemStacks[slot] = null;
                    }

                    return stack;
                }
            }
        }
        else
        {
            if (boundPlayer != null && this.hasUpgrade(UpgradeNames.TRANSFER))
            {
                return boundPlayer.inventory.decrStackSize(slot - ((this.hasUpgrade(UpgradeNames.BUFFER)) ? this.bufferSlots.length : 0), par2);
            }
        }

        changed = true;
        return null;
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int p_70304_1_)
    {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        ItemStack[] itemStacks = this.bufferSlots;

        if(slot < this.bufferSlots.length && this.hasUpgrade(UpgradeNames.BUFFER))
        {
            itemStacks[slot] = itemStack;
        }
        else
        {
            if (boundPlayer != null && this.hasUpgrade(UpgradeNames.TRANSFER))
            {
                boundPlayer.inventory.setInventorySlotContents(slot - ((this.hasUpgrade(UpgradeNames.BUFFER)) ? this.bufferSlots.length : 0), itemStack);
            }
        }

        changed = true;
    }

    @Override
    public String getInventoryName()
    {
        return "";
    }

    @Override
    public boolean hasCustomInventoryName()
    {
        return false;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 64;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_)
    {
        return false;
    }

    @Override
    public void openInventory()
    {

    }

    @Override
    public void closeInventory()
    {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_)
    {
        return this.hasUpgrade(UpgradeNames.TRANSFER);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);

        tag.setString("boundPlayer", (boundPlayer != null) ? boundPlayer.getUniqueID().toString() : "");

        NBTTagList tagList = new NBTTagList();

        for(int i = 0; i < bufferSlots.length; i++)
        {
            NBTTagCompound tag1 = new NBTTagCompound();

            if(bufferSlots[i] != null)
                bufferSlots[i].writeToNBT(tag1);

            tagList.appendTag(tag1);
        }

        tag.setTag("Buffer_Slots", tagList);

        //this.worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);

        String uuid = tag.getString("boundPlayer");

        if(!uuid.equals(""))
        {
            bindPlayer = true;
            this.uuid = uuid;
        }
        else
        {
            this.boundPlayer = null;
        }

        if(tag.hasKey("Buffer_Slots"))
        {
            NBTTagList tagList = tag.getTagList("Buffer_Slots", 10);

            for(int i = 0; i < tagList.tagCount() && i < bufferSlots.length; i++)
            {
                NBTTagCompound tag1 = tagList.getCompoundTagAt(i);

                bufferSlots[i] = ItemStack.loadItemStackFromNBT(tag1);
            }
        }
    }

    @Override
    public int[] getAccessibleSlotsFromSide(int p_94128_1_)
    {
        if(boundPlayer != null)
        {
            int[] temp = new int[boundPlayer.inventory.getSizeInventory()];

            for(int i = 0; i < boundPlayer.inventory.getSizeInventory(); i++)
            {
                temp[i] = i;
            }

            return temp;
        }

        return new int[0];
    }

    @Override
    public boolean canInsertItem(int p_102007_1_, ItemStack p_102007_2_, int p_102007_3_)
    {
        return this.hasUpgrade(UpgradeNames.TRANSFER);
    }

    @Override
    public boolean canExtractItem(int p_102008_1_, ItemStack p_102008_2_, int p_102008_3_)
    {
        return true;
    }

    @Override
    public void onUpgrade(IUpgrade upgrade)
    {
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        changed = true;
    }

    public void bindPlayer(EntityPlayer player)
    {
        boundPlayer = player;
        worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
        changed = true;
    }

    public EntityPlayer getBoundPlayer()
    {
        return this.boundPlayer;
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

    public void writeToSyncNBT(NBTTagCompound tag)
    {
        super.writeToSyncNBT(tag);

        tag.setString("boundPlayer", (this.boundPlayer != null) ? this.boundPlayer.getUniqueID().toString() : "");
    }

    public void readFromSyncNBT(NBTTagCompound tag)
    {
        super.readFromSyncNBT(tag);

        String boundPlayer = tag.getString("boundPlayer");

        if(!boundPlayer.equals(""))
        {
            uuid = boundPlayer;
            bindPlayer = true;
        }
        else
        {
            this.boundPlayer = null;
        }
    }
}
