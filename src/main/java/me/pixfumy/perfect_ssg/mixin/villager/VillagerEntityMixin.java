package me.pixfumy.perfect_ssg.mixin.villager;

import me.pixfumy.perfect_ssg.IVillager;
import net.minecraft.block.Blocks;
import net.minecraft.entity.PathAwareEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DyeColor;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VillagerEntity.class)
public abstract class VillagerEntityMixin extends PathAwareEntity implements IVillager {
    private boolean traded = false;

    @Shadow
    @Final
    @Mutable
    private static final VillagerEntity.TradeProvider[][][][] TRADES;

    // I hate everything and everyone and I hate myself for needing to do this but for some reason mixins to static inner class constructors do not work
    static {
        TRADES = new VillagerEntity.TradeProvider[][][][]{{{{new VillagerEntity.ItemTradeEntry(Items.WHEAT, new VillagerEntity.Cost(18, 22)), new VillagerEntity.ItemTradeEntry(Items.POTATO, new VillagerEntity.Cost(15, 19)), new VillagerEntity.ItemTradeEntry(Items.CARROT, new VillagerEntity.Cost(15, 19)), new VillagerEntity.ItemStackTradeEntry(Items.BREAD, new VillagerEntity.Cost(-4, -2))}, {new VillagerEntity.ItemTradeEntry(Item.fromBlock(Blocks.PUMPKIN), new VillagerEntity.Cost(8, 13)), new VillagerEntity.ItemStackTradeEntry(Items.PUMPKIN_PIE, new VillagerEntity.Cost(-3, -2))}, {new VillagerEntity.ItemTradeEntry(Item.fromBlock(Blocks.MELON_BLOCK), new VillagerEntity.Cost(7, 12)), new VillagerEntity.ItemStackTradeEntry(Items.APPLE, new VillagerEntity.Cost(-5, -7))}, {new VillagerEntity.ItemStackTradeEntry(Items.COOKIE, new VillagerEntity.Cost(-6, -10)), new VillagerEntity.ItemStackTradeEntry(Items.CAKE, new VillagerEntity.Cost(1, 1))}}, {{new VillagerEntity.ItemTradeEntry(Items.STRING, new VillagerEntity.Cost(15, 20)), new VillagerEntity.ItemTradeEntry(Items.COAL, new VillagerEntity.Cost(16, 24)), new VillagerEntity.EmeraldToItem(Items.RAW_FISH, new VillagerEntity.Cost(6, 6), Items.COOKED_FISH, new VillagerEntity.Cost(6, 6))}, {new VillagerEntity.EnchantedItemStackTradeEntry(Items.FISHING_ROD, new VillagerEntity.Cost(7, 8))}}, {{new VillagerEntity.ItemTradeEntry(Item.fromBlock(Blocks.WOOL), new VillagerEntity.Cost(16, 22)), new VillagerEntity.ItemStackTradeEntry(Items.SHEARS, new VillagerEntity.Cost(3, 4))}, {new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 0), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 1), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 2), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 3), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 4), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 5), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 6), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 7), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 8), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 9), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 10), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 11), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 12), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 13), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 14), new VillagerEntity.Cost(1, 2)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Item.fromBlock(Blocks.WOOL), 1, 15), new VillagerEntity.Cost(1, 2))}}, {{new VillagerEntity.ItemTradeEntry(Items.STRING, new VillagerEntity.Cost(15, 20)), new VillagerEntity.ItemStackTradeEntry(Items.ARROW, new VillagerEntity.Cost(-12, -8))}, {new VillagerEntity.ItemStackTradeEntry(Items.BOW, new VillagerEntity.Cost(2, 3)), new VillagerEntity.EmeraldToItem(Item.fromBlock(Blocks.GRAVEL), new VillagerEntity.Cost(10, 10), Items.FLINT, new VillagerEntity.Cost(6, 10))}}}, {{{new VillagerEntity.ItemTradeEntry(Items.PAPER, new VillagerEntity.Cost(24, 24)), new VillagerEntity.EnchantedBook()}, {new VillagerEntity.ItemTradeEntry(Items.BOOK, new VillagerEntity.Cost(8, 8)), new VillagerEntity.ItemStackTradeEntry(Items.COMPASS, new VillagerEntity.Cost(10, 12)), new VillagerEntity.ItemStackTradeEntry(Item.fromBlock(Blocks.BOOKSHELD), new VillagerEntity.Cost(3, 4))}, {new VillagerEntity.ItemTradeEntry(Items.WRITTEN_BOOK, new VillagerEntity.Cost(2, 2)), new VillagerEntity.ItemStackTradeEntry(Items.CLOCK, new VillagerEntity.Cost(10, 12)), new VillagerEntity.ItemStackTradeEntry(Item.fromBlock(Blocks.GLASS), new VillagerEntity.Cost(-5, -3))}, {new VillagerEntity.EnchantedBook()}, {new VillagerEntity.EnchantedBook()}, {new VillagerEntity.ItemStackTradeEntry(Items.NAME_TAG, new VillagerEntity.Cost(20, 22))}}}, {{{new VillagerEntity.ItemTradeEntry(Items.ROTTEN_FLESH, new VillagerEntity.Cost(36, 40)), new VillagerEntity.ItemTradeEntry(Items.GOLD_INGOT, new VillagerEntity.Cost(8, 8))}, {new VillagerEntity.ItemStackTradeEntry(Items.REDSTONE, new VillagerEntity.Cost(-4, -1)), new VillagerEntity.ItemStackTradeEntry(new ItemStack(Items.DYE, 1, DyeColor.BLUE.getSwappedId()), new VillagerEntity.Cost(-2, -1))}, {new VillagerEntity.ItemStackTradeEntry(Items.EYE_OF_ENDER, new VillagerEntity.Cost(7, 7)), new VillagerEntity.ItemStackTradeEntry(Item.fromBlock(Blocks.GLOWSTONE), new VillagerEntity.Cost(-3, -1))}, {new VillagerEntity.ItemStackTradeEntry(Items.EXPERIENCE_BOTTLE, new VillagerEntity.Cost(3, 11))}}}, {{{new VillagerEntity.ItemTradeEntry(Items.COAL, new VillagerEntity.Cost(16, 24)), new VillagerEntity.ItemStackTradeEntry(Items.IRON_HELMET, new VillagerEntity.Cost(4, 6))}, {new VillagerEntity.ItemTradeEntry(Items.IRON_INGOT, new VillagerEntity.Cost(7, 9)), new VillagerEntity.ItemStackTradeEntry(Items.IRON_CHESTPLATE, new VillagerEntity.Cost(10, 14))}, {new VillagerEntity.ItemTradeEntry(Items.DIAMOND, new VillagerEntity.Cost(3, 4)), new VillagerEntity.EnchantedItemStackTradeEntry(Items.DIAMOND_CHESTPLATE, new VillagerEntity.Cost(16, 19))}, {new VillagerEntity.ItemStackTradeEntry(Items.CHAINMAIL_BOOTS, new VillagerEntity.Cost(5, 7)), new VillagerEntity.ItemStackTradeEntry(Items.CHAINMAIL_LEGGINGS, new VillagerEntity.Cost(9, 11)), new VillagerEntity.ItemStackTradeEntry(Items.CHAINMAIL_HELMET, new VillagerEntity.Cost(5, 7)), new VillagerEntity.ItemStackTradeEntry(Items.CHAINMAIL_CHESTPLATE, new VillagerEntity.Cost(11, 15))}}, {{new VillagerEntity.ItemTradeEntry(Items.COAL, new VillagerEntity.Cost(16, 24)), new VillagerEntity.ItemStackTradeEntry(Items.IRON_AXE, new VillagerEntity.Cost(6, 8))}, {new VillagerEntity.ItemTradeEntry(Items.IRON_INGOT, new VillagerEntity.Cost(7, 9)), new VillagerEntity.EnchantedItemStackTradeEntry(Items.IRON_SWORD, new VillagerEntity.Cost(9, 10))}, {new VillagerEntity.ItemTradeEntry(Items.DIAMOND, new VillagerEntity.Cost(3, 4)), new VillagerEntity.EnchantedItemStackTradeEntry(Items.DIAMOND_SWORD, new VillagerEntity.Cost(12, 15)), new VillagerEntity.EnchantedItemStackTradeEntry(Items.DIAMOND_AXE, new VillagerEntity.Cost(9, 12))}}, {{new VillagerEntity.ItemTradeEntry(Items.COAL, new VillagerEntity.Cost(16, 24)), new VillagerEntity.EnchantedItemStackTradeEntry(Items.IRON_SHOVEL, new VillagerEntity.Cost(5, 7))}, {new VillagerEntity.ItemTradeEntry(Items.IRON_INGOT, new VillagerEntity.Cost(7, 9)), new VillagerEntity.EnchantedItemStackTradeEntry(Items.IRON_PICKAXE, new VillagerEntity.Cost(9, 11))}, {new VillagerEntity.ItemTradeEntry(Items.DIAMOND, new VillagerEntity.Cost(3, 4)), new VillagerEntity.EnchantedItemStackTradeEntry(Items.DIAMOND_PICKAXE, new VillagerEntity.Cost(12, 15))}}}, {{{new VillagerEntity.ItemTradeEntry(Items.RAW_PORKCHOP, new VillagerEntity.Cost(14, 18)), new VillagerEntity.ItemTradeEntry(Items.CHICKEN, new VillagerEntity.Cost(14, 18))}, {new VillagerEntity.ItemTradeEntry(Items.COAL, new VillagerEntity.Cost(16, 24)), new VillagerEntity.ItemStackTradeEntry(Items.COOKED_PORKCHOP, new VillagerEntity.Cost(-7, -5)), new VillagerEntity.ItemStackTradeEntry(Items.COOKED_CHICKEN, new VillagerEntity.Cost(-8, -6))}}, {{new VillagerEntity.ItemTradeEntry(Items.LEATHER, new VillagerEntity.Cost(9, 12)), new VillagerEntity.ItemStackTradeEntry(Items.LEATHER_LEGGINGS, new VillagerEntity.Cost(2, 4))}, {new VillagerEntity.EnchantedItemStackTradeEntry(Items.LEATHER_CHESTPLATE, new VillagerEntity.Cost(7, 12))}, {new VillagerEntity.ItemStackTradeEntry(Items.SADDLE, new VillagerEntity.Cost(8, 10))}}}};
    }

    public VillagerEntityMixin(World world) {
        super(world);
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
