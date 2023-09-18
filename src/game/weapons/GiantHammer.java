package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.AttackAction;
import game.actions.GreatSlam;

public class GiantHammer extends WeaponItem {
    private ActionList actions = new ActionList();
    /**
     * Constructor.
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', 160, "slams", 90);
    }

    /**
     * Add a new StabStepAction to the weapon.
     * @param newAction the new action to be added.
     */
    public void addAction(GreatSlam newAction){
        actions.add(newAction);
    }

    /**
     * Adds the focusAction to the allowable actions.
     * @param actor the actor that owns the item
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        return actions;
    }

    /**
     * Adds the attack action to attack mobs.
     * @param actor the other actor
     * @param location the location of the other actor
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(actor, location.toString(), this));
        return actions;
    }
}
