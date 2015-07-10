package net.stegr.playerinterfacemod.init;

import cpw.mods.fml.common.registry.GameRegistry;
import net.stegr.playerinterfacemod.item.upgrade.ItemUpgrade;
import net.stegr.playerinterfacemod.item.upgrade.ItemUpgradeBuffer;
import net.stegr.playerinterfacemod.item.upgrade.ItemUpgradeComperator;
import net.stegr.playerinterfacemod.item.upgrade.ItemUpgradeTransfer;

public class ModItems
{
    public static final ItemUpgrade BufferUpgrade = new ItemUpgradeBuffer();
    public static final ItemUpgrade TransferUpgrade = new ItemUpgradeTransfer();
    public static final ItemUpgrade ComperatorUpgrade = new ItemUpgradeComperator();

    public static void init()
    {
        GameRegistry.registerItem(BufferUpgrade, "upgrade_buffer");
        GameRegistry.registerItem(TransferUpgrade, "upgrade_transfer");
        GameRegistry.registerItem(ComperatorUpgrade, "upgrade_comperator");
    }
}
