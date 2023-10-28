package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * A class that represents a tickable action.
 *
 * @author Daryl
 */
public abstract class TickableAction extends Action {
    private final float defDmgMultiplier;
    private final int defHitRate;
    private WeaponItem weaponItem;
    private int counter;
    private int maxCounter;
    private boolean active = false;
    private String actionName;

    /**
     * Constructor.
     */
    public TickableAction(WeaponItem weaponItem, String actionName, float defDmgMultiplier, int defHitRate, int maxCounter){
        this.weaponItem = weaponItem;
        this.actionName = actionName;
        this.defDmgMultiplier = defDmgMultiplier;
        this.defHitRate = defHitRate;
        this.maxCounter = maxCounter;
        this.counter = 0;
    }

    public WeaponItem getWeaponItem() {
        return weaponItem;
    }

    /**
     * Returns the default damage multiplier of the weapon.
     * @return the default damage multiplier of the weapon
     */
    public float getDefDmgMultiplier() {
        return defDmgMultiplier;
    }

    /**
     * Returns the default hit rate of the weapon.
     * @return the default hit rate of the weapon
     */
    public int getDefHitRate() {
        return defHitRate;
    }

    /**
     * Executes the TickableAction, but this abstract class does not provide a concrete implementation.
     *
     * @param actor the actor performing the action
     * @param map   the game map where the action is executed
     * @return a description of the action's outcome (null in this abstract class)
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    /**
     * Provides a menu description for the TickableAction, but this abstract class does not provide a concrete implementation.
     *
     * @param actor the actor performing the action
     * @return a description of the action for display in the game menu (null in this abstract class)
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    /**
     * Resets the state of the TickableAction. Subclasses should implement this method to reset specific attributes.
     */
    public abstract void reset();

    /**
     * Activates the TickableAction, making it active.
     */
    public void activate(){
        active = true;
    }

    /**
     * Deactivates the TickableAction, making it inactive.
     */
    public void deactivate(){
        active = false;
    }

    /**
     * Checks if the TickableAction is currently active.
     *
     * @return true if the action is active, false otherwise
     */
    public Boolean isActive(){
        return active;
    }

    /**
     * Resets the counter for the TickableAction to 0.
     */
    public void resetCounter(){
        counter = 0;
    }

    /**
     * Logic for the TickableAction to be executed every tick.
     */
    public void tick(){
        // Increase counter by 1
        if (isActive()){
            if (counter < maxCounter) {  // Check if max counter is reached
                counter++;
                new Display().println(String.format("%s counter: %d/%d", actionName, counter, maxCounter));
                if (counter == maxCounter){
                    new Display().println(String.format("%s would expire next round.", actionName));
                }
            } else{
                reset();  // Reset the weapon
            }
        }
    }
}
