package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.actions.IncreaseMaxAttributeAction;

public class Bloodberry extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Bloodberry() {
        super("Bloodberry", '*', true);
    }

    @Override
    public ActionList allowableActions(Actor owner) {
        int increaseValue = 5;

        // Increase the actor's maximum health by a value
        IncreaseMaxAttributeAction changeAttributeAction = new IncreaseMaxAttributeAction(this, BaseActorAttributes.HEALTH,
                ActorAttributeOperations.INCREASE, increaseValue, true);

        return new ActionList(changeAttributeAction);
    }
}
