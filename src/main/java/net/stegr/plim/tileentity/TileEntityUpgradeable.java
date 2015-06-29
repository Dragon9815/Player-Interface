package net.stegr.plim.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.stegr.plim.item.upgrade.IUpgrade;
import net.stegr.plim.item.upgrade.ItemUpgradeBuffer;

import java.util.*;

public abstract class TileEntityUpgradeable extends TileEntity
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
            var1 += installedUpgrades.get(upgrade.getUpgradeID());
        }

        installedUpgrades.put(upgrade.getUpgradeID(), var1);
    }

    public void onUpgrade(IUpgrade upgrade)
    {

    }

}
