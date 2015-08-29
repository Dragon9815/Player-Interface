package net.stegr.playerinterfacemod.block;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.creativetab.CreativeTabPlim;

public abstract class BlockTileEntityBase extends BlockPlim implements ITileEntityProvider
{
    public BlockTileEntityBase(Material material)
    {
        super(material);
        this.isBlockContainer = true;
    }

    public BlockTileEntityBase()
    {
        this(Material.rock);
    }

    @Override
    public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
    }

    public void breakBlock(World world, int x, int y, int z, Block block, int meta)
    {
        super.breakBlock(world, x, y, z, block, meta);
        world.removeTileEntity(x, y, z);
    }

    public boolean onBlockEventReceived(World world, int x, int y, int z, int par5, int par6)
    {
        super.onBlockEventReceived(world, x, y, z, par5, par6);
        TileEntity tileentity = world.getTileEntity(x, y, z);
        return tileentity != null && tileentity.receiveClientEvent(par5, par6);
    }
}
