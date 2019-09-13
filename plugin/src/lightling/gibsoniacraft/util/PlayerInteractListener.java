package lightling.gibsoniacraft.util;

import java.util.HashMap;								// Needed to store block-face information

import org.bukkit.block.BlockFace;						// Needed by BlockBreakListener which calls upon this class
import org.bukkit.entity.Player;						// The main focus of this class
import org.bukkit.plugin.java.JavaPlugin;				// Needed to register events on the server

// Needed for events
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * A listener for player interactions, primarily saves block-face information
 * @author Lightling
 */
public class PlayerInteractListener implements Listener
{
	private HashMap<String, BlockFace> faces;
	
	public PlayerInteractListener(JavaPlugin plugin) 
	{
		faces = new HashMap<String, BlockFace>();
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}
	
	@EventHandler(priority = EventPriority.LOW, ignoreCancelled = true)
	public void SaveBlockFace(PlayerInteractEvent event) 
	{
		Player player = event.getPlayer();
		BlockFace bFace = event.getBlockFace();
		 
		if (player != null && bFace != null)
		{
			String pName = player.getName();
			faces.put(pName, bFace);
		}
	}
	 
	/**
	 * Gets a block-face with a player's name
	 * @param name The name being searched
	 * @return The matching block face
	 */
	public BlockFace GetFaceByName(String name)
	{
		return faces.get(name);
	}
}
