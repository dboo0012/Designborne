package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.GreatKnife;

import java.util.ArrayList;
import java.util.Random;

/**
 * The class represents a stab and step action.
 *
 * @author Daryl
 */
public class StabStepAction extends Action {
    private final GreatKnife greatKnife;
    private final Actor target;
    private final Random random = new Random();

    /**
     * Constructor for StabStepAction.
     *
     * @param greatKnife the great knife
     * @param otherActor the actor that the action is performed on
     */
    public StabStepAction(GreatKnife greatKnife, Actor otherActor){
        this.greatKnife = greatKnife;
        this.target = otherActor;
    }

    /**
     * Executes the Stab & Step action, which involves performing a stab attack with a Great Knife and stepping to a nearby location.
     *
     * @param actor the actor performing the action
     * @param map the game map where the action is executed
     * @return a description of the action's outcome
     */
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

            // Step
            Location step = step(actor, map);
            if (step != map.locationOf(actor)){
                map.moveActor(actor, step);
            }else{
                new DoNothingAction(); // Stay put if no exits can be found
            }
//            map.moveActor(actor, step);

            return attack + "\n" + String.format("%s stabbed %s and stepped to %s", actor, target, step.toString());
        }
    }

    /**
     * Determines a valid nearby location for the actor to step to.
     *
     * @param actor the actor performing the action
     * @param map the game map where the action is executed
     * @return a valid nearby location for the actor to step to
     */
    public Location step(Actor actor, GameMap map){
        ArrayList<Location> locations = new ArrayList<>(); // A list of all possible locations the actor can go

        Location target = map.locationOf(actor);

        for (Exit exit : target.getExits()) {
            Location destination = exit.getDestination();

            // Player can enter and there is no actor on the destination
            if (destination.canActorEnter(actor) && !destination.containsAnActor()) { //&& !destination.containsAnActor()
                locations.add(destination);
            }
        }

        System.out.println("List of locations: " +locations);

        if (!locations.isEmpty()) {
            return locations.get(random.nextInt(locations.size())); // A random exit is chosen
        }
        else {
            return map.locationOf(actor); // Stay put if no exits can be found
        }
    }

    /**
     * Provides a description of the action for display in the game menu.
     *
     * @param actor the actor performing the action
     * @return a description of the action
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of Stab & Step on " + greatKnife.toString();
    }
}