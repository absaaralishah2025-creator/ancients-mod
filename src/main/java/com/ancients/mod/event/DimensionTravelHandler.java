package com.ancients.mod.event;

import com.ancients.mod.AncientsMod;
import com.ancients.mod.registry.ModDimensions;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.EntityTravelToDimensionEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = AncientsMod.MOD_ID)
public class DimensionTravelHandler {

    @SubscribeEvent
    public static void onEntityTravelToDimension(EntityTravelToDimensionEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            if (event.getDimension().equals(ModDimensions.ANCIENTS_DIMENSION_KEY)) {
                // Show welcome message when entering the Ancients Dimension
                player.sendSystemMessage(
                        Component.literal("").append(
                                Component.literal("★ ═══════════════════════ ★")
                                        .withStyle(ChatFormatting.DARK_AQUA)));
                player.sendSystemMessage(
                        Component.literal("   Welcome to the Ancients Dimension   ")
                                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
                player.sendSystemMessage(
                        Component.literal("  Where the old world still breathes...  ")
                                .withStyle(ChatFormatting.DARK_GRAY, ChatFormatting.ITALIC));
                player.sendSystemMessage(
                        Component.literal("★ ═══════════════════════ ★")
                                .withStyle(ChatFormatting.DARK_AQUA));
            }
        }
    }
}
