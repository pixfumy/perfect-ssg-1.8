package me.pixfumy.perfect_ssg.mixin.worldgen;

import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerWorld.class)
public class WaterFreezeMixin {
    @Redirect(method = "tickBlocks", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;)Z", ordinal = 0))
    private boolean cancelTurnToIce(ServerWorld instance, BlockPos blockPos, BlockState blockState) {
        return false;
    }
}
