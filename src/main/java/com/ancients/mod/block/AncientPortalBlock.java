package com.ancients.mod.block;

import com.ancients.mod.registry.ModDimensions;
import com.ancients.mod.registry.ModItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class AncientPortalBlock extends Block {

    private boolean isActive = false;

    public AncientPortalBlock(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                  Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getItemInHand(hand);

        // Only Ancient Igniter can activate this portal
        if (heldItem.is(ModItems.ANCIENT_IGNITER.get())) {
            if (!isActive) {
                isActive = true;
                level.playSound(null, pos, net.minecraft.sounds.SoundEvents.PORTAL_AMBIENT,
                        net.minecraft.sounds.SoundSource.BLOCKS, 1.0F, 1.0F);
                // Consume durability
                heldItem.hurtAndBreak(1, player, p -> p.broadcastBreakEvent(hand));
                player.displayClientMessage(
                        Component.literal("The ancient portal hums with forgotten power...")
                                .withStyle(ChatFormatting.DARK_AQUA, ChatFormatting.ITALIC), true);
                return InteractionResult.SUCCESS;
            }
        } else if (!heldItem.isEmpty()) {
            if (!isActive) {
                player.displayClientMessage(
                        Component.literal("This portal cannot be opened with ordinary tools...")
                                .withStyle(ChatFormatting.DARK_RED, ChatFormatting.ITALIC), true);
                return InteractionResult.FAIL;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (!level.isClientSide() && isActive && entity instanceof ServerPlayer player) {
            ServerLevel ancientsDim = player.getServer().getLevel(ModDimensions.ANCIENTS_DIMENSION_KEY);
            if (ancientsDim != null) {
                player.sendSystemMessage(
                        Component.literal("✦ Welcome to the Ancients Dimension ✦")
                                .withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
                player.changeDimension(ancientsDim, new AncientsTeleporter(pos));
            }
        }
    }

    // Simple teleporter to place player at spawn point in Ancients dimension
    private static class AncientsTeleporter implements ITeleporter {
        private final BlockPos fromPos;

        public AncientsTeleporter(BlockPos fromPos) {
            this.fromPos = fromPos;
        }

        @Override
        public Entity placeEntity(Entity entity, ServerLevel currentLevel, ServerLevel destLevel,
                                   float yaw, Function<Boolean, Entity> repositionEntity) {
            entity = repositionEntity.apply(false);
            BlockPos targetPos = new BlockPos(0, 70, 0);
            entity.teleportTo(targetPos.getX(), targetPos.getY(), targetPos.getZ());
            return entity;
        }
    }
}
