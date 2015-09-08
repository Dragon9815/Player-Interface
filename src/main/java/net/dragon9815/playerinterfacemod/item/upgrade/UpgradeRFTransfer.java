package net.dragon9815.playerinterfacemod.item.upgrade;

import net.dragon9815.dragoncore.item.ItemUpgrade;
import net.dragon9815.playerinterfacemod.creativetab.CreativeTabPlim;
import net.dragon9815.playerinterfacemod.reference.Reference;
import net.dragon9815.playerinterfacemod.reference.UpgradeNames;

public class UpgradeRFTransfer extends ItemUpgrade {
    public UpgradeRFTransfer() {
        super();

        this.setCreativeTab(CreativeTabPlim.PLIM_TAB);
        this.setUnlocalizedName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.RFTRANSFER));
        this.setTextureName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.RFTRANSFER));
    }

    @Override
    public String getModID() {
        return Reference.MOD_ID;
    }

    @Override
    public String getUnlocalizedUpgradeName() {
        return UpgradeNames.RFTRANSFER;
    }
}
