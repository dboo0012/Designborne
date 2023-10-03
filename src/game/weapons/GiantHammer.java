package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.GreatSlamAction;
import game.attributes.EntityTypes;
import game.attributes.TradeCharacteristics;
import game.items.TradeableWeaponItem;

public class GiantHammer extends TradeableWeaponItem {
    /**
     * Constructor.
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', 160, "slams", 90, 250);
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
            actions.add(new GreatSlamAction(this, otherActor));
        }
        return actions;
    }

    @Override
    public boolean isPriceAffected(Actor seller) {
        return false;
    }

    @Override
    public int affectedPrice(Actor seller) {
        return 0;
    }

    @Override
    public Item spawn() {
        return new GiantHammer();
    }
}
