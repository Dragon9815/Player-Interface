package net.stegr.plim.item.upgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.stegr.plim.tileentity.TileEntityUpgradeable;

public class ItemUpgradeBuffer extends ItemUpgrade
{
    public ItemUpgradeBuffer()
    {
        super();

        this.setUnlocalizedName("upgrade_buffer");
        this.setTextureName("upgrade_buffer");
    }

    @Override
    public String getUpgradeID()
    {
        return "buffer";
    }

    @Override
    public String[] getPrerequisites()
    {
        String[] s = {"transfer"};
        return s;
    }
}
