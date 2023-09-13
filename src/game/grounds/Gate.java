package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.actions.UnlockAction;

/**
 * A class that represents a gate.
 */
public class Gate extends Ground {
    private GameMap destination;
    private boolean isLocked = true;
    private ActionList actions = new ActionList();

    /**
     * Constructor.
     * @param destination The map where the actor will be spawned
     */
    public Gate(GameMap destination){
        super('=');
        this.destination = destination;
    }

    /**
     * Unlocks the gate.
     */
    public void unlock(){
        isLocked = false;
    }

    /**
     * Actor can enter the gate if it is unlocked.
     * @param actor the Actor to check
     * @return lock state of the gate
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return !isLocked;
    }

    /**
     * Tick method for the gate.
     * Adds UnlockAction to the action list if the gate is locked.
     * Adds TravelAction to the action list if the gate is unlocked.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        if(isLocked && actions.size() == 0){
            actions.add(new UnlockAction(this));
        } else if (!isLocked && actions.size() > 0){
            actions.clear();
            int x = location.x();
            int y = location.y();
            actions.add(new TravelAction(this.destination, x, y));
        }
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        return actions;
    }
}
