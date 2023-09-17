package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ChangeAttributeAction;
import game.actions.RefreshAction;

/**
 * An item that can be used to recover Player's stamina.
 */
public class RefreshingFlask extends TradeableItem {
    private ActionList actions = new ActionList();
    /***
     * Constructor.
     */
    public RefreshingFlask() {
        super("Refreshing Flask", 'u', true, 25);
    }

    /**
     * Generates an allowable action for the owner of the Refreshing Flask, which is to use it and increase their stamina.
     *
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        int maxStamina = owner.getAttributeMaximum(BaseActorAttributes.STAMINA);
        float increasePercentage = 0.2f;

        // Create a ChangeAttributeAction to increase the actor's stamina
        ChangeAttributeAction changeAttributeAction = new ChangeAttributeAction(this,
                BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, (int) (maxStamina * increasePercentage), true);

        return new ActionList(changeAttributeAction);
    }

    @Override
    public TradeableItem spawn() {
        return new RefreshingFlask();
    }
}
