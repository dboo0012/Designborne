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
import game.items.Upgradable;

/**
 * A BroadSword weapon.
 *
 * @author Daryl
 */
public class BroadSword extends TradeableWeaponItem implements Upgradable {
    private static final int INITIAL_DAMAGE = 110;
    private static final int INITIAL_HIT_RATE = 80;
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
    private int damage;
    private int hitRate;
    private float damageMultiplier;
    private FocusAction focusAction;
    private boolean isUpgraded = false;

    /**
     * Constructor.
     */
    public BroadSword() {
        super("BroadSword", '1', INITIAL_DAMAGE, "slashes", INITIAL_HIT_RATE, 100);
        this.damage = INITIAL_DAMAGE;
        this.hitRate = INITIAL_HIT_RATE;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
        this.focusAction = new FocusAction(this, DEFAULT_DAMAGE_MULTIPLIER, this.hitRate,5);
        addCapability(Ability.UPGRADE);
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
        focusAction.tick();
    }

    /**
     * Adds the focusAction to the allowable actions.
     * @param actor the actor that owns the item
     * @return ActionList of allowable actions
     */
    @Override
    public ActionList allowableActions(Actor actor) {
        ActionList actions = new ActionList();
        actions.add(focusAction);
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
        double traderScamChance = 0.05; //0.05
        return Math.random() < traderScamChance;
    }

    public int affectedPrice(Actor seller) {
        return getPrice();
    }

    @Override
    public void increaseDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier += damageMultiplier;
    }

    @Override
    public void updateDamageMultiplier(float newDamageMultiplier) {
        this.damageMultiplier = newDamageMultiplier;
    }

    @Override
    public int damage() {
        int finalDamage = Math.round(INITIAL_DAMAGE * damageMultiplier); // Multiply the initial damage by damage multiplier

//        System.out.println("Damage multiplier: " + damageMultiplier);
//        System.out.println("Damage: " + damage);
//        System.out.println("Before adding: " + finalDamage);

        // Add upgraded damage (non multiplied damage) to the final damage
        if (this.damage > INITIAL_DAMAGE){
            finalDamage += (this.damage - INITIAL_DAMAGE);
        }
//        if (isUpgraded){
//            int change = damage - INITIAL_DAMAGE;
//            finalDamage += change;
//        }
//        System.out.println("After adding: " + finalDamage);

        // Set and return the final damage value
        this.damage = finalDamage;
        return finalDamage;
    }

    @Override
    public Enum<TradeCharacteristics> getScamType(Actor seller) {
        Enum<TradeCharacteristics> scamType = super.getScamType(seller);
        if (seller.hasCapability(EntityTypes.TRADER)){
            scamType = TradeCharacteristics.STEAL_RUNES;
        }
        return scamType;
    }

    @Override
    public String upgrade() {
        // Increase damage by the upgrade value and set to isUpgraded
        int upgradeValue = 10;
        this.damage += upgradeValue;
        isUpgraded = true;

        return String.format("%s has been upgraded to +%d damage, total dealing %d DAMAGE.", this, upgradeValue,this.damage);
    }

    @Override
    public int upgradePrice() {
        return 1000;
    }

    @Override
    public boolean singleUpgrade() {
        return false;
    }
}

