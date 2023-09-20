package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.FocusAction;
import game.actions.GreatSlamAction;
import game.actions.StabStepAction;
import game.attributes.EntityTypes;

public class GreatKnife extends WeaponItem {
    /**
     * Constructor.
     */
    public GreatKnife() {
        super("Great Knife", '>', 75, "slashes", 70);
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

}