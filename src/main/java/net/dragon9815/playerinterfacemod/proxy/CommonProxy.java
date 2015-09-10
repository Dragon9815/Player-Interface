package net.dragon9815.playerinterfacemod.proxy;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IGuiHandler;
import net.dragon9815.playerinterfacemod.client.gui.GuiTrashInventory;
import net.dragon9815.playerinterfacemod.container.ContainerTrash;
import net.dragon9815.playerinterfacemod.handler.ConfigurationHandler;
import net.dragon9815.playerinterfacemod.handler.KeyEventHandler;
import net.dragon9815.playerinterfacemod.init.ModBlocks;
import net.dragon9815.playerinterfacemod.init.ModItems;
import net.dragon9815.playerinterfacemod.init.ModTileEntities;
import net.dragon9815.playerinterfacemod.inventory.InventoryTrash;
import net.dragon9815.playerinterfacemod.network.PacketHandler;
import net.dragon9815.playerinterfacemod.tileentity.TileEntityPlayerInterface;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.dragon9815.playerinterfacemod.client.gui.GuiSideConfig;

public abstract class CommonProxy implements IProxy, IGuiHandler {
    public abstract EntityPlayer getClientPlayer();

    public void intermodComm() {}

    public void registerRenderers() {}

    public void registerTilEntitySpecialRenderers() {}

    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == GuiTrashInventory.GUI_ID) {
            return new ContainerTrash(player.inventory, new InventoryTrash(player));
        }
        return null;
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        if(ID == GuiTrashInventory.GUI_ID) {
            return new GuiTrashInventory(player.inventory, new InventoryTrash(player));
        }

        return null;
    }

    public void registerClientStuff() {}
    public void registerServerStuff() {}
    public void registerCommonStuff() {
        ModTileEntities.init();
        ModBlocks.init();
        ModItems.init();

        PacketHandler.init();

        FMLCommonHandler.instance().bus().register(new KeyEventHandler());
    }
}
