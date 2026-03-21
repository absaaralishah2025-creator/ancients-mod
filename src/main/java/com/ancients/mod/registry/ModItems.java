package com.ancients.mod.registry;

import com.ancients.mod.AncientsMod;
import com.ancients.mod.item.AncientIgniterItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AncientsMod.MOD_ID);

    // Ancient Igniter - the special flint and steel
    public static final RegistryObject<Item> ANCIENT_IGNITER = ITEMS.register("ancient_igniter",
            () -> new AncientIgniterItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
