package net.fabricmc.terrarianplatforms;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.EntityShapeContext;
import net.minecraft.block.ShapeContext;
import net.minecraft.block.Waterloggable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.SwordItem;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.item.Item;

/*
 * Access widened by fabric-transitive-access-wideners-v1 to accessible
 * Scott here, I moved the to-do list to To-do list.java.
 */
public class PlatformBlock
extends Block
implements Waterloggable {
    private static final VoxelShape COLLISION_SHAPE;
    public static final BooleanProperty WATERLOGGED;

    public PlatformBlock(AbstractBlock.Settings settings) {
        super(settings);
        BlockState blockState = this.stateManager.getDefaultState().with(WATERLOGGED, false);
        this.setDefaultState(blockState);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context.isHolding(state.getBlock().asItem())) {
            return VoxelShapes.fullCube();
        }

        if (context instanceof EntityShapeContext) {
            Entity entity = ((EntityShapeContext)context).getEntity();            
            if (entity instanceof PlayerEntity) {
                PlayerEntity playerEntity = ((PlayerEntity)entity);
                Item item = playerEntity.getMainHandStack().getItem();
                if (item instanceof SwordItem) {
                    return VoxelShapes.empty();
                }
            }
        }
        return COLLISION_SHAPE;
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.empty();
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockPos blockPos = ctx.getBlockPos();
        World world = ctx.getWorld();
        return (BlockState)this.getDefaultState().with(WATERLOGGED, world.getFluidState(blockPos).getFluid() == Fluids.WATER);
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED).booleanValue()) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (!world.isClient()) {
            world.createAndScheduleBlockTick(pos, this, 1);
        }
        return state;
    }

    /* Message from: Ben
     * This is called by the *raycasting* code too.
     * 
     * Message from: Scott
     * please use the format ("message from"":""name of person""down 1 line""message") when making comments.
     * can we make this dependent on  head pos of mobs, meaning that all platforms at (head pos) or higher do not count as solid for mobs,
     * nor as something they can pathfind through, such that they see it as something they can pathfind through as if it were air when it is
     * at their head pos or higher, allowing them to see through, walk through, and shoot through walls of platforms?
     */
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        java.lang.StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement element : stackTrace) {
            if (element.getMethodName() == "render" || element.getMethodName() == "raycast") {
                return VoxelShapes.empty();
            } else if (
                element.getMethodName() == "shouldSuffocate" ||
                element.getMethodName() == "wouldCollideAt" ||
                element.getMethodName() == "isPlayerNotCollidingWithBlocks" ||
                element.getMethodName() == "adjustMovementForCollisions" ||
                element.getMethodName() == "wouldPoseNotCollide" ||
                element.getClassName() == "net.minecraft.entity.ItemEntity" ||
                false) {
                return getCollisionShapeForReal(state, world, pos, context);
            }
        }

        return getCollisionShapeForReal(state, world, pos, context);
    }

    private static VoxelShape getCollisionShapeForReal(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if (context instanceof EntityShapeContext) {
            Entity entity = ((EntityShapeContext)context).getEntity();
            if (entity != null && entity.getClass().getName().contains("hookshot")) {
                // Hookshots can use platforms.
                return COLLISION_SHAPE;
            }
            if (entity instanceof ProjectileEntity) {
                // Projectiles pass through platforms.
                return VoxelShapes.empty();
            }
            if (entity instanceof MobEntity) {
                LivingEntity target = ((MobEntity)entity).getTarget();
                if (target != null && target.getPos().y < pos.getY()) {
                    // While a mob's target is below it, the mob falls through platforms.
                    return VoxelShapes.empty();
                }
            }
        } 

        // If an entity is below the top of a platform, it does not collide with the platform.
        if (!context.isAbove(VoxelShapes.fullCube(), pos, true)) {
            return VoxelShapes.empty();
        } 

        // If an entity is crouching, it does not collide with the platform.
        if (context.isDescending()) {
            return VoxelShapes.empty();
        }

        return COLLISION_SHAPE;
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        if (state.get(WATERLOGGED).booleanValue()) {
            return Fluids.WATER.getStill(false);
        }
        return super.getFluidState(state);
    }

    @Override
    public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
        return type != NavigationType.LAND;
    }
    /* Message From: Scott
     * if we use
     * public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos) {
     * return VoxelShapes.empty();
     * }
     * and we also have
     * protected static final VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos) {
     * return VoxelShapes.NORMAL_OUTLINE_SHAPE();
     * }
     * does this allow the RaycastContext.class file to think we are empty, but allow the actual collision of the block to be normal?
     * If not, can we change protected to private, remove static, and/or remove final to change it to
     * allow the RaycastContext.class file to think we are empty, but allow the actual collision of the block to be normal?
     * also, please use the message format (in brackets)
     * (Message From: NameHere
     * Message)
     * properly?
     * also, do not call yourself "appleton" in the NameHere section. Call yourself by you first name or nickname. No last/middle names.
     * 
     */

    static {
        WATERLOGGED = Properties.WATERLOGGED;
        
        VoxelShape voxelShape = Block.createCuboidShape(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
        COLLISION_SHAPE = VoxelShapes.union(voxelShape);
    }
}