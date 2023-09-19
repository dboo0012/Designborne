package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ChangeAttributeAction;
import game.actions.RefreshAction;
import game.attributes.Ability;
import game.attributes.EntityTypes;

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
        ActionList actions = new ActionList();

        if (owner.hasCapability(Ability.CONSUME)){
            int maxStamina = owner.getAttributeMaximum(BaseActorAttributes.STAMINA);
            float increasePercentage = 0.2f;

            // Create a ChangeAttributeAction to increase the actor's stamina
            ChangeAttributeAction changeAttributeAction = new ChangeAttributeAction(this,
                    BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, (int) (maxStamina * increasePercentage), true);

            actions.add(changeAttributeAction);
        }

        return actions;
    }

    @Override
    public Item spawn() {
        return new RefreshingFlask();
    }

    /**
     *
     * @param seller the Actor selling, passed in because different seller types may have different probability
     * @return a boolean indicating if the price is affected
     */
    @Override
    public boolean isPriceAffected(Actor seller) {
        double chance = 0;
        if (seller.hasCapability(EntityTypes.TRADER)){
            chance = 0.10; //0.25
        } else if (seller.hasCapability(EntityTypes.PLAYABLE)){
            chance = 0.50; //0.25
        }
        return Math.random() < chance;
    }

    @Override
    public int affectedPrice(Actor seller) {
        double affectedPercentage = 1;

        if (seller.hasCapability(EntityTypes.TRADER)){
            affectedPercentage = 0.8;
        }

        return (int) (getPrice() * affectedPercentage);
    }

    @Override
    public boolean isScam(Actor seller) {
        return false;
    }
}
