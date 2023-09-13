package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.actors.Player;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Daryl Boon (32836899)
 *
 */
public class Floor extends Ground {
    /**
     * Constructor.
     */
    public Floor() {
        super('_');
    }

    /**
     * Checks if the Actor can enter the Floor. Only Player can enter.
     * @param actor the Actor to check
     * @return Boolean value of whether the Actor can enter the Floor
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor instanceof Player;
    }
}

