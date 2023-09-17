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
import game.items.Tradeable;
import game.items.TradeableItem;
import game.weapons.BroadSword;

import java.util.ArrayList;

public abstract class Trader extends Actor {
//    protected final ArrayList<Class<? extends Item>> itemsToBeDropped = new ArrayList<>();
    private final ArrayList<Tradeable> itemsToSell = new ArrayList<>();

    /**
     * The constructor of the Traveller.
     */
    public Trader(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Ability.TRADE);
        this.addCapability(EntityTypes.TRADER);

        addItemToSell(new HealingVial().setPrice(100));
        addItemToSell(new RefreshingFlask().setPrice(75));
        addItemToSell(new BroadSword().setPrice(250));
    }

    public void addItemToSell(Tradeable item){
        itemsToSell.add(item);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // check exits for player


        // Prompt player to trade (buy or sell)
        return null;
    }

    public ActionList getItems(Actor sellingActor){
        ActionList actions = new ActionList(); //List of actions

        for (int i = 0; i < sellingActor.getItemInventory().size(); i++){ // For each item to sell
            Item item = sellingActor.getItemInventory().get(i); // Get Item Code Smell (Downcasting)
            if (item.hasCapability(Ability.TRADABLE)){
                int price = (Tradeable) item.getPrice(); // Get price
            }
            actions.add(new SellAction(item, price, sellingActor)); //Create a selling action
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
