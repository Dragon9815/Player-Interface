package net.stegr.plim.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.stegr.plim.item.upgrade.ItemUpgrade;
import net.stegr.plim.item.upgrade.ItemUpgradeBuffer;

public class ModItems
{
    public static final ItemUpgrade BufferUpgrade = new ItemUpgradeBuffer();

    public static void init()
    {
        GameRegistry.registerItem(BufferUpgrade, "upgrade_buffer");
    }
}
