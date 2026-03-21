package com.ancients.mod.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

import java.util.Random;

/**
 * Void Creeper - teleports before exploding, combination of Creeper + Enderman
 * Unique to Ancients Dimension
 */
public class VoidCreeperEntity extends Monster {

    private int fuseTimer = 0;
    private boolean isLit = false;
    private static final int FUSE_MAX = 40;

    public VoidCreeperEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.2, false));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 25.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.35D)
                .add(Attributes.ATTACK_DAMAGE, 6.0D)
                .add(Attributes.FOLLOW_RANGE, 18.0D);
    }

    @Override
    public void tick() {
        super.tick();

        if (!level().isClientSide()) {
            // If close to a player, start fuse and teleport randomly first
            Player nearest = level().getNearestPlayer(this, 4.0);
            if (nearest != null && !isLit) {
                isLit = true;
                // Teleport randomly before exploding (Enderman trait)
                teleportRandomly();
            }

            if (isLit) {
                fuseTimer++;
                if (fuseTimer >= FUSE_MAX) {
                    level().explode(this, this.getX(), this.getY(), this.getZ(),
                            4.0F, Level.ExplosionInteraction.MOB);
                    this.discard();
                }
            }
        }
    }

    private void teleportRandomly() {
        Random rand = new Random();
        double x = this.getX() + (rand.nextDouble() - 0.5) * 8;
        double z = this.getZ() + (rand.nextDouble() - 0.5) * 8;
        double y = this.getY();
        this.teleportTo(x, y, z);
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }
}
