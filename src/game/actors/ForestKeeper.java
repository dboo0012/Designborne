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
import game.attributes.EntityTypes;
import game.items.HealingVial;
import game.weather.Weather;
import game.weather.WeatherControl;


/**
 * A Forest Keeper  actor that has the ability to be spawned.
 *
 * @author Jerry
 */
public class ForestKeeper extends EnemyActor implements ActorSpawn {
    private final double DEFAULT_RATE = 0.15;
    private double rate = DEFAULT_RATE;
    private final int FOLLOW_BEHAVIOUR_ID = 997;
    public ForestKeeper() {
        super("Forest Keeper", '8', 125, 50);
        addDroppableItem(new HealingVial(), 0.2); //0.2
    }

    /**
     * Intrinsic weapon for ForestKeeper
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(25, "touches", 75);
    }

    /**
     * Determine the allowable actions for the ForestKeeper, including the FollowBehaviour.
     *
     * @param otherActor the target actor to check for follow behavior
     * @param direction  the direction in which to perform the action
     * @param map        the GameMap in which the action is performed
     * @return an ActionList containing allowable actions for the ForestKeeper
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(EntityTypes.PLAYABLE) && !getBehaviours().containsKey(FOLLOW_BEHAVIOUR_ID)){
            addBehaviour(FOLLOW_BEHAVIOUR_ID, new FollowBehaviour(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);

    }

    /**
     * Perform actions during the ForestKeeper's turn, including handling weather effects.
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
            rate = DEFAULT_RATE * 2;
        } else if (WeatherControl.getCurrentWeather() == Weather.RAINY){
            rate = DEFAULT_RATE;
            int healAmount = 10;
            this.modifyAttribute((BaseActorAttributes.HEALTH), ActorAttributeOperations.INCREASE, healAmount);
        } else if (WeatherControl.getCurrentWeather() == Weather.DEFAULT){
            rate = DEFAULT_RATE;
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Spawn a Forest Keeper with a chance based on the current spawn rate.
     *
     * @return a new ForestKeeper instance if spawned, or null if not spawned
     */
    @Override
    public Actor spawn() {

        if (Math.random() < rate){
            return new ForestKeeper();
        }
        return null;
    }
}
