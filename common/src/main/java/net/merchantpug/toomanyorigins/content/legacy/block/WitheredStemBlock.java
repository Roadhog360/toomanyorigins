package net.merchantpug.toomanyorigins.content.legacy.block;

import com.mojang.serialization.MapCodec;
import net.merchantpug.toomanyorigins.content.legacy.block.WitheredCropBlock;
import net.merchantpug.toomanyorigins.registry.TMOItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Supplier;

public class WitheredStemBlock extends BushBlock {
    public static final MapCodec<WitheredStemBlock> CODEC = simpleCodec(WitheredStemBlock::new);

    public WitheredStemBlock(Properties settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BushBlock> codec() {
        return CODEC;
    }

    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        return Block.box(7.0D, 0.0D, 7.0D, 9.0D, 2.0D, 9.0D);
    }

    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        VoxelShape voxelShape = this.getShape(state, level, pos, CollisionContext.empty());
        Vec3 vec3d = voxelShape.bounds().getCenter();
        double d = (double)pos.getX() + vec3d.x;
        double e = (double)pos.getZ() + vec3d.z;

        for(int i = 0; i < 1; ++i) {
            if (random.nextBoolean()) {
                level.addParticle(ParticleTypes.SMOKE, d + random.nextDouble() / 5.0D, (double)pos.getY() + (0.5D - random.nextDouble()), e + random.nextDouble() / 5.0D, 0.0D, 0.0D, 0.0D);
            }
        }
    }

    @Override
    public boolean canSurvive(BlockState floor, LevelReader world, BlockPos pos) {
        return floor.is(Blocks.FARMLAND);
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(TMOItems.WITHERED_STEM_SEEDS.get());
    }
}
