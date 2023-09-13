package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action when actors fall into the void.
 */
public class VoidDeathAction extends Action {
    public VoidDeathAction() {
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);;
        return "The void has consumed " + actor;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " fell into the void.";
    }
}
