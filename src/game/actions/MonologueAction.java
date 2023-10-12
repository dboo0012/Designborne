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

public class MonologueAction extends Action {
    private ArrayList monologueOptions;
    private Abxervyer abxervyer;
    public MonologueAction(ArrayList monologueOptions, Abxervyer abxervyer) {
        this.monologueOptions = monologueOptions;
        this.abxervyer = abxervyer;
    }

    public ArrayList<String> getMonologueOptions() {
        return monologueOptions;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        String monologue = getMonologueOptions().get((int)(Math.random() * getMonologueOptions().size() - 1));
        return monologue;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Talk to the blacksmith.";
    }
}
