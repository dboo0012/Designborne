package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

/**
 * Interface that allows an actor to have a monologue conversation.
 *
 * @author Daryl
 */
public interface Monologue {
    /**
     * Stores all the monologue without conditions
     */
    void monologue();

    /**
     * Initializes the monologue list as an Arraylist of Strings
     * @return the monologue list
     */
    ArrayList<String> setMonologueList();

    /**
     * Stores all the monologue with conditions, as well as the conditions
     * @param otherActor the actor that the monologue is directed to
     */
    void monologueConditions(Actor otherActor);
}
