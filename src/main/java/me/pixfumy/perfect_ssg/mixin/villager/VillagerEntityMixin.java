package me.pixfumy.perfect_ssg.mixin.villager;

import net.minecraft.class_2669;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.passive.VillagerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(VillagerEntity.class)
public class VillagerEntityMixin {
    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/class_2669", ordinal = 50))
    private static class_2669 setEyeTradeTo7Emeralds(int j, int i) {
        return new class_2669(j, j);
    }

    @ModifyArg(method = "<init>(Lnet/minecraft/world/World;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 12))
    private int setRoutingPriority(int priority) {
        return -1;
    }
}
