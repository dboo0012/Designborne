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
 * A Abxervyer actor that has the ability to be spawned.
 */
public class Abxervyer extends EnemyActor  {
    private WeatherControl weatherControl;

    public Abxervyer(WeatherControl weatherControl) {
        super("Abxervyer", 'Y', 2000, 5000);
        addDroppableItem(new HealingVial(), 0.2); //0.2
        addCapability(EntityTypes.BOSS);
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
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        weatherControl.updateWeather();

        // Get the current weather condition
        Weather currentWeather = weatherControl.getCurrentWeather();

        // Apply weather effects based on the current weather condition
        if (currentWeather == Weather.SUNNY) {
            // Implement sunny weather effects
            // Modify spawning rates and enemy damage accordingly
        } else if (currentWeather == Weather.RAINY) {
            // Implement rainy weather effects
            // Modify spawning rates and healing effects accordingly
        }

        // Perform other actions for the boss during its turn
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
