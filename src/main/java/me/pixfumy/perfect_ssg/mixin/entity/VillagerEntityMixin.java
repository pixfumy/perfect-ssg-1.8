package me.pixfumy.perfect_ssg.mixin.entity;

import net.minecraft.class_2669;
import net.minecraft.entity.passive.VillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/class_2669", ordinal = 50))
    private static class_2669 setEyeTradeTo7(int j, int i) {
        return new class_2669(j, j);
    }
}
