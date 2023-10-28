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
 *
 * @author Daryl
 */
public class FocusAction extends TickableAction {
//    private WeaponItem weaponItem = getWeaponItem();
    private BroadSword broadSword;
    private boolean focusActive = false;

    /**
     * Constructor.
     * @param broadSword The broadsword weapon item.
     * @param defDmgMultiplier The default damage multiplier.
     * @param defHitRate     The default hit rate.
     * @param maxFocusCounter The maximum counter for the focus skill.
     */
    public FocusAction(BroadSword broadSword, float defDmgMultiplier, int defHitRate, int maxFocusCounter) {
        super(broadSword, "Focus", defDmgMultiplier, defHitRate, maxFocusCounter); // counter starts at 0 (not activated
        this.broadSword = broadSword;
    }

    /**
     * Activates the focus skill.
     * @param actor the actor performing the action
     * @param map   the game map where the action is executed
     * @return
     */
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
            broadSword.updateHitRate(newHitRate);
            broadSword.increaseDamageMultiplier(increaseDamageMultiplierBy);

            // stamina decrease 20% of max
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, (int) requiredStamina);

            return actor + " has activated Focus on " + broadSword.toString();
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of Focus on " + broadSword.toString();
    }

    /**
     * Resets the broadsword's stats to default.
     */
    @Override
    public void reset() {
        deactivate();
        resetCounter();
        broadSword.updateDamageMultiplier(getDefDmgMultiplier());
        broadSword.updateHitRate(getDefHitRate());
    }

}
