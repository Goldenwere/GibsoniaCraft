package lightling.gibsoniacraft;

// Server related
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.Chunk;
import org.bukkit.World;
import java.util.ArrayList;

// GibsoniaCraft related
import lightling.gibsoniacraft.crafting.Excavator;
import lightling.gibsoniacraft.crafting.Hammer;
import lightling.gibsoniacraft.crafting.LumberAxe;
import lightling.gibsoniacraft.listener.BlockListener;
import lightling.gibsoniacraft.listener.PlayerInteractListener;
import lightling.gibsoniacraft.crafting.ChunkLoader;

@SuppressWarnings("unused")
public final class GibsoniaCraft extends JavaPlugin {
	
	private Excavator excavatorClass;
	private Hammer hammerClass;
	private LumberAxe lumberAxeClass;
	private ChunkLoader chunkLoaderClass;
	private PlayerInteractListener pListener;
	private BlockListener bListener;
	private ArrayList<Chunk> loadedChunks;
	
	@Override
	public void onEnable() {
		excavatorClass = new Excavator(this);
		hammerClass = new Hammer(this);
		lumberAxeClass = new LumberAxe(this);
		chunkLoaderClass = new ChunkLoader(this);
		pListener = new PlayerInteractListener(this);
		bListener = new BlockListener(this);
		
		// Load all force-loaded chunks upon server restart
		this.loadedChunks = new ArrayList<Chunk>();
        for (final World world : this.getServer().getWorlds()) 
        {
            for (final Chunk chunk : world.getForceLoadedChunks()) 
            {
            	this.loadedChunks.add(chunk);
            }
        }
	}
	
	@Override
	public void onDisable() {
		
	}

	public PlayerInteractListener GetPlayerInteractListener()
	{
		return pListener;
	}
	
	/**
	 * Adds a chunk to the list of force-loaded chunks
	 * @param chunk The chunk to set as force-loaded
	 * @return Whether a chunk was successfully added
	 */
	public boolean ForceChunkActive(final Chunk chunk)
	{
        if (!this.loadedChunks.contains(chunk)) 
        {
            chunk.setForceLoaded(true);
            this.loadedChunks.add(chunk);
            return true;
        }

        return false;
	}
	
	/**
	 * Removes a chunk from the list of force-loaded chunks
	 * @param chunk The chunk to remove as force-loaded
	 * @return Whether a chunk was successfully removed
	 */
	public boolean ForceChunkInactive(final Chunk chunk)
	{
		if (this.loadedChunks.contains(chunk))
		{
			chunk.setForceLoaded(false);
			this.loadedChunks.remove(chunk);
			return true;
		}
		
		return false;
	}
}
