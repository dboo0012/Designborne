package game.actors;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.behaviours.FollowBehaviour;
import game.attributes.EntityTypes;
import game.items.HealingVial;
import game.weather.Weather;
import game.weather.WeatherControl;


/**
 * A Red Wolf  actor that has the ability to be spawned.
 *
 * @author Jerry
 */
public class RedWolf extends EnemyActor implements ActorSpawn {
    private final int DEFAULT_DAMAGE = 15;
    private int damage = DEFAULT_DAMAGE;
    private final double DEFAULT_RATE = 0.30;
    private double rate = DEFAULT_RATE;
    private final int FOLLOW_BEHAVIOUR_ID = 997;

    /**
     * Constructor for the RedWolf class.
     */
    public RedWolf() {
        super("Red Wolf", 'r', 25, 25);
        addDroppableItem(new HealingVial(), 0.1);
    }

    /**
     * Intrinsic weapon for RedWolf
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage, "bites", 80);
    }

    /**
     * Determine the allowable actions for the Red Wolf, including the FollowBehaviour.
     *
     * @param otherActor the target actor to check for follow behavior
     * @param direction  the direction in which to perform the action
     * @param map        the GameMap in which the action is performed
     * @return an ActionList containing allowable actions for the Red Wolf
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(EntityTypes.PLAYABLE) && !getBehaviours().containsKey(FOLLOW_BEHAVIOUR_ID)){
            addBehaviour(FOLLOW_BEHAVIOUR_ID, new FollowBehaviour(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);


    }

    /**
     * Perform actions during the Red Wolf's turn, including handling weather effects.
     *
     * @param actions    the list of available actions
     * @param lastAction the last action performed
     * @param map        the GameMap in which the action is performed
     * @param display    the Display object to print messages
     * @return the selected Action to perform
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        if (WeatherControl.getCurrentWeather() == Weather.SUNNY){
            damage = DEFAULT_DAMAGE * 3;
            rate = DEFAULT_RATE;
        } else if (WeatherControl.getCurrentWeather() == Weather.RAINY){
            rate = DEFAULT_RATE * 1.5;
            damage = DEFAULT_DAMAGE;
        } else if (WeatherControl.getCurrentWeather() == Weather.DEFAULT){
            rate = DEFAULT_RATE;
            damage = DEFAULT_DAMAGE;
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Spawn a Red Wolf with a chance based on the current spawn rate.
     *
     * @return a new Red Wolf instance if spawned, or null if not spawned
     */
    @Override
    public Actor spawn() {
        if (Math.random() < rate){
            return new RedWolf();
        }
        return null;
    }
}
