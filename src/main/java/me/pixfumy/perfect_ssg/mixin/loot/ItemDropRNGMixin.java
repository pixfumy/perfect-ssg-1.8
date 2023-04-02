package me.pixfumy.perfect_ssg.mixin.loot;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.predicate.EntityPredicate;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Comparator;
import java.util.List;

@Mixin(ItemEntity.class)
public abstract class ItemDropRNGMixin extends Entity {
    public ItemDropRNGMixin(World world) {
        super(world);
    }

    @Inject(method = "<init>(Lnet/minecraft/world/World;DDDLnet/minecraft/item/ItemStack;)V", at = @At("TAIL"))
    private void setXandZVelocity(World world, double x, double y, double z, ItemStack itemStack, CallbackInfo ci) {
        List<PlayerEntity> players = world.getEntitiesInBox(PlayerEntity.class, this.getBoundingBox().expand(4, 4, 4), EntityPredicate.EXCEPT_SPECTATOR);
        if (players.isEmpty()) {
            return;
        }
        players.sort(Comparator.comparingDouble(player -> this.distanceTo(player)));
        PlayerEntity closestPlayer = players.get(0);
        this.velocityX = MathHelper.clamp(closestPlayer.x - this.x, -0.1, 0.1);
        this.velocityZ = MathHelper.clamp(closestPlayer.z - this.z, -0.1, 0.1);
    }
}
