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
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	public static final Block PLATFORM_BLOCK = new PlatformBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.SPRUCE_BROWN).strength(1.0f, 4.5f).nonOpaque().noCollision().sounds(BlockSoundGroup.WOOD).dynamicBounds());
	public static final Item PLATFORM_ITEM = new PlatformItem(PLATFORM_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		Registry.register(Registry.BLOCK, new Identifier("terrarianplatforms", "platform"), PLATFORM_BLOCK);
		Registry.register(Registry.ITEM, new Identifier("terrarianplatforms", "platform"), PLATFORM_ITEM);
	}
}
