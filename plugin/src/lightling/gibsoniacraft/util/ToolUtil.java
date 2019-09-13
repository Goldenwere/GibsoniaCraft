package lightling.gibsoniacraft.util;

import org.bukkit.Material;

public class ToolUtil 
{
	public boolean IsMineable(Material mat) 
	{
		return BlockRef.ValidHammerBlocks.contains(mat);
	}
	
	public boolean IsDiggable(Material mat) 
	{
		return BlockRef.ValidExcavatorBlocks.contains(mat);
	}
}
