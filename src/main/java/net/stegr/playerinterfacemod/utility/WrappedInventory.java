package net.stegr.playerinterfacemod.utility;

import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ReportedException;

import java.util.concurrent.Callable;

public class WrappedInventory
{
    protected ItemStack[] slots;

    public WrappedInventory(int numberSlots)
    {
        this.slots = new ItemStack[numberSlots];
    }

    public static WrappedInventory fromIInventory(IInventory inv)
    {
        WrappedInventory retInv = new WrappedInventory(inv.getSizeInventory());

        for(int i = 0; i < inv.getSizeInventory(); i++)
        {
            retInv.setInventorySlotContents(i, inv.getStackInSlot(i));
        }

        return retInv;
    }

    public void addItemStack(ItemStack itemStack)
    {
        for(int i = 0; i < this.slots.length; i++)
        {
            if (this.slots[i] == null)
            {
                this.slots[i] = itemStack.copy();
                itemStack.stackSize = 0;
                break;
            }
            else if (this.slots[i].getItem().equals(itemStack.getItem()))
            {
                for (int j = 0; j < this.slots[i].getMaxStackSize(); j++)
                {
                    this.slots[i].stackSize++;
                    itemStack.stackSize--;
                }

                if(itemStack.stackSize <= 0)
                {
                    break;
                }
            }
        }
    }

    public boolean isFull()
    {
        boolean full = true;

        for(int i = 0; i < this.slots.length; i++)
        {
            if(slots[i] == null)
            {
                full = false;
            }
            else
            {
                if(slots[i].stackSize < slots[i].getMaxStackSize())
                {
                    full = false;
                }
            }
        }

        return full;
    }


    public int getSizeInventory()
    {
        return this.slots.length;
    }


    public ItemStack getStackInSlot(int slot)
    {
        return this.slots[slot];
    }


    public ItemStack decrStackSize(int slot, int num)
    {
        ItemStack[] itemStacks = this.slots;
        ItemStack itemStack;

        if(itemStacks[slot] != null)
        {
            itemStack = itemStacks[slot].copy();

            if(itemStacks[slot].stackSize <= num)
            {
                itemStack = itemStacks[slot];
                itemStacks[slot] = null;
                return itemStack;
            }
            else
            {
                itemStack = itemStacks[slot].splitStack(num);

                if(itemStacks[slot].stackSize == 0)
                {
                    itemStacks[slot] = null;
                }

                return itemStack;
            }
        }
        else
        {
            return null;
        }
    }

    public void setInventorySlotContents(int slot, ItemStack itemStack)
    {
        ItemStack[] itemStacks = this.slots;

        itemStacks[slot] = itemStack;
    }

    @Override
    public boolean equals(Object object)
    {
        WrappedInventory inv = (WrappedInventory)object;
        if(this.slots.length != inv.getSizeInventory())
            return false;

        for(int i = 0; i < this.slots.length; i++)
        {
            if((slots[i] == null && inv.getStackInSlot(i) != null) || (slots[i] != null && inv.getStackInSlot(i) == null))
            {
                return false;
            }

            if(slots != null && inv.getStackInSlot(i) != null)
            {
                if (!slots[i].getItem().equals(inv.getStackInSlot(i).getItem()))
                {
                    return false;
                }

                if (slots[i].stackSize != inv.getStackInSlot(i).stackSize)
                {
                    return false;
                }
            }
        }

        return true;
    }
}
