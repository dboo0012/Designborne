package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.*;
import game.actions.AttackAction;
import game.actions.FocusAction;
import game.actions.TickableAction;

import java.util.ArrayList;

/**
 * A BroadSword weapon.
 */
public class BroadSword extends WeaponItem{
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private float damageMultiplier;
    private int focusCounter;
    private ActionList actions = new ActionList();
    private FocusAction focusAction;
    /**
     * Constructor.
     */
    public BroadSword() {
        super("BroadSword", '1', 110, "slashes", 80);
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
    }

    public void reset(){
        setFocusCounter();
        this.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
        this.updateHitRate(80);
    }

    /**
     * Add a new FocusAction to the weapon.
     * @param newAction the new action to be added.
     */
    public void addAction(FocusAction newAction){
        actions.add(newAction);
        this.focusAction = newAction;
    }

    /**
     * Initialise the focus counter.
     */
    public void setFocusCounter() {
        this.focusCounter = 0;
    }

    /**
     * Revert the BroadSword to its original stats if the weapon is dropped.
     * @param location The location of the ground on which we lie.
     */
    public void tick (Location location){
        focusAction.reset();
    }

    /**
     * Keeps track of the Focus skill.
     * @param location The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location location, Actor actor){
        // Keeps track of focus
//        if(focusAction.isFocusActive()){
//            int MAX_FOCUS_COUNTER = 5;
//            if (focusCounter < MAX_FOCUS_COUNTER) {
//                focusCounter++;
//                new Display().println(String.format("Focus counter: %d/%d", focusCounter, MAX_FOCUS_COUNTER));
//                if (focusCounter == MAX_FOCUS_COUNTER){
//                    new Display().println("Focus expires next round!");
//                }
//            } else{
//                this.updateDamageMultiplier(DEFAULT_DAMAGE_MULTIPLIER);
//            }
//        }
        // Tick all the actions in the list of actions
//        for(Action action: actions){
//            action.tick();
//        }
        focusAction.tick();
    }

    /**
     * Adds the focusAction to the allowable actions.
     * @param actor the actor that owns the item
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor) {
//        ActionList actions = new ActionList();
//        actions.add(this.focusAction);
        return actions;
    }

    /**
     * Adds the attack action to attack mobs.
     * @param actor the other actor
     * @param location the location of the other actor
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(actor, location.toString(), this));
        return actions;
    }
}
