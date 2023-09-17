package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.EntityTypes;

public class SellAction extends Action {

    private final String itemName;
    private Item item;
    private int price;
    private Actor buyer;

    public SellAction(Item item, int price, Actor buyer) {
        this.item = item;
        this.price = price;
        this.itemName = item.toString();
        this.buyer = buyer;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        // Player selling
        if (actor.hasCapability(EntityTypes.TRADER)){
            if (actor.getBalance() > price){
                actor.removeItemFromInventory(item);
                actor.addBalance(price);
                return String.format("You have bought %s for %d dollars.", itemName, price);
            }else{
                return String.format("You do not have enough Runes to buy %s.", itemName);
            }
        }
        // Player buying
        else if (actor.hasCapability(EntityTypes.PLAYABLE)){
            actor.addItemToInventory(item.getConstructor().newInstance());
            actor.deductBalance(price);
            return String.format("Trader purchased %s from %s for $ %d", itemName, actor, price);
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s purchase %s for $ %d.", actor, itemName, price);
    }
}
