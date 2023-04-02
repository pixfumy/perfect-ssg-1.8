package me.pixfumy.perfect_ssg.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonPart;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(EnderDragonEntity.class)
public abstract class EnderDragonEntityMixin extends Entity {
    @Shadow public double field_3742;

    @Shadow public double field_3751;

    @Shadow public double field_3752;

    @Shadow private Entity target;

    private int chargeCoolDown = 300;

    public EnderDragonEntityMixin(World world) {
        super(world);
    }

    @Inject(method = "tickMovement", at = @At("HEAD"))
    private void decrementChargeCooldown(CallbackInfo ci) {
        this.chargeCoolDown--;
    }

    @Inject(method = "setAngry", at = @At("HEAD"))
    private void setChargeCoolDown(EnderDragonPart source, DamageSource angry, float par3, CallbackInfoReturnable<Boolean> cir) {
        this.chargeCoolDown = 120;
    }

    @Redirect(method = "method_2906", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0))
    private int betterChargeChance(Random instance, int bound) {
        return this.chargeCoolDown > 0 ? 1 : Math.round(instance.nextFloat() - 0.25f);
    }

    /**
     * Fixes the bug where the dragon calculates its target distance based on its old target, resulting in
     * it needing to roll a player as a target twice in a row (halfing the chance of a charge).
     */
    @Inject(method = "method_2906", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/boss/dragon/EnderDragonEntity;target:Lnet/minecraft/entity/Entity;", ordinal = 0, shift = At.Shift.AFTER))
    private void dontDoubleCharge(CallbackInfo ci) {
        this.field_3742 = this.target.x;
        this.field_3752 = this.target.z;
        double d7 = this.field_3742 - this.x;
        double d8 = this.field_3752 - this.z;
        double d9 = Math.sqrt(d7 * d7 + d8 * d8);
        double d5 = (double)0.4f + d9 / 80.0 - 1.0;
        if (d5 > 10.0) {
            d5 = 10.0;
        }
        this.field_3751 = this.target.getBoundingBox().minY + d5;
    }
}
