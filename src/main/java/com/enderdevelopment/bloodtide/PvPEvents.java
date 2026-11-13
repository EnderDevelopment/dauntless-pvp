package com.enderdevelopment.bloodtide;

import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Fluids;

public final class PvPEvents {
    private PvPEvents() {
    }

    public static void init() {
        ServerPlayerEvents.JOIN.register(player -> player.sendSystemMessage(
                Component.literal("Bloodtide rules active: hard PvP, no water, no fishing, and no safe retreat.")));

        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                ServerLevel level = (ServerLevel) player.level();
                BlockPos center = player.blockPosition();
                for (int x = -2; x <= 2; x++) {
                    for (int y = -1; y <= 2; y++) {
                        for (int z = -2; z <= 2; z++) {
                            BlockPos pos = center.offset(x, y, z);
                            if (level.getFluidState(pos).getType() == Fluids.WATER) {
                                level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                            }
                        }
                    }
                }
            }
        });
    }
}
