package net.beholderface.hexiron.forge;

import net.beholderface.hexiron.HexironAbstractions;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class HexironAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexironAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
	
    public static void initPlatformSpecific() {
        HexironConfigForge.init();
    }
}
