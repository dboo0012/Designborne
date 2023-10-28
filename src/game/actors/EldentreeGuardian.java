package game.actors;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.behaviours.FollowBehaviour;
import game.attributes.Ability;
import game.attributes.EntityTypes;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weather.Weather;
import game.weather.WeatherControl;


/**
 * An Eldentree Guardian  actor that has the ability to be spawned.
 *
 * @author Jerry
 */
public class EldentreeGuardian extends EnemyActor implements ActorSpawn {
    private final double DEFAULT_RATE = 0.2;
//    private double rate = DEFAULT_RATE;
    private final int FOLLOW_BEHAVIOUR_ID = 997;
    public EldentreeGuardian() {
        super("Eldentree Guardian", 'e', 250, 250);
        addDroppableItem(new HealingVial(), 0.25); //0.2
        addDroppableItem(new RefreshingFlask(), 0.15);
        addCapability(Ability.VOID_IMMUNITY);
    }

    /**
     * Intrinsic weapon for EldentreeGuardian
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "hits", 80);
    }

    /**
     * Determine the allowable actions for the EldentreeGuardian, including the FollowBehaviour.
     *
     * @param otherActor the target actor to check for follow behavior
     * @param direction  the direction in which to perform the action
     * @param map        the GameMap in which the action is performed
     * @return an ActionList containing allowable actions for the EldentreeGuardian
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(EntityTypes.PLAYABLE) && !getBehaviours().containsKey(FOLLOW_BEHAVIOUR_ID)){
            addBehaviour(FOLLOW_BEHAVIOUR_ID, new FollowBehaviour(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);

    }

    /**
     * Spawn an Eldentree Guardian with a chance based on the current spawn rate.
     *
     * @return a new Eldentree Guardian instance if spawned, or null if not spawned
     */
    @Override
    public Actor spawn() {

        if (Math.random() < DEFAULT_RATE){
            return new EldentreeGuardian();
        }
        return null;
    }
}
