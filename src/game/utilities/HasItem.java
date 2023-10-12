package game.utilities;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.weapons.BroadSword;
import game.weapons.GiantHammer;

public class HasItem {
    private Actor actor;
    private Item itemToFind;
    public HasItem(Actor actor, Item itemToFind){
        this.actor = actor;
        this.itemToFind = itemToFind;
    }

    public boolean actorHasItem() {
        for (Item item : actor.getItemInventory()) {
            if (itemToFind.getClass().isInstance(item)) {
                return true;
            }
        }
        return false;
    }
}
