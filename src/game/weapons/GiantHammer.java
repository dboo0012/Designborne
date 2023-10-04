package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.GreatSlamAction;
import game.attributes.EntityTypes;
import game.attributes.TradeCharacteristics;
import game.items.TradeableWeaponItem;

/**
 * A Giant Hammer weapon.
 *
 * @author Daryl
 */
public class GiantHammer extends TradeableWeaponItem {
    /**
     * Constructor for a giant hammer.
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', 160, "slams", 90, 250);
    }

    /**
     * Returns a list of allowable actions when another actor is nearby.
     *
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(EntityTypes.ENEMY)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
            actions.add(new GreatSlamAction(this, otherActor));
        }
        return actions;
    }

    /**
     * Checks if the price of this item is affected when sold by an actor.
     *
     * @param seller the actor selling the item
     * @return {@code true} if the price is affected, {@code false} otherwise
     */
    @Override
    public boolean isPriceAffected(Actor seller) {
        return false;
    }

    /**
     * Calculates the affected price when this item is sold by an actor.
     *
     * @param seller the actor selling the item
     * @return the affected price
     */
    @Override
    public int affectedPrice(Actor seller) {
        return 0;
    }

    /**
     * Spawn a new instance of the Giant Hammer item.
     *
     * @return a new instance of the Giant Hammer
     */
    @Override
    public Item spawn() {
        return new GiantHammer();
    }
}
