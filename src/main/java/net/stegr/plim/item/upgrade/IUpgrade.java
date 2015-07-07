package net.stegr.plim.item.upgrade;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public interface IUpgrade
{
    String getUpgradeID();

    String getLocalizedName();

    String[] getPrerequisites();
}
