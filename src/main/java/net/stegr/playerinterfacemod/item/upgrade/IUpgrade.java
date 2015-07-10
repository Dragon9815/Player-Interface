package net.stegr.playerinterfacemod.item.upgrade;

public interface IUpgrade
{
    String getUpgradeID();

    String getLocalizedName();

    String[] getPrerequisites();
}
