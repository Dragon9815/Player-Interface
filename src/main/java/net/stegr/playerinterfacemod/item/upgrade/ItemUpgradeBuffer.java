package net.stegr.playerinterfacemod.item.upgrade;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import net.stegr.playerinterfacemod.reference.UpgradeNames;
import net.stegr.repackage.cofh.lib.util.helpers.StringHelper;

import java.util.List;

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
