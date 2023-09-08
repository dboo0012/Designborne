package edu.monash.fit2099.engine.actors;


import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.attributes.ActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Printable;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.*;

/**
 * The Actor class represents a {@link GameEntity} that can perform an {@link Action}.
 * It has a hit points attribute, which represents its health. When this value
 * reaches zero, the Actor is unconscious.
 * Additionally, an Actor has a collection of {@link Item}s it is carrying in its inventory.
 */
public abstract class Actor extends GameEntity implements Printable {
    private static final int DEFAULT_INTRINSIC_WEAPON_DAMAGE = 5;
    private static final String DEFAULT_INTRINSIC_WEAPON_VERB = "punches";
    private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;

    /**
     * A flexible and extensible attributes system that allows new attributes to be added,
     * which enables more interesting game mechanics.
     * For example, in addition to hit points, another attribute that represents its stamina can be added.
     * If the actor runs out of stamina, it will be unable to perform certain actions, such as attacking.
     */
    private final Map<Enum<?>, ActorAttribute<Integer>> attributes = new HashMap<>();
    /**
     * Actor's name
     */
    protected final String name;
    /**
     * A character that will be displayed on the terminal/console
     */
    private char displayChar;
    /**
     * A bag of items
     */
    private final List<Item> itemInventory = new ArrayList<>();
    /**
     * damage multiplier for actor's intrinsic weapon
     */
    private float damageMultiplier;
    private Wallet wallet;

    /**
     * The constructor of the Actor class.
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Actor(String name, char displayChar, int hitPoints) {
        this.name = name;
        this.displayChar = displayChar;
        this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
        this.addAttribute(BaseActorAttributes.HEALTH, new BaseActorAttribute(hitPoints));
        this.wallet = new Wallet();
    }

    /**
     * A method for checking whether this actor has a specific attribute
     * @param name the name of the attribute, such as BaseActorAttributes.HEALTH
     * @return true if the actor has the queried attribute, false otherwise
     */
    public boolean hasAttribute(Enum<?> name) {
        return this.attributes.containsKey(name);
    }

    /**
     * A method for adding an attribute to the actor.
     * @param name the name of the attribute to be added, which must be a value of an enumeration, such as BaseActorAttributes.STAMINA.
     * @param attribute an object that implements the {@link ActorAttribute} interface.
     */
    public void addAttribute(Enum<?> name, ActorAttribute<Integer> attribute) {
        this.attributes.put(name, attribute);
    }

    /**
     * A method for modifying the value of an attribute.
     * An alternative implementation to this method is to create several smaller methods,
     * such as increaseAttribute(), decreaseAttribute(), and updateAttribute().
     * However, this results in the Actor class being bloated with boilerplate methods.
     * Another alternative is to simply return the attribute object and let the client modify it directly.
     * However, this results in privacy leak, i.e. the client directly knows the underlying implementation of the attributes, 
     * which could result in unwanted dependencies.
     * Therefore, this method is chosen as a compromise.
     * It could be improved with the use of higher-order functions, but since it is not relevant to object-oriented programming, 
     * it is not implemented here.
     * @param name the name of the attribute to be modified, which must be a value of an enumeration, such as BaseActorAttributes.STAMINA.
     * @param operation the operation to be performed on the attribute, such as INCREASE, DECREASE, or UPDATE.
     * @param value the value to be used in the operation.
     * @throws IllegalArgumentException if the operation is invalid.
     */
    public void modifyAttribute(Enum<?> name, ActorAttributeOperations operation, int value) throws IllegalArgumentException {
        if (operation == ActorAttributeOperations.INCREASE) {
            this.attributes.get(name).increase(value);
        } else if (operation == ActorAttributeOperations.DECREASE) {
            this.attributes.get(name).decrease(value);
        } else if (operation == ActorAttributeOperations.UPDATE) {
            this.attributes.get(name).update(value);
        } else {
            throw new IllegalArgumentException("Invalid operation for modifying the value of actor's attribute.");
        }
    }

    /**
     * A method for modifying the maximum value of an attribute.
     * See the rationale for {@link Actor#modifyAttribute(Enum, ActorAttributeOperations, int)} for why this method is implemented this way.
     * @param name the name of the attribute to be modified, which must be a value of an enumeration, such as BaseActorAttributes.STAMINA.
     * @param operation the operation to be performed on the attribute, such as INCREASE, DECREASE, or UPDATE.
     * @param value the value to be used in the operation.
     * @throws IllegalArgumentException if the operation is invalid.
     */
    public void modifyAttributeMaximum(Enum<?> name, ActorAttributeOperations operation, int value) throws IllegalArgumentException {
        if (operation == ActorAttributeOperations.INCREASE) {
            this.attributes.get(name).increaseMaximum(value);
        } else if (operation == ActorAttributeOperations.DECREASE) {
            this.attributes.get(name).decreaseMaximum(value);
        } else if (operation == ActorAttributeOperations.UPDATE) {
            this.attributes.get(name).updateMaximum(value);
        } else {
            throw new IllegalArgumentException("Invalid operation for modifying the maximum value of actor's attribute.");
        }
    }

    /**
     * A method for getting the value of an attribute.
     * @param name the name of the attribute to be retrieved, which must be a value of an enumeration, such as BaseActorAttributes.STAMINA.
     * @return the value of the attribute.
     */
    public int getAttribute(Enum<?> name) {
        return this.attributes.get(name).get();
    }

    /**
     * A method for getting the maximum value of an attribute, such as the maximum hit points of the current actor.
     * @param name the name of the attribute to be retrieved, which must be a value of an enumeration, such as BaseActorAttributes.STAMINA.
     * @return the maximum value of the attribute.
     */
    public int getAttributeMaximum(Enum<?> name) {
        return this.attributes.get(name).getMaximum();
    }

    /**
     * A method for increasing the balance of the Actor's wallet.
     * Although this can be handled by the Wallet class itself, the prevention of privacy leak is prioritised.
     * @param amount the amount to be added to the balance
     */
    public void addBalance(int amount) {
        this.wallet.addBalance(amount);
    }

    /**
     * A method for deducting the balance of the Actor's wallet.
     * Although this can be handled by the Wallet class itself, the prevention of privacy leak is prioritised.
     * @param amount the amount to be deducted from the balance
     */
    public void deductBalance(int amount) {
        this.wallet.deductBalance(amount);
    }

    /**
     * A method for getting the balance of the Actor's wallet.
     * Although this can be handled by the Wallet class itself, the prevention of privacy leak is prioritised.
     * @return the balance of the Actor's wallet
     */
    public int getBalance() {
        return this.wallet.getBalance();
    }

    /**
     * A method for updating the damage multiplier of the actor's intrinsic weapon.
     * Although this can be implemented in the intrinsic weapon class itself, the prevention of privacy leak is prioritised.
     * @param newDamageMultiplier the new damage multiplier.
     */
    public void updateDamageMultiplier(float newDamageMultiplier) {
        this.damageMultiplier = newDamageMultiplier;
    }

    /**
     * A method for increasing the damage multiplier of the actor's intrinsic weapon.
     * Although this can be implemented in the intrinsic weapon class itself, the prevention of privacy leak is prioritised.
     * @param damageMultiplier the damage multiplier to be added to the current damage multiplier.
     */
    public void increaseDamageMultiplier(float damageMultiplier) {
        this.damageMultiplier += damageMultiplier;
    }

    /**
     * A method for returning the string representation of an actor.
     * It displays the actor's name and its current hit points, along with its maximum health hit points.
     */
    @Override
    public String toString() {
        return name + " (" +
                this.getAttribute(BaseActorAttributes.HEALTH) + "/" +
                this.getAttributeMaximum(BaseActorAttributes.HEALTH) +
                ")";
    }

    /**
     * Returns the Actor's display character, so that it can be displayed in the UI.
     * @return the Actor's display character
     */
    @Override
    public char getDisplayChar() {
        return displayChar;
    }

    /**
     * Replace current display character.
     * @param displayChar one character that will be displayed in the terminal/console
     */
    public final void setDisplayChar(char displayChar) {
        this.displayChar = displayChar;
    }

    /**
     * Add an item to this Actor's inventory.
     * @param item The Item to add.
     */
    public void addItemToInventory(Item item) {
        itemInventory.add(item);
    }

    /**
     * Remove an item from this Actor's inventory.
     * Although it is possible to return the itemInventory to be modified by the client directly,
     * it is not done so to prevent privacy leak.
     * @param item The Item to remove.
     */
    public void removeItemFromInventory(Item item) {
        itemInventory.remove(item);
    }

    /**
     * Get a copy of this Actor's inventory list.
     * Although it is possible to return the itemInventory to be modified by the client directly,
     * it is not done so to prevent privacy leak.
     * @return An unmodifiable wrapper of the inventory.
     */
    public List<Item> getItemInventory() {
        return Collections.unmodifiableList(itemInventory);
    }

    /**
     * Select and return an action to perform on the current turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the Action to be performed
     */
    public abstract Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display);

    /**
     * Returns a new collection of the Actions that the otherActor can do to the current Actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        return new ActionList();
    }

    /**
     * Is this Actor conscious?
     * Returns true if the current Actor has positive hit points.
     * Actors on zero hit points are deemed to be unconscious.
     * Depending on the game client, this status may be interpreted as either
     * unconsciousness or death, or inflict some other kind of status.
     * @return true if and only if hitPoints is positive.
     */
    public boolean isConscious() {
        return this.getAttribute(BaseActorAttributes.HEALTH) > 0;
    }

    /**
     * Method that can be executed when the actor is unconscious due to the action of another actor
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    public String unconscious(Actor actor, GameMap map) {
        map.removeActor(this);
        return this + " met their demise in the hand of " + actor;
    }

    /**
     * Method that can be executed when the actor is unconscious due to natural causes or accident
     * For example, the actor is unconscious due to them falling into a deep well.
     * @param map where the actor fell unconscious
     * @return a string describing what happened when the actor is unconscious
     */
    public String unconscious(GameMap map) {
        map.removeActor(this);
        return this + " ceased to exist.";
    }

    /**
     * Hurt the current actor with the given damage points.
     * @param damage the damage points that the actor receives
     */
    public void hurt(int damage) {
        this.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, damage);
    }

    /**
     * Heal the current actor with the given health points.
     * @param points the health points that the actor receives
     */
    public void heal(int points) {
        this.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.INCREASE, points);
    }

    /**
     * Creates and returns an intrinsic weapon.
     * Override this method to create an intrinsic weapon for
     * the current Actor with more interesting descriptions and/or different damage.
     * @return a freshly-instantiated IntrinsicWeapon
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        int damage = Math.round(DEFAULT_INTRINSIC_WEAPON_DAMAGE * damageMultiplier);
        return new IntrinsicWeapon(damage, DEFAULT_INTRINSIC_WEAPON_VERB);
    }

    /**
     * Returns true if and only if the current Actor has the required capability.
     * It will also return true if any of the items that the actor is carrying has the required capability.
     * @param capability the capability required
     * @return true if and only if the current Actor has the required capability
     */
    @Override
    public final boolean hasCapability(Enum<?> capability) {
        for (Item item : itemInventory) {
            if (item.hasCapability(capability)) {
                return true;
            }
        }
        return capabilitySet.hasCapability(capability);
    }
}
