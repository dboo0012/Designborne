package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.Status;

import java.util.ArrayList;

public class ItemDrop {
    /**
            * Drops items when the actor becomes unconscious.
     *
             * @param actor                The actor that became unconscious.
            * @param map                  The map where the actor fell unconscious.
            * @param itemsToBeDropped     A list of items to be dropped.
            * @param itemsToBeDroppedChance An array of chances to drop items, with indices matching itemsToBeDropped.
     */
    public void dropItems(Actor actor, GameMap map, ArrayList<Class<? extends Item>> itemsToBeDropped, int[] itemsToBeDroppedChance) {
        Location currentLocation = map.locationOf(this);

        for (int i = 0; i < itemsToBeDropped.size(); i++) {
            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
                Class<? extends Item> item = itemsToBeDropped.get(i);
                int chanceToSpawn = itemsToBeDroppedChance[i];
                System.out.println(item + "|" + chanceToSpawn);
                if (rand.nextInt(100) < chanceToSpawn) {
                    try {
                        map.at(currentLocation.x(), currentLocation.y()).addItem(item.getConstructor().newInstance());
                    } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
                             InvocationTargetException e) {
                        // Handle exceptions if necessary
                    }

                }
            }
        }
    }
}
