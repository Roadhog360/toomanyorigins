package net.merchantpug.toomanyorigins;

import io.github.apace100.calio.util.IdentifierAlias;
import net.merchantpug.toomanyorigins.registry.*;
import net.minecraft.resources.ResourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TooManyOrigins {
    
    public static final String MOD_ID = "toomanyorigins";
    public static final String MOD_NAME = "TooManyOrigins";
    public static final Logger LOG = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        TMOBadges.register();
        TMOBlocks.register();
        TMOEffects.register();
        TMOEntityTypes.register();
        TMOItems.register();
        TMOParticleTypes.register();
        TMOPowers.register();
        TMOSounds.register();

        IdentifierAlias.GLOBAL.addNamespaceAlias(MOD_ID, "apugli");
    }
    
    public static ResourceLocation asResource(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
    
}