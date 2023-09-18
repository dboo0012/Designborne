package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.GiantHammer;

public class GreatSlam extends Action {

    private final GiantHammer giantHammer;

    public GreatSlam(GiantHammer giantHammer){
        this.giantHammer = giantHammer;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        double requiredStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA)*0.05; // 5% of maximum stamina
        int currentStamina = actor.getAttribute(BaseActorAttributes.STAMINA);

        if(currentStamina < requiredStamina){
            return actor + " does not have enough stamina to activate Great Slam";
        } else {
            // stamina decrease 5% of max
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, (int) requiredStamina);

            // Slam

            return actor + " has activated Great Slam on " + giantHammer.toString();
        }
    }

    public void slam(){
        // get all the exits of the enemy, within those exits, if there is an actor, attack with 50% damage
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of Great Slam on " + giantHammer.toString();
    }
}
