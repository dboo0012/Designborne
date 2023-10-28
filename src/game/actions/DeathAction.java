package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import game.main.FancyMessage;

/**
 * Action that is performed when Player dies.
 *
 * @author Daryl
 */
public class DeathAction extends Action{
    /**
     * Constructor.
     * @param actor The actor performing the action.
     * @param map The map the actor is located.
     */
    public DeathAction(Actor actor, GameMap map) {
    }

    /**
     * Prints the fancy death message and removes the actor from the map.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return unconscious message
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        map.removeActor(actor);
        return "You died.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " died.";
    }
}
