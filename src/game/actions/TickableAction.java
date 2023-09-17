package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;

public abstract class TickableAction extends Action {
    protected final float defDmgMultiplier;
    protected final int defHitRate;
    protected WeaponItem weaponItem;
    protected int counter;
    protected int maxCounter;

    protected boolean active = false;

    protected String actionName;

    public TickableAction(WeaponItem weaponItem, String actionName, float defDmgMultiplier, int defHitRate, int maxCounter){
        this.weaponItem = weaponItem;
        this.actionName = actionName;
        this.defDmgMultiplier = defDmgMultiplier;
        this.defHitRate = defHitRate;
        this.maxCounter = maxCounter;
        this.counter = 0;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }

    public abstract void reset();

    public void activate(){
        active = true;
    }

    public void deactivate(){
        active = false;
    }

    public Boolean isActive(){
        return active;
    }

    public void resetCounter(){
        counter = 0;
    }


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
