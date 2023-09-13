package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

/**
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by: Daryl Boon (32836899)
 *
 * A class that represents a wall.
 */
public class Wall extends Ground {
    /**
     *  Constructor.
     */
    public Wall() {
        super('#');
    }

    /**
     * No entity can enter this ground.
     * @param actor the Actor to check
     * @return  false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}
