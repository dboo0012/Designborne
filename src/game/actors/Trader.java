package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SellAction;
import game.attributes.Ability;
import game.attributes.EntityTypes;
import game.items.Tradeable;

import javax.swing.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Class representing a trader actor.
 *
 * @author Meekal
 */
public abstract class Trader extends Actor {

    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();

    /**
     * The constructor of the Traveller.
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Ability.TRADE);
        this.addCapability(EntityTypes.TRADER);
    }

    /**
     * Determines the action to be performed during the enemy actor's turn.
     *
     * @param actions    Collection of possible Actions for this actor.
     * @param lastAction The Action this actor took last turn.
     * @param map        The map containing the actor.
     * @param display    The I/O object to which messages may be written.
     * @return The valid action that can be performed in that iteration or null if no valid action is found.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Returns a list of the Actions of all the items sold by the sellingActor which can be performed on the current actor.
     *
     * @param sellingActor The actor selling the items
     * @return The list of actions to sell of all the items in the sellingActor's inventory
     */
    public ActionList getItems(Actor sellingActor){
        ActionList actions = new ActionList(); //List of actions

        for (int i = 0; i < sellingActor.getItemInventory().size(); i++){ // For each item to sell
            Item item = sellingActor.getItemInventory().get(i); // Get Item

            if (item.hasCapability(Ability.TRADABLE)){ // IF that item is tradeable
                Tradeable tradeableItem = ((Tradeable) item); //[Code Smell (Downcasting)]
                int price = tradeableItem.getPrice(); // Get price
                actions.add(new SellAction(item, tradeableItem, price, sellingActor)); //Create a selling action
            }
        }

        return actions;
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(Ability.TRADE)){
            actions.add(getItems(this));
            actions.add(getItems(otherActor));
        }

        return actions;
    }
}
