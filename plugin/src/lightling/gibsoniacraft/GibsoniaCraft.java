package lightling.gibsoniacraft;

import org.bukkit.plugin.java.JavaPlugin;

import lightling.gibsoniacraft.crafting.Excavator;
import lightling.gibsoniacraft.util.PlayerInteractListener;

public final class GibsoniaCraft extends JavaPlugin {
	
	private Excavator excavatorClass;
	private PlayerInteractListener pListener;
	
	@Override
	public void onEnable() {
		excavatorClass = new Excavator(this);
	}
	
	@Override
	public void onDisable() {
		
	}

	public PlayerInteractListener GetPlayerInteractListener()
	{
		return pListener;
	}
	
}
