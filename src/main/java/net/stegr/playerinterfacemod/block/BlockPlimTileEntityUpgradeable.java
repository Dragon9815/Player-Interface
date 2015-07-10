package net.stegr.playerinterfacemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.item.upgrade.IUpgrade;
import net.stegr.playerinterfacemod.item.upgrade.ItemUpgrade;
import net.stegr.playerinterfacemod.tileentity.TileEntityUpgradeable;

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

                    if (this.doUpgrade(item, te))
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

    public boolean doUpgrade(IUpgrade upgrade, TileEntityUpgradeable tileEntity)
    {
        if (tileEntity.installedUpgrades.size() < this.getUpgradeSlots())
        {
            if (tileEntity.isUpgradeValid(upgrade))
            {
                tileEntity.addUpgrade(upgrade);
                return true;
            }
        }

        return false;
    }
}