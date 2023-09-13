package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action that allows the actor to travel to another map.
 */
public class TravelAction extends Action {
    private final GameMap destination;
    private final int x;
    private final int y;

    /**
     * Constructor.
     * @param destination the destination map
     * @param x the x coordinate of the destination
     * @param y the y coordinate of the destination
     */
    public TravelAction(GameMap destination, int x, int y){
        super();
        this.destination = destination;
        this.x = x;
        this.y = y;

    }

    /**
     * Transport the Player to the destination map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String describing the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Travel to next map
        map.moveActor(actor, destination.at(x, y));
        return String.format("Welcome to %s, %s", destination.locationOf(actor), actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " travels through the Gate";
    }
}
