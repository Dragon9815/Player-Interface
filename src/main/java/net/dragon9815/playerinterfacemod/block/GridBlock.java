package net.dragon9815.playerinterfacemod.block;

import net.dragon9815.playerinterfacemod.tileentity.GridTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public abstract class GridBlock extends Block {
    public GridBlock(Material p_i45394_1_) {
        super(p_i45394_1_);
    }

    public GridBlock() {
        super(Material.iron);
    }

    protected abstract String getName();
    protected abstract GridTileEntity getTileEntity();
}
