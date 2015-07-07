package net.stegr.plim.tileentity;

import net.minecraft.block.BlockFurnace;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.*;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.world.World;
import net.stegr.plim.item.upgrade.IUpgrade;
import net.stegr.plim.item.upgrade.ItemUpgrade;
import net.stegr.plim.item.upgrade.ItemUpgradeBuffer;
import net.stegr.plim.utility.LogHelper;
import net.stegr.plim.utility.UpgradeRegistry;

import javax.swing.text.html.parser.Entity;
import java.util.*;

public abstract class TileEntityUpgradeable extends TileEntityBase
{
    public Map<String, Integer> installedUpgrades;
    public Map<String, Integer> validUpgrades;

    public TileEntityUpgradeable()
    {
        super();

        installedUpgrades = new HashMap<String, Integer>();
        validUpgrades = new HashMap<String, Integer>();
    }

    public boolean isUpgradeValid(IUpgrade upgrade)
    {
        if(validUpgrades.containsKey(upgrade.getUpgradeID()))
        {
            if(validUpgrades.get(upgrade.getUpgradeID()) > 0)
            {
                String[] prerequisites = upgrade.getPrerequisites();

                if(prerequisites != null)
                {
                    for(String s : prerequisites)
                    {
                        if(!installedUpgrades.containsKey(s))
                        {
                            return false;
                        }
                    }
                }

                if (installedUpgrades.containsKey(upgrade.getUpgradeID()))
                {
                    if (installedUpgrades.get(upgrade.getUpgradeID()) < validUpgrades.get(upgrade.getUpgradeID()))
                    {
                        return true;
                    }
                }
                else
                {
                    return true;
                }

            }
        }

        return false;
    }

    public void addUpgrade(IUpgrade upgrade)
    {
        int var1 = 1;

        if(installedUpgrades.containsKey(upgrade.getUpgradeID()))
        {
            //LogHelper.info("var1: " + installedUpgrades.get(upgrade.getUpgradeID()));
            var1 += installedUpgrades.get(upgrade.getUpgradeID());
        }

        installedUpgrades.put(upgrade.getUpgradeID(), var1);

        onUpgrade(upgrade);
    }

    public abstract void onUpgrade(IUpgrade upgrade);

    public void dropAllUpgrades(World world, int x, int y, int z)
    {
        Set<String> stringSet = installedUpgrades.keySet();
        Iterator<String> it1 = stringSet.iterator();

        while (it1.hasNext())
        {
            String id = it1.next();
            ItemUpgrade upgrade = (ItemUpgrade)UpgradeRegistry.getUpgrade(id);

            if(upgrade != null)
            {
                ItemStack itemstack = new ItemStack(upgrade, 1);

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

    @Override
    public void readFromNBT(NBTTagCompound tag)
    {
        super.readFromNBT(tag);

        installedUpgrades.clear();

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
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound tag)
    {
        super.writeToNBT(tag);
        Set<String> keySet = installedUpgrades.keySet();
        Iterator<String> it1 = keySet.iterator();
        NBTTagList tag1 = new NBTTagList();
        int i = 0;

        while(it1.hasNext())
        {
            String key = it1.next();
            int count = installedUpgrades.get(key);
            NBTTagCompound tag2 = new NBTTagCompound();

            //tag2.appendTag(new NBTTagString(key));
            //tag2.appendTag(new NBTTagInt(count));
            tag2.setString("name", key);
            tag2.setInteger("count", count);

            tag1.appendTag(tag2);
            i++;
        }

        tag.setTag("Upgrades", tag1);
    }

    public void writeToSyncNBT(NBTTagCompound tag)
    {
        Set<String> keySet = installedUpgrades.keySet();
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

        tag.setTag("Upgrades", tag1);
    }

    public void readFromSyncNBT(NBTTagCompound tag)
    {
        installedUpgrades.clear();

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
        }
    }

}
