package net.beholderface.hexiron.forge;

import dev.architectury.event.events.common.CommandRegistrationEvent;
import dev.architectury.event.events.common.LifecycleEvent;
import dev.architectury.platform.forge.EventBuses;
import net.beholderface.hexiron.Hexiron;
import net.beholderface.hexiron.HexironConfig;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import software.bernie.shadowed.eliotlash.mclib.math.functions.limit.Min;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static net.minecraft.server.command.CommandManager.literal;

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
        initConfig();
    }
    private static void initConfig(){
        Pair<ForgeHexironConfig, ForgeConfigSpec> config = (new ForgeConfigSpec.Builder()).configure(ForgeHexironConfig::new);
        Pair<ForgeHexironConfig.Client, ForgeConfigSpec> clientConfig = (new ForgeConfigSpec.Builder()).configure(ForgeHexironConfig.Client::new);
        Pair<ForgeHexironConfig.Server, ForgeConfigSpec> serverConfig = (new ForgeConfigSpec.Builder()).configure(ForgeHexironConfig.Server::new);
        HexironConfig.setCommon(config.getLeft());
        HexironConfig.setClient(clientConfig.getLeft());
        HexironConfig.setServer(serverConfig.getLeft());
        ModLoadingContext mlc = ModLoadingContext.get();
        mlc.registerConfig(ModConfig.Type.COMMON, config.getRight());
        mlc.registerConfig(ModConfig.Type.CLIENT, clientConfig.getRight());
        mlc.registerConfig(ModConfig.Type.SERVER, serverConfig.getRight());
    }
}
