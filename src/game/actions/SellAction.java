package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.EntityTypes;
import game.items.Tradeable;

public class SellAction extends Action {

    private final String itemName;
    private Item item;
    private Tradeable tradeableItem;
    private int price;
    private Actor seller;

    public SellAction(Item item, Tradeable tradeableItem, int price, Actor seller) {
        this.item = item;
        this.tradeableItem = tradeableItem;
        this.price = price;
        this.itemName = item.toString();
        this.seller = seller;
    }

    /**
     * Perform the Action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String output = "";
        boolean isScam = false;

        if (tradeableItem.isPriceAffected(seller)){ //If the price is affected
            if (tradeableItem.isScam(seller)){
                isScam = true;
            } else {
                price = tradeableItem.affectedPrice(seller);
                output += String.format("%s has scammed you by increasing the price to %d\n", seller, price);
            }
        }

        // Player selling
        if (seller.hasCapability(EntityTypes.TRADER)){


            if (isScam){
                actor.deductBalance(price);
                return String.format("SCAMMED! %s took your money ($%d) without giving you a %s", seller, price, itemName);
            } else if (actor.getBalance() > price){
                actor.deductBalance(price);
                actor.addItemToInventory(tradeableItem.spawn());
                output += String.format("You have bought %s for %d.", itemName, price);
            } else{
                output += String.format("You do not have enough Runes to buy %s.", itemName);
            }
        }
        // Player buying
        else if (seller.hasCapability(EntityTypes.PLAYABLE)){
            actor.removeItemFromInventory(item);

            if (isScam){
                return String.format("SCAMMED! %s took %s without paying you!", seller, itemName);
            } else {
                    actor.addBalance(price);
                    output += String.format("Trader purchased %s from %s for $%d", itemName, actor, price);
            }
        }

        return output;
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s sells %s for $%d.", seller, itemName, price);
    }
}
