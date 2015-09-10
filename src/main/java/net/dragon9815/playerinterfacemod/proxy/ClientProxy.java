package net.dragon9815.playerinterfacemod.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.dragon9815.playerinterfacemod.client.settings.Keybindings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraftforge.client.MinecraftForgeClient;
import net.dragon9815.playerinterfacemod.client.renderer.item.ItemPlayerInterfaceRenderer;
import net.dragon9815.playerinterfacemod.client.renderer.tileentity.PlayerInterfaceRenderer;
import net.dragon9815.playerinterfacemod.init.ModBlocks;
import net.dragon9815.playerinterfacemod.tileentity.TileEntityPlayerInterface;

public class ClientProxy extends CommonProxy {
    @Override
    public EntityPlayer getClientPlayer() {
        return Minecraft.getMinecraft().thePlayer;
    }

    public void registerRenderers() {
        TileEntitySpecialRenderer renderer = new PlayerInterfaceRenderer();
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityPlayerInterface.class, renderer);
        MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.player_interface), new ItemPlayerInterfaceRenderer(renderer, new TileEntityPlayerInterface()));
    }

    public void registerTilEntitySpecialRenderers() {

    }

    @Override
    public void registerClientStuff() {
        Keybindings.init();
    }
}
