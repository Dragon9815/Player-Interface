package net.stegr.playerinterfacemod.item.upgrade;

import net.stegr.playerinterfacemod.reference.UpgradeNames;

public class ItemUpgradeComperator extends ItemUpgrade
{
    public ItemUpgradeComperator()
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
