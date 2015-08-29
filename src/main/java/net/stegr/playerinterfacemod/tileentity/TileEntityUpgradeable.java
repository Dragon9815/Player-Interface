package net.stegr.playerinterfacemod.tileentity;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.inventory.InventoryUpgradeable;
import net.stegr.playerinterfacemod.item.ItemUpgrade;
import net.stegr.playerinterfacemod.registry.UpgradeRegistry;

import java.util.*;

public abstract class TileEntityUpgradeable extends TileEntityBase
{
    //public Map<String, Integer> installedUpgrades;
    //public Map<String, Integer> validUpgrades;

    public InventoryUpgradeable upgradeInventory;

    protected ItemStack[] getItemStackArray(Map<String, Integer> upgrades)
    {
        ItemStack[] itemStacks = new ItemStack[upgrades.size()];
        String[] keySet = (String[])upgrades.keySet().toArray();

        for(int i = 0; i < itemStacks.length; i++)
        {
            itemStacks[i] = new ItemStack((ItemUpgrade)UpgradeRegistry.instance().getUpgrade(keySet[i]), upgrades.get(keySet[i]));
        }

        return itemStacks;
    }

    protected abstract int getUpgradeSlots();
    protected abstract String getMachineName();

    public TileEntityUpgradeable()
    {
        super();

        this.upgradeInventory = new InventoryUpgradeable(this.getUpgradeSlots(), UpgradeRegistry.instance().getValidUpgradesForMachine(this.getMachineName()));
    }

    @Override
    public void updateEntity()
    {
        super.updateEntity();
    }

    public InventoryUpgradeable getUpgradeInventory()
    {
        return this.upgradeInventory;
    }

    public boolean isUpgradeValid(ItemUpgrade upgrade)
    {
        return this.upgradeInventory.isUpgradeValid(upgrade);
    }

    public void addUpgrade(ItemUpgrade upgrade, int num)
    {
        upgradeInventory.addUpgrade(upgrade, num);

        onUpgrade(upgrade);
    }

    public abstract void onUpgrade(ItemUpgrade upgrade);

    public void dropAllUpgrades(World world, int x, int y, int z)
    {
        for(int i = 0; i < this.upgradeInventory.getSizeInventory(); i++)
        {
            ItemStack itemstack = this.upgradeInventory.getStackInSlot(i);

            if(itemstack != null)
            {
                float f = this.worldObj.rand.nextFloat() * 0.8F + 0.1F;
                float f1 = this.worldObj.rand.nextFloat() * 0.8F + 0.1F;
                float f2 = this.worldObj.rand.nextFloat() * 0.8F + 0.1F;

                while(itemstack.stackSize > 0)
                {
                    int j = this.worldObj.rand.nextInt(21) + 10;

                    if(j > itemstack.stackSize)
                    {
                        j = itemstack.stackSize;
                    }

                    itemstack.stackSize -= j;
                    EntityItem entityItem = new EntityItem(world, (double)((float)x + f), (double)((float)y + f1), (double)((float)z + f2), new ItemStack(itemstack.getItem(), j, itemstack.getItemDamage()));

                    if (itemstack.hasTagCompound())
                    {
                        entityItem.getEntityItem().setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                    }

                    float f3 = 0.05F;

                    entityItem.motionX = (double)((float)this.worldObj.rand.nextGaussian() * f3);
                    entityItem.motionY = (double)((float)this.worldObj.rand.nextGaussian() * f3 + 0.2F);
                    entityItem.motionZ = (double)((float)this.worldObj.rand.nextGaussian() * f3);

                    world.spawnEntityInWorld(entityItem);
                }
            }

        }
    }

    public boolean hasUpgrade(ItemUpgrade upgrade)
    {
        return this.upgradeInventory.hasUpgrade(upgrade);
    }

    public boolean hasUpgrade(String upgradeID)
    {
        return this.upgradeInventory.hasUpgrade(UpgradeRegistry.instance().getUpgrade(upgradeID));
    }

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);

        this.upgradeInventory.readFromNBT(tag);
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);

        this.upgradeInventory.writeToNBT(tag);
    }

    public void writeToSyncNBT(NBTTagCompound tag)
    {
        this.upgradeInventory.writeToNBT(tag);
        /*Set<String> keySet = installedUpgrades.keySet();
        Iterator<String> it1 = keySet.iterator();
        NBTTagList tag1 = new NBTTagList();
        int i = 0;

        while(it1.hasNext())
        {
            String key = it1.next();
            int count = installedUpgrades.get(key);
            NBTTagCompound tag2 = new NBTTagCompound();
            tag2.setString("name", key);
            tag2.setInteger("count", count);

            tag1.appendTag(tag2);
            i++;
        }

        tag.setTag("Upgrades", tag1);*/
    }

    public void readFromSyncNBT(NBTTagCompound tag)
    {
        this.upgradeInventory.readFromNBT(tag);
        /*installedUpgrades.clear();

        if(tag.hasKey("Upgrades"))
        {
            NBTTagList tagList = tag.getTagList("Upgrades", 10);

            for(int i = 0; i < tagList.tagCount(); i++)
            {
                NBTTagCompound tag1 = tagList.getCompoundTagAt(i);

                if(tag1.hasKey("name") && tag1.hasKey("count"))
                {
                    installedUpgrades.put(tag1.getString("name"), tag1.getInteger("count"));
                    //LogHelper.info("name: " + tag1.getString("name") + ", count: " + String.valueOf(tag1.getInteger("count")));
                }
                else
                {
                    LogHelper.fatal("Something messed up my NBT!");
                }
           }
        }*/
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
