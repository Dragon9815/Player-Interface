package net.stegr.plim.item.upgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.stegr.plim.tileentity.TileEntityUpgradeable;

public class ItemUpgradeBuffer extends ItemUpgrade
{
    @Override
    public int getMaxUpgrades()
    {
        return 1;
    }

    @Override
    public String getUpgradeID()
    {
        return "buffer";
    }
}
