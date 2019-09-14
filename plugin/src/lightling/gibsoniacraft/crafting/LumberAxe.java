package lightling.gibsoniacraft.crafting;

// Collections
import java.util.List;								// Needed for recipe choices and lore
import java.util.ArrayList;							// Needed for recipe choices and lore

// LumberAxe-specific
import org.bukkit.NamespacedKey;					// Needed for defining LumberAxe's item key
import org.bukkit.inventory.meta.ItemMeta;			// Needed for defining LumberAxe's item meta information
import org.bukkit.Material;							// Needed for defining pre-existing items
import org.bukkit.inventory.ShapedRecipe;			// Needed for defining crafting recipes
import org.bukkit.inventory.RecipeChoice;			// Needed for wood-LumberAxe's wood choices
import org.bukkit.inventory.ItemStack;				// LumberAxe is a type of item, which uses ItemStack

// Plugin-specific
import org.bukkit.plugin.java.JavaPlugin;			// For registering the LumberAxe within the plugin
import org.bukkit.plugin.Plugin;					// Involved in setting up namespaced-key
import org.bukkit.Server;							// For registering recipes on the server

/**
 * An LumberAxe is a shovel-based item that digs in a 3x3 radius as opposed to a singular block
 * @author Lightling
 */
public class LumberAxe 
{	
	// The item variants for the LumberAxes
	private ItemStack woodLumberAxe;
	private ItemStack stoneLumberAxe;
	private ItemStack ironLumberAxe;
	private ItemStack goldLumberAxe;
	private ItemStack diamondLumberAxe;
	
	// The item recipes for the LumberAxes
	private ShapedRecipe woodLumAxeRecipe;
	private ShapedRecipe stoneLumAxeRecipe;
	private ShapedRecipe ironLumAxeRecipe;
	private ShapedRecipe goldLumAxeRecipe;
	private ShapedRecipe diamondLumAxeRecipe;
	
	// The namespaces for the LumberAxes
	private NamespacedKey woodLumAxeKey;
	private NamespacedKey stoneLumAxeKey;
	private NamespacedKey ironLumAxeKey;
	private NamespacedKey goldLumAxeKey;
	private NamespacedKey diamondLumAxeKey;	
	
	/**
	 * Defines an LumberAxe
	 * @param plugin The parent plugin the LumberAxe resides
	 */
	public LumberAxe(final JavaPlugin plugin) 
	{
		SetupItems(plugin);
		SetupMetadata(plugin);
		SetupRecipes(plugin);
	}
	
	/**
	 * Sets up the basic LumberAxe items
	 * @param plugin The parent plugin the LumberAxe resides
	 */
	private void SetupItems(final JavaPlugin plugin) 
	{
		// Define the LumberAxe items
		woodLumberAxe = new ItemStack(Material.WOODEN_SHOVEL);
		stoneLumberAxe = new ItemStack(Material.STONE_SHOVEL);
		ironLumberAxe = new ItemStack(Material.IRON_SHOVEL);
		goldLumberAxe = new ItemStack(Material.GOLDEN_SHOVEL);
		diamondLumberAxe = new ItemStack(Material.DIAMOND_SHOVEL);
		
		// Define the LumberAxe namespaced keys
		woodLumAxeKey = new NamespacedKey((Plugin)plugin, "w_LumberAxe");
		stoneLumAxeKey = new NamespacedKey((Plugin)plugin, "s_LumberAxe");
		ironLumAxeKey = new NamespacedKey((Plugin)plugin, "i_LumberAxe");
		goldLumAxeKey = new NamespacedKey((Plugin)plugin, "g_LumberAxe");
		diamondLumAxeKey = new NamespacedKey((Plugin)plugin, "d_LumberAxe");
	}
	
	/**
	 * Sets up relevant metadata for the LumberAxe
	 * @param plugin The parent plugin the LumberAxe resides
	 */
	private void SetupMetadata(final JavaPlugin plugin) 
	{
		// Define metadata
		ItemMeta woodLumAxeMeta = woodLumberAxe.getItemMeta();
		ItemMeta stoneLumAxeMeta = stoneLumberAxe.getItemMeta();
		ItemMeta ironLumAxeMeta = ironLumberAxe.getItemMeta();
		ItemMeta goldLumAxeMeta = goldLumberAxe.getItemMeta();
		ItemMeta diamondLumAxeMeta = diamondLumberAxe.getItemMeta();
		
		// Define lore
		String loreString = "Based off of item from PowerMining/Tinkers' Construct";
		List<String> lore = new ArrayList<String>() {{ add(loreString); }};
		woodLumAxeMeta.setLore(lore);
		stoneLumAxeMeta.setLore(lore);
		ironLumAxeMeta.setLore(lore);
		goldLumAxeMeta.setLore(lore);
		diamondLumAxeMeta.setLore(lore);
		
		// Setup display names
		woodLumAxeMeta.setDisplayName("Wooden LumberAxe");
		stoneLumAxeMeta.setDisplayName("Stone LumberAxe");
		ironLumAxeMeta.setDisplayName("Iron LumberAxe");
		goldLumAxeMeta.setDisplayName("Golden LumberAxe");
		diamondLumAxeMeta.setDisplayName("Diamond LumberAxe");
		
		// Set item metadata
		woodLumberAxe.setItemMeta(woodLumAxeMeta);
		stoneLumberAxe.setItemMeta(stoneLumAxeMeta);
		ironLumberAxe.setItemMeta(ironLumAxeMeta);
		goldLumberAxe.setItemMeta(goldLumAxeMeta);
		diamondLumberAxe.setItemMeta(diamondLumAxeMeta);
	}
	
	/**
	 * Sets up the LumberAxe recipes (defining required items) on the server
	 * @param plugin The parent plugin the LumberAxe resides
	 */
	private void SetupRecipes(final JavaPlugin plugin) 
	{
		// Define recipe entries
		woodLumAxeRecipe = new ShapedRecipe(woodLumAxeKey, woodLumberAxe);
		stoneLumAxeRecipe = new ShapedRecipe(stoneLumAxeKey, stoneLumberAxe);
		ironLumAxeRecipe = new ShapedRecipe(ironLumAxeKey, ironLumberAxe);
		goldLumAxeRecipe = new ShapedRecipe(goldLumAxeKey, goldLumberAxe);
		diamondLumAxeRecipe = new ShapedRecipe(diamondLumAxeKey, diamondLumberAxe);
		
		// Define recipe shapes
		woodLumAxeRecipe.shape(new String[] { " x ", "xix", " x "});
		stoneLumAxeRecipe.shape(new String[] { " x ", "xix", " x "});
		ironLumAxeRecipe.shape(new String[] { " x ", "xix", " x "});
		goldLumAxeRecipe.shape(new String[] { " x ", "xix", " x "});
		diamondLumAxeRecipe.shape(new String[] { " x ", "xix", " x "});
		
		// Define recipe choice ingredients for wood LumberAxe
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
		
		// Define recipes for the LumberAxes
		woodLumAxeRecipe.setIngredient('x', woodChoice);
		woodLumAxeRecipe.setIngredient('i', Material.WOODEN_SHOVEL);
		stoneLumAxeRecipe.setIngredient('x', Material.COBBLESTONE);
		stoneLumAxeRecipe.setIngredient('i', Material.STONE_SHOVEL);
		ironLumAxeRecipe.setIngredient('x', Material.IRON_INGOT);
		ironLumAxeRecipe.setIngredient('i', Material.IRON_SHOVEL);
		goldLumAxeRecipe.setIngredient('x', Material.GOLD_INGOT);
		goldLumAxeRecipe.setIngredient('i', Material.GOLDEN_SHOVEL);
		diamondLumAxeRecipe.setIngredient('x', Material.DIAMOND);
		diamondLumAxeRecipe.setIngredient('i', Material.DIAMOND_SHOVEL);
		
		// Register recipes on the server
		Server server = plugin.getServer();
		server.addRecipe(woodLumAxeRecipe);
		server.addRecipe(stoneLumAxeRecipe);
		server.addRecipe(ironLumAxeRecipe);
		server.addRecipe(goldLumAxeRecipe);
		server.addRecipe(diamondLumAxeRecipe);
	}
}
