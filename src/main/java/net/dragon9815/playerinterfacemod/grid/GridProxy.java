package net.dragon9815.playerinterfacemod.grid;

import java.util.ArrayList;

public class  GridProxy {
    public static GridProxy INSTANCE = new GridProxy();
    private ArrayList<Grid> grids;

    public GridProxy() {
        grids = new ArrayList<Grid>();
    }

    public void addGrid(Grid g) {
        grids.add(g);
    }

    public void removeGrid(int x, int y, int z) {
        int i = 0;

        for(Grid grid : grids) {
            if(grid.getParent().getX() == x && grid.getParent().getY() == y && grid.getParent().getZ() == z) {
                grids.remove(i);
            }

            i++;
        }
    }
}
