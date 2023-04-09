package me.pixfumy.perfect_ssg.mixin.villager;

import me.pixfumy.perfect_ssg.IVillager;
import net.minecraft.entity.PathAwareEntity;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.util.RandomVectorGenerator;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(WanderAroundGoal.class)
public abstract class VillagerRoutingMixin {
    @Shadow private PathAwareEntity mob;

    @Shadow private boolean ignoringChance;

    private Vec3d optimalTarget;
    private int waitTicks = 800 + (new Random()).nextInt(100);

    @Inject(method = "canStart", at = @At(value = "HEAD"), cancellable = true)
    private void setOptimalTarget(CallbackInfoReturnable<Boolean> cir) {
        if (this.mob instanceof VillagerEntity) {
            if (((VillagerEntity) this.mob).profession() == 2 && this.mob.ticksAlive > waitTicks && !((IVillager) this.mob).hasTraded()) {
                if (this.optimalTarget == null) {
                    Random random = new Random();
                    optimalTarget = new Vec3d(-254 + random.nextInt(4), this.mob.y, -398 + random.nextInt(4));
                }
                if (this.mob.distanceTo(optimalTarget.x, optimalTarget.y, optimalTarget.z) > 1) {
                    this.ignoringChance = true; // makes it so canStart does not return false early
                }
            } else if (((VillagerEntity)this.mob).profession() == 1) {
                cir.setReturnValue(false);
            }
        }
    }

    @Redirect(method = "canStart", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/RandomVectorGenerator;method_2799(Lnet/minecraft/entity/PathAwareEntity;II)Lnet/minecraft/util/math/Vec3d;"))
    private Vec3d routeToOptimalTarget(PathAwareEntity mob, int i, int j) {
        if (this.mob instanceof VillagerEntity && ((VillagerEntity)this.mob).profession() == 2 && this.mob.ticksAlive > waitTicks && !((IVillager)this.mob).hasTraded()) {
            return this.optimalTarget;
        }
        return RandomVectorGenerator.method_2799(mob, i, j);
    }
}
