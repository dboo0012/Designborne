package game.weapons;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actions.FocusAction;
import game.attributes.Ability;
import game.attributes.EntityTypes;
import game.attributes.TradeCharacteristics;
import game.items.TradeableWeaponItem;

/**
 * A BroadSword weapon.
 */
public class BroadSword extends TradeableWeaponItem {
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private float damageMultiplier;
    private int focusCounter;
    private int initialHitRate;
    private ActionList actions = new ActionList();
    private FocusAction focusAction;


    /**
     * Constructor.
     *
     */
    public BroadSword() {
        super("BroadSword", '1', 110, "slashes", 80, 100);
        this.initialHitRate = 80;
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
        reset();
        focusAction.reset();
    }

    /**
     * Keeps track of the Focus skill.
     * @param location The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    public void tick(Location location, Actor actor){
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
//        actions.add(new FocusAction(this, 1.0f,80,5));
        return actions;
    }

    /**
     * Adds the attack action to attack mobs.
     * @param otherActor the other actor
     * @param location the location of the other actor
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(EntityTypes.ENEMY)){
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }
        return actions;
    }

    @Override
    public Item spawn() {
        return new BroadSword();
    }

    /**
     *
     * @param seller the Actor selling, passed in because different seller types may have different probability
     * @return a boolean indicating if the price is affected
     */
    public boolean isPriceAffected(Actor seller) {
        double traderScamChance = 1; //0.05
        return Math.random() < traderScamChance;
    }

    public int affectedPrice(Actor seller) {
        return getPrice();
    }

    @Override
    public Enum<TradeCharacteristics> getScamType(Actor seller) {
        Enum<TradeCharacteristics> scamType = super.getScamType(seller);
        if (seller.hasCapability(EntityTypes.TRADER)){
            scamType = TradeCharacteristics.STEAL_ITEMS;
        }
        return scamType;
    }
}

