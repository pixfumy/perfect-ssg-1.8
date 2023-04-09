package me.pixfumy.perfect_ssg.mixin.loot;

import net.minecraft.block.BlockState;
import net.minecraft.block.FallingBlock;
import net.minecraft.block.GravelBlock;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Random;

@Mixin(GravelBlock.class)
public class GuaranteedFlintMixin extends FallingBlock {
    @Override
    public Item getDropItem(BlockState state, Random random, int id) {
        return Items.FLINT;
    }
}
