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
 */
public class Gate extends Ground {
    private GameMap destination;
    private ActionList actions = new ActionList();

    private int x = -1;
    private int y = -1;

    /**
     * Constructor.
     * @param destination The map where the actor will be spawned
     */
    public Gate(GameMap destination){
        super('=');
        this.destination = destination;
        addCapability(Status.LOCKED);
    }

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
        // Check if the actor has an Old Key and add an UnlockGateAction
//        if (actor.hasCapability(ItemTypes.OLD_KEY)){
//            actions.add(new UnlockAction(ItemTypes.OLD_KEY, location));
//        }
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

    /**
     * Checks if an actor can enter this locked gate.
     *
     * @param actor The actor attempting to enter.
     * @return True if the actor has the TRAVEL capability, otherwise false.
     */
    @Override
    public boolean canActorEnter(Actor actor){
        return actor.hasCapability(Ability.TRAVEL);
    }
//    /**
//     * Unlocks the gate.
//     */
//    public void unlock(){
//        isLocked = false;
//    }
//
//    /**
//     * Actor can enter the gate if it is unlocked.
//     * @param actor the Actor to check
//     * @return lock state of the gate
//     */
//    @Override
//    public boolean canActorEnter(Actor actor) {
//        return !isLocked;
//    }
//
//    /**
//     * Tick method for the gate.
//     * Adds UnlockAction to the action list if the gate is locked.
//     * Adds TravelAction to the action list if the gate is unlocked.
//     * @param location The location of the Ground
//     */
//    @Override
//    public void tick(Location location) {
//        if(isLocked && actions.size() == 0){
//            actions.add(new UnlockAction(this));
//        } else if (!isLocked && actions.size() > 0){
//            actions.clear();
//            int x = location.x();
//            int y = location.y();
//            actions.add(new TravelAction(this.destination, x, y));
//        }
//    }
//
//    @Override
//    public ActionList allowableActions(Actor actor, Location location, String direction) {
//        return actions;
//    }
}
