package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.ChangeMaxAttributeAction;

/**
 * A Bloodberry that increases the player's maximum health when used.
 *
 * @author Daryl
 */
public class Bloodberry extends TradeableItem {
    /***
     * Constructor.
     */
    public Bloodberry() {
        super("Bloodberry", '*', true, 10);
    }

    /**
     * Generates an allowable action for the owner of the Bloodberry, which is to use it and increase their maximum health.
     *
     * @param owner the actor that owns the item
     * @return a list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        int increaseValue = 5;

        // Increase the actor's maximum health by a value
        ChangeMaxAttributeAction changeAttributeAction = new ChangeMaxAttributeAction(this, BaseActorAttributes.HEALTH,
                ActorAttributeOperations.INCREASE, increaseValue, true);

        return new ActionList(changeAttributeAction);
    }

    /**
     * Instantiates a new Bloodberry and returns it.
     *
     * @return a new Bloodberry object
     */
    @Override
    public Item spawn() {
        return new Bloodberry();
    }

    /**
     * Bloodberry prices can't be affected
     *
     * @param seller the Actor selling, passed in because different seller types may have different probability
     * @return a boolean indicating if the price is affected
     */
    public boolean isPriceAffected(Actor seller) {
        return false;
    }

    /**
     * The affected price of Bloodberry
     *
     * @param seller The actor representing the seller.
     * @return the price of Bloodberry
     */
    public int affectedPrice(Actor seller) {
        return getPrice();
    }

}
