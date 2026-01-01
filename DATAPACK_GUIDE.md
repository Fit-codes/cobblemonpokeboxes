# Pokebox Loot Pool Datapack Guide

## Overview

As of the latest version, Cobblemon Pokeboxes now supports **datapack-based loot pools** that can be reloaded without restarting the server using the `/reload` command!

## Benefits

- ✅ **No server restart required** - Changes apply with `/reload`
- ✅ **Datapack distribution** - Share loot pools as datapacks
- ✅ **Easy customization** - JSON format is easy to edit
- ✅ **Server-side control** - Server admins have full control

## File Structure

Loot pool JSON files go in the `data/<namespace>/pokebox_lootpools/` directory.

### For the mod itself (built-in):
```
src/main/resources/data/cobblemonpokeboxes/pokebox_lootpools/
├── lootbox1.json
├── lootbox2.json
├── lootbox3.json
└── ...
```

### For a server datapack:
```
world/datapacks/my_custom_loot/
└── data/
    └── cobblemonpokeboxes/
        └── pokebox_lootpools/
            ├── lootbox1.json
            ├── lootbox2.json
            └── ...
```

### For a custom namespace:
```
world/datapacks/my_loot_pack/
└── data/
    └── mypack/
        └── pokebox_lootpools/
            └── custom_box1.json
```

## JSON Format

Each loot pool file follows this structure:

```json
{
  "name": "Pokeball Pokebox",
  "item_id": "pokeball_lootbox",
  "tiers": [
    {
      "weight": 130,
      "color": "none",
      "items": [
        {
          "item": "cobblemon:relic_coin",
          "count": 2
        },
        {
          "item": "cobblemon:premier_ball",
          "count": 4
        }
      ]
    },
    {
      "weight": 59,
      "color": "green",
      "items": [
        {
          "item": "cobblemon:ultra_ball",
          "count": 4
        }
      ]
    }
  ]
}
```

### Field Descriptions

- **name**: Display name of the loot box (currently informational)
- **item_id**: The item ID this loot pool is for (e.g., `pokeball_lootbox`)
- **tiers**: Array of loot tiers (can have 1-6 tiers)

#### Tier Fields:
- **weight**: Drop weight for this tier (higher = more common). Set to 0 to disable a tier
- **color**: Tier color identifier (e.g., "none", "green", "blue", "purple", "red", "yellow")
- **items**: Array of possible item drops for this tier

#### Item Fields:
- **item**: Resource location of the item (e.g., `cobblemon:rare_candy`, `minecraft:diamond`)
- **count**: Number of items to give

## Loot Pool Numbering

The loot pool number is extracted from the filename:
- `lootbox1.json` → Loot pool #1
- `lootbox2.json` → Loot pool #2
- `custom_box5.json` → Loot pool #5

The pokebox items are configured to use specific loot pool numbers:
- Pokeball Pokebox → #1
- Greatball Pokebox → #2
- Ultraball Pokebox → #3
- Miners & Crafters Box → #4
- Cosmetic Box → #5
- Simple Hats Box → #6
- Pokemon Cosmetic Pokebox → #7
- Pokedoll Pokebox → #8
- Plushie Box → #9

## How Tiers Work

### Weight System

Tiers use a cumulative weight system. For example:
- Tier 1: weight 130 (rolls 1-130)
- Tier 2: weight 59 (rolls 131-189)
- Tier 3: weight 27 (rolls 190-216)
- Total: 216 possible outcomes

When opened, the pokebox rolls a random number and selects the corresponding tier, then picks a random item from that tier.

### Disabling Tiers

Set a tier's weight to 0 to disable it entirely:

```json
{
  "weight": 0,
  "color": "none",
  "items": []
}
```

## Creating a Custom Datapack

1. Create a folder structure:
   ```
   my_custom_loot/
   ├── pack.mcmeta
   └── data/
       └── cobblemonpokeboxes/
           └── pokebox_lootpools/
               └── lootbox1.json
   ```

2. Create `pack.mcmeta`:
   ```json
   {
     "pack": {
       "pack_format": 48,
       "description": "My Custom Pokebox Loot"
     }
   }
   ```

3. Add your loot pool JSON files

4. Place the datapack folder in your world's `datapacks/` folder

5. Run `/reload` in-game

## Testing Changes

1. Edit a JSON file in `data/cobblemonpokeboxes/pokebox_lootpools/`
2. Run `/reload` command
3. The changes take effect immediately!
4. Check the server log to see confirmation:
   ```
   [PokeboxLootpoolManager]: Loading pokebox loot pools from datapacks...
   [PokeboxLootpoolManager]: Loaded loot pool 1 (pokeball_lootbox): Pokeball Pokebox
   ...
   [PokeboxLootpoolManager]: Loaded 10 pokebox loot pools
   ```

## Example: Adding More Rare Items

To make a tier drop more valuable items, just add them to the items array:

```json
{
  "weight": 10,
  "color": "purple",
  "items": [
    {
      "item": "cobblemon:master_ball",
      "count": 1
    },
    {
      "item": "cobblemon:ability_patch",
      "count": 1
    },
    {
      "item": "minecraft:netherite_ingot",
      "count": 5
    }
  ]
}
```

## Troubleshooting

### Loot pool not loading
- Check the filename has a number (e.g., `lootbox1.json`)
- Verify JSON syntax is valid (use a JSON validator)
- Check server logs for error messages

### Items not appearing
- Verify item IDs are correct (namespace:item_name)
- Make sure the mod providing the item is installed
- Check that the tier weight is greater than 0

### Changes not applying
- Did you run `/reload`?
- Check for JSON syntax errors in the log
- Make sure the file is in the correct directory

## Migration from Config Files

The old TOML config files in `config/cobblemonpokeboxes/` are no longer used. All loot pools are now defined in datapacks.

Your existing config files have been converted to JSON and are included in the mod. You can override them by creating a datapack with the same filenames.

## Need Help?

If you encounter issues, check the server logs for detailed error messages from the PokeboxLootpoolManager class.
