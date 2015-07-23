package net.stegr.playerinterfacemod.integration;

import cpw.mods.fml.common.event.FMLInterModComms;
import net.stegr.playerinterfacemod.integration.waila.TileWailaDataProvider;
import net.stegr.repackage.mcp.mobius.waila.api.IWailaDataProvider;
import net.stegr.repackage.mcp.mobius.waila.api.IWailaRegistrar;
import net.stegr.playerinterfacemod.tileentity.TileEntityUpgradeable;

public class Waila
{
    public static Waila instance;

    public static void register(IWailaRegistrar registrar)
    {
        final IWailaDataProvider tile = new TileWailaDataProvider();

        registrar.registerBodyProvider(tile, TileEntityUpgradeable.class);
        registrar.registerNBTProvider(tile, TileEntityUpgradeable.class);
    }

    public void init() throws Throwable
    {
        testClassExistence(IWailaDataProvider.class);
        testClassExistence(IWailaRegistrar.class);

        FMLInterModComms.sendMessage("Waila", "register", this.getClass().getName() + ".register");
    }

    protected void testClassExistence(Class<?> _class)
    {
        _class.isInstance(this);
    }
}
