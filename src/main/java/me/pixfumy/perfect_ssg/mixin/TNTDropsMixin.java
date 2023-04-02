package me.pixfumy.perfect_ssg.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.TntEntity;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(Explosion.class)
public abstract class TNTDropsMixin {
    @Shadow @Final private Entity causingEntity;

    /**
     * Adapted from tildejustin/tnt-drop-all
     */
    @ModifyArg(method = "affectWorld", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;randomDropAsItem(Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;FI)V"))
    private float changeTo1(float f) {
        if (this.causingEntity instanceof TntEntity) {
            return 0.4F;
        }
        return f;
    }
}
