package net.stegr.playerinterfacemod.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.stegr.playerinterfacemod.reference.Reference;

public class PacketHandler
{
    public static final SimpleNetworkWrapper INSTANCE = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

    public static void init()
    {
        //INSTANCE.registerMessage(MessageSendPlayerName.class, MessageSendPlayerName.class, 0, Side.CLIENT);
    }
}
