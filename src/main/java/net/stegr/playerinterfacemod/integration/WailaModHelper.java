package net.stegr.playerinterfacemod.integration;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.event.FMLInterModComms;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.stegr.playerinterfacemod.integration.waila.TileWailaDataProvider;
import net.stegr.playerinterfacemod.reference.ModIDs;
import net.stegr.playerinterfacemod.tileentity.TileEntityPlayerInterface;

public class WailaModHelper extends BaseModHelper {
    @Optional.Method(modid = ModIDs.WAILA)
    public static void register(IWailaRegistrar registrar) {
        final IWailaDataProvider tile = new TileWailaDataProvider();

        registrar.registerBodyProvider(tile, TileEntityPlayerInterface.class);
        registrar.registerNBTProvider(tile, TileEntityPlayerInterface.class);
    }

    @Override
    protected String getModId() {
        return ModIDs.WAILA;
    }

    @Override
    public void init() {
        super.init();
    }

    @Override
    public void load() throws Exception {
        FMLInterModComms.sendMessage("Waila", "register", this.getClass().getName() + ".register");
    }
}
