package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * A class that represents an old key.
 */
public class OldKey extends Item {

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if the Item can be picked up, false otherwise
     */
    public OldKey(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }
}
