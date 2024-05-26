package net.beholderface.hexiron.forge;

import net.beholderface.hexiron.HexironClient;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Forge client loading entrypoint.
 */
public class HexironClientForge {
    public static void init(FMLClientSetupEvent event) {
        HexironClient.init();
    }
}
