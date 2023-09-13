package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.actions.Action;
import game.weapons.BroadSword;

/**
 * An action to activate the skill of Broadsword (focus).
 */
public class FocusAction extends Action {
    private final BroadSword broadSword;
    private boolean focusActive = false;
    public FocusAction(BroadSword broadSword) {
        this.broadSword = broadSword;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        double requiredStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA)*0.2; // 20% of maximum stamina
        int currentStamina = actor.getAttribute(BaseActorAttributes.STAMINA);

        if(currentStamina < requiredStamina){
            return actor + " does not have enough stamina to activate Focus";
        } else {
            // Broadswords new stats
            int newHitRate = 90;
            float increaseDamageMultiplierBy = 0.1f;
            broadSword.updateHitRate(newHitRate);
            broadSword.increaseDamageMultiplier(increaseDamageMultiplierBy);

            // stamina decrease 20% of max
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, (int) requiredStamina);

            // Activates skill and reset counter
            focusActive = true;
            broadSword.setFocusCounter();
            return actor + " has activated Focus on " + broadSword.toString();
        }
    }

    public void toggleFocusActive(){
        focusActive = !focusActive;
    }

    public Boolean isFocusActive(){
        return focusActive;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of Broadsword (focus)";
    }

}
