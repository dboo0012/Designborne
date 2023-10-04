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
import game.weather.Weather;
import game.weather.WeatherControl;

import static game.weather.WeatherControl.getCurrentWeather;


/**
 * A Abxervyer actor that has the ability to be spawned.
 */
public class Abxervyer extends EnemyActor  {
    private WeatherControl weatherControl;
    private GameMap destination;
    private final int FOLLOW_BEHAVIOUR_ID = 997;

    /**
     * Constructor for Abxervyer.
     *
     * @param destination   The destination GameMap where Abxervyer spawns gates.
     * @param weatherControl The WeatherControl object to manage weather conditions.
     */
    public Abxervyer(GameMap destination, WeatherControl weatherControl) {
        super("Abxervyer", 'Y', 2000, 5000);
        addCapability(EntityTypes.BOSS);
        this.destination = destination;
        this.weatherControl = weatherControl;
    }

    /**
     * Returns the intrinsic weapon for Abxervyer.
     *
     * @return A freshly-instantiated IntrinsicWeapon for Abxervyer.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches", 25);
    }

    /**
     * Handles the action when Abxervyer becomes unconscious.
     *
     * @param actor The actor that renders Abxervyer unconscious.
     * @param map   The GameMap where Abxervyer is located.
     * @return A message indicating that Abxervyer has been slain.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        weatherControl.setCurrentWeather(Weather.DEFAULT);
        Location currentLocation = map.locationOf(this);
        currentLocation.setGround(new Gate(destination, 20, 3));
        return "Abxervyer has been slain!"  + " " + super.unconscious(actor, map);
    }

    /**
     * Handles Abxervyer's turn, including weather control updates and display of current weather.
     *
     * @param actions    A list of available actions for Abxervyer.
     * @param lastAction The last action performed by Abxervyer.
     * @param map        The GameMap where Abxervyer is located.
     * @param display    The display for showing game information.
     * @return The selected action for Abxervyer's turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        weatherControl.updateWeather();
        new Display().println("Current weather: " + getCurrentWeather());
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Determines the allowable actions for Abxervyer based on other actors and the game map.
     *
     * @param otherActor  The other actor involved in the action.
     * @param direction   The direction of the action.
     * @param map         The GameMap where Abxervyer is located.
     * @return A list of allowable actions for Abxervyer.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(EntityTypes.PLAYABLE) && !getBehaviours().containsKey(FOLLOW_BEHAVIOUR_ID)){
            addBehaviour(FOLLOW_BEHAVIOUR_ID, new FollowBehaviour(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);


    }

}
