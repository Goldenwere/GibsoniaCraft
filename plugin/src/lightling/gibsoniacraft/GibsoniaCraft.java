package lightling.gibsoniacraft;

import org.bukkit.plugin.java.JavaPlugin;

import lightling.gibsoniacraft.crafting.Excavator;
import lightling.gibsoniacraft.crafting.Hammer;
import lightling.gibsoniacraft.util.BlockListener;
import lightling.gibsoniacraft.util.PlayerInteractListener;

public final class GibsoniaCraft extends JavaPlugin {
	
	private Excavator excavatorClass;
	private Hammer hammerClass;
	private PlayerInteractListener pListener;
	private BlockListener bListener;
	
	@Override
	public void onEnable() {
		excavatorClass = new Excavator(this);
		hammerClass = new Hammer(this);
		pListener = new PlayerInteractListener(this);
		bListener = new BlockListener(this);
	}
	
	@Override
	public void onDisable() {
		
	}

	public PlayerInteractListener GetPlayerInteractListener()
	{
		return pListener;
	}
	
}
