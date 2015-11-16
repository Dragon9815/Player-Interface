package net.dragon9815.playerinterfacemod.handler;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.PlayerEvent;
import net.dragon9815.playerinterfacemod.block.BlockPlayerInterface;
import net.dragon9815.playerinterfacemod.registry.InterfaceRegistry;
import net.dragon9815.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.event.world.BlockEvent;

public class PlimEventHandler {
    @SubscribeEvent
    public void playerLoginEvent(PlayerEvent.PlayerLoggedInEvent event) {
        //event.player.addChatComponentMessage(new ChatComponentText("Hallo " + event.player.getDisplayName()));
        if (event.player == null) {
            return;
        }

        // DEBUG
        //event.player.addChatComponentMessage(new ChatComponentText("Your Player-Interfaces are: "));

        for (InterfaceRegistry.InterfaceDataContainer dataContainer : InterfaceRegistry.getInterfacesOfPlayer(event.player.getUniqueID())) {
            // DEBUG
            //event.player.addChatComponentMessage(new ChatComponentText(String.format("x: %d, y: %d, z: %d", dataContainer.x, dataContainer.y, dataContainer.z)));
            TileEntityPlayerInterface te = ((TileEntityPlayerInterface) DimensionManager.getWorld(dataContainer.dimensionID).getTileEntity(dataContainer.x, dataContainer.y, dataContainer.z));

            if (te != null) {
                te.bindPlayer(event.player.getUniqueID());
            }
        }
    }

    @SubscribeEvent
    public void playerLogoutEvent(PlayerEvent.PlayerLoggedOutEvent event) {
        if (event.player == null) {
            return;
        }

        for (InterfaceRegistry.InterfaceDataContainer dataContainer : InterfaceRegistry.getInterfacesOfPlayer(event.player.getUniqueID())) {
            // DEBUG
            //event.player.addChatComponentMessage(new ChatComponentText(String.format(">>>>>> x: %d, y: %d, z: %d", dataContainer.x, dataContainer.y, dataContainer.z)));
            //LogHelper.info(String.format(">>>>>> DIM: %d, x: %d, y: %d, z: %d", dataContainer.dimensionID, dataContainer.x, dataContainer.y, dataContainer.z));
            TileEntityPlayerInterface te = ((TileEntityPlayerInterface) DimensionManager.getWorld(dataContainer.dimensionID).getTileEntity(dataContainer.x, dataContainer.y, dataContainer.z));

            if (te != null) {
                te.setPlayerOffline();
            }
        }
    }

    @SubscribeEvent
    public void onBreakBlock(BlockEvent.BreakEvent event) {
        if (event.block instanceof BlockPlayerInterface) {
            InterfaceRegistry.removeInterface(new InterfaceRegistry.InterfaceDataContainer(null, event.world.provider.dimensionId, event.x, event.y, event.z));
        }
    }
}
