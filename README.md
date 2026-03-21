# ⚔️ Ancients Mod — Forge 1.20.1

A Minecraft Java Edition mod that unlocks the **Ancient City's hidden portal** and adds a brand new **Ancients Dimension** filled with danger.

---

## 📦 Features

### 🔥 Ancient Igniter
- **Recipe:** Cobblestone (top) + Flint (bottom) — 2x1 crafting
- A glowing special flint & steel that emits light
- **Only this item** can activate the Ancient City portal
- Using any other flint & steel will show a warning message

### 🌀 Ancient City Portal Activation
1. Find an **Ancient City** in deep underground (y = -51)
2. Locate the **portal frame** (surrounded by Reinforced Deepslate)
3. Right-click the portal frame with your **Ancient Igniter**
4. The portal hums and awakens!
5. Step through to enter the **Ancients Dimension**

### 🌑 The Ancients Dimension
A dark, terrifying fusion of:
- **Overworld** — terrain structure, cave systems
- **Nether** — lava seas, blazes, piglin brutes
- **The End** — eerie atmosphere, endermen, phantoms
- **Ancient City** — sculk surface, deepslate terrain, dark blue fog

**Dimension properties:**
- Surface: Sculk + Deepslate
- No sun, ceiling exists, permanent dark
- Ambient light: very low (0.1)
- Lava as default fluid
- Monsters spawn at ALL light levels

### 🐉 New Mobs

#### Ancient Warden (Passive until attacked)
- Lives in **Warden Villages** scattered across the dimension
- **500 HP**, 30 attack damage — extremely dangerous if provoked
- Will NOT attack you unless you hit them first
- Has full knockback resistance
- Does not despawn

#### Soul Phantom (Hostile, flying)
- Combination of Phantom + Blaze
- Always on fire
- **40 HP**, flies fast, attacks on sight
- Spawns in the open skies of the dimension

#### Void Creeper (Hostile, teleporting)
- Combination of Creeper + Enderman
- **Teleports randomly** right before it explodes
- **25 HP**, sneaky and unpredictable
- Explosion radius: 4 blocks

### 🏘️ Warden Village
- Spawns naturally in the Ancients Dimension
- Built from deepslate and sculk blocks
- Ancient Wardens patrol and live here peacefully
- Attack them and the whole village turns hostile!

### 👾 Mixed Mob Spawns
The dimension spawns a combination of:
- Zombies, Skeletons (Overworld)
- Blazes, Piglin Brutes (Nether)
- Endermen, Phantoms (End)
- Ancient Wardens, Soul Phantoms, Void Creepers (Custom)

---

## 🛠️ Setup & Installation

### Requirements
- Java 17+
- Minecraft Java Edition 1.20.1
- Forge 47.2.0+

### Building from Source

1. **Download Forge MDK** for 1.20.1 from [files.minecraftforge.net](https://files.minecraftforge.net)
2. Extract the MDK
3. **Replace** the `src/` folder with this mod's `src/` folder
4. **Copy** `build.gradle`, `settings.gradle`, `gradle.properties` from this mod
5. Open a terminal in the mod folder and run:

```bash
# Windows
gradlew.bat genEclipseRuns   (for Eclipse)
gradlew.bat genIntellijRuns  (for IntelliJ)

# Mac/Linux
./gradlew genEclipseRuns
./gradlew genIntellijRuns
```

6. To build the `.jar` file:
```bash
gradlew.bat build   # Windows
./gradlew build     # Mac/Linux
```

7. Find your mod `.jar` in `build/libs/`
8. Drop it in your Minecraft `mods/` folder!

---

## 🎨 Adding Textures (Required)

You need to add texture images for:

| File | Path |
|------|------|
| Ancient Igniter | `assets/ancientsmod/textures/item/ancient_igniter.png` |
| Ancient Warden | `assets/ancientsmod/textures/entity/ancient_warden.png` |
| Soul Phantom | `assets/ancientsmod/textures/entity/soul_phantom.png` |
| Void Creeper | `assets/ancientsmod/textures/entity/void_creeper.png` |

All textures should be **16x16 PNG** files (or multiples like 32x32, 64x64 for entities).

---

## 📁 Project Structure

```
ancientsmod/
├── build.gradle
├── settings.gradle
├── gradle.properties
└── src/main/
    ├── java/com/ancients/mod/
    │   ├── AncientsMod.java           ← Main mod class
    │   ├── block/
    │   │   └── AncientPortalBlock.java
    │   ├── item/
    │   │   └── AncientIgniterItem.java
    │   ├── entity/
    │   │   ├── AncientWardenEntity.java
    │   │   ├── SoulPhantomEntity.java
    │   │   └── VoidCreeperEntity.java
    │   ├── event/
    │   │   ├── PortalActivationHandler.java
    │   │   └── DimensionTravelHandler.java
    │   └── registry/
    │       ├── ModItems.java
    │       ├── ModBlocks.java
    │       ├── ModEntities.java
    │       ├── ModDimensions.java
    │       └── ModBiomes.java
    └── resources/
        ├── META-INF/mods.toml
        ├── pack.mcmeta
        ├── assets/ancientsmod/
        │   ├── lang/en_us.json
        │   ├── models/item/ancient_igniter.json
        │   └── textures/          ← ADD YOUR TEXTURES HERE
        └── data/ancientsmod/
            ├── recipes/ancient_igniter.json
            ├── dimension/ancients_dimension.json
            └── worldgen/
                ├── biome/ancients_biome.json
                ├── dimension_type/ancients_dimension_type.json
                ├── noise_settings/ancients_noise_settings.json
                └── structure/warden_village.json
```

---

## 🧪 Testing In-Game Commands

```
/give @s ancientsmod:ancient_igniter
/tp @s ~ ~ ~   (after dimension loads)
/summon ancientsmod:ancient_warden
/summon ancientsmod:soul_phantom
/summon ancientsmod:void_creeper
```

---

## 📜 License
MIT — Free to use and modify!
