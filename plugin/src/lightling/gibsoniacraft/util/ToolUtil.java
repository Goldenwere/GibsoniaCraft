package lightling.gibsoniacraft.util;

import java.util.ArrayList;
import java.util.Collections;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.World;

public class ToolUtil 
{
	/**
	 * Determines whether a block is mineable
	 * @param mat The material being checked
	 * @return Whether the block is mineable
	 */
	public boolean IsMineable(Material mat) 
	{
		return BlockRef.ValidHammerBlocks.contains(mat);
	}
	
	/**
	 * Determines whether a block is diggable
	 * @param mat The material being checked
	 * @return Whether the block is diggable
	 */
	public boolean IsDiggable(Material mat) 
	{
		return BlockRef.ValidExcavatorBlocks.contains(mat);
	}
	
	/**
	 * Determines whether an item is a valid hammer
	 * @param mat The material being checked
	 * @return Whether the item is a valid hammer
	 */
	public boolean IsHammer(Material mat) 
	{
		return BlockRef.ValidHammers.contains(mat);
	}
	
	/**
	 * Determines whether an item is a valid excavator
	 * @param mat The material being checked
	 * @return Whether the item is a valid excavator
	 */
	public boolean IsExcavator(Material mat) 
	{
		return BlockRef.ValidExcavators.contains(mat);
	}
	
	/**
	 * Determines whether a valid hammer can take place
	 * @param ham The item that should be a hammer
	 * @param block The block that should be mineable
	 * @return Valid or invalid action
	 */
	public boolean IsHammerable(Material ham, Material block) 
	{
		return IsMineable(block) && IsHammer(ham);
	}
	
	/**
	 * Determines whether a valid excavation can take place
	 * @param exc The item that should be an excavator
	 * @param block The block that should be diggable
	 * @return Valid or invalid action
	 */
	public boolean IsExcavatable(Material exc, Material block) 
	{
		return IsDiggable(block) && IsExcavator(exc);
	}
	
	/**
	 * Grabs the surrounding blocks in the world from the one that was mined/dug, and adds them to a list
	 * @param blockFace The face that was targeted (changes the direction of the 3x3 grid)
	 * @param target The block that was targeted
	 * @return A list containing the surrounding blocks
	 */
	public static ArrayList<Block> GetSurroundingBlocks(BlockFace blockFace, Block target)
	{
		// Create the list to work with
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		// Reference the world-space
		World world = target.getWorld();
		
		// Determine the anchor XYZ coordinates
		int x = target.getX();
		int y = target.getY();
		int z = target.getZ();
		
		// Determine the direction of the 3x3 grid
        switch (blockFace) 
        {
        	case UP:
        	case DOWN: {
        		blocks.add(world.getBlockAt(x + 1, y, z));
        		blocks.add(world.getBlockAt(x - 1, y, z));
        		blocks.add(world.getBlockAt(x, y, z + 1));
        		blocks.add(world.getBlockAt(x, y, z - 1));
        		blocks.add(world.getBlockAt(x + 1, y, z + 1));
        		blocks.add(world.getBlockAt(x - 1, y, z + 1));
        		blocks.add(world.getBlockAt(x + 1, y, z - 1));
        		blocks.add(world.getBlockAt(x - 1, y, z - 1));
        		break;
	        }
	        case EAST:
	        case WEST: {
	            blocks.add(world.getBlockAt(x, y, z + 1));
	            blocks.add(world.getBlockAt(x, y, z - 1));
	            blocks.add(world.getBlockAt(x, y + 1, z));
	            blocks.add(world.getBlockAt(x, y - 1, z));
	            blocks.add(world.getBlockAt(x, y + 1, z + 1));
	            blocks.add(world.getBlockAt(x, y - 1, z + 1));
	            blocks.add(world.getBlockAt(x, y + 1, z - 1));
	            blocks.add(world.getBlockAt(x, y - 1, z - 1));
	            break;
	        }
	        case NORTH:
	        case SOUTH: {
	            blocks.add(world.getBlockAt(x + 1, y, z));
	            blocks.add(world.getBlockAt(x - 1, y, z));
	            blocks.add(world.getBlockAt(x, y + 1, z));
	            blocks.add(world.getBlockAt(x, y - 1, z));
	            blocks.add(world.getBlockAt(x + 1, y + 1, z));
	            blocks.add(world.getBlockAt(x - 1, y + 1, z));
	            blocks.add(world.getBlockAt(x + 1, y - 1, z));
	            blocks.add(world.getBlockAt(x - 1, y - 1, z));
	            break;
	        }
	        default:
	        	break;
        }
        
        blocks.removeAll(Collections.singleton((Object)null));
        
        return blocks;
	}
}
