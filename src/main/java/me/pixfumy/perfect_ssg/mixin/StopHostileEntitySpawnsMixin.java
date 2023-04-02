package me.pixfumy.perfect_ssg.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HostileEntity.class)
public abstract class StopHostileEntitySpawnsMixin extends Entity {
    public StopHostileEntitySpawnsMixin(World world) {
        super(world);
    }

    @Inject(method = "method_3087", at = @At("HEAD"), cancellable = true)
    private void cancelSpawnsInOverworld(CallbackInfoReturnable<Boolean> cir) {
        if (this.world.dimension.getType() == 0) {
            cir.setReturnValue(false);
        }
    }
}
