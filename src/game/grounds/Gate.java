package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.actions.UnlockAction;
import game.attributes.Ability;
import game.attributes.Status;

/**
 * A class that represents a gate.
 *
 * @author Daryl, Jerry
 */
public class Gate extends Ground {
    private GameMap destination;
    private int x;
    private int y;

    /**
     * Constructor.
     * @param destination The map where the actor will be spawned
     */
    public Gate(GameMap destination, int x, int y){
        super('=');
        this.destination = destination;
        addCapability(Status.LOCKED);
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a list of allowable actions that an actor can perform on this locked gate.
     *
     * @param actor     The actor performing the action.
     * @param location  The Location of the locked gate.
     * @param direction The direction of the actor relative to the locked gate.
     * @return A list of allowable actions for the actor.
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        // If the gate is not locked, add a TravelAction to allow actor to pass through
        if (!this.hasCapability(Status.LOCKED)){
            if (x < 0){
                x = location.x();
                y = location.y();
            }
            actions.add(new TravelAction(this.destination, x, y));
        } else{
            actions.add(new UnlockAction(this));
        }
        return actions;
    }
}
