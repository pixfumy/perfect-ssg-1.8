package me.pixfumy.perfect_ssg.mixin.villager;

import me.pixfumy.perfect_ssg.IVillager;
import net.minecraft.class_2669;
import net.minecraft.entity.PathAwareEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.GoalSelector;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.village.TradeOffer;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends PathAwareEntity implements IVillager {
    private boolean traded = false;

    public VillagerEntityMixin(World world) {
        super(world);
    }

    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/class_2669", ordinal = 47))
    private static class_2669 setGoldTradeTo8(int j, int i) {
        return new class_2669(j, j);
    }

    @Redirect(method = "<clinit>", at = @At(value = "NEW", target = "net/minecraft/class_2669", ordinal = 50))
    private static class_2669 setEyeTradeTo7Emeralds(int j, int i) {
        return new class_2669(j, j);
    }

    @ModifyArg(method = "<init>(Lnet/minecraft/world/World;I)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/ai/goal/GoalSelector;add(ILnet/minecraft/entity/ai/goal/Goal;)V", ordinal = 12))
    private int setRoutingPriority(int priority) {
        return -1;
    }

    @Inject(method = "getOffers()V", at = @At("HEAD"))
    private void setTraded(CallbackInfo ci) {
        this.traded = true;
        this.navigation.stop();
    }

    @Override
    public boolean hasTraded() {
        return traded;
    }
}
