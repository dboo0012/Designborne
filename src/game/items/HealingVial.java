package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.ChangeAttributeAction;
import game.actions.HealAction;
import game.attributes.Ability;
import game.attributes.EntityTypes;
import game.attributes.TradeCharacteristics;

/**
 * An item that can be used to heal the player.
 *
 * @author Daryl, Meekal, Jerry
 */
public class HealingVial extends TradeableItem {
    /***
     * Constructor.
     */
    public HealingVial() {
        super("Healing Vial", 'a', true, 35);
    }

    /**
     * Generates an allowable action for the owner of the Healing Vial, which is to use it and increase their health.
     *
     * @param owner the actor that owns the item
     * @return an unmodifiable list of Actions
     */
    public ActionList allowableActions(Actor owner) {
        ActionList actions = new ActionList();

        if (owner.hasCapability(Ability.CONSUME)) {
            int maxHealth = owner.getAttributeMaximum(BaseActorAttributes.HEALTH);
            float increasePercentage = 0.1f;

            // Create a ChangeAttributeAction to increase the actor's health
            ChangeAttributeAction changeAttributeAction = new ChangeAttributeAction(this, BaseActorAttributes.HEALTH,
                    ActorAttributeOperations.INCREASE, (int) (maxHealth * increasePercentage), true);

            actions.add(changeAttributeAction);
        }

        return actions;
    }

    /**
     * Instantiates a new Healing vial and returns it.
     *
     * @return a HealingVial object
     */
    @Override
    public Item spawn() {
        return new HealingVial();
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
            chance = 0.25; //0.25
        } else if (seller.hasCapability(EntityTypes.PLAYABLE)){
            chance = 0.10; //0.25
        }
        return Math.random() < chance;
    }

    /**
     * The price if the item is scammed.
     *
     * @param seller The actor representing the seller.
     * @return the scam price
     */
    @Override
    public int affectedPrice(Actor seller) {
        double affectedPercentage = 1;

        if (seller.hasCapability(EntityTypes.TRADER)){
            affectedPercentage = 1.5;
        } else if (seller.hasCapability(EntityTypes.PLAYABLE)) {
            affectedPercentage = 2;
        }

        return (int) (getPrice() * affectedPercentage);
    }
}
