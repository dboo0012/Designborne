package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.Ability;
import game.attributes.EntityTypes;

import java.util.ArrayList;

public class Traveller extends Actor {
//    protected final ArrayList<Class<? extends Item>> itemsToBeDropped = new ArrayList<>();
    /**
     * The constructor of the Traveller.
     */
    public Traveller() {
        super("Traveller", 'ඞ', 1);
        this.addCapability(EntityTypes.TRADER);
        this.addCapability(Ability.TRADE);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Actor player = map.locationOf(this).getActor();

        // check exits for player

        // Prompt player to trade (buy or sell)
        return null;
    }

}
