package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.attributes.Ability;
import game.attributes.EntityTypes;

/**
 * A type of Ground that represents a void. Actors stepping into this void become unconscious.
 */
public class Void extends Ground {

    /**
     * Constructor for the Void class.
     * Initializes the display character for the void as '+'.
     */
    public Void() {
        super('+');
    }

    /**
     * This method is called on each game turn to perform actions associated with the Void ground.
     * If there is an actor on this void ground, it will become unconscious.
     *
     * @param location The Location object representing the location of this ground.
     */
    @Override
    public void tick(Location location) {
        if (location.containsAnActor() && !location.getActor().hasCapability(Ability.VOID_IMMUNITY)){
            Actor actor = location.getActor();
            GameMap map = location.map();
            // Make the actor unconscious when stepping into the void
            actor.unconscious(map);
        }
    }
}
