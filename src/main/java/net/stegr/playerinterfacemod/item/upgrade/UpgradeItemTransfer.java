package net.stegr.playerinterfacemod.item.upgrade;


import net.stegr.playerinterfacemod.item.ItemUpgrade;
import net.stegr.playerinterfacemod.reference.UpgradeNames;

public class UpgradeItemTransfer extends ItemUpgrade
{
    public UpgradeItemTransfer()
    {
        super();

        this.setUnlocalizedName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.ITEMTRANSFER));
        this.setTextureName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.ITEMTRANSFER));
    }

    @Override
    public String getUpgradeID()
    {
        return UpgradeNames.ITEMTRANSFER;
    }
}
