package net.beholderface.hexiron.forge;

import dev.architectury.platform.forge.EventBuses;
import net.beholderface.hexiron.Hexiron;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

/**
 * This is your loading entrypoint on forge, in case you need to initialize
 * something platform-specific.
 */
@Mod(Hexiron.MOD_ID)
public class HexironForge {
    public HexironForge() {
        // Submit our event bus to let architectury register our content on the right time
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        EventBuses.registerModEventBus(Hexiron.MOD_ID, bus);
        bus.addListener(HexironClientForge::init);
        Hexiron.init();
    }
}
