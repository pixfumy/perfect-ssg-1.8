package me.pixfumy.perfect_ssg.mixin.access;

import net.minecraft.block.entity.SpawnerBlockEntityBehavior;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SpawnerBlockEntityBehavior.class)
public interface SpawnerBlockEntityBehaviorAccess {
    @Accessor
    String getEntityId();
}
