package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.SellAction;
import game.attributes.Ability;
import game.attributes.EntityTypes;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weapons.BroadSword;

import java.util.ArrayList;

public abstract class Trader extends Actor {
//    protected final ArrayList<Class<? extends Item>> itemsToBeDropped = new ArrayList<>();
    private final ArrayList<Integer> itemPrice = new ArrayList<>();
    /**
     * The constructor of the Traveller.
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Ability.TRADE);
        addTradeItem(new HealingVial(), 100);
        addTradeItem(new RefreshingFlask(), 75);
        addTradeItem(new BroadSword(), 250);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // check exits for player


        // Prompt player to trade (buy or sell)
        return null;
    }

    // PROBLEM, player uses additemToinventory when picking up items
    @Override
    public void addItemToInventory(Item item) {
        if (item.hasCapability(Ability.TRADABLE)) {
            super.addItemToInventory(item);
        }
    }

    public void addTradeItem(Item item, int price){
        this.addItemToInventory(item);
        this.itemPrice.add(price);
    }

    public ActionList getItems(Actor sellingActor){
        ActionList actions = new ActionList();
        for (int i = 0; i < sellingActor.getItemInventory().size(); i++){
            Item item = sellingActor.getItemInventory().get(i); // Get Item
            int price = sellingActor.itemPrice.get(i); // Get price
            if (item.hasCapability(Ability.TRADABLE)){
                actions.add(new SellAction(item, price, sellingActor));
            }
        }
        return actions;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        getItems(this);
        getItems(otherActor);
        return actions;
    }
}
