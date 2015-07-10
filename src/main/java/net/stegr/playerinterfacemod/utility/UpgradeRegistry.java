package net.stegr.playerinterfacemod.utility;

import net.stegr.playerinterfacemod.item.upgrade.IUpgrade;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class UpgradeRegistry
{
    private static List<IUpgrade> registeredUpgrades;

    public static void init()
    {
        registeredUpgrades = new LinkedList<IUpgrade>();
    }


    public static void registerUpgrade(IUpgrade upgrade)
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

    public static IUpgrade getUpgrade(String upgradeID)
    {
        Iterator<IUpgrade> it = registeredUpgrades.iterator();

        while(it.hasNext())
        {
            IUpgrade upgrade = it.next();

            if(upgrade.getUpgradeID().equals(upgradeID))
            {
                return upgrade;
            }
        }

        return null;
    }
}
