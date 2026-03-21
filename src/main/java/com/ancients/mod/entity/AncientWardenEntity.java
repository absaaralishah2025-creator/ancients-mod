package com.ancients.mod.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

/**
 * Ancient Warden - peaceful unless attacked
 * Lives in Warden Villages in the Ancients Dimension
 */
public class AncientWardenEntity extends Monster {

    private boolean wasHurt = false;

    public AncientWardenEntity(EntityType<? extends Monster> type, Level level) {
        super(type, level);
        this.setMaxUpStep(1.0F);
    }

    @Override
    protected void registerGoals() {
        // Movement goals
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.8));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));

        // Only attacks if hurt - HurtByTargetGoal handles this
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 500.0D)         // Very tanky like a Warden
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.ATTACK_DAMAGE, 30.0D)        // Hits hard if provoked
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
                .add(Attributes.ARMOR, 10.0D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    // Override to make it passive unless it was hurt
    @Override
    public boolean isAggressive() {
        return wasHurt || super.isAggressive();
    }

    @Override
    protected void actuallyHurt(net.minecraft.world.damagesource.DamageSource source, float amount) {
        super.actuallyHurt(source, amount);
        wasHurt = true; // Once hurt, it retaliates
    }

    // Does not naturally target players — only retaliates
    @Override
    protected boolean shouldDespawnInPeaceful() {
        return false; // Always stay in the dimension
    }
}
