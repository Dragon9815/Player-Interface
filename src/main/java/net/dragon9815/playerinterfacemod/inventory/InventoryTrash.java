package net.dragon9815.playerinterfacemod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class InventoryTrash implements IInventory {
    public ItemStack[] inventory = new ItemStack[27];
    public EntityPlayer parent;

    public InventoryTrash(EntityPlayer player) {
        super();

        this.parent = player;

        this.readFromNBT(player.getEntityData());
    }

    @Override
    public int getSizeInventory() {
        return inventory.length;
    }

    @Override
    public ItemStack getStackInSlot(int slot) {
        return inventory[slot];
    }

    @Override
    public ItemStack decrStackSize(int slot, int quantity) {
        if (inventory[slot] != null) {
            if (inventory[slot].stackSize <= quantity) {
                ItemStack stack = inventory[slot];
                inventory[slot] = null;
                return stack;
            }

            ItemStack split = inventory[slot].splitStack(quantity);
            if (inventory[slot].stackSize == 0) {
                inventory[slot] = null;
            }

            return split;
        }
        else {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int slot) {
        return null;
    }

    @Override
    public void setInventorySlotContents(int slot, ItemStack itemStack) {
        inventory[slot] = itemStack;

        if (itemStack != null && itemStack.stackSize > getInventoryStackLimit()) {
            itemStack.stackSize = getInventoryStackLimit();
        }
    }

    @Override
    public String getInventoryName() {
        return "";
    }

    @Override
    public boolean hasCustomInventoryName() {
        return false;
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public void markDirty() {

    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
        return true;
    }

    @Override
    public void openInventory() {

    }

    @Override
    public void closeInventory() {

    }

    @Override
    public boolean isItemValidForSlot(int p_94041_1_, ItemStack p_94041_2_) {
        return false;
    }

    public void saveToNBT(NBTTagCompound tagCompound) {
        NBTTagList tagList = new NBTTagList();
        NBTTagCompound invSlot;

        for (int i = 0; i < this.inventory.length; ++i) {
            if (this.inventory[i] != null) {
                invSlot = new NBTTagCompound();
                invSlot.setByte("Slot", (byte) i);
                this.inventory[i].writeToNBT(invSlot);
                tagList.appendTag(invSlot);
            }
        }

        tagCompound.setTag("Inventory", tagList);
    }

    public void readFromNBT(NBTTagCompound tagCompound) {
        if (tagCompound != null) {
            NBTTagList tagList = tagCompound.getTagList("Inventory", 10);
            for (int i = 0; i < tagList.tagCount(); ++i) {
                NBTTagCompound nbttagcompound = (NBTTagCompound) tagList.getCompoundTagAt(i);
                int j = nbttagcompound.getByte("Slot") & 255;
                ItemStack itemstack = ItemStack.loadItemStackFromNBT(nbttagcompound);

                if (itemstack != null) {
                    this.inventory[j] = itemstack;
                }
            }
        }
    }
}
