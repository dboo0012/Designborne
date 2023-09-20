package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.BroadSword;

/**
 * An action to activate the skill of Broadsword (focus).
 */
public class FocusAction extends TickableAction {

    private boolean focusActive = false;

    public FocusAction(WeaponItem weaponItem, float defDmgMultiplier, int defHitRate, int maxFocusCounter) {
        super(weaponItem, "Focus", defDmgMultiplier, defHitRate, maxFocusCounter); // counter starts at 0 (not activated
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        double requiredStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA)*0.2; // 20% of maximum stamina
        int currentStamina = actor.getAttribute(BaseActorAttributes.STAMINA);

        if(currentStamina < requiredStamina){
            return actor + " does not have enough stamina to activate Focus";
        } else {
            activate();
            resetCounter();
            // Broadswords new stats
            int newHitRate = 90;
            float increaseDamageMultiplierBy = 0.1f;
            weaponItem.updateHitRate(newHitRate);
            weaponItem.increaseDamageMultiplier(increaseDamageMultiplierBy);

            // stamina decrease 20% of max
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, (int) requiredStamina);

//            // Activates skill and reset counter
//            focusActive = true;
//            weaponItem.setFocusCounter();
            return actor + " has activated Focus on " + weaponItem.toString();
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of Focus on " + weaponItem.toString();
    }

    @Override
    public void reset() {
        deactivate();
        resetCounter();
        weaponItem.updateDamageMultiplier(defDmgMultiplier);
        weaponItem.updateHitRate(defHitRate);
    }

}
