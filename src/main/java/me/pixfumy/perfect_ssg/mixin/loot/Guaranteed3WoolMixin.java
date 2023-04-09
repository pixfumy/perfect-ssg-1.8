package me.pixfumy.perfect_ssg.mixin.loot;

import net.minecraft.entity.passive.SheepEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;

@Mixin(SheepEntity.class)
public class Guaranteed3WoolMixin {
    @Redirect(method = "method_2537", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
    private int drop3Wool(Random instance, int bound) {
        return bound - 1;
    }
}
