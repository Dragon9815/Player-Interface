package net.dragon9815.playerinterfacemod.utility;

public class Position {
    public double x, y, z;
    private double oldX, oldY, oldZ;

    public Position(double x, double y, double z)  {
        this.x = this.oldX = x;
        this.y = this.oldY = y;
        this.z = this.oldZ = z;
    }

    public Position() {
        this(0.0, 0.0, 0.0);
    }

    public boolean changed() {
        return (oldX != x || oldY != y || oldZ != z);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position) {
            Position pos = (Position)obj;
            return (pos.x == this.x && pos.y == this.y && pos.z == this.z);
        }

        return false;
    }
}
