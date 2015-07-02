package net.stegr.plim.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.stegr.plim.item.upgrade.ItemUpgrade;
import net.stegr.plim.item.upgrade.ItemUpgradeBuffer;
import net.stegr.plim.item.upgrade.ItemUpgradeTransfer;

public class ModItems
{
    public static final ItemUpgrade BufferUpgrade = new ItemUpgradeBuffer();
    public static final ItemUpgrade TransferUpgrade = new ItemUpgradeTransfer();

    public static void init()
    {
        GameRegistry.registerItem(BufferUpgrade, "upgrade_buffer");
        GameRegistry.registerItem(TransferUpgrade, "upgrade_transfer");
    }
}
