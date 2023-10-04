package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.attributes.TradeCharacteristics;

/**
 * An interface representing a tradeable item or entity.
 * It defines methods for setting and retrieving the price, spawning a new item, checking if the price is affected,
 * determining the scam type, and calculating the affected price.
 *
 * @author Meekal
 */
public interface Tradeable {

    /**
     * Get the price of the tradeable item.
     *
     * @return The price of the tradeable item.
     */
    int getPrice();

    /**
     * Set the price of the tradeable item.
     *
     * @param price The new price of the tradeable item.
     * @return The updated tradeable item with the new price.
     */
    Item setPrice(int price);

    /**
     * Spawn a new instance of the tradeable item.
     *
     * @return A new instance of the tradeable item.
     */
    Item spawn();

    /**
     * Check if the price of the tradeable item is affected by certain conditions.
     *
     * @param seller The actor representing the seller.
     * @return {@code true} if the price is affected, {@code false} otherwise.
     */
    boolean isPriceAffected(Actor seller);

    /**
     * Get the scam type associated with the tradeable item.
     *
     * @param seller The actor representing the seller.
     * @return The scam type as an enum value from {@link TradeCharacteristics}.
     */
    Enum<TradeCharacteristics> getScamType(Actor seller);

    /**
     * Calculate the affected price of the tradeable item based on specific conditions.
     *
     * @param seller The actor representing the seller.
     * @return The affected price of the tradeable item.
     */
    int affectedPrice(Actor seller);
}
