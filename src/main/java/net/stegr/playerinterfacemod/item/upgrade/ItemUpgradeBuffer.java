package net.stegr.playerinterfacemod.item.upgrade;

import net.stegr.playerinterfacemod.reference.UpgradeNames;

public class ItemUpgradeBuffer extends ItemUpgrade
{
    public ItemUpgradeBuffer()
    {
        super();

        this.setUnlocalizedName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.BUFFER));
        this.setTextureName(UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.BUFFER));
    }

    @Override
    public String getUpgradeID()
    {
        return UpgradeNames.BUFFER;
    }

    @Override
    public String[] getPrerequisites()
    {
        String[] s = {UpgradeNames.TRANSFER};
        return s;
    }
}
