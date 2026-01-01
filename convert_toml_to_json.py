import json
import re
from pathlib import Path

def parse_toml_lootbox(toml_path):
    """Parse a TOML lootbox config and convert to JSON format."""
    with open(toml_path, 'r', encoding='utf-8') as f:
        content = f.read()

    # Extract name
    name_match = re.search(r'Name = "([^"]+)"', content)
    name = name_match.group(1) if name_match else "Unknown Lootbox"

    # Extract item ID
    item_id_match = re.search(r'ItemID = "([^"]+)"', content)
    item_id = item_id_match.group(1) if item_id_match else "pokeball_lootbox"

    # Extract tiers
    tiers = []
    tier_sections = re.findall(r'\[lootpool\."Loot Tier: \d+"\](.*?)(?=\[lootpool\."Loot Tier:|$)', content, re.DOTALL)

    for tier_content in tier_sections:
        # Extract weight
        weight_match = re.search(r'Weight = (\d+)', tier_content)
        weight = int(weight_match.group(1)) if weight_match else 0

        # Extract color
        color_match = re.search(r'Color = "([^"]+)"', tier_content)
        color = color_match.group(1) if color_match else "none"

        # Extract items
        items_match = re.search(r'Items = \[(.*?)\]', tier_content, re.DOTALL)
        items = []
        if items_match:
            items_str = items_match.group(1)
            # Split by commas and clean up
            item_entries = [s.strip().strip('"') for s in items_str.split(',')]
            # Pair up item names and counts
            for i in range(0, len(item_entries), 2):
                if i + 1 < len(item_entries):
                    item_name = item_entries[i]
                    count = int(item_entries[i + 1]) if item_entries[i + 1].isdigit() else 1
                    items.append({"item": item_name, "count": count})

        tiers.append({
            "weight": weight,
            "color": color,
            "items": items
        })

    return {
        "name": name,
        "item_id": item_id,
        "tiers": tiers
    }

# Convert all lootbox TOML files
config_dir = Path(r"C:\Users\owens\AppData\Roaming\ModrinthApp\profiles\Createmon_ (V1.0.2)\config\cobblemonpokeboxes")
output_dir = Path(r"c:\Users\owens\projects\misc\minecraft_modding\custom\cobblemonpokeboxes\src\main\resources\data\cobblemonpokeboxes\pokebox_lootpools")

output_dir.mkdir(parents=True, exist_ok=True)

for i in range(1, 11):
    toml_file = config_dir / f"lootbox{i}-server.toml"
    if toml_file.exists():
        print(f"Converting lootbox{i}...")
        data = parse_toml_lootbox(toml_file)

        json_file = output_dir / f"lootbox{i}.json"
        with open(json_file, 'w', encoding='utf-8') as f:
            json.dump(data, f, indent=2)
        print(f"  -> Created {json_file}")

print("Conversion complete!")
