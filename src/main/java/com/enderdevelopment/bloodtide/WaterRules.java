package com.enderdevelopment.bloodtide;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;

public final class WaterRules {
    private WaterRules() {
    }

    public static void init() {
        UseItemCallback.EVENT.register((player, level, hand) -> {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.is(Items.WATER_BUCKET) || stack.is(Items.FISHING_ROD)) {
                return InteractionResult.FAIL;
            }
            return InteractionResult.PASS;
        });

        UseBlockCallback.EVENT.register((player, level, hand, hit) -> {
            if (level.getFluidState(hit.getBlockPos()).isSourceOfType(Fluids.WATER)) {
                return InteractionResult.FAIL;
            }
            return InteractionResult.PASS;
        });
    }
}
