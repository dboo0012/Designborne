package game.actors;

import edu.monash.fit2099.engine.actors.Actor;

import java.util.ArrayList;

public interface Monologue {
    void monologue(); // Stores all the monologue without conditions
    ArrayList<String> setMonologueList();
    void monologueConditions(Actor otherActor); // Stores all the monologue with conditions
}
