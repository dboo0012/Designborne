package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.attributes.Ability;

import java.util.Random;

public abstract class TradeableWeaponItem extends WeaponItem implements Tradeable {

    private int price;



    /** Constructor.
     *
     * @param name name of the item
     * @param displayChar character to use for display when item is on the ground
     * @param damage amount of damage this weapon does
     * @param verb verb to use for this weapon, e.g. "hits", "zaps"
     * @param hitRate the probability/chance to hit the target.
     * @param price the price of the WeaponItem
     */

    public TradeableWeaponItem(String name, char displayChar, int damage, String verb, int hitRate, int price) {
        super(name, displayChar, damage, verb, hitRate);
        this.price = price;
        this.addCapability(Ability.TRADABLE);
    }

    public int getPrice() {return price;}

    public Item setPrice(int price) {
        this.price = price;
        return this;
    }

    public abstract Item spawn();



}
