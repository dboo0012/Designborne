package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.Status;
import game.grounds.Gate;
import game.items.OldKey;

/**
 * Action for unlocking the gate.
 *
 * @author Daryl
 */
public class UnlockAction extends Action {
    private final Gate gate;

    /**
     * Constructor.
     * @param gate Gate to be unlocked.
     */
    public UnlockAction(Gate gate) {
        this.gate = gate;
    }

    /**
     * Unlocks the gate if Player has Old key.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String representation of the action outcome.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        boolean hasOldKey = false;
        for(Item item: actor.getItemInventory()){
            if(item instanceof OldKey){
                hasOldKey = true;
                break;
            }
        }
        if(hasOldKey){ // Actor has old key in inventory
            gate.removeCapability(Status.LOCKED);
            return actor + " unlocks the gate to The Burial Grounds!";
        }
        return "Gate is locked shut.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks Gate";
    }
}
