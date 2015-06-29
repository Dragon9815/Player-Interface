package net.stegr.plim.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.stegr.plim.item.upgrade.IUpgrade;
import net.stegr.plim.item.upgrade.ItemUpgradeBuffer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class TileEntityUpgradeable extends TileEntity
{
    List<IUpgrade> installedUpgrades;
    List<Class<? extends IUpgrade>> validUpgrades;

    public TileEntityUpgradeable()
    {
        super();

        validUpgrades = new LinkedList<Class<? extends IUpgrade>>();
        installedUpgrades = new LinkedList<IUpgrade>();
    }

    public boolean isUpgradeValid(IUpgrade upgrade)
    {
        Iterator<Class<? extends IUpgrade>> it1 = this.validUpgrades.iterator();
        Class<? extends IUpgrade> upgrade1;

        while(it1.hasNext())
        {
            upgrade1 = it1.next();

            if(upgrade.getClass().equals(upgrade1))
            {
                return true;
            }

        }

        return false;
    }

    public boolean addUpgrade(IUpgrade upgrade)
    {
        Iterator<IUpgrade> it = this.installedUpgrades.iterator();
        IUpgrade upgrade1;
        int num = 0;

        while (it.hasNext())
        {
            upgrade1 = it.next();

            if(upgrade.getClass().equals(upgrade1.getClass()))
            {
                num++;
            }
        }

        if(num >= upgrade.getMaxUpgrades())
        {
            return false;
        }

        installedUpgrades.add(upgrade);
        return true;
    }


}
