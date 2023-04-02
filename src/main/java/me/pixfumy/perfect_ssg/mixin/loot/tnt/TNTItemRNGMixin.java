package me.pixfumy.perfect_ssg.mixin.loot.tnt;

import me.pixfumy.perfect_ssg.Pingable;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(DebugHud.class)
public class TNTItemRNGMixin implements Pingable {
    @Redirect(method = "getRightText", at = @At(value = "INVOKE", target = "Ljava/lang/String;format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;", ordinal = 1))
    private String dropItemTowardsPlayer(String str, Object[] args) {
        return String.format("_" + str, args);
    }

    @Override
    public void notifyListeners() {

    }
}
