package game.actors;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.behaviours.FollowBehaviour;
import game.attributes.EntityTypes;
import game.attributes.Status;
import game.items.HealingVial;
import game.main.Weather;
import game.main.WeatherControl;


/**
 * A Red Wolf  actor that has the ability to be spawned.
 */
public class RedWolf extends EnemyActor implements ActorSpawn {
    private final int DEFAULT_DAMAGE = 15;
    private int damage = DEFAULT_DAMAGE;
    private final double DEFAULT_RATE = 0.30;
    private double rate = DEFAULT_RATE;


    public RedWolf() {
        super("Red Wolf", 'r', 25, 25);
        addDroppableItem(new HealingVial(), 0.1);
    }

    /**
     * Intrinsic weapon for ForestKeeper
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(damage, "bites", 80);
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
            damage = DEFAULT_DAMAGE * 3;
            rate = DEFAULT_RATE;
        } else if (WeatherControl.getCurrentWeather() == Weather.RAINY){
            rate = DEFAULT_RATE * 1.5;
            damage = DEFAULT_DAMAGE;
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Spawn a Red Wolf with a 30% chance.
     * @return RedWolf or null
     */
    @Override
    public Actor spawn() {
        if (Math.random() < rate){
            return new RedWolf();
        }
        return null;
    }
}
