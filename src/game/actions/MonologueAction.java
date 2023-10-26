package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Abxervyer;
import game.attributes.EntityTypes;
import game.attributes.Status;
import game.items.OldKey;
import game.weapons.GreatKnife;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Action that chooses a random monologue from the list of monologues when the player talks to an actor.
 *
 * @author Jerry
 */
public class MonologueAction extends Action {
    private Actor monologueActor;
    private ArrayList monologueOptions;

    /**
     * Constructor.
     * @param monologueActor the actor that the monologue is directed to
     * @param monologueOptions the list of monologues
     */
    public MonologueAction(Actor monologueActor, ArrayList monologueOptions) {
        this.monologueActor = monologueActor;
        this.monologueOptions = monologueOptions;
    }

    /**
     * The list of all available monologues.
     * @return the list of monologues
     */
    public ArrayList<String> getMonologueOptions() {
        return monologueOptions;
    }

    /**
     * Randomly selects a monologue from the list of monologues.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return The monologue string that is selected.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String monologue = getMonologueOptions().get((int)(Math.random() * (getMonologueOptions().size() - 1)));
        return monologue;
    }

    /**
     * Returns a string describing the action suitable for displaying in the menu.
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("Talk to %s", monologueActor);
    }
}
