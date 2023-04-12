package me.pixfumy.perfect_ssg.mixin.hostile_mob;

import me.pixfumy.perfect_ssg.PerfectSSG;
import net.minecraft.block.entity.SpawnerBlockEntityBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SpawnerBlockEntityBehavior.class)
public abstract class SpawnOneSilverfishOrBlazeMixin {
    @Shadow private String entityId;

    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/block/entity/SpawnerBlockEntityBehavior;spawnCount:I"))
    private int spawnCountTo1(SpawnerBlockEntityBehavior instance) {
        String entityId = this.entityId;
        if (entityId.equals("Silverfish") || entityId.equals("Blaze")) {
            PerfectSSG.LOGGER.info("[Perfect SSG] Spawning singular mob from spawner.");
            return 1;
        }
        return 4; // this shouldn't be hardcoded but who cares
    }
}
