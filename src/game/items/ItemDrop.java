package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.EntityTypes;
import game.attributes.Status;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Random;

public class ItemDrop {

    private final Random rand = new Random();
    /**
            * Drops items when the actor becomes unconscious.
     *
             * @param actor                The actor that became unconscious.
            * @param map                  The map where the actor fell unconscious.
            * @param itemsToBeDropped     A list of items to be dropped.
            * @param itemsToBeDroppedChance An array of chances to drop items, with indices matching itemsToBeDropped.
     */
    public void dropItems(Actor actor, GameMap map, ArrayList<Class<? extends Item>> itemsToBeDropped, ArrayList<Double> itemsToBeDroppedChance) {
        Location currentLocation = map.locationOf(actor);
        System.out.println(actor);
        //ToDo: doesn't check if the actor that killed it was Player

        for (int i = 0; i < itemsToBeDropped.size(); i++) {
            Class<? extends Item> item = itemsToBeDropped.get(i);
            double chanceToSpawn = itemsToBeDroppedChance.get(i);

            double chance = Math.random();

            System.out.println(item + "|" + chanceToSpawn + "|" + chance);

            if (chance < chanceToSpawn) {
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
