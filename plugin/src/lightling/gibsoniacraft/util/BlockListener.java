package lightling.gibsoniacraft.util;

// Needed for block information
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Map;

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
		ItemStack tool = player.getInventory().getItemInMainHand();
		Material toolType = tool.getType();
		
		// Grab the current block information
		Block block = bbEvent.getBlock();
		
		// Only do extra block-breaking if the player is not sneaking (method of disabling) or if the player doesn't have the appropriate tools
		if (player != null && (player instanceof Player))
		{
			if (player.isSneaking() || !ToolUtil.IsExcavatable(tool, block.getType()) && !ToolUtil.IsHammerable(tool, block.getType()))
			{
				return;
			}
		}
		
		// Get blockface information via the player listener
		String pName = player.getName();
        PlayerInteractListener pListener = gcPlugin.GetPlayerInteractListener();
        BlockFace blockFace = pListener.GetFaceByName(pName);
        ArrayList<Block> blocks = ToolUtil.GetSurroundingBlocks(blockFace, block, tool);
        
        // Grab durability and enchantment information
        ItemMeta meta = tool.getItemMeta();
        Damageable dMeta = (Damageable)meta;
        int currDur = dMeta.getDamage();
        int maxDur = tool.getType().getMaxDurability();
        Map<Enchantment, Integer> enchantments = tool.getEnchantments();
        
        // Used in determining if an extra block was broken (for durability)
        boolean success = false;
        if (blocks.size() > 1)
        {
        	success = true;
        }
        
        // Iterates through to break surrounding blocks 
        for (Block b : blocks)
        {
        	Location blockLoc = b.getLocation();
        	boolean exc = ToolUtil.IsExcavator(tool);
        	boolean ham = ToolUtil.IsHammer(tool);
        	Material blockMat = b.getType();
            
            // Handle Fortune enchantment
            if (enchantments.containsKey(Enchantment.LOOT_BONUS_BLOCKS) && ham && BlockRef.ValidHammerFortune.containsKey(blockMat)
            	|| enchantments.containsKey(Enchantment.LOOT_BONUS_BLOCKS) && exc && BlockRef.ValidExcavatorFortune.containsKey(blockMat))
            {
            	double level = tool.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
            	Material drop;
            	
            	// Determine drop
            	if (ham)
            	{
            		drop = BlockRef.ValidHammerFortune.get(blockMat);
            	}
            	else 
            	{
            		drop = BlockRef.ValidExcavatorFortune.get(blockMat);
            	}
            	
            	// Wiki: "Fortune I gives a 33% chance to multiply drops by 2"
            	if (level == 1)
            	{
            		double rng = (Math.random() * 100) + 1;
            		if (rng >= 0 && rng < 33)
            		{
            			b.getWorld().dropItemNaturally(blockLoc, new ItemStack(drop));
            			b.setType(Material.AIR);
            		}
            		
            		else
            		{
            			b.breakNaturally();
            		}
            	}
            	
            	// Wiki: "Fortune II gives a chance to multiply drops by 2 or 3 (25% chance each...)"
            	else if (level == 2)
            	{
            		double rng = (Math.random() * 100) + 1;
            		if (rng >= 0 && rng < 25)
            		{
            			b.getWorld().dropItemNaturally(blockLoc, new ItemStack(drop, 1));
            			b.setType(Material.AIR);
            		}
            		
            		else if (rng >= 25 && rng < 50)
            		{
            			b.getWorld().dropItemNaturally(blockLoc, new ItemStack(drop, 2));
            			b.setType(Material.AIR);
            		}
            		
            		else
            		{
                		b.breakNaturally();
            		}
            	}
            	
            	// Wiki: "Fortune III gives a chance to multiply drops by 2, 3, or 4 (20% chance each...)"
            	else 
            	{
            		double rng = (Math.random() * 100) + 1;
            		if (rng >= 0 && rng < 20)
            		{
            			b.getWorld().dropItemNaturally(blockLoc, new ItemStack(drop, 1));
            			b.setType(Material.AIR);
            		}
            		
            		else if (rng >= 20 && rng < 40)
            		{
            			b.getWorld().dropItemNaturally(blockLoc, new ItemStack(drop, 2));
            			b.setType(Material.AIR);
            		}
            		
            		else if (rng >= 40 && rng < 60)
            		{
            			b.getWorld().dropItemNaturally(blockLoc, new ItemStack(drop, 3));
            			b.setType(Material.AIR);
            		}
            		
            		else 
            		{
            			b.breakNaturally();
            		}
            	}
            }
            
            else if (enchantments.containsKey(Enchantment.SILK_TOUCH) && ham && BlockRef.ValidHammerFortune.containsKey(blockMat)
            	|| enchantments.containsKey(Enchantment.SILK_TOUCH) && exc && BlockRef.ValidExcavatorFortune.containsKey(blockMat))
            {
            	b.getWorld().dropItemNaturally(blockLoc, new ItemStack(blockMat));
            	b.setType(Material.AIR);
            }
            
        	// Handle snow
            else if (blockMat == Material.SNOW && exc) 
            {
                @SuppressWarnings("deprecation")
                final ItemStack snow = new ItemStack(Material.SNOWBALL, 1 + b.getData());
                b.getWorld().dropItemNaturally(blockLoc, snow);
            }
            
            // In all other cases, break naturally
            else
            {
            	b.breakNaturally();
            }
        }
        
        
        // Used for determining durability loss
        int addToDamage = 1;
        
        // Do extra damage to durability if extra blocks were broken
        if (success && !enchantments.containsKey(Enchantment.DURABILITY))
        {
        	addToDamage = 2;
        	
        	// Diamond tools take less damage
        	if (toolType == Material.DIAMOND_PICKAXE || toolType == Material.DIAMOND_SHOVEL)
        	{
        		addToDamage = 1;
        	}
        }
        
        else if (enchantments.containsKey(Enchantment.DURABILITY))
        {
        	double level = tool.getEnchantmentLevel(Enchantment.DURABILITY);
        	double chance = 100 / (level + 1);
        	double rng = (Math.random() * 100) + 1;
        	if (rng >= chance) 
        	{
        		addToDamage = 0;
        	}
        }
        
        // Update durability
    	dMeta.setDamage(currDur + addToDamage);
    	tool.setItemMeta(meta);
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
        	success = true;
        }
        
        for (Block b : ToolUtil.GetUpwardLogs(block))
        {        	
        	b.breakNaturally();
        }
        
        // Used for determining durability loss
        int addToDamage = 1;
        
        // Do extra damage to durability if extra blocks were broken
        if (success && !item.getEnchantments().containsKey(Enchantment.DURABILITY))
        {
        	addToDamage = 2;
        	
        	// Diamond tools take less damage
        	if (item.getType() == Material.DIAMOND_AXE)
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
