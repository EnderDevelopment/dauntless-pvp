package com.enderdevelopment.bloodtide;

import net.fabricmc.api.ModInitializer;

public final class BloodtideMod implements ModInitializer {
    public static final String MOD_ID = "bloodtide";

    @Override
    public void onInitialize() {
        WaterRules.init();
        PvPEvents.init();
    }
}
