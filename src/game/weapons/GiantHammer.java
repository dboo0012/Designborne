package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.GreatSlamAction;

public class GiantHammer extends WeaponItem {
    /**
     * Constructor.
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', 160, "slams", 90);
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
        actions.add(new AttackAction(otherActor, location.toString(), this));
        actions.add(new GreatSlamAction(this, otherActor));
        return actions;
    }
}
