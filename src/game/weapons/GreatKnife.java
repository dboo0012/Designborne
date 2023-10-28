package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.StabStepAction;
import game.attributes.Ability;
import game.attributes.EntityTypes;
import game.attributes.TradeCharacteristics;
import game.items.TradeableWeaponItem;
import game.items.Upgradable;

/**
 * A Great Knife weapon.
 *
 * @author Daryl
 */

public class GreatKnife extends TradeableWeaponItem implements Upgradable {
    private static final int INITIAL_HIT_RATE = 70;
    private int hitRate;
    /**
     * Constructor for a great knife.
     */
    public GreatKnife() {
        super("Great Knife", '>', 75, "slashes", INITIAL_HIT_RATE, 175);
        this.hitRate = INITIAL_HIT_RATE;
        addCapability(TradeCharacteristics.STEAL_RUNES);
        addCapability(Ability.UPGRADE);
    }

    /**
     * Adds the all the allowable actions when mobs are close.
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(EntityTypes.ENEMY)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
            actions.add(new StabStepAction(this, otherActor));
        }
        return actions;
    }

    /**
     *The affected price of Great Knife.
     *
     * @param seller The actor representing the seller.
     * @return Whether the price is affected by the scam.
     */
    @Override
    public boolean isPriceAffected(Actor seller) {
        double traderScamChance = 0.05;
        return Math.random() < traderScamChance;
    }

    /**
     * The scam type of Great Knife.
     *
     * @param seller The actor representing the seller.
     * @return The enum scam type of the Great Knife.
     */
    @Override
    public Enum<TradeCharacteristics> getScamType(Actor seller) {
        Enum<TradeCharacteristics> scamType = super.getScamType(seller);
        if (seller.hasCapability(EntityTypes.TRADER)){
            scamType = TradeCharacteristics.STEAL_RUNES;
        }
        return scamType;
    }

    /**
     * The affected price of Great Knife.
     *
     * @param seller The actor representing the seller.
     * @return The affected price of Great Knife.
     */
    @Override
    public int affectedPrice(Actor seller) {
        double affectedPercentage = 1;

        if (seller.hasCapability(EntityTypes.TRADER)){ // Trader
            affectedPercentage = 3;
        } else if (seller.hasCapability(EntityTypes.PLAYABLE)) { // Player
            affectedPercentage = 2;
        }

        return (int) (getPrice() * affectedPercentage);
    }

    /**
     * Spawn a new instance of the tradeable item.
     *
     * @return a new Great Knife instance.
     */
    @Override
    public Item spawn() {
        return new GreatKnife();
    }

    /**
     * Upgrade the Great Knife to increase hit rate.
     * @return a string describing the upgrade
     */
    @Override
    public String upgrade() {
        // Increase hit rate
        int upgradeValue = 1;
        increaseHitRate(upgradeValue);
        hitRate += upgradeValue; // Just to print the total hit rate, as there is no getter in parent class

        return String.format("%s has been upgraded to +%d hit rate, total dealing %d HIT RATE.", this, upgradeValue,this.hitRate);
    }

    /**
     * The price of upgrading the Great Knife.
     * @return  the price of upgrading the Great Knife
     */
    @Override
    public int upgradePrice() {
        return 2000;
    }

    /**
     * Determines if the Great Knife can only be upgraded once.
     * @return true if the Great Knife can only be upgraded once, false otherwise.
     */
    @Override
    public boolean singleUpgrade() {
        return false;
    }
}