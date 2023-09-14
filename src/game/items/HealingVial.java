package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.HealAction;

/**
 * An item that can be used to heal the player.
 */
public class HealingVial extends Item {
    private ActionList actions = new ActionList();
    /***
     * Constructor.
     */
    public HealingVial() {
        super("Healing Vial", 'a', true);
    }

    /**
     * Player is able to heal if item in inventory.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (actor.getItemInventory().contains(this) && actions.size() == 0){
            actions.add(new HealAction(this));
        }
    }

    @Override
    public ActionList allowableActions(Actor owner) {
        return actions;
    }
}
