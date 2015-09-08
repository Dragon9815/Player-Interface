package net.dragon9815.playerinterfacemod.item.upgrade;

import net.dragon9815.dragoncore.item.ItemUpgrade;
import net.dragon9815.playerinterfacemod.creativetab.CreativeTabPlim;
import net.dragon9815.playerinterfacemod.reference.Reference;
import net.dragon9815.playerinterfacemod.reference.UpgradeNames;

public class UpgradeComperator extends ItemUpgrade {
    public UpgradeComperator() {
        super();

        this.setCreativeTab(CreativeTabPlim.PLIM_TAB);
        this.setUnlocalizedName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.COMPERATOR));
        this.setTextureName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.COMPERATOR));
    }

    @Override
    public String getModID() {
        return Reference.MOD_ID;
    }

    @Override
    public String getUnlocalizedUpgradeName() {
        return UpgradeNames.COMPERATOR;
    }
}
