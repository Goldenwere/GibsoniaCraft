package lightling.gibsoniacraft;

import org.bukkit.plugin.java.JavaPlugin;

import lightling.gibsoniacraft.crafting.Excavator;

public final class GibsoniaCraft extends JavaPlugin {
	
	private Excavator excavatorClass;
	
	@Override
	public void onEnable() {
		excavatorClass = new Excavator(this);
	}
	
	@Override
	public void onDisable() {
		
	}

}
