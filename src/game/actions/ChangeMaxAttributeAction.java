package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * An action that increases the maximum value of an attribute of an actor.
 *
 * @author Meekal, Daryl
 */
public class ChangeMaxAttributeAction extends Action{
    private Item item; // The item to be consumed
    private BaseActorAttributes attributeAffected; // The attribute affected by the consumption
    private ActorAttributeOperations operation; // The operation applied to the attribute
    private int amountAffected; // The amount by which the attribute is modified
    private boolean singleConsume; // Indicates whether the item is consumed and removed from inventory after use

    /**
     * Constructor for IncreaseMaxAttributeAction.
     * @param item the item to be consumed
     * @param attributeAffected the attribute affected by the consumption
     * @param operation the operation applied to the attribute
     * @param amountAffected the amount by which the attribute is modified
     * @param singleConsume indicates whether the item is consumed and removed from inventory after use
     */
    public ChangeMaxAttributeAction(Item item, BaseActorAttributes attributeAffected,
                                    ActorAttributeOperations operation, int amountAffected, boolean singleConsume){
        this.item = item;
        this.attributeAffected = attributeAffected;
        this.operation = operation;
        this.amountAffected = amountAffected;
        this.singleConsume = singleConsume;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.modifyAttributeMaximum(attributeAffected, operation, amountAffected);

        if (singleConsume) {
            actor.removeItemFromInventory(item);
        }

        return String.format("%s consumed %s", actor, item);

    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("Consume %s (MAX %s + %d)", item, attributeAffected, amountAffected);
    }
}
