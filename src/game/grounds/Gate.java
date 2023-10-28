package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TravelAction;
import game.actions.UnlockAction;
import game.attributes.Ability;
import game.attributes.GroundTypes;
import game.attributes.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a gate.
 *
 * @author Daryl, Jerry, Meekal
 */
public class Gate extends Ground {
    //The list of destinations the Gate can travel to
    private List<Destination> destinationList = new ArrayList<>();

    /**
     * Constructor with one destination.
     *
     * @param destination The map where the actor will be spawned
     */
    public Gate(Destination destination){
        super('=');
        destinationList.add(destination);
        addCapability(Status.LOCKED);
        addCapability(GroundTypes.GATE);
    }

    /**
     * Constructor with list of destination.
     *
     * @param destinationList The list of destinations the gate can travel to
     */
    public Gate(List<Destination> destinationList){
        super('=');
        this.destinationList = destinationList;
        addCapability(Status.LOCKED);
        addCapability(GroundTypes.GATE);
    }

    /**
     * Modifier method to add destinations to the gate as the game goes on
     *
     * @param destination
     */
    public void addDestination(Destination destination){
        destinationList.add(destination);
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
            for (Destination destination: destinationList) {
                int x = destination.getX();
                int y = destination.getY();
                GameMap destinationMap = destination.getDestinationMap();
                if (x < 0 || y < 0){ //If x/y value is not set (x/y is automatically set to -1)
                    x = location.x();
                    y = location.y();
                }
                actions.add(new TravelAction(destination, x, y));
            }
        } else{
            actions.add(new UnlockAction(this));
        }
        return actions;
    }
}
