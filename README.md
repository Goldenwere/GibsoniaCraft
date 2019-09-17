# GibsoniaCraft

## About

GibsoniaCraft is a plugin created for a dorm Minecraft server. GibsoniaCraft is partially a remake from the ground up of BradzCraft (a plugin made for a previous dorm server) (Credit: [BradzTech](https://github.com/bradztech)). There are three goals behind GibsoniaCraft:
- To have a custom plugin with desired features for for an otherwise lightweight server
- To begin learning Java
- To begin learning Bukkit API (and subsequent derivatives (-> Spigot -> Paper))

## Features

### Tools

GibsoniaCraft adds new tools to the server. Excavator/Hammer based off of BradzCraft which extends [PowerTool](https://bitbucket.org/bloodyshade/powermining/src/default/) which is based off of [Tinkers' Construct](https://github.com/SlimeKnights/TinkersConstruct). Lumber Axe concept based off of Tinkers' Construct. 

#### Current Features

- Excavator
  - Digs in a 3x3 square based off of the targetted direction
  - Crafted with four of a corresponding tool material in a circle surrounding a shovel
- Hammer
  - Mines in a 3x3 square based off of the targetted direction
  - Crafted with four of a corresponding tool material in a circle surrounding a pickaxe
- Lumber Axe
  - Chops an entire tree from the bottom-up
  - Crafted with four of a corresponding tool material in a circle surrounding an axe
  
#### Current Issues

- [Tools](#Tools) does not account for enchantments (other than unbreaking)

## License
[MIT](LICENSE)
