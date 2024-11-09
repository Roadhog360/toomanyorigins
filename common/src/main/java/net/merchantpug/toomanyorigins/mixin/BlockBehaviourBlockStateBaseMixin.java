package net.merchantpug.toomanyorigins.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.merchantpug.toomanyorigins.util.FluidShapeUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BlockBehaviour.BlockStateBase.class)
public class BlockBehaviourBlockStateBaseMixin {

    @ModifyReturnValue(method = "getCollisionShape(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;", at = @At("RETURN"))
    private VoxelShape toomanyorigins$fixFluidloggedCollisionShape(VoxelShape original, BlockGetter level, BlockPos pos, CollisionContext context) {
        FluidState fluidState = FluidShapeUtil.getFluidState(level, pos);
        int amount = fluidState.getAmount();
        if (amount == 0)
            return original;
        VoxelShape shape = FluidShapeUtil.VOXEL_SHAPES[Mth.clamp(amount, 0, 16)];
        VoxelShape shapeBelow = FluidShapeUtil.VOXEL_SHAPES[Mth.clamp(amount - 1, 0, 16)];
        if (context.isAbove(shapeBelow, pos, true)
                && context.canStandOnFluid(FluidShapeUtil.getFluidState(level, pos.above()), fluidState)) {
            return Shapes.or(original, shape);
        }
        return original;
    }
}