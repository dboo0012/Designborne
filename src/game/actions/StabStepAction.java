package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.GreatKnife;

import java.util.ArrayList;
import java.util.Random;

public class StabStepAction extends Action {
    private final GreatKnife greatKnife;
    private final Actor target;
    private final Random random = new Random();
    public StabStepAction(GreatKnife greatKnife, Actor otherActor){
        this.greatKnife = greatKnife;
        this.target = otherActor;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        double requiredStamina = actor.getAttributeMaximum(BaseActorAttributes.STAMINA)*0.25; // 25% of maximum stamina
        int currentStamina = actor.getAttribute(BaseActorAttributes.STAMINA);

        if(currentStamina < requiredStamina){
            return actor + " does not have enough stamina to activate Stab & Step";
        } else {
            // stamina decrease 25% of max
            actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, (int) requiredStamina);

            // Stab
            String attack = new AttackAction(target, " stabbed ", greatKnife).attack(actor, map);

            // Step action (stab is done in weapon allowable actions)
            Location step = step(actor, map);
            map.moveActor(actor, step);

            return attack + "\n" + String.format("%s stabbed %s and stepped to %s", actor, target, step.toString());
        }
    }

    public Location step(Actor actor, GameMap map){
        ArrayList<Location> locations = new ArrayList<>(); // A list of all possible locations the actor can go

        Location target = map.locationOf(actor);

        for (Exit exit : target.getExits()) {
            Location destination = exit.getDestination();

            if (destination.canActorEnter(actor)) {
                locations.add(destination);
            }
        }
        if (!locations.isEmpty()) {
            return locations.get(random.nextInt(locations.size())); // A random exit is chosen
        }
        else {
            return map.locationOf(actor); // Stay put if no exits can be found
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of Stab & Step on " + greatKnife.toString();
    }
}