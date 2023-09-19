package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.IncreaseMaxAttributeAction;

public class Bloodberry extends TradeableItem {
    /***
     * Constructor.
     */
    public Bloodberry() {
        super("Bloodberry", '*', true, 10);
    }

    @Override
    public ActionList allowableActions(Actor owner) {
        int increaseValue = 5;

        // Increase the actor's maximum health by a value
        IncreaseMaxAttributeAction changeAttributeAction = new IncreaseMaxAttributeAction(this, BaseActorAttributes.HEALTH,
                ActorAttributeOperations.INCREASE, increaseValue, true);

        return new ActionList(changeAttributeAction);
    }


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

    public int affectedPrice(Actor seller) {
        return getPrice();
    }

    @Override
    public boolean isScam(Actor seller) {
        return true;
    }
}
