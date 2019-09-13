package lightling.gibsoniacraft.util;

// Needed for block information
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
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
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void Break(BlockBreakEvent bbEvent)
	{
		// Grab current tool information
		Player player = bbEvent.getPlayer();
		ItemStack item = player.getInventory().getItemInMainHand();
		Material itemType = item.getType();
		
		// Only do extra block-breaking if the player is not sneaking (method of disabling) or if the player doesn't have the appropriate tools
		if (player != null && (player instanceof Player))
		{
			if (player.isSneaking())
			{
				return;
			}
	        if (!ToolUtil.IsExcavator(item) && !ToolUtil.IsHammer(item))
	        {
	        	return;
	        }
		}
		
		// Get blockface information via the player listener
		Block block = bbEvent.getBlock();
		String pName = player.getName();
        PlayerInteractListener pListener = gcPlugin.GetPlayerInteractListener();
        BlockFace blockFace = pListener.GetFaceByName(pName);
        
        // getDurability deprecated, must now go through meta information
        ItemMeta meta = item.getItemMeta();
        Damageable dMeta = (Damageable)meta;
        int currDur = dMeta.getDamage();
        int maxDur = item.getType().getMaxDurability();
        
        // Used in determining if an extra block was broken
        boolean success = false;
        
        // Iterates through to break surrounding blocks 
        for (Block b : ToolUtil.GetSurroundingBlocks(blockFace, block))
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
        		success = true;
        		
        		b.breakNaturally();
        	}
        }
        
        // Do extra damage to durability if extra blocks were broken
        if (success && !item.getEnchantments().containsKey(Enchantment.DURABILITY))
        {
        	int addToDamage = 2;
        	if (itemType == Material.DIAMOND_PICKAXE && itemType == Material.DIAMOND_SHOVEL) 
        	{
        		addToDamage = 1;
        	}
        	dMeta.setDamage(currDur + addToDamage);
        	item.setItemMeta(meta);
        }
	}
}
