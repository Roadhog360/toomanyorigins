package net.merchantpug.toomanyorigins.platform;

import net.merchantpug.toomanyorigins.TooManyOrigins;
import net.merchantpug.toomanyorigins.platform.services.*;

import java.util.ServiceLoader;

public class Services {

    public static final IPowerHelper<?> POWER = load(IPowerHelper.class);
    public static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);

    public static <T> T load(Class<T> clazz) {

        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        TooManyOrigins.LOG.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
    
}
