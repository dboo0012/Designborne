package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.weapons.GiantHammer;

public class GreatSlamAction extends Action {
    private final GiantHammer giantHammer;
    private final Actor target;

    public GreatSlamAction(GiantHammer giantHammer, Actor otherActor){
        this.giantHammer = giantHammer;
        this.target = otherActor;
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
            slam(target, map);

            // Attack
            String attack = new AttackAction(target, " slams ", giantHammer).attack(actor, map);

            return attack + "\n" + String.format("Great Slam did AOE damage around %s", target);
        }
    }

    public void slam(Actor otherActor, GameMap map){
        // get all the exits of the enemy, within those exits, if there is an actor, attack with 50% damage
        int originalDamage = giantHammer.damage();
        int splashDamage = (int) (originalDamage * 0.5);

        Location target = map.locationOf(otherActor); // Get exits around the target actor

        for (Exit exit : target.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor affectedActor = destination.getActor();
                affectedActor.hurt(splashDamage);
                new Display().println(String.format("%s is splashed by %d damage from Great Slam", affectedActor, splashDamage));
            }
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates the skill of Great Slam on " + giantHammer.toString();
    }
}
