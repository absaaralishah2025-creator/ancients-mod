package com.ancients.mod.event;

import com.ancients.mod.AncientsMod;
import com.ancients.mod.registry.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashSet;
import java.util.Set;

@Mod.EventBusSubscriber(modid = AncientsMod.MOD_ID)
public class PortalActivationHandler {

    // Track which portal frames have been activated
    private static final Set<BlockPos> activatedPortals = new HashSet<>();

    @SubscribeEvent
    public static void onPlayerRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        Player player = event.getEntity();
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        InteractionHand hand = event.getHand();

        ItemStack stack = player.getItemInHand(hand);

        // Check if player is using Ancient Igniter on the portal frame in Ancient City
        if (stack.is(ModItems.ANCIENT_IGNITER.get())) {
            // Check if we are near a reinforced deepslate frame (Ancient City portal area)
            if (isAncientCityPortalFrame(level, pos)) {
                if (!activatedPortals.contains(pos)) {
                    activatedPortals.add(pos);
                    if (!level.isClientSide()) {
                        player.displayClientMessage(
                                Component.literal("✦ The Ancient Portal awakens... ✦")
                                        .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD), false);
                        player.displayClientMessage(
                                Component.literal("Step through to enter the Ancients Dimension")
                                        .withStyle(ChatFormatting.DARK_AQUA, ChatFormatting.ITALIC), false);
                        // Consume one durability from Ancient Igniter
                        stack.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
                    }
                    event.setCanceled(true);
                }
            } else if (!level.isClientSide()) {
                player.displayClientMessage(
                        Component.literal("This does not feel like the right place...")
                                .withStyle(ChatFormatting.DARK_RED, ChatFormatting.ITALIC), true);
            }
        }
    }

    private static boolean isAncientCityPortalFrame(Level level, BlockPos center) {
        // Check if there is reinforced deepslate nearby (indicates Ancient City portal area)
        for (BlockPos nearby : BlockPos.betweenClosed(
                center.offset(-3, -3, -3),
                center.offset(3, 3, 3))) {
            if (level.getBlockState(nearby).is(Blocks.REINFORCED_DEEPSLATE)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPortalActivated(BlockPos pos) {
        return activatedPortals.contains(pos);
    }
}
