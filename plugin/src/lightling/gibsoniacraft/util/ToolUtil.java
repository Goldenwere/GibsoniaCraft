package lightling.gibsoniacraft.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.World;

/**
 * Manages calculations regarding added tools
 * @author Lightling
 */
public class ToolUtil 
{
	/**
	 * Determines whether a block is mineable
	 * @param mat The material being checked
	 * @return Whether the block is mineable
	 */
	public static boolean IsMineable(Material mat) 
	{
		return BlockRef.ValidHammerBlocks.contains(mat);
	}
	
	/**
	 * Determines whether a block is diggable
	 * @param mat The material being checked
	 * @return Whether the block is diggable
	 */
	public static boolean IsDiggable(Material mat) 
	{
		return BlockRef.ValidExcavatorBlocks.contains(mat);
	}
	
	/**
	 * Determines whether a block is axe-able
	 * @param mat The material being checked
	 * @return Whether the block is axe-able
	 */
	public static boolean IsAxeable(Material mat)
	{
		return BlockRef.ValidLumberAxeBlocks.contains(mat);
	}
	
	/**
	 * Determines whether an item is a valid hammer
	 * @param item The item being checked
	 * @return Whether the item is a valid hammer
	 */
	public static boolean IsHammer(ItemStack item) 
	{
		List<String> lore = item.getLore();
		Material mat = item.getType();
		if (lore != null && mat != null)
			return BlockRef.ValidHammers.contains(mat) && lore.contains("Based off of item from PowerMining/Tinkers' Construct");
		else
			return false;
	}
	
	/**
	 * Determines whether an item is a valid excavator
	 * @param item The item being checked
	 * @return Whether the item is a valid excavator
	 */
	public static boolean IsExcavator(ItemStack item) 
	{
		List<String> lore = item.getLore();
		Material mat = item.getType();
		if (lore != null && mat != null)
			return BlockRef.ValidExcavators.contains(mat) && lore.contains("Based off of item from PowerMining/Tinkers' Construct");
		else
			return false;
	}
	
	/**
	 * Determines whether an item is a valid lumber-axe
	 * @param item The item being checked
	 * @return Whether the item is a valid lumber-axe
	 */
	public static boolean IsLumberAxe(ItemStack item)
	{
		List<String> lore = item.getLore();
		Material mat = item.getType();
		if (lore != null && mat != null)
			return BlockRef.ValidLumberAxes.contains(mat) && lore.contains("Based off of item from Tinkers' Construct");
		else
			return false;
	}
	
	/**
	 * Determines whether a valid hammer can take place
	 * @param ham The item that should be a hammer
	 * @param block The block that should be mineable
	 * @return Valid or invalid action
	 */
	public static boolean IsHammerable(ItemStack ham, Material block) 
	{
		return IsMineable(block) && IsHammer(ham);
	}
	
	/**
	 * Determines whether a valid excavation can take place
	 * @param exc The item that should be an excavator
	 * @param block The block that should be diggable
	 * @return Valid or invalid action
	 */
	public static boolean IsExcavatable(ItemStack exc, Material block) 
	{
		return IsDiggable(block) && IsExcavator(exc);
	}
	
	/**
	 * Determines whether a valid lumber-axe execution can take place
	 * @param axe The item that should be a lumber-axe
	 * @param block The block that should be axe-able
	 * @return Valid or invalid action
	 */
	public static boolean IsLumberAxeable(ItemStack axe, Material block)
	{
		return IsAxeable(block) && IsLumberAxe(axe);
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
	
	public static ArrayList<Block> GetUpwardLogs(Block target)
	{
		// Create the list to work with
		ArrayList<Block> blocks = new ArrayList<Block>();
		
		// Reference the world-space
		World world = target.getWorld();
		
		// Determine the anchor XYZ coordinates
		int x = target.getX();
		int y = target.getY();
		int z = target.getZ();
		
		// Create control for loop
		boolean moreBlocks = true;
		
		// Grab all logs in a tree, ensuring target block is a log first
		if (BlockRef.ValidLumberAxeBlocks.contains(target.getType())) 
		{
			do 
			{
				// Variable that ends loop if remains 0
				int blocksAbove = 0;
				
				// Grab the surrounding blocks that may be wood
				for (int i = -2; i <= 2; i++)
				{
					for (int j = -2; j <= 2; j++)
					{
						// Add all the surrounding logs to the list
						Block temp = world.getBlockAt(x + i, y, z + j);
						if (BlockRef.ValidLumberAxeBlocks.contains(temp.getType()))
						{
							blocks.add(temp);
						}
						
						// Determine if there are still blocks left
						Block tempAbove = world.getBlockAt(x + i, y + 1, z + j);
						if (BlockRef.ValidLumberAxeBlocks.contains(temp.getType()))
						{
							blocksAbove++;
						}
					}
				}
				
				// If there are still blocks left, go up a level for the for-loop
				if (blocksAbove != 0)
				{
					y++;
				}
				
				// Otherwise, end the loop
				else
				{
					moreBlocks = false;
				}
			}
			while (moreBlocks);
		}
		
		return blocks;
	}
	
	// TO-DO: Implement enchantments
	
	// TO-DO: Implement scythes (3x3 harvest) and tillers (3x3 hoe)
}
