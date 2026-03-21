package com.ancients.mod.registry;

import com.ancients.mod.AncientsMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraftforge.eventbus.api.IEventBus;

public class ModDimensions {

    // The key for our Ancients Dimension
    public static final ResourceKey<Level> ANCIENTS_DIMENSION_KEY =
            ResourceKey.create(Registries.DIMENSION,
                    new ResourceLocation(AncientsMod.MOD_ID, "ancients_dimension"));

    public static void register(IEventBus eventBus) {
        // Dimension is registered via JSON data files
    }
}
