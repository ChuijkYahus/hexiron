package net.beholderface.hexiron.fabric;

import net.fabricmc.loader.api.FabricLoader;
import net.beholderface.hexiron.HexironAbstractions;

import java.nio.file.Path;

public class HexironAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexironAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return null;
    }
	
    public static void initPlatformSpecific() {
    }
}
