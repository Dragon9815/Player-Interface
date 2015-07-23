package net.stegr.playerinterfacemod.utility;

import net.stegr.playerinterfacemod.item.upgrade.ItemUpgrade;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UpgradeRegistry
{
    private static List<ItemUpgrade> registeredUpgrades;

    public static void init()
    {
        registeredUpgrades = new LinkedList<ItemUpgrade>();
    }


    public static void registerUpgrade(ItemUpgrade upgrade)
    {
        if(!isRegistered(upgrade.getUpgradeID()))
        {
            registeredUpgrades.add(upgrade);
        }
        else
        {
            LogHelper.warn("Upgrade already registered! " + upgrade.getUpgradeID());
        }
    }

    public static boolean isRegistered(String upgradeID)
    {
        return getUpgrade(upgradeID) != null;
    }

    public static ItemUpgrade getUpgrade(String upgradeID)
    {
        Iterator<ItemUpgrade> it = registeredUpgrades.iterator();

        while(it.hasNext())
        {
            ItemUpgrade upgrade = it.next();

            if(upgrade.getUpgradeID().equals(upgradeID))
            {
                return upgrade;
            }
        }

        return null;
    }
}
