package net.dragon9815.playerinterfacemod.grid;

import net.dragon9815.playerinterfacemod.block.GridBlock;
import net.dragon9815.playerinterfacemod.utility.Position;

public interface IGridNode {
    int getX();
    int getY();
    int getZ();

    GridBlock getGridBlock();
}
