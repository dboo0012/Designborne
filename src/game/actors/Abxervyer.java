package game.actors;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.behaviours.FollowBehaviour;
import game.attributes.EntityTypes;
import game.grounds.Gate;
import game.weather.WeatherControl;


/**
 * A Abxervyer actor that has the ability to be spawned.
 */
public class Abxervyer extends EnemyActor  {
    private WeatherControl weatherControl;
    private GameMap destination;

    public Abxervyer(GameMap destination, WeatherControl weatherControl) {
        super("Abxervyer", 'Y', 2000, 5000);
        addCapability(EntityTypes.BOSS);
        this.destination = destination;
        this.weatherControl = weatherControl;
    }

    /**
     * Intrinsic weapon for Abxervyer
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches", 25);
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(this);
        currentLocation.setGround(new Gate(destination));
        return "Abxervyer has been slain!"  + " " + super.unconscious(actor, map);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        weatherControl.updateWeather();
        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(EntityTypes.PLAYABLE) && !this.behaviours.containsKey(997)){
            this.behaviours.put(997, new FollowBehaviour(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);


    }

}
