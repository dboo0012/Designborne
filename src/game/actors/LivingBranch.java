package game.actors;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.behaviours.WanderBehaviour;
import game.attributes.Ability;
import game.attributes.EntityTypes;
import game.items.Bloodberry;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weather.Weather;
import game.weather.WeatherControl;


/**
 * A Living Branch  actor that has the ability to be spawned.
 *
 * @author Jerry
 */
public class LivingBranch extends EnemyActor implements ActorSpawn {
    private final double DEFAULT_RATE = 0.9;
    //    private double rate = DEFAULT_RATE;
    private final int WANDER_BEHAVIOUR_ID = 999;
    public LivingBranch() {
        super("Living Branch", '?', 75, 500);
        addDroppableItem(new Bloodberry(), 0.5);
        addCapability(Ability.VOID_IMMUNITY);
    }

    /**
     * Intrinsic weapon for LivingBranch
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(250, "swings", 90);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        removeBehaviour(WANDER_BEHAVIOUR_ID);
        return super.allowableActions(otherActor, direction, map);

    }

    /**
     * Spawn a Living Branch with a chance based on the current spawn rate.
     *
     * @return a new Living Branch instance if spawned, or null if not spawned
     */
    @Override
    public Actor spawn() {

        if (Math.random() < DEFAULT_RATE){
            return new LivingBranch();
        }
        return null;
    }
}
