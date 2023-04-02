package me.pixfumy.perfect_ssg.mixin.loot.tnt;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PressurePlateBlock;
import net.minecraft.block.material.Material;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PressurePlateBlock.class)
public abstract class TNTDropsPressurePlateMixin extends Block {
    protected TNTDropsPressurePlateMixin(Material material) {
        super(material);
    }

    @Override
    public void randomDropAsItem(World world, BlockPos pos, BlockState state, float chance, int id) {
        if (world.isClient) {
            return;
        }
        Block.onBlockBreak(world, pos, new ItemStack(this.getDropItem(state, world.random, id), 1, this.getMeta(state)));
    }
}
