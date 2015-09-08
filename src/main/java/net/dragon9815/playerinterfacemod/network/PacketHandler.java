package net.dragon9815.playerinterfacemod.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.dragon9815.playerinterfacemod.reference.Reference;

public class PacketHandler {
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init() {
        //INSTANCE.registerMessage(MessageSendPlayerName.class, MessageSendPlayerName.class, 0, Side.CLIENT);
    }
}
