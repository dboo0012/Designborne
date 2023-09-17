package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.EntityTypes;

public class SellAction extends Action {

    private final String itemName;
    private Item buyItem;
    private int price;

    public SellAction(Item buyItem, int price) {
        this.buyItem = buyItem;
        this.price = price;
        this.itemName = buyItem.toString();
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getBalance() > price){
            if (actor.hasCapability(EntityTypes.PLAYABLE)){
                actor.removeItemFromInventory(buyItem);
                actor.addBalance(price);
            }
            else if (actor.hasCapability(EntityTypes.TRADER)){
                actor.addItemToInventory(buyItem.getConstructor().newInstance());
                actor.deductBalance(price);
            }

            return String.format("You have bought %s for %d dollars.", itemName, price);
        }
        else{
            return String.format("You do not have enough money to buy %s.", itemName);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("Purchase %s for $ %d.", itemName, price);
    }
}
