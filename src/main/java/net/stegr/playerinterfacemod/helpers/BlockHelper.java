package net.stegr.playerinterfacemod.helpers;

import buildcraft.api.transport.IPipe;
import cpw.mods.fml.common.Loader;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.stegr.playerinterfacemod.reference.ModIDs;

public class BlockHelper {

    public static boolean isBlockPipe(TileEntity tileEntity) {
        if(!Loader.isModLoaded(ModIDs.BUILDCRAFT))
            return false;

        return (tileEntity instanceof IPipe);
    }
}
