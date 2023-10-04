package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.StabStepAction;
import game.attributes.EntityTypes;
import game.attributes.TradeCharacteristics;
import game.items.TradeableWeaponItem;

/**
 * A Great Knife weapon.
 *
 * @author Daryl
 */

public class GreatKnife extends TradeableWeaponItem {
    /**
     * Constructor for a great knife.
     */
    public GreatKnife() {
        super("Great Knife", '>', 75, "slashes", 70, 175);
        this.addCapability(TradeCharacteristics.STEAL_RUNES);
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
}