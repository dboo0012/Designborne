# FIT 2099 - Assignment 2

# DESIGNBORNE

Assignment members:
* dboo0012@student.monash.edu (Daryl Boon Yang 32836899)
* mche0170@student.monash.edu (Jerry Chew Ming Kang)
* szai0011@student.monash.edu (Syed Meekal Hussain Zaidi)

Contribution Log:
https://docs.google.com/spreadsheets/d/1vj8J0GAtlLftseKgvPqccp7mSe9SyKv6kHdS1xSvJjk/edit?usp=sharing

TODO:

- Tasks
- [ ] REQ 1:
- [ ] REQ 2:
- [ ] REQ 3:
- [ ] REQ 4:
- [ ] REQ 5:
- UML for each REQ
- Design rationale (SOLID, DRY principles) (DONE)
- Javadoc (DONE)
- Final check (magic numbers)
## Notes:

### REQ 1: The Ancient Woods
**1. New map**
- Gate required (accessed from Burial Ground)
- empty huts

**2. Enemy**
- huts, "h"
- Forest Keeper, "8", 15% spawn rate, 125HP, 25dmg, 75% Hitrate
  - 20% chance to drop a healing vial.
- Bushes, "r"
- Red Wolf, "r", 30% spawn rate, 25HP, 15dmg, 80% Hitrate
  - 10% chance of dropping a healing vial
- Can walk freely on void
- FOLLOW player when in proximity (until either entity dies)

### REQ 2: Deeper into the woods
**1. Runes**
"$"
- Drop Runes only when slain (not environmental death)
- Forest Keeper, 50
- Red Wolf, 25
- Wandering Undead, 50
- Hollow soldier, 100

**2. Water**
- Puddle, add drink ability
- Gain 1HP, 1% of max stamina

**3. Bloodberry**
- "*", portable
- add to map as Item
- Maximum HP +5 permanently

### REQ 3: The Isolated Traveller
**1. Suspicious traveller**
- Located in old building
- “ඞ”
- Purchase and sell items using Runes.

**2. Store items (traveller -> player)**
- If the player’s balance is less than what the traveller asks for, the purchase fails
- Healing Vials, 100 runes
  - 25% chance that the traveller asks to pay 50% extra
- Refreshing flasks, 75 runes
  - 10% chance that the traveller gives a 20% discount to the player
- Broadswords, 250 runes
  - 5% chance to be scammed

**3. Sell Items (player -> traveller)**
- Healing vials, 35 runes
  - 10% chance get 2x original price
- Refreshing flasks, 25 runes
  - 50% chance to get scammed
- Broadswords, 100 runes
- Bloodberries, 10 runes
- Old keys, n/a

### REQ 4: The room at the end of the forest
**1. Great knife Weapon**
- Sold to player before leaving the old building
- ">", 75 damage, 70% Hitrate,
  - skill: "stab and step"
  - 300 runes
  - 25% of max stamina
    **2. Purchasing Great Knife**
  - 5% chance to pay 3x price
  - insuffecient balance, rejected

**3. Sell Great knife**
- 175 runes,
- 10% chance getting scammed
- player has < 175 runes, only balance is taken (no -ve balance)

**4. Locked gate**
- New gate to boss in a room
- huts and bushes that spawn respective enemy

**5. Giant Hammer Weapon**
- "P", 160dmg, 90 Hitrate
  - Skill: "Great Slam"
  - 5% of players maximum stamina
  - Sold for 250 runes, cannot be bought

### REQ 5: Hollow Soldier (Inhabitants of Burial Grounds)
- Boos room is a new map travelled from gate in the forest
  **1. Boss**
- “Abxervyer, the Forest Watcher”
- "Y", 2000HP, 80dmg, 25% Hitrate
- same logic as other enemy (* immune to void)

**2. Special ability**
- Sunny
  - hut spawn rate x2
  - red wolf 3x dmg
  - bush spawn rate normal
- Rainy
  - bush spawn rate 1.5x
  - Forest keeper healed 10 points?
  - Huts’ spawning rate and red wolf damage return to normal
- Weather alternates every 3 turns until boss dead
- * Boss change weather even if player not in room

**3. End**
- Once defeated, spawn a gate at last location
- TP back to ancient woods
- 5000 runes dropped
- Print message
