package net.stegr.playerinterfacemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.inventory.InventoryUpgradeable;
import net.stegr.playerinterfacemod.item.upgrade.ItemUpgrade;
import net.stegr.playerinterfacemod.tileentity.TileEntityUpgradeable;
import net.stegr.repackage.cofh.lib.util.helpers.StringHelper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BlockPlimTileEntityUpgradeable extends BlockPlimTileEntity implements IBlockUpgradeable
{
    public BlockPlimTileEntityUpgradeable()
    {
        super();
    }

    public BlockPlimTileEntityUpgradeable(Material material)
    {
        super(material);
    }

    @Override
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int meta, float par7, float par8, float par9)
    {
        if(!world.isRemote)
        {
            TileEntityUpgradeable te = (TileEntityUpgradeable) world.getTileEntity(x, y, z);

            if (player.getHeldItem() != null)
            {
                if (player.getHeldItem().getItem() instanceof ItemUpgrade)
                {
                    ItemUpgrade item = (ItemUpgrade) player.getHeldItem().getItem();

                    if (this.doUpgrade(item, te, player))
                    {
                        player.getHeldItem().stackSize--;
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        TileEntityUpgradeable te = (TileEntityUpgradeable)world.getTileEntity(x, y, z);

        te.dropAllUpgrades(world, x, y, z);

        super.breakBlock(world, x, y, z, block, meta);
    }

    public boolean doUpgrade(ItemUpgrade upgrade, TileEntityUpgradeable tileEntity, EntityPlayer player)
    {
        if (tileEntity.isUpgradeValid(upgrade))
        {
            tileEntity.addUpgrade(upgrade, 1);
            player.addChatMessage(new ChatComponentText(StringHelper.GREEN + String.format(StringHelper.localize("message.upgrade.installed"), upgrade.getLocalizedName())));
            return true;
        }
        else
        {
            InventoryUpgradeable.UpgradeFault fault;
            fault = ((InventoryUpgradeable) tileEntity.getUpgradeInventory()).getLastUpgradeFault();

            player.addChatComponentMessage(new ChatComponentText(StringHelper.RED + String.format(StringHelper.localize("message.upgrade.fault"), upgrade.getLocalizedName())));

            switch (fault)
            {
            case Unknown:
                player.addChatComponentMessage(new ChatComponentText(StringHelper.RED + StringHelper.localize("message.upgrade.fault.unknown")));
                break;
            case Invalid:
                player.addChatComponentMessage(new ChatComponentText(StringHelper.RED + StringHelper.localize("message.upgrade.fault.invalid")));
                break;
            case AlreadyInstalled:
                player.addChatComponentMessage(new ChatComponentText(StringHelper.RED + StringHelper.localize("message.upgrade.fault.alreadyInstalled")));
                break;
            case MissingReq:
                player.addChatComponentMessage(new ChatComponentText(StringHelper.RED + StringHelper.localize("message.upgrade.fault.missingReq")));

                List<ItemUpgrade> missig = ((InventoryUpgradeable) tileEntity.getUpgradeInventory()).listMissingUpgrades(upgrade);
                Iterator<ItemUpgrade> it1 = missig.iterator();

                while(it1.hasNext())
                {
                    ItemUpgrade var1 = it1.next();

                    player.addChatComponentMessage(new ChatComponentText(StringHelper.RED + " - " + var1.getLocalizedName()));
                }

                break;
            }
        }



        return false;
    }
}