package net.dragon9815.playerinterfacemod.integration;

import cpw.mods.fml.common.Optional;
import cpw.mods.fml.common.event.FMLInterModComms;
import mcp.mobius.waila.api.IWailaDataProvider;
import mcp.mobius.waila.api.IWailaRegistrar;
import net.dragon9815.playerinterfacemod.integration.waila.TileWailaDataProvider;
import net.dragon9815.playerinterfacemod.reference.ModIDs;
import net.dragon9815.playerinterfacemod.tileentity.TileEntityPlayerInterface;

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
    public boolean load() throws Exception {
        return FMLInterModComms.sendMessage("Waila", "register", this.getClass().getName() + ".register");
    }
}
