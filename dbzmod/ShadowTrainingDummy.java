package com.dragonballz.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.damagesource.DamageSource;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.builder.AnimationController;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.builder.RawAnimation;

public class ShadowTrainingDummy extends Mob implements IAnimatable {
    private final AnimationFactory factory = new AnimationFactory(this);
    private Player owner;

    public ShadowTrainingDummy(EntityType<? extends ShadowTrainingDummy> type, Level world) {
        super(type, world);
    }

    // AI: Follows, attacks, and flies to the owner
    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2, true));
        this.goalSelector.addGoal(2, new FollowOwnerGoal(this, 1.1, 2.0f, 10.0f));
        this.goalSelector.addGoal(3, new DummyFlyGoal(this, 1.1));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0f));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    @Override
    public boolean isNoGravity() {
        return true; // Always flying
    }

    @Override
    public void tick() {
        super.tick();
        // AI: If owner is in the air, dummy flies too
        if (owner != null && !owner.isOnGround()) {
            this.setDeltaMovement(new Vec3(0, 0.3, 0));
        }
    }

    // Geckolib setup
    @Override
    public AnimationFactory getFactory() {
        return factory;
    }

    @Override
    public <E extends IAnimatable> void registerControllers(software.bernie.geckolib3.core.manager.AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "move", 5, this::predicate));
    }

    private <E extends IAnimatable> software.bernie.geckolib3.core.event.predicate.PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(RawAnimation.begin().then("animation.shadowdummy.walk", ILoopType.EDefaultLoopTypes.LOOP));
        } else {
            event.getController().setAnimation(RawAnimation.begin().then("animation.shadowdummy.idle", ILoopType.EDefaultLoopTypes.LOOP));
        }
        return software.bernie.geckolib3.core.event.predicate.PlayState.CONTINUE;
    }
}

// Example of a fly goal for the dummy
class DummyFlyGoal extends Goal {
    private final ShadowTrainingDummy dummy;
    private final double speed;

    public DummyFlyGoal(ShadowTrainingDummy dummy, double speed) {
        this.dummy = dummy;
        this.speed = speed;
    }

    @Override
    public boolean canUse() {
        return dummy.owner != null && dummy.distanceTo(dummy.owner) > 4.0F;
    }

    @Override
    public void tick() {
        if (dummy.owner != null) {
            dummy.getMoveControl().setWantedPosition(
                    dummy.owner.getX(), dummy.owner.getY() + 1.5, dummy.owner.getZ(), speed);
        }
    }
}

// Goal for following owner (simplified)
class FollowOwnerGoal extends Goal {
    private final ShadowTrainingDummy dummy;
    private final double speed;
    private final float minDist;
    private final float maxDist;

    public FollowOwnerGoal(ShadowTrainingDummy dummy, double speed, float minDist, float maxDist) {
        this.dummy = dummy;
        this.speed = speed;
        this.minDist = minDist;
        this.maxDist = maxDist;
    }

    @Override
    public boolean canUse() {
        return dummy.owner != null && dummy.distanceTo(dummy.owner) > minDist;
    }

    @Override
    public void tick() {
        if (dummy.owner != null) {
            dummy.getNavigation().moveTo(dummy.owner, speed);
        }
    }
}