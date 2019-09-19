package lightling.gibsoniacraft.util;

// Needed for block information
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import org.bukkit.Location;

// Needed for handling events
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import lightling.gibsoniacraft.GibsoniaCraft;

// Needed for GibsoniaCraft tools
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.enchantments.Enchantment;

/**
 * A listener for block-based events, used primarily for blocks being broken by GibsoniaCraft tools
 * @author Lightling
 */
public class BlockListener implements Listener
{		
	GibsoniaCraft gcPlugin;
	public BlockListener(GibsoniaCraft plugin) 
	{
		gcPlugin = plugin;
		gcPlugin.getServer().getPluginManager().registerEvents(this, gcPlugin);
	}
	
	/**
	 * Handle the breaking of blocks via the hammer/excavator
	 * @param bbEvent The event that triggered this method
	 */
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void Break(BlockBreakEvent bbEvent)
	{
		// Grab current tool information
		Player player = bbEvent.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		Material itemType = item.getType();
		
		// Grab the current block information
		Block block = bbEvent.getBlock();
		
		// Only do extra block-breaking if the player is not sneaking (method of disabling) or if the player doesn't have the appropriate tools
		if (player != null && (player instanceof Player))
		{
			if (player.isSneaking() || !ToolUtil.IsExcavatable(item, block.getType()) && !ToolUtil.IsHammerable(item, block.getType()))
			{
				return;
			}
		}
		
		// Get blockface information via the player listener
		String pName = player.getName();
        PlayerInteractListener pListener = gcPlugin.GetPlayerInteractListener();
        BlockFace blockFace = pListener.GetFaceByName(pName);
        ArrayList<Block> blocks = ToolUtil.GetSurroundingBlocks(blockFace, block);
        
        // Grab durability information
        ItemMeta meta = item.getItemMeta();
        Damageable dMeta = (Damageable)meta;
        int currDur = dMeta.getDamage();
        int maxDur = item.getType().getMaxDurability();
        
        // Used in determining if an extra block was broken (for durability)
        boolean success = false;
        if (blocks.size() > 1)
        {
        	success = true;
        }
        
        // Iterates through to break surrounding blocks 
        for (Block b : blocks)
        {
        	// Determine the block type and position
        	Material blockMat = b.getType();
        	Location blockLoc = b.getLocation();
        	
        	// Determine whether an appropriate tool is being used
        	boolean isExc = ToolUtil.IsExcavatable(item, blockMat);
        	boolean isHam = ToolUtil.IsHammerable(item, blockMat);
        	
        	// If using the correct tools, break surrounding blocks
        	if (isExc || isHam)
        	{
        		// Handle snow
                if (blockMat == Material.SNOW && isExc) 
                {
                    @SuppressWarnings("deprecation")
					final ItemStack snow = new ItemStack(Material.SNOWBALL, 1 + b.getData());
                    b.getWorld().dropItemNaturally(blockLoc, snow);
                }
        		
        		b.breakNaturally();
        	}
        }
        
        
        // Used for determining durability loss
        int addToDamage = 1;
        
        // Do extra damage to durability if extra blocks were broken
        if (success && !item.getEnchantments().containsKey(Enchantment.DURABILITY))
        {
        	addToDamage = 2;
        	
        	// Diamond tools take less damage
        	if (itemType == Material.DIAMOND_PICKAXE || itemType == Material.DIAMOND_SHOVEL)
        	{
        		addToDamage = 1;
        	}
        }
        
        else if (item.getEnchantments().containsKey(Enchantment.DURABILITY))
        {
        	double level = item.getEnchantmentLevel(Enchantment.DURABILITY);
        	double chance = 100 / (level + 1);
        	double rng = (Math.random() * 100) + 1;
        	if (rng >= chance) 
        	{
        		addToDamage = 0;
        	}
        }
        
        // Update durability
    	dMeta.setDamage(currDur + addToDamage);
    	item.setItemMeta(meta);
	}
	
	/**
	 * Handle the breaking of blocks via the lumber-axe
	 * @param bbEvent The event that triggered this method
	 */
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void Chop(BlockBreakEvent bbEvent) 
	{
		// Grab current tool information
		Player player = bbEvent.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		Material itemType = item.getType();
		
		// Get block information via the player listener
		Block block = bbEvent.getBlock();
		
		// Only do extra block-breaking if the player is not sneaking (method of disabling)
		// Or if the player is breaking the incorrect block/using the incorrect tool
		if (player != null && (player instanceof Player))
		{
			if (player.isSneaking() || !ToolUtil.IsLumberAxeable(item, block.getType()))
			{
				return;
			}
		}
		
		// Get all of the relevant blocks that are a part of the tree
		ArrayList<Block> blocks = ToolUtil.GetUpwardLogs(block);
		
        // Grab durability information
        ItemMeta meta = item.getItemMeta();
        Damageable dMeta = (Damageable)meta;
        int currDur = dMeta.getDamage();
        int maxDur = item.getType().getMaxDurability();
        
        // Used in determining if an extra block was broken (for durability)
        boolean success = false;
        if (blocks.size() > 1)
        {
        	System.out.println("Blocks.size() = " + blocks.size());
        	success = true;
        }
        
        for (Block b : ToolUtil.GetUpwardLogs(block))
        {
        	// Determine the block type and position
        	Material blockMat = b.getType();
        	Location blockLoc = b.getLocation();
        	
        	// Determine whether an appropriate tool is being used
        	boolean isAxe = ToolUtil.IsLumberAxeable(item, blockMat);
        	
        	if (isAxe)
        	{
        		b.breakNaturally();
        	}
        }
        
        // Used for determining durability loss
        int addToDamage = 1;
        
        // Do extra damage to durability if extra blocks were broken
        if (success && !item.getEnchantments().containsKey(Enchantment.DURABILITY))
        {
        	addToDamage = 2;
        	
        	// Diamond tools take less damage
        	if (itemType == Material.DIAMOND_AXE)
        	{
        		addToDamage = 1;
        	}
        }
        
        else if (item.getEnchantments().containsKey(Enchantment.DURABILITY))
        {
        	double level = item.getEnchantmentLevel(Enchantment.DURABILITY);
        	double chance = 100 / (level + 1);
        	double rng = (Math.random() * 100) + 1;
        	if (rng >= chance) 
        	{
        		addToDamage = 0;
        	}
        }
        
        // Update durability
    	dMeta.setDamage(currDur + addToDamage);
    	item.setItemMeta(meta);
	}
}
