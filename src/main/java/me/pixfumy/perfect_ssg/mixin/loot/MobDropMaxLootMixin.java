package me.pixfumy.perfect_ssg.mixin.loot;

import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.passive.SheepEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.Random;

@Mixin({EndermanEntity.class, BlazeEntity.class, SheepEntity.class})
public class MobDropMaxLootMixin {
    @Redirect(method = "dropLoot", at = @At(value = "INVOKE", target = "Ljava/util/Random;nextInt(I)I"))
    private int dropMaxLoot(Random instance, int bound) {
        System.out.println("PerfectSSG mod dropping max mob loot.");
        return bound - 1;
    }
}
