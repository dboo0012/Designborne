package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action when actors fall into the void.
 *
 * @author Meekal
 */
public class VoidDeathAction extends Action {
    public VoidDeathAction() {
    }

    /**
     * Executes the action that removes the actor from the map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return String describing the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);;
        return "The void has consumed " + actor;
    }

    /**
     * Returns a string describing the action.
     *
     * @param actor The actor performing the action.
     * @return String describing the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " fell into the void.";
    }
}
