package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.FocusAction;
import game.actions.GreatSlamAction;
import game.actions.StabStepAction;
import game.attributes.EntityTypes;
import game.attributes.TradeCharacteristics;
import game.items.TradeableWeaponItem;

public class GreatKnife extends TradeableWeaponItem {
    /**
     * Constructor.
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

    @Override
    public boolean isPriceAffected(Actor seller) {
        double traderScamChance = 0.05;
        return Math.random() < traderScamChance;
    }

    @Override
    public Enum<TradeCharacteristics> getScamType(Actor seller) {
        Enum<TradeCharacteristics> scamType = super.getScamType(seller);
        if (seller.hasCapability(EntityTypes.TRADER)){
            scamType = TradeCharacteristics.STEAL_RUNES;
        }
        return scamType;
    }

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

    @Override
    public Item spawn() {
        return new GreatKnife();
    }
}