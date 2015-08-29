package net.stegr.playerinterfacemod.item.upgrade;

import net.stegr.playerinterfacemod.item.ItemUpgrade;
import net.stegr.playerinterfacemod.reference.UpgradeNames;

public class UpgradeRFTransfer extends ItemUpgrade
{
    public UpgradeRFTransfer()
    {
        super();

        this.setUnlocalizedName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.RFTRANSFER));
        this.setTextureName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.RFTRANSFER));
    }

    @Override
    public String getUpgradeID()
    {
        return UpgradeNames.RFTRANSFER;
    }
}
