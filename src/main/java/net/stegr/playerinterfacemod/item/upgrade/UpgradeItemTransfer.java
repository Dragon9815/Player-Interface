package net.stegr.playerinterfacemod.item.upgrade;


import net.dragon9815.dragoncore.item.ItemUpgrade;
import net.stegr.playerinterfacemod.creativetab.CreativeTabPlim;
import net.stegr.playerinterfacemod.reference.Reference;
import net.stegr.playerinterfacemod.reference.UpgradeNames;

public class UpgradeItemTransfer extends ItemUpgrade {
    public UpgradeItemTransfer() {
        super();

        this.setCreativeTab(CreativeTabPlim.PLIM_TAB);
        this.setUnlocalizedName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.ITEMTRANSFER));
        this.setTextureName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.ITEMTRANSFER));
    }

    @Override
    public String getModID() {
        return Reference.MOD_ID;
    }

    @Override
    public String getUnlocalizedUpgradeName() {
        return UpgradeNames.ITEMTRANSFER;
    }
}
