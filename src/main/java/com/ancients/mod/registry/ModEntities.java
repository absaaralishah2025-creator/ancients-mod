package com.ancients.mod.registry;

import com.ancients.mod.AncientsMod;
import com.ancients.mod.entity.AncientWardenEntity;
import com.ancients.mod.entity.SoulPhantomEntity;
import com.ancients.mod.entity.VoidCreeperEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = AncientsMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, AncientsMod.MOD_ID);

    // Ancient Warden - peaceful guardian mob
    public static final RegistryObject<EntityType<AncientWardenEntity>> ANCIENT_WARDEN =
            ENTITIES.register("ancient_warden",
                    () -> EntityType.Builder.<AncientWardenEntity>of(AncientWardenEntity::new, MobCategory.MONSTER)
                            .sized(0.9F, 2.9F)
                            .clientTrackingRange(16)
                            .build(new ResourceLocation(AncientsMod.MOD_ID, "ancient_warden").toString()));

    // Soul Phantom - flying hostile mob
    public static final RegistryObject<EntityType<SoulPhantomEntity>> SOUL_PHANTOM =
            ENTITIES.register("soul_phantom",
                    () -> EntityType.Builder.<SoulPhantomEntity>of(SoulPhantomEntity::new, MobCategory.MONSTER)
                            .sized(2.0F, 0.5F)
                            .clientTrackingRange(16)
                            .build(new ResourceLocation(AncientsMod.MOD_ID, "soul_phantom").toString()));

    // Void Creeper - teleporting exploding mob
    public static final RegistryObject<EntityType<VoidCreeperEntity>> VOID_CREEPER =
            ENTITIES.register("void_creeper",
                    () -> EntityType.Builder.<VoidCreeperEntity>of(VoidCreeperEntity::new, MobCategory.MONSTER)
                            .sized(0.6F, 1.7F)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(AncientsMod.MOD_ID, "void_creeper").toString()));

    @SubscribeEvent
    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
        event.put(ANCIENT_WARDEN.get(), AncientWardenEntity.createAttributes().build());
        event.put(SOUL_PHANTOM.get(), SoulPhantomEntity.createAttributes().build());
        event.put(VOID_CREEPER.get(), VoidCreeperEntity.createAttributes().build());
    }

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
