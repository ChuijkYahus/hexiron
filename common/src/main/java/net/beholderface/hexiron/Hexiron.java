package net.beholderface.hexiron;

import net.beholderface.hexiron.registry.HexironIotaTypeRegistry;
import net.beholderface.hexiron.registry.HexironItemRegistry;
import net.beholderface.hexiron.registry.HexironPatternRegistry;
import net.beholderface.hexiron.networking.HexironNetworking;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is effectively the loading entrypoint for most of your code, at least
 * if you are using Architectury as intended.
 */
public class Hexiron {
    public static final String MOD_ID = "hexiron";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);


    public static void init() {
        LOGGER.info("Hexiron says hello!");

        HexironAbstractions.initPlatformSpecific();
        HexironItemRegistry.init();
        HexironIotaTypeRegistry.init();
        HexironPatternRegistry.init();
		HexironNetworking.init();

        LOGGER.info(HexironAbstractions.getConfigDirectory().toAbsolutePath().normalize().toString());
    }

    /**
     * Shortcut for identifiers specific to this mod.
     */
    public static Identifier id(String string) {
        return new Identifier(MOD_ID, string);
    }
}
