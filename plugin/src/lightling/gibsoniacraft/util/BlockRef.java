package lightling.gibsoniacraft.util;

import java.util.ArrayList;

import org.bukkit.Material;

public class BlockRef 
{
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
		
		add(Material.END_STONE);
		add(Material.NETHERRACK);
		add(Material.NETHER_QUARTZ_ORE);
	}};
	
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
}
