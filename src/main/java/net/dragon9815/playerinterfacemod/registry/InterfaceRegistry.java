package net.dragon9815.playerinterfacemod.registry;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import net.dragon9815.playerinterfacemod.tileentity.TileEntityPlayerInterface;

import java.util.LinkedList;
import java.util.UUID;

public class InterfaceRegistry {
    private static LinkedList<InterfaceDataContainer> interfaces = new LinkedList<InterfaceDataContainer>();

    public static void addInterface(InterfaceDataContainer interfaceDataContainer) {
        if (interfaceDataContainer == null) {
            return;
        }

        if (hasInterface(interfaceDataContainer)) {
            return;
        }

        interfaces.add(interfaceDataContainer);
        //LogHelper.info(">>> Owner: " + interfaceDataContainer.playerUUID);
    }

    public static void removeInterface(InterfaceDataContainer interfaceDataContainer) {
        if (interfaceDataContainer == null) {
            return;
        }

        for (InterfaceDataContainer dataContainer : interfaces) {
            if (interfaceDataContainer.dimensionID == dataContainer.dimensionID && dataContainer.x == interfaceDataContainer.x && dataContainer.y == interfaceDataContainer.y && dataContainer.z == interfaceDataContainer.z) {
                interfaces.remove(dataContainer);
                return;
            }
        }
    }

    public static boolean hasInterface(InterfaceDataContainer interfaceDataContainer) {
        if (interfaceDataContainer == null) {
            return false;
        }

        for (InterfaceDataContainer dataContainer : interfaces) {
            if (dataContainer.equals(interfaceDataContainer)) {
                return true;
            }
        }

        return false;
    }

    public static LinkedList<InterfaceDataContainer> getInterfacesOfPlayer(UUID playerUUID) {
        LinkedList<InterfaceDataContainer> ret = new LinkedList<InterfaceDataContainer>();

        if (playerUUID == null) {
            return null;
        }

        for (InterfaceDataContainer dataContainer : interfaces) {
            //LogHelper.info(">>>> Container Owner: " + dataContainer.playerUUID);
            if (dataContainer.playerUUID != null && dataContainer.playerUUID.equals(playerUUID)) {
                ret.add(dataContainer);
            }
        }

        return ret;
    }

    public static InterfaceDataContainer getDataContainer(TileEntityPlayerInterface tileEntity) {
        if (tileEntity == null || !tileEntity.hasWorldObj()) {
            return null;
        }

        for (InterfaceDataContainer dataContainer : interfaces) {
            if (dataContainer.dimensionID == tileEntity.getWorldObj().provider.dimensionId && dataContainer.x == tileEntity.xCoord && dataContainer.y == tileEntity.yCoord && dataContainer.z == tileEntity.zCoord) {

                return dataContainer;
            }
        }

        return null;
    }

    public static void setPlayer(InterfaceDataContainer dataContainer, UUID playerUUID) {
        if (dataContainer == null) {
            return;
        }

        for (InterfaceDataContainer dataContainer1 : interfaces) {
            if (dataContainer1.dimensionID == dataContainer.dimensionID && dataContainer1.x == dataContainer.x && dataContainer1.y == dataContainer.y && dataContainer1.z == dataContainer.z) {
                //LogHelper.info("InterfaceRegistry[100]: Found TileEntity, storing new PlayerData");
                dataContainer1.playerUUID = playerUUID;
            }
        }
    }

    public static void setInterfaceData(InterfaceDataContainer dataContainer) {
        if (dataContainer == null) {
            return;
        }

        World world = DimensionManager.getWorld(dataContainer.dimensionID);

        if (world == null) {
            return;
        }

        TileEntity te = world.getTileEntity(dataContainer.x, dataContainer.y, dataContainer.z);

        if (!(te instanceof TileEntityPlayerInterface)) {
            return;
        }

        ((TileEntityPlayerInterface) te).bindPlayer(dataContainer.playerUUID);
    }

    public static class InterfaceDataContainer {
        public UUID playerUUID;
        public int dimensionID;
        public int x;
        public int y;
        public int z;

        public InterfaceDataContainer(UUID playerUUID, int dimensionID, int x, int y, int z) {
            this.playerUUID = playerUUID;
            this.dimensionID = dimensionID;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public InterfaceDataContainer(int dimensionID, int x, int y, int z) {
            this(null, dimensionID, x, y, z);
        }

        public InterfaceDataContainer(TileEntityPlayerInterface playerInterface) {
            if (playerInterface == null || !playerInterface.hasWorldObj()) {
                throw new IllegalArgumentException(String.format("%s. Report this log to the Mod Author!", (playerInterface == null) ? "TileEntity must not be null" : "World must not be null"));
            }

            //this(playerInterface.getBoundPlayer(), playerInterface.xCoord, playerInterface.yCoord, playerInterface.zCoord);

            this.playerUUID = (playerInterface.getOwner() != null) ? playerInterface.getOwner().getUniqueID() : null;
            this.dimensionID = (playerInterface.hasWorldObj()) ? playerInterface.getWorldObj().provider.dimensionId : -1;
            this.x = playerInterface.xCoord;
            this.y = playerInterface.yCoord;
            this.z = playerInterface.zCoord;
        }
    }
}
