package net.fabricmc.terrarianplatforms;

import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Material;
import net.minecraft.block.MapColor;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TerrarianPlatformsMod
		implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("terrarianplatforms");

	public static final Block PLATFORM_BLOCK = new PlatformBlock(
			AbstractBlock.Settings.of(Material.WOOD, MapColor.SPRUCE_BROWN).strength(1.0f, 4.5f).nonOpaque()
					.noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());
	public static final Block ACACIA_PLATFORM_BLOCK = new PlatformBlock(
			AbstractBlock.Settings.of(Material.WOOD, MapColor.ORANGE).strength(1.0f, 4.5f).nonOpaque()
					.noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());
	public static final Block BIRCH_PLATFORM_BLOCK = new PlatformBlock(
			AbstractBlock.Settings.of(Material.WOOD, MapColor.PALE_YELLOW).strength(1.0f, 4.5f).nonOpaque()
					.noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());
	public static final Block CRIMSON_PLATFORM_BLOCK = new PlatformBlock(
			AbstractBlock.Settings.of(Material.WOOD, MapColor.DULL_PINK).strength(1.0f, 4.5f).nonOpaque()
					.noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());
	public static final Block DARK_OAK_PLATFORM_BLOCK = new PlatformBlock(
			AbstractBlock.Settings.of(Material.WOOD, MapColor.BROWN).strength(1.0f, 4.5f).nonOpaque()
					.noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());
	public static final Block JUNGLE_PLATFORM_BLOCK = new PlatformBlock(
			AbstractBlock.Settings.of(Material.WOOD, MapColor.DIRT_BROWN).strength(1.0f, 4.5f).nonOpaque()
					.noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());
	public static final Block MANGROVE_PLATFORM_BLOCK = new PlatformBlock(
			AbstractBlock.Settings.of(Material.WOOD, MapColor.RED).strength(1.0f, 4.5f).nonOpaque()
					.noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());
	public static final Block OAK_PLATFORM_BLOCK = new PlatformBlock(
			AbstractBlock.Settings.of(Material.WOOD, MapColor.OAK_TAN).strength(1.0f, 4.5f).nonOpaque()
					.noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());
	public static final Block SPRUCE_PLATFORM_BLOCK = new PlatformBlock(
			AbstractBlock.Settings.of(Material.WOOD, MapColor.SPRUCE_BROWN).strength(1.0f, 4.5f).nonOpaque()
					.noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());
	public static final Block WARPED_PLATFORM_BLOCK = new PlatformBlock(
			AbstractBlock.Settings.of(Material.WOOD, MapColor.DARK_AQUA).strength(1.0f, 4.5f).nonOpaque()
					.noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());

	public static final Item PLATFORM_ITEM = new PlatformItem(PLATFORM_BLOCK,
			new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final Item ACACIA_PLATFORM_ITEM = new PlatformItem(ACACIA_PLATFORM_BLOCK,
			new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final Item BIRCH_PLATFORM_ITEM = new PlatformItem(BIRCH_PLATFORM_BLOCK,
			new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final Item CRIMSON_PLATFORM_ITEM = new PlatformItem(CRIMSON_PLATFORM_BLOCK,
			new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final Item DARK_OAK_PLATFORM_ITEM = new PlatformItem(DARK_OAK_PLATFORM_BLOCK,
			new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final Item JUNGLE_PLATFORM_ITEM = new PlatformItem(JUNGLE_PLATFORM_BLOCK,
			new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final Item MANGROVE_PLATFORM_ITEM = new PlatformItem(MANGROVE_PLATFORM_BLOCK,
			new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final Item OAK_PLATFORM_ITEM = new PlatformItem(OAK_PLATFORM_BLOCK,
			new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final Item SPRUCE_PLATFORM_ITEM = new PlatformItem(SPRUCE_PLATFORM_BLOCK,
			new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
	public static final Item WARPED_PLATFORM_ITEM = new PlatformItem(WARPED_PLATFORM_BLOCK,
			new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "platform"), PLATFORM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "platform"), PLATFORM_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "acacia_platform"),
				ACACIA_PLATFORM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "acacia_platform"), ACACIA_PLATFORM_ITEM);

		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "birch_platform"), BIRCH_PLATFORM_ITEM);
		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "birch_platform"), BIRCH_PLATFORM_BLOCK);

		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "crimson_platform"),
				CRIMSON_PLATFORM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "crimson_platform"),
				CRIMSON_PLATFORM_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "dark_oak_platform"),
				DARK_OAK_PLATFORM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "dark_oak_platform"),
				DARK_OAK_PLATFORM_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "jungle_platform"),
				JUNGLE_PLATFORM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "jungle_platform"), JUNGLE_PLATFORM_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "mangrove_platform"),
				MANGROVE_PLATFORM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "mangrove_platform"),
				MANGROVE_PLATFORM_ITEM);

		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "oak_platform"), OAK_PLATFORM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "oak_platform"), OAK_PLATFORM_ITEM);

		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "spruce_platform"), SPRUCE_PLATFORM_ITEM);
		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "spruce_platform"),
				SPRUCE_PLATFORM_BLOCK);

		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "warped_platform"),
				WARPED_PLATFORM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "warped_platform"), WARPED_PLATFORM_ITEM);
	}
}
