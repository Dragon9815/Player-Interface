package net.dragon9815.playerinterfacemod.grid;

import java.util.ArrayList;

public class GridProxy {
    public static GridProxy INSTANCE = new GridProxy();
    private ArrayList<Grid> grids;

    public GridProxy() {
        grids = new ArrayList<Grid>();
    }

    public void addGrid(Grid g) {
        grids.add(g);
    }
}
