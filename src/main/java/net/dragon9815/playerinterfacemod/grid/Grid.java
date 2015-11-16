package net.dragon9815.playerinterfacemod.grid;

import net.dragon9815.playerinterfacemod.utility.Position;

import java.util.ArrayList;
import java.util.HashMap;

public class Grid {
    protected HashMap<Position, IGridNode> connectedNodes;
    protected IGridNode parent;

    public Grid(IGridNode parent) {
        this.connectedNodes = new HashMap<Position, IGridNode>();
        this.parent = parent;
    }

    public void addNode(IGridNode node) {
        this.connectedNodes.put(new Position(node.getX(), node.getY(), node.getZ()), node);
    }

    public void removeNode(Position pos) {
        this.e
    }
}
