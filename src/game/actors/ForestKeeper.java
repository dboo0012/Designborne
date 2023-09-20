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
 */
public class ForestKeeper extends EnemyActor implements ActorSpawn {
    private final double DEFAULT_RATE = 0.15;
    private double rate = DEFAULT_RATE;
    private int healAmount = 10;
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

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(EntityTypes.PLAYABLE) && !this.behaviours.containsKey(997)){
            this.behaviours.put(997, new FollowBehaviour(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if (WeatherControl.getCurrentWeather() == Weather.SUNNY){
            rate = DEFAULT_RATE * 2;
        } else if (WeatherControl.getCurrentWeather() == Weather.RAINY){
            rate = DEFAULT_RATE;
            this.modifyAttribute((BaseActorAttributes.HEALTH), ActorAttributeOperations.INCREASE, 10);
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Spawn a Forest Keeper with a 15% chance.
     * @return ForestKeeper or null
     */
    @Override
    public Actor spawn() {

        if (Math.random() < rate){
            return new ForestKeeper();
        }
        return null;
    }
}
