package me.pixfumy.perfect_ssg.mixin.worldgen;

import me.pixfumy.perfect_ssg.Pingable;
import me.pixfumy.perfect_ssg.mixin.access.InGameHudAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.DebugHud;
import net.minecraft.world.gen.feature.FillerBlockFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.Random;

@Mixin(FillerBlockFeature.class)
public class ShorterEndCrystalsMixin {
    @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I", ordinal = 0))
    private int shorterEndCrystal(Random instance, int bound) {
        return instance.nextInt(16);
    }
}
