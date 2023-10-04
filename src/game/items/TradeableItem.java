package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.attributes.Ability;
import game.attributes.TradeCharacteristics;

/**
 * An abstract class representing a tradeable item in the game.
 * Extends the {@link Item} class and implements the {@link Tradeable} interface.
 *
 * @author Meekal
 */
public abstract class TradeableItem extends Item implements Tradeable {

    private int price;

    /**
     * Constructor for a tradeable item.
     *
     * @param name        The name of the item.
     * @param displayChar The character to use for representation when the item is on the ground.
     * @param portable    {@code true} if and only if the item can be picked up; {@code false} otherwise.
     * @param price       The price of the tradeable item.
     */
    public TradeableItem(String name, char displayChar, boolean portable, int price) {
        super(name, displayChar, portable);
        this.price = price;
        this.addCapability(Ability.TRADABLE);
    }

    /**
     * Get the price of the tradeable item.
     *
     * @return The price of the tradeable item.
     */
    public int getPrice() {
        return price;
    }

    /**
     * Set the price of the tradeable item.
     *
     * @param price The new price of the tradeable item.
     * @return The updated tradeable item with the new price.
     */
    public Item setPrice(int price) {
        this.price = price;
        return this;
    }

    /**
     * Spawn a new instance of the tradeable item.
     *
     * @return A new instance of the tradeable item.
     */
    public abstract Item spawn();

    /**
     * Get the scam type associated with the tradeable item.
     *
     * @param seller The actor representing the seller.
     * @return The scam type as an enum value from {@link TradeCharacteristics}.
     */
    @Override
    public Enum<TradeCharacteristics> getScamType(Actor seller) {
        return TradeCharacteristics.NON_SCAMMABLE;
    }
}
