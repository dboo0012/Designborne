package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.attributes.Ability;

import java.util.Random;

public abstract class TradeableItem extends Item implements Tradeable {

    private int price;


    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public TradeableItem(String name, char displayChar, boolean portable, int price) {
        super(name, displayChar, portable);
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
