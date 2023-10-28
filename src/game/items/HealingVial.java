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
public class HealingVial extends TradeableItem implements Upgradable{
    private float increasePercentage = 0.1f;
    private int maxHealth;
    /***
     * Constructor.
     */
    public HealingVial() {
        super("Healing Vial", 'a', true, 35);
        addCapability(Ability.UPGRADE);
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
            maxHealth = owner.getAttributeMaximum(BaseActorAttributes.HEALTH);

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
     * Returns whether the price of this item is affected when sold by an actor.
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
            chance = 0.10; //0.10
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

    /**
     * Upgrade the Healing Vial to heal more health.
     * @return a string describing the upgrade
     */
    @Override
    public String upgrade() {
        float upgradePercentage = 0.8f;
        increasePercentage = upgradePercentage;
        return String.format("%s has been upgraded to heal %d HEALTH.", this, (int) (maxHealth * upgradePercentage));
    }

    /**
     * Returns the price of upgrading the Healing Vial.
     * @return the price of upgrading the Healing Vial
     */
    @Override
    public int upgradePrice() {
        return 250;
    }

    /**
     * Determine if the Healing Vial is single upgrade.
     * @return true if the Healing Vial is single upgrade
     */
    @Override
    public boolean singleUpgrade() {
        return true;
    }
}
