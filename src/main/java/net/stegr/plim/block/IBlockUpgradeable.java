package net.stegr.plim.block;

import net.stegr.plim.item.upgrade.IUpgrade;

public interface IBlockUpgradeable
{
    void GetMaxUpgrades();

    void doUpgrade(IUpgrade upgrade);
}
