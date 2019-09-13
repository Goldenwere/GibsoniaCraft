package lightling.gibsoniacraft.util;

import java.util.ArrayList;

import org.bukkit.Material;

public class BlockRef 
{
	/**
	 * A list of all valid hammer blocks (must be valid pickaxe blocks)
	 */
	public static ArrayList<Material> ValidHammerBlocks = new ArrayList<Material>()
	{{
		add(Material.COAL_ORE);
		add(Material.IRON_ORE);
		add(Material.GOLD_ORE);
		add(Material.DIAMOND_ORE);
		add(Material.EMERALD_ORE);
		add(Material.LAPIS_ORE);
		add(Material.REDSTONE_ORE);
		
		add(Material.COAL_BLOCK);
		add(Material.IRON_BLOCK);
		add(Material.GOLD_BLOCK);
		add(Material.DIAMOND_BLOCK);
		add(Material.EMERALD_BLOCK);
		add(Material.LAPIS_BLOCK);
		add(Material.REDSTONE_BLOCK);
		
		add(Material.COBBLESTONE);
		add(Material.STONE);
		add(Material.SANDSTONE);
		add(Material.RED_SANDSTONE);
		add(Material.DIORITE);
		add(Material.ANDESITE);
		add(Material.GRANITE);
		add(Material.ICE);
		add(Material.BLUE_ICE);
		add(Material.FROSTED_ICE);
		add(Material.PACKED_ICE);
		
		add(Material.END_STONE);
		add(Material.NETHERRACK);
		add(Material.NETHER_QUARTZ_ORE);
	}};
	
	/**
	 * A list of all valid excavator blocks (must be valid shovel blocks)
	 */
	public static ArrayList<Material> ValidExcavatorBlocks = new ArrayList<Material>()
	{{	
		add(Material.DIRT);
		add(Material.COARSE_DIRT);
		add(Material.PODZOL);
		add(Material.GRASS_BLOCK);
		add(Material.GRASS_PATH);
		
		add(Material.SAND);
		add(Material.RED_SAND);
		add(Material.GRAVEL);
		add(Material.CLAY);
		
		add(Material.SNOW);
		add(Material.SNOW_BLOCK);
		
		add(Material.SOUL_SAND);
	}};
	
	/**
	 * A list of all valid hammers (pickaxes)
	 */
	public static ArrayList<Material> ValidHammers = new ArrayList<Material>()
	{{
		add(Material.WOODEN_PICKAXE);
		add(Material.STONE_PICKAXE);
		add(Material.IRON_PICKAXE);
		add(Material.GOLDEN_PICKAXE);
		add(Material.DIAMOND_PICKAXE);
	}};
	
	/**
	 * A list of all valid excavators (shovels)
	 */
	public static ArrayList<Material> ValidExcavators = new ArrayList<Material>()
	{{
		add(Material.WOODEN_SHOVEL);
		add(Material.STONE_SHOVEL);
		add(Material.IRON_SHOVEL);
		add(Material.GOLDEN_SHOVEL);
		add(Material.DIAMOND_SHOVEL);
	}};
}
