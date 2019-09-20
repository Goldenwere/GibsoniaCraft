package lightling.gibsoniacraft.crafting;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * A chunk loader is a block (beacon in appearance) that keeps a chunk loaded
 * @author Lightling
 */
@SuppressWarnings("serial")
public class ChunkLoader {
	
	private ItemStack chunkItem;
	private ShapedRecipe chunkRecipe;
	private NamespacedKey chunkKey;
	private ItemMeta chunkMeta;

	/**
	 * Initializes a chunk loader
	 * @param plugin GibsoniaCraft plugin
	 */
	public ChunkLoader(final JavaPlugin plugin)
	{
		Setup(plugin);
	}
	
	/**
	 * Defines the chunk loader block
	 * @param plugin GibsoniaCraft plugin
	 */
	public void Setup(JavaPlugin plugin)
	{
		// Handle initial values
		chunkItem = new ItemStack(Material.BEACON);
		chunkKey = new NamespacedKey(plugin, "chunkloader");
		
		// Handle meta
		chunkMeta = chunkItem.getItemMeta();
		String loreString = "Keeps chunks loaded";
		ArrayList<String> lore = new ArrayList<String>() {{ add(loreString); }};
		chunkMeta.setLore(lore);
		chunkMeta.setDisplayName("Chunk Loader");
		chunkItem.setItemMeta(chunkMeta);
		
		// Handle recipe
		chunkRecipe = new ShapedRecipe(chunkKey, chunkItem);
		chunkRecipe.shape(new String[] { "epe", "eme", "ooo" });
		chunkRecipe.setIngredient('o', Material.OBSIDIAN);
		chunkRecipe.setIngredient('e', Material.EMERALD);
		chunkRecipe.setIngredient('m', Material.MAGMA_BLOCK);
		chunkRecipe.setIngredient('p', Material.PRISMARINE_CRYSTALS);
		Server server = plugin.getServer();
		server.addRecipe(chunkRecipe);
	}
	
	/**
	 * Defines the chunk loader drop
	 * @return the chunk loader drop
	 */
	public static ItemStack Setup() 
	{
		// Instantiate item
		ItemStack item = new ItemStack(Material.BEACON);
		
		// Handle meta
		ItemMeta meta = item.getItemMeta();
		String loreString = "Keeps chunks loaded";
		ArrayList<String> lore = new ArrayList<String>() {{ add(loreString); }};
		meta.setLore(lore);
		meta.setDisplayName("Chunk Loader");
		item.setItemMeta(meta);
		
		// Return the appropriately edited item
		return item;
	}
}
