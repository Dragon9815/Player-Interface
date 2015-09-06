package net.stegr.playerinterfacemod.init;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;
import net.dragon9815.dragoncore.item.ItemBase;
import net.dragon9815.dragoncore.item.ItemUpgrade;
import net.stegr.playerinterfacemod.item.ItemUpgradeBase;
import net.stegr.playerinterfacemod.item.ItemWrench;
import net.stegr.playerinterfacemod.item.upgrade.UpgradeComperator;
import net.stegr.playerinterfacemod.item.upgrade.UpgradeItemTransfer;
import net.stegr.playerinterfacemod.item.upgrade.UpgradeRFTransfer;
import net.stegr.playerinterfacemod.reference.ModIDs;
import net.stegr.playerinterfacemod.reference.UpgradeNames;

public class ModItems {
    // Upgrades
    public static final ItemUpgrade ItemTransferUpgrade = new UpgradeItemTransfer();
    //public static final ItemUpgrade ItemBufferUpgrade = new UpgradeItemBuffer();
    public static final ItemUpgrade RFTransferUpgrade = new UpgradeRFTransfer();
    public static final ItemUpgrade ComperatorUpgrade = new UpgradeComperator();

    // Items
    public static final ItemBase UpgradeBase = new ItemUpgradeBase();
    public static final ItemBase Wrench = new ItemWrench();

    public static void init() {
        GameRegistry.registerItem(ItemTransferUpgrade, UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.ITEMTRANSFER));
        //GameRegistry.registerItem(ItemBufferUpgrade, UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.ITEMBUFFER));
        GameRegistry.registerItem(ComperatorUpgrade, UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.COMPERATOR));

        GameRegistry.registerItem(UpgradeBase, "upgrade_base");
        GameRegistry.registerItem(Wrench, "wrench");

        if (Loader.isModLoaded(ModIDs.THERMAL_EXPANSION) || Loader.isModLoaded(ModIDs.THERMAL_DYNAMICS)) {
            GameRegistry.registerItem(RFTransferUpgrade, UpgradeNames.getUnlocalizedUpgradeName(UpgradeNames.RFTRANSFER));
        }
    }
}
