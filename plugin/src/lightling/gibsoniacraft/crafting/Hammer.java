package lightling.gibsoniacraft.crafting;

// Collections
import java.util.List;								// Needed for recipe choices and lore
import java.util.ArrayList;							// Needed for recipe choices and lore

// Hammer-specific
import org.bukkit.NamespacedKey;					// Needed for defining Hammer's item key
import org.bukkit.inventory.meta.ItemMeta;			// Needed for defining Hammer's item meta information
import org.bukkit.Material;							// Needed for defining pre-existing items
import org.bukkit.inventory.ShapedRecipe;			// Needed for defining crafting recipes
import org.bukkit.inventory.RecipeChoice;			// Needed for wood-Hammer's wood choices
import org.bukkit.inventory.ItemStack;				// Hammer is a type of item, which uses ItemStack

// Plugin-specific
import org.bukkit.plugin.java.JavaPlugin;			// For registering the Hammer within the plugin
import org.bukkit.plugin.Plugin;					// Involved in setting up namespaced-key
import org.bukkit.Server;							// For registering recipes on the server

/**
 * An Hammer is a pickaxe-based item that digs in a 3x3 radius as opposed to a singular block
 * @author Lightling
 */
@SuppressWarnings("serial")
public class Hammer
{	
	// The item variants for the Hammers
	private ItemStack woodHammer;
	private ItemStack stoneHammer;
	private ItemStack ironHammer;
	private ItemStack goldHammer;
	private ItemStack diamondHammer;
	
	// The item recipes for the Hammers
	private ShapedRecipe woodHamRecipe;
	private ShapedRecipe stoneHamRecipe;
	private ShapedRecipe ironHamRecipe;
	private ShapedRecipe goldHamRecipe;
	private ShapedRecipe diamondHamRecipe;
	
	// The namespaces for the Hammers
	private NamespacedKey woodHamKey;
	private NamespacedKey stoneHamKey;
	private NamespacedKey ironHamKey;
	private NamespacedKey goldHamKey;
	private NamespacedKey diamondHamKey;	
	
	/**
	 * Defines an Hammer
	 * @param plugin The parent plugin the Hammer resides
	 */
	public Hammer(final JavaPlugin plugin) 
	{
		SetupItems(plugin);
		SetupMetadata(plugin);
		SetupRecipes(plugin);
	}
	
	/**
	 * Sets up the basic Hammer items
	 * @param plugin The parent plugin the Hammer resides
	 */
	private void SetupItems(final JavaPlugin plugin) 
	{
		// Define the Hammer items
		woodHammer = new ItemStack(Material.WOODEN_PICKAXE);
		stoneHammer = new ItemStack(Material.STONE_PICKAXE);
		ironHammer = new ItemStack(Material.IRON_PICKAXE);
		goldHammer = new ItemStack(Material.GOLDEN_PICKAXE);
		diamondHammer = new ItemStack(Material.DIAMOND_PICKAXE);
		
		// Define the Hammer namespaced keys
		woodHamKey = new NamespacedKey((Plugin)plugin, "w_hammer");
		stoneHamKey = new NamespacedKey((Plugin)plugin, "s_hammer");
		ironHamKey = new NamespacedKey((Plugin)plugin, "i_hammer");
		goldHamKey = new NamespacedKey((Plugin)plugin, "g_hammer");
		diamondHamKey = new NamespacedKey((Plugin)plugin, "d_hammer");
	}
	
	/**
	 * Sets up relevant metadata for the Hammer
	 * @param plugin The parent plugin the Hammer resides
	 */
	private void SetupMetadata(final JavaPlugin plugin) 
	{
		// Define metadata
		ItemMeta woodHamMeta = woodHammer.getItemMeta();
		ItemMeta stoneHamMeta = stoneHammer.getItemMeta();
		ItemMeta ironHamMeta = ironHammer.getItemMeta();
		ItemMeta goldHamMeta = goldHammer.getItemMeta();
		ItemMeta diamondHamMeta = diamondHammer.getItemMeta();
		
		// Define lore
		String loreString = "Based off of item from PowerMining/Tinkers' Construct";
		List<String> lore = new ArrayList<String>() {{ add(loreString); }};
		woodHamMeta.setLore(lore);
		stoneHamMeta.setLore(lore);
		ironHamMeta.setLore(lore);
		goldHamMeta.setLore(lore);
		diamondHamMeta.setLore(lore);
		
		// Setup display names
		woodHamMeta.setDisplayName("Wooden Hammer");
		stoneHamMeta.setDisplayName("Stone Hammer");
		ironHamMeta.setDisplayName("Iron Hammer");
		goldHamMeta.setDisplayName("Golden Hammer");
		diamondHamMeta.setDisplayName("Diamond Hammer");
		
		// Set item metadata
		woodHammer.setItemMeta(woodHamMeta);
		stoneHammer.setItemMeta(stoneHamMeta);
		ironHammer.setItemMeta(ironHamMeta);
		goldHammer.setItemMeta(goldHamMeta);
		diamondHammer.setItemMeta(diamondHamMeta);
	}
	
	/**
	 * Sets up the Hammer recipes (defining required items) on the server
	 * @param plugin The parent plugin the Hammer resides
	 */
	private void SetupRecipes(final JavaPlugin plugin) 
	{
		// Define recipe entries
		woodHamRecipe = new ShapedRecipe(woodHamKey, woodHammer);
		stoneHamRecipe = new ShapedRecipe(stoneHamKey, stoneHammer);
		ironHamRecipe = new ShapedRecipe(ironHamKey, ironHammer);
		goldHamRecipe = new ShapedRecipe(goldHamKey, goldHammer);
		diamondHamRecipe = new ShapedRecipe(diamondHamKey, diamondHammer);
		
		// Define recipe shapes
		woodHamRecipe.shape(new String[] { " x ", "xix", " x "});
		stoneHamRecipe.shape(new String[] { " x ", "xix", " x "});
		ironHamRecipe.shape(new String[] { " x ", "xix", " x "});
		goldHamRecipe.shape(new String[] { " x ", "xix", " x "});
		diamondHamRecipe.shape(new String[] { " x ", "xix", " x "});
		
		// Define recipe choice ingredients for wood Hammer
		List<Material> woodChoices = new ArrayList<Material>()
		{{
			add(Material.ACACIA_PLANKS);
			add(Material.BIRCH_PLANKS);
			add(Material.DARK_OAK_PLANKS);
			add(Material.JUNGLE_PLANKS);
			add(Material.OAK_PLANKS);
			add(Material.SPRUCE_PLANKS);
		}};
		RecipeChoice woodChoice = new RecipeChoice.MaterialChoice(woodChoices);
		
		// Define recipes for the Hammers
		woodHamRecipe.setIngredient('x', woodChoice);
		woodHamRecipe.setIngredient('i', Material.WOODEN_PICKAXE);
		stoneHamRecipe.setIngredient('x', Material.COBBLESTONE);
		stoneHamRecipe.setIngredient('i', Material.STONE_PICKAXE);
		ironHamRecipe.setIngredient('x', Material.IRON_INGOT);
		ironHamRecipe.setIngredient('i', Material.IRON_PICKAXE);
		goldHamRecipe.setIngredient('x', Material.GOLD_INGOT);
		goldHamRecipe.setIngredient('i', Material.GOLDEN_PICKAXE);
		diamondHamRecipe.setIngredient('x', Material.DIAMOND);
		diamondHamRecipe.setIngredient('i', Material.DIAMOND_PICKAXE);
		
		// Register recipes on the server
		Server server = plugin.getServer();
		server.addRecipe(woodHamRecipe);
		server.addRecipe(stoneHamRecipe);
		server.addRecipe(ironHamRecipe);
		server.addRecipe(goldHamRecipe);
		server.addRecipe(diamondHamRecipe);
	}
}
