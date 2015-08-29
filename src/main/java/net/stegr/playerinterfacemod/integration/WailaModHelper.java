package net.stegr.playerinterfacemod.integration;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.event.FMLInterModComms;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.stegr.playerinterfacemod.integration.waila.TileWailaDataProvider;
import net.stegr.playerinterfacemod.tileentity.TileEntityUpgradeable;

public class WailaModHelper extends BaseModHelper
{
    @Override
    protected String getModId()
    {
        return "Waila";
    }

    @Override
    public void init()
    {
        super.init();
    }

    @Override
    public void load() throws Exception
    {
        FMLInterModComms.sendMessage("Waila", "register", this.getClass().getName() + ".register");
    }

    @Optional.Method(modid = "Waila")
    public static void register(IWailaRegistrar registrar)
    {
        final IWailaDataProvider tile = new TileWailaDataProvider();

        registrar.registerBodyProvider(tile, TileEntityUpgradeable.class);
        registrar.registerNBTProvider(tile, TileEntityUpgradeable.class);
    }
}
