package net.stegr.plim.item.upgrade;

public interface IUpgrade
{
    void onUse();

    void onTick();

    int getMaxUpgrades();

    String getUpgradeID();
}
