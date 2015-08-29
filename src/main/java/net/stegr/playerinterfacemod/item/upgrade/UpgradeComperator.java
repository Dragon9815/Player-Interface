package net.stegr.playerinterfacemod.item.upgrade;

import net.stegr.playerinterfacemod.item.ItemUpgrade;
import net.stegr.playerinterfacemod.reference.UpgradeNames;

public class UpgradeComperator extends ItemUpgrade
{
    public UpgradeComperator()
    {
        super();

        this.setUnlocalizedName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.COMPERATOR));
        this.setTextureName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.COMPERATOR));
    }

    @Override
    public String getUpgradeID()
    {
        return UpgradeNames.COMPERATOR;
    }
}
