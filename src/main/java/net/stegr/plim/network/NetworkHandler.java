package net.stegr.plim.network;

import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.stegr.plim.reference.Reference;

public class NetworkHandler
{
    private static SimpleNetworkWrapper INSTANCE;

    public static void init()
    {
        INSTANCE =  NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID);
    }
}
