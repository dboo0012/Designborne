package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.attributes.Ability;
import game.attributes.TradeCharacteristics;

/**
 * An abstract class representing a tradeable weapon item in the game.
 * Extends the {@link WeaponItem} class and implements the {@link Tradeable} interface.
 *
 * @author Meekal
 */
public abstract class TradeableWeaponItem extends WeaponItem implements Tradeable {

    private int price;

    /**
     * Constructor for a tradeable weapon item.
     *
     * @param name        Name of the item.
     * @param displayChar Character to use for display when the item is on the ground.
     * @param damage      Amount of damage this weapon does.
     * @param verb        Verb to use for this weapon, e.g., "hits," "zaps."
     * @param hitRate     The probability/chance to hit the target.
     * @param price       The price of the weapon item.
     */
    public TradeableWeaponItem(String name, char displayChar, int damage, String verb, int hitRate, int price) {
        super(name, displayChar, damage, verb, hitRate);
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
     * Spawn a new instance of the tradeable weapon item.
     *
     * @return A new instance of the tradeable weapon item.
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
