package com.github.merchantpug.toomanyorigins.integration;

import com.github.merchantpug.toomanyorigins.TooManyOrigins;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import com.github.merchantpug.toomanyorigins.util.TooManyOriginsConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class TMOModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> MidnightConfig.getScreen(parent, TooManyOrigins.MODID);
    }
}
