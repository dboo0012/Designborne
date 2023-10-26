package game.utilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

/**
 * A class that checks if an actor has a certain item.
 *
 * @author Daryl
 */
public class HasItem {
    private Actor actor;
    private Item itemToFind;

    /**
     * Constructor.
     * @param actor the actor to check
     * @param itemToFind the item to find
     */
    public HasItem(Actor actor, Item itemToFind){
        this.actor = actor;
        this.itemToFind = itemToFind;
    }

    /**
     * Iterates the actor's inventory to check if the actor has the item.
     * @return true if the actor has the item, false otherwise
     */
    public boolean actorHasItem() {
        for (Item item : actor.getItemInventory()) {
            if (itemToFind.getClass().isInstance(item)) {
                return true;
            }
        }
        return false;
    }
}
