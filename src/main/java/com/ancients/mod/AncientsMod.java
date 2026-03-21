package com.ancients.mod;

import com.ancients.mod.registry.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(AncientsMod.MOD_ID)
public class AncientsMod {
    public static final String MOD_ID = "ancientsmod";
    public static final Logger LOGGER = LogManager.getLogger();

    public AncientsMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModEntities.register(modEventBus);
        ModDimensions.register(modEventBus);
        ModBiomes.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
