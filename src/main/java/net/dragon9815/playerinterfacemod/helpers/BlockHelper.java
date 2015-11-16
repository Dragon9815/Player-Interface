package net.dragon9815.playerinterfacemod.helpers;

import buildcraft.api.transport.IPipeTile;
import cofh.api.energy.IEnergyProvider;
import cpw.mods.fml.common.Loader;
import net.dragon9815.playerinterfacemod.reference.ModIDs;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class BlockHelper {

    public static boolean isBlockPipe(TileEntity tileEntity, ForgeDirection direction) {
        if (Loader.isModLoaded(ModIDs.BUILDCRAFT)) {
            if (tileEntity instanceof IPipeTile) {
                if (((IPipeTile) tileEntity).isPipeConnected(direction.getOpposite())) {
                    return true;
                }
            }
        }

        if (Loader.isModLoaded(ModIDs.THERMAL_EXPANSION) || Loader.isModLoaded(ModIDs.THERMAL_DYNAMICS)) {

            if (tileEntity instanceof IEnergyProvider) {
                if (((IEnergyProvider) tileEntity).canConnectEnergy(direction.getOpposite())) {
                    return true;
                }
            }
        }

        return false;
    }
}
