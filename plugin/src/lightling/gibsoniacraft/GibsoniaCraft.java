package lightling.gibsoniacraft;

// Server related
import org.bukkit.plugin.java.JavaPlugin;

// GibsoniaCraft related
import lightling.gibsoniacraft.crafting.Excavator;
import lightling.gibsoniacraft.crafting.Hammer;
import lightling.gibsoniacraft.crafting.LumberAxe;
import lightling.gibsoniacraft.util.BlockListener;
import lightling.gibsoniacraft.util.PlayerInteractListener;

@SuppressWarnings("unused")
public final class GibsoniaCraft extends JavaPlugin {
	
	private Excavator excavatorClass;
	private Hammer hammerClass;
	private LumberAxe lumberAxeClass;
	private PlayerInteractListener pListener;
	private BlockListener bListener;
	
	@Override
	public void onEnable() {
		excavatorClass = new Excavator(this);
		hammerClass = new Hammer(this);
		lumberAxeClass = new LumberAxe(this);
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
