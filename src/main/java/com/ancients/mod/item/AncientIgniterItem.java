package com.ancients.mod.item;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;

public class AncientIgniterItem extends Item {

    public AncientIgniterItem(Properties properties) {
        super(properties.durability(64));
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        BlockState state = level.getBlockState(pos);

        // Light up clicked face with ancient fire particle
        level.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);

        if (!level.isClientSide()) {
            BlockPos above = pos.relative(context.getClickedFace());
            if (level.isEmptyBlock(above)) {
                level.setBlockAndUpdate(above, Blocks.FIRE.defaultBlockState());
            }
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(),
                    (player) -> player.broadcastBreakEvent(context.getHand()));
        }
        return InteractionResult.sidedSuccess(level.isClientSide());
    }
}
