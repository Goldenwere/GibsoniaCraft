# GibsoniaCraft

## About

GibsoniaCraft is a plugin created for a dorm Minecraft server. GibsoniaCraft is partially a remake from the ground up of BradzCraft (a plugin made for a previous dorm server) (Credit: [BradzTech](https://github.com/bradztech)). There are three goals behind GibsoniaCraft:
- To have a custom plugin with desired features for for an otherwise lightweight server
- To begin learning Java
- To begin learning Bukkit API (and subsequent derivatives (-> Spigot -> Paper))

## Features

### Tools & Blocks

GibsoniaCraft adds new tools and blocks to the server.  
Note: Excavator/Hammer based off of BradzCraft which extends [PowerTool](https://bitbucket.org/bloodyshade/powermining/src/default/) which is based off of [Tinkers' Construct](https://github.com/SlimeKnights/TinkersConstruct). Lumber Axe concept based off of Tinkers' Construct. 

#### Current Features

- Excavator (Tool)
  - Digs in a 3x3 square based off of the targetted direction
  - Crafted with four of a corresponding tool material in a circle surrounding a shovel
- Hammer (Tool)
  - Mines in a 3x3 square based off of the targetted direction
  - Crafted with four of a corresponding tool material in a circle surrounding a pickaxe
- Lumber Axe (Tool)
  - Chops an entire tree from the bottom-up
  - Crafted with four of a corresponding tool material in a circle surrounding an axe
- Chunk Loader (Block)
  - Keeps a chunk loaded when no player is inside it.
  - Can only place one per chunk
  - Removing chunk loader will stop the chunk from being loaded forcefully
  - Crafted with three obsidian (along bottom row), 1 magma block (center center), 1 prismarine crystals (top center), and 4 emeralds (remaining mid/upper sides)
  
#### Current Issues

- Chunk Loader: If a chunk loader and a beacon are placed in the same chunk, either block will drop the chunk loader first and the beacon next
  - This is a limitation of the server plugin interface: custom block items lose all their custom metadata when placed in the world. There is no known workaround.

## License
[MIT](LICENSE)
