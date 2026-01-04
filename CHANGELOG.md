# Cobblemon Pokeboxes Changelog

## Latest Changes

### ✅ Merchant System Removed
All merchant-related code has been removed as it was ported to a separate mod:
- Deleted `/merchant/` directory and all subpackages
- Removed `MerchantDebugStick` item
- Removed merchant debug stick model
- Cleaned up all merchant references from ModItems

### ✅ Datapack-Based Loot Pools Implemented
Converted from config-based to datapack-based loot system:

#### New Features:
- **Live Reload Support**: Changes apply with `/reload` command (no server restart!)
- **JSON Format**: Standard Minecraft datapack format
- **Datapack Distribution**: Easy to share and customize

#### New Files:
- `loot/PokeboxLootpoolData.java` - Data structure with JSON codec
- `loot/PokeboxLootpoolManager.java` - Resource reload listener
- `data/cobblemonpokeboxes/pokebox_lootpools/*.json` - 10 loot pool files

#### Modified Files:
- `item/custom/PokeboxLootpool.java` - Now loads from datapacks
- `CobblemonPokeboxes.java` - Registers datapack listener
- `item/ModItems.java` - Removed merchant items

#### Documentation:
- `DATAPACK_GUIDE.md` - Complete guide for creating and using datapacks

### File Structure:
```
data/cobblemonpokeboxes/pokebox_lootpools/
├── lootbox1.json   (Pokeball Pokebox)
├── lootbox2.json   (Greatball Pokebox)
├── lootbox3.json   (Ultraball Pokebox)
├── lootbox4.json   (Miners & Crafters Box)
├── lootbox5.json   (Cosmetic Box)
├── lootbox6.json   (Simple Hats Box)
├── lootbox7.json   (Pokemon Cosmetic Pokebox)
├── lootbox8.json   (Pokedoll Pokebox)
├── lootbox9.json   (Plushie Box)
└── lootbox10.json  (Additional box)
```

### Backwards Compatibility:
The old config files in `config/cobblemonpokeboxes/` are no longer used. All loot pools are now defined via datapacks. The existing TOML configs have been converted to JSON and included in the mod.

### For Server Admins:
1. Edit JSON files in a datapack
2. Run `/reload`
3. Changes take effect immediately!

See `DATAPACK_GUIDE.md` for detailed instructions.
