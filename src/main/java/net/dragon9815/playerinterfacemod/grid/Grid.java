package net.dragon9815.playerinterfacemod.grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Grid {
    protected ArrayList<IGridNode> connectedNodes;
    protected IGridNode parent;

    /**
     * @param parent First node of the Grid
     */
    public Grid(IGridNode parent) {
        this.connectedNodes = new ArrayList<IGridNode>();
        this.parent = parent;
        connectedNodes.add(parent);
    }

    /**
     * This function adds a node to the list
     * @param node The node to add
     */
    public void addNode(IGridNode node) {
        this.connectedNodes.add(node);
    }

    /**
     * This function removes a node by its coordinates
     * @param x The x coordinate
     * @param y The y coordinate
     * @param z The z coordinate
     */
    public void removeNode(int x, int y, int z) {
        Iterator<IGridNode> it1 = connectedNodes.iterator();
        int i = 0;
        boolean isParent = false;
        Random random = new Random();

        for(IGridNode node : connectedNodes) {
            if(node.getX() == x && node.getY() == y && node.getZ() == z) {
                if(node.equals(parent)) {
                    isParent = true;
                }

                connectedNodes.remove(i);
                break;
            }

            i++;
        }

        if(isParent) {
            parent = connectedNodes.get(random.nextInt(connectedNodes.size()));
        }
    }

    /**
     * This function removes all nodes with the same type.
     * @param typeName The type of the nodes to remove
     */
    public void removeNode(String typeName) {
        Iterator<IGridNode> it1 = connectedNodes.iterator();
        int i = 0;
        boolean isParent = false;
        Random random = new Random();

        for(IGridNode node : connectedNodes) {
            if(node.getTypeName().equals(typeName)) {
                if(node.equals(parent)) {
                    isParent = true;
                }

                connectedNodes.remove(i);
            }

            i++;
        }

        if(isParent) {
            parent = connectedNodes.get(random.nextInt(connectedNodes.size()));
        }
    }

    /**
     * This function returns you the total number of nodes in this grid.
     * @return Number of nodes in this Grid
     */
    public int getNumberNodes() {
        return connectedNodes.size();
    }

    /**
     * This function returns you how many nodes of the specified type are in this grid.
     * @param typeName Type of the Node to count
     * @return How many nodes of the type
     */
    public int countNodeType(String typeName) {
        int num = 0;

        if(typeName == null)
            return -1;

        for(IGridNode node : connectedNodes)  {
            if(node.getTypeName().equals(typeName))
                num++;
        }

        return num;
    }

    /**
     * This function returns you the Parent(First node in the Grid) of the Grid.
     * @return Parent node
     */
    public IGridNode getParent() {
        return parent;
    }
}
