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
            String slam = slam(target, map);

            // Attack
            String attack = new AttackAction(target, " slams ", giantHammer).attack(actor, map);

            return attack + slam;
        }
    }

    public String slam(Actor otherActor, GameMap map){
        // get all the exits of the enemy, within those exits, if there is an actor, attack with 50% damage
        int originalDamage = giantHammer.damage();
        int splashDamage = (int) (originalDamage * 0.5);

        Location target = map.locationOf(otherActor); // Get exits around the target actor

//        new Display().println(String.format("Great Slam did AOE damage around %s", target));
        String result = String.format("\nGreat Slam did AOE damage around %s", target);

        for (Exit exit : target.getExits()) {
            Location destination = exit.getDestination();

            if (destination.containsAnActor()) {
                Actor affectedActor = destination.getActor();
                affectedActor.hurt(splashDamage);
//                new Display().println(String.format("%s is splashed by %d damage from Great Slam", affectedActor, splashDamage));
                result += String.format("\n%s is splashed by %d damage from Great Slam", affectedActor, splashDamage);
                if (!affectedActor.isConscious()) {
                    result += String.format("\n%s", affectedActor.unconscious(otherActor, map));
//                    new Display().println(affectedActor.unconscious(affectedActor, map));
                }
            }
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s activates the skill of Great Slam on %s", actor, giantHammer.toString());
    }
}
