package net.stegr.plim.item.upgrade;

public class ItemUpgradeTransfer extends ItemUpgrade
{
    public ItemUpgradeTransfer()
    {
        super();

        this.setUnlocalizedName("upgrade_transfer");
        this.setTextureName("upgrade_transfer");
    }

    @Override
    public String getUpgradeID()
    {
        return "transfer";
    }
}
