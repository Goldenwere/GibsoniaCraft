package lightling.gibsoniacraft.util;

// Needed for block information
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

// Needed for handling events
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import lightling.gibsoniacraft.GibsoniaCraft;

// Needed to determine if using a GibsoniaCraft tool or not
import org.bukkit.inventory.ItemStack;

/**
 * A listener for block-based events, used primarily for blocks being broken by GibsoniaCraft tools
 * @author Lightling
 */
public class BlockListener implements Listener
{		
	public BlockListener(GibsoniaCraft plugin) 
	{
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void Break(GibsoniaCraft plugin, BlockBreakEvent bbEvent)
	{
		Player player = bbEvent.getPlayer();
		ItemStack stack = player.getInventory().getItemInMainHand();
		Material itemType = stack.getType();
		
		if (player != null && (player instanceof Player))
		{
			if (player.isSneaking())
				return;
	        if (!ToolUtil.IsExcavator(itemType) || !ToolUtil.IsHammer(itemType))
	            return;
		}
		
		Block block= bbEvent.getBlock();
		final String pName = player.getName();
        final PlayerInteractListener pListener = plugin.GetPlayerInteractListener();
        final BlockFace blockFace = pListener.GetFaceByName(pName);
	}
}
