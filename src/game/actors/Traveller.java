package game.actors;

import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weapons.BroadSword;
import game.weapons.GreatKnife;

/**
 * A Traveller actor.
 *
 * @author Meekal
 */
public class Traveller extends Trader {
    /**
     * Constructor for a Traveller.
     */
    public Traveller(){
        super("Traveller", 'ඞ', 1);

        // The items sold by the Traveller
        addItemToInventory(new HealingVial().setPrice(100));
        addItemToInventory(new RefreshingFlask().setPrice(75));
        addItemToInventory(new BroadSword().setPrice(250));
        addItemToInventory(new GreatKnife().setPrice(300));
    }
}
