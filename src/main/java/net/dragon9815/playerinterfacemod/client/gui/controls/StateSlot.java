package net.dragon9815.playerinterfacemod.client.gui.controls;

public class StateSlot {
    public enum State {
        INVALID, LOCKED, INPUT, OUTPUT, BIDIRECTIONAL
    }

    public int x;
    public int y;
    public int width;
    public int height;
    public State mode;

    StateSlot(int x, int y, int width, int height, State mode) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.mode = mode;
    }
}
