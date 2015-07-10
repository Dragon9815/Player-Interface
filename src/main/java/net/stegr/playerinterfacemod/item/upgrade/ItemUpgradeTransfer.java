package net.stegr.playerinterfacemod.item.upgrade;

import net.stegr.playerinterfacemod.reference.UpgradeNames;

public class ItemUpgradeTransfer extends ItemUpgrade
{
    public ItemUpgradeTransfer()
    {
        super();

        this.setUnlocalizedName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.TRANSFER));
        this.setTextureName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.TRANSFER));
    }

    @Override
    public String getUpgradeID()
    {
        return UpgradeNames.TRANSFER;
    }
}
