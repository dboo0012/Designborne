package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.EntityTypes;
import game.attributes.TradeCharacteristics;
import game.items.Tradeable;

/**
 * A class representing the action of selling an item in the game.
 *
 * @author Meekal
 */
public class SellAction extends Action {

    private final String itemName;
    private Item item;
    private Tradeable tradeableItem;
    private int price;
    private Actor seller;

    /**
     * Constructor for the SellAction class.
     *
     * @param item           the item to be sold
     * @param tradeableItem  the tradeable item being sold
     * @param price          the price at which the item is sold
     * @param seller         the actor selling the item
     */
    public SellAction(Item item, Tradeable tradeableItem, int price, Actor seller) {
        this.item = item;
        this.tradeableItem = tradeableItem;
        this.price = price;
        this.itemName = item.toString();
        this.seller = seller;
    }

    /**
     * Performs the sell action, handling buying and selling between actors.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened (the result of the action being performed) that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String output = "";
        boolean isScam = false;
        Enum<TradeCharacteristics> scamType = TradeCharacteristics.NON_SCAMMABLE;

        // Check if the price is affected
        if (tradeableItem.isPriceAffected(seller)) {
            Enum<TradeCharacteristics> itemScamType = tradeableItem.getScamType(seller);
            if (itemScamType != TradeCharacteristics.NON_SCAMMABLE) {
                scamType = itemScamType;
                isScam = true;
            } else {
                price = tradeableItem.affectedPrice(seller);
                output += String.format("%s changed the price to $%d while selling.\n", seller, price);
            }
        }

        // Trader selling
        if (seller.hasCapability(EntityTypes.TRADER)) {
            if (isScam) { // Take runes without giving item
                if (scamType == TradeCharacteristics.STEAL_RUNES) {
                    actor.deductBalance(price);
                    return String.format("SCAMMED! %s took your money ($ %d) without giving you a %s", seller, price, itemName);
                }
            } else if (actor.getBalance() > price) { // Normal Buying
                actor.deductBalance(price);
                actor.addItemToInventory(tradeableItem.spawn());
                output += String.format("You have bought %s for $%d.", itemName, price);
            } else { // Not enough Runes
                output += String.format("You do not have enough Runes to buy %s.", itemName);
            }
        }
        // Player selling
        else if (seller.hasCapability(EntityTypes.PLAYABLE)) {

            if (isScam) { // It's a scam!
                if (scamType == TradeCharacteristics.STEAL_RUNES) {
                    seller.deductBalance(price);
                    return String.format("SCAMMED! %s took your Runes instead of your %s!", seller, itemName);
                } else if (scamType == TradeCharacteristics.STEAL_ITEMS) {
                    actor.removeItemFromInventory(item);
                    return String.format("SCAMMED! %s took your %s without paying!", seller, itemName);
                }
                return String.format("SCAMMED! %s took %s without paying you!", seller, itemName);
            } else { // Normal selling
                actor.removeItemFromInventory(item);
                actor.addBalance(price);
                output += String.format("Trader purchased %s from %s for $%d", itemName, actor, price);
            }
        }
        return output;
    }

    /**
     * Returns a description of the action for display in the user interface.
     *
     * @param actor The actor performing the action.
     * @return a description of the action for display in the user interface.
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s sells %s for $%d.", seller, itemName, price);
    }
}
