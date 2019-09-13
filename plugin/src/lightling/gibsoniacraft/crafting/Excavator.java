package lightling.gibsoniacraft.crafting;

// Collections
import java.util.List;											// Needed for recipe choices and lore
import java.util.ArrayList;										// Needed for recipe choices and lore

// Excavator-specific
import org.bukkit.NamespacedKey;								// Needed for defining excavator's item key
import org.bukkit.inventory.meta.ItemMeta;						// Needed for defining excavator's item meta information
import org.bukkit.Material;										// Needed for defining pre-existing items
import org.bukkit.inventory.ShapedRecipe;						// Needed for defining crafting recipes
import org.bukkit.inventory.RecipeChoice;						// Needed for wood-excavator's wood choices
import org.bukkit.inventory.ItemStack;							// Excavator is a type of item, which uses ItemStack

// Plugin-specific
import org.bukkit.plugin.java.JavaPlugin;						// For registering the excavator within the plugin
import org.bukkit.plugin.Plugin;								// Involved in setting up namespaced-key
import org.bukkit.Server;										// For registering recipes on the server

/**
 * An excavator is a shovel-based item that digs in a 3x3 radius as opposed to a singular block
 * @author Lightling
 */
public class Excavator 
{	
	// The item variants for the excavators
	private ItemStack woodExcavator;
	private ItemStack stoneExcavator;
	private ItemStack ironExcavator;
	private ItemStack goldExcavator;
	private ItemStack diamondExcavator;
	
	// The item recipes for the excavators
	private ShapedRecipe woodExcRecipe;
	private ShapedRecipe stoneExcRecipe;
	private ShapedRecipe ironExcRecipe;
	private ShapedRecipe goldExcRecipe;
	private ShapedRecipe diamondExcRecipe;
	
	// The namespaces for the excavators
	private NamespacedKey woodExcKey;
	private NamespacedKey stoneExcKey;
	private NamespacedKey ironExcKey;
	private NamespacedKey goldExcKey;
	private NamespacedKey diamondExcKey;	
	
	/**
	 * Defines an excavator
	 * @param plugin The parent plugin the excavator resides
	 */
	public Excavator(final JavaPlugin plugin) 
	{
		SetupItems(plugin);
		SetupMetadata(plugin);
		SetupRecipes(plugin);
	}
	
	/**
	 * Sets up the basic excavator items
	 * @param plugin The parent plugin the excavator resides
	 */
	private void SetupItems(final JavaPlugin plugin) 
	{
		// Define the excavator items
		woodExcavator = new ItemStack(Material.WOODEN_SHOVEL);
		stoneExcavator = new ItemStack(Material.STONE_SHOVEL);
		ironExcavator = new ItemStack(Material.IRON_SHOVEL);
		goldExcavator = new ItemStack(Material.GOLDEN_SHOVEL);
		diamondExcavator = new ItemStack(Material.DIAMOND_SHOVEL);
		
		// Define the excavator namespaced keys
		woodExcKey = new NamespacedKey((Plugin)plugin, "w_excavator");
		stoneExcKey = new NamespacedKey((Plugin)plugin, "s_excavator");
		ironExcKey = new NamespacedKey((Plugin)plugin, "i_excavator");
		goldExcKey = new NamespacedKey((Plugin)plugin, "g_excavator");
		diamondExcKey = new NamespacedKey((Plugin)plugin, "d_excavator");
	}
	
	/**
	 * Sets up relevant metadata for the excavator
	 * @param plugin The parent plugin the excavator resides
	 */
	private void SetupMetadata(final JavaPlugin plugin) 
	{
		// Define metadata
		ItemMeta woodExcMeta = woodExcavator.getItemMeta();
		ItemMeta stoneExcMeta = stoneExcavator.getItemMeta();
		ItemMeta ironExcMeta = ironExcavator.getItemMeta();
		ItemMeta goldExcMeta = goldExcavator.getItemMeta();
		ItemMeta diamondExcMeta = diamondExcavator.getItemMeta();
		
		// Define lore
		String loreString = "Based off of item from Tinkers' Construct";
		List<String> lore = new ArrayList<String>() {{ add(loreString); }};
		woodExcMeta.setLore(lore);
		stoneExcMeta.setLore(lore);
		ironExcMeta.setLore(lore);
		goldExcMeta.setLore(lore);
		diamondExcMeta.setLore(lore);
		
		// Setup display names
		woodExcMeta.setDisplayName("Wooden Excavator");
		stoneExcMeta.setDisplayName("Stone Excavator");
		ironExcMeta.setDisplayName("Iron Excavator");
		goldExcMeta.setDisplayName("Golden Excavator");
		diamondExcMeta.setDisplayName("Diamond Excavator");
		
		// Set item metadata
		woodExcavator.setItemMeta(woodExcMeta);
		stoneExcavator.setItemMeta(stoneExcMeta);
		ironExcavator.setItemMeta(ironExcMeta);
		goldExcavator.setItemMeta(goldExcMeta);
		diamondExcavator.setItemMeta(diamondExcMeta);
	}
	
	/**
	 * Sets up the excavator recipes (defining required items) on the server
	 * @param plugin The parent plugin the excavator resides
	 */
	private void SetupRecipes(final JavaPlugin plugin) 
	{
		// Define recipe entries
		woodExcRecipe = new ShapedRecipe(woodExcKey, woodExcavator);
		stoneExcRecipe = new ShapedRecipe(stoneExcKey, stoneExcavator);
		ironExcRecipe = new ShapedRecipe(ironExcKey, ironExcavator);
		goldExcRecipe = new ShapedRecipe(goldExcKey, goldExcavator);
		diamondExcRecipe = new ShapedRecipe(diamondExcKey, diamondExcavator);
		
		// Define recipe shapes
		woodExcRecipe.shape(new String[] { " x ", "xix", " x "});
		stoneExcRecipe.shape(new String[] { " x ", "xix", " x "});
		ironExcRecipe.shape(new String[] { " x ", "xix", " x "});
		goldExcRecipe.shape(new String[] { " x ", "xix", " x "});
		diamondExcRecipe.shape(new String[] { " x ", "xix", " x "});
		
		// Define recipe choice ingredients for wood excavator
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
		
		// Define recipes for the excavators
		woodExcRecipe.setIngredient('x', woodChoice);
		woodExcRecipe.setIngredient('i', Material.WOODEN_SHOVEL);
		stoneExcRecipe.setIngredient('x', Material.COBBLESTONE);
		stoneExcRecipe.setIngredient('i', Material.STONE_SHOVEL);
		ironExcRecipe.setIngredient('x', Material.IRON_INGOT);
		ironExcRecipe.setIngredient('i', Material.IRON_SHOVEL);
		goldExcRecipe.setIngredient('x', Material.GOLD_INGOT);
		goldExcRecipe.setIngredient('i', Material.GOLDEN_SHOVEL);
		diamondExcRecipe.setIngredient('x', Material.DIAMOND);
		diamondExcRecipe.setIngredient('i', Material.DIAMOND_SHOVEL);
		
		// Register recipes on the server
		Server server = plugin.getServer();
		server.addRecipe(woodExcRecipe);
		server.addRecipe(stoneExcRecipe);
		server.addRecipe(ironExcRecipe);
		server.addRecipe(goldExcRecipe);
		server.addRecipe(diamondExcRecipe);
	}
}
