package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

public class ChangeMaxAttributeAction extends Action{
    private Item item; // The item to be consumed
    private BaseActorAttributes attributeAffected; // The attribute affected by the consumption
    private ActorAttributeOperations operation; // The operation applied to the attribute
    private int amountAffected; // The amount by which the attribute is modified
    private boolean singleConsume; // Indicates whether the item is consumed and removed from inventory after use
    public ChangeMaxAttributeAction(Item item, BaseActorAttributes attributeAffected,
                                 ActorAttributeOperations operation, int amountAffected, boolean singleConsume) {
        this.item = item;
        this.attributeAffected = attributeAffected;
        this.operation = operation;
        this.amountAffected = amountAffected;
        this.singleConsume = singleConsume;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
