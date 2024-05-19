package net.merchantpug.toomanyorigins.registry;

import net.merchantpug.toomanyorigins.content.legacy.block.WitheredCropBlock;
import net.merchantpug.toomanyorigins.content.legacy.block.WitheredStemBlock;
import net.merchantpug.toomanyorigins.registry.services.RegistrationProvider;
import net.merchantpug.toomanyorigins.registry.services.RegistryObject;
import net.merchantpug.toomanyorigins.TooManyOrigins;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;

public class TMOBlocks {
    private static final RegistrationProvider<Block> BLOCKS = RegistrationProvider.get(Registries.BLOCK, TooManyOrigins.MOD_ID);

    public static final RegistryObject<Block> WITHERED_CROP = register("withered_crop", () -> new WitheredCropBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.CROP)));
    public static final RegistryObject<Block> WITHERED_STEM = register("withered_stem", () -> new WitheredStemBlock(BlockBehaviour.Properties.of().noCollission().randomTicks().instabreak().sound(SoundType.STEM)));

    public static <T extends Block> RegistryObject<T> register(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    public static void register() {

    }
}
