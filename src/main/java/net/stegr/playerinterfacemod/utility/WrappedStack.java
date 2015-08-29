package net.stegr.playerinterfacemod.utility;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class WrappedStack
{
    private Item item;
    private int stackSize;
    private int itemDamage;

    public WrappedStack(Item item, int amount, int itemDamage)
    {
        this.item = item;
        this.stackSize = amount;
        this.itemDamage = itemDamage;
    }

    public WrappedStack(Item item, int amount)
    {
        this(item, amount, 0);
    }

    public WrappedStack(ItemStack stack)
    {
        if(stack != null)
        {
            this.item = stack.getItem();
            this.stackSize = stack.stackSize;
            this.itemDamage = stack.getItemDamage();
        }
    }

    public WrappedStack()
    {
        this(null, 0, 0);
    }

    public ItemStack toItemStack()
    {
        return new ItemStack(this.item, this.stackSize, this.itemDamage);
    }

    public Item getItem()
    {
        return this.item;
    }

    public int getItemDamage()
    {
        return this.itemDamage;
    }

    public void setItemDamage(int newItemDamage)
    {
        if(this.item != null && newItemDamage <= this.item.getMaxDamage())
        {
            this.itemDamage = newItemDamage;
        }
    }

    public void writeToNBT(NBTTagCompound tagCompound)
    {

    }

    public void readFromNBT(NBTTagCompound tagCompound)
    {

    }
}
