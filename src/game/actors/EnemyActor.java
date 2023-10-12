package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.WanderBehaviour;
import game.attributes.EntityTypes;
import game.attributes.Status;
import game.items.ItemDrop;
import game.items.Runes;

import java.util.*;

/**
 * Class representing an enemy actor in the game.
 * The EnemyActor class extends the Actor class and represents an enemy character in the game.
 * It includes behaviors such as wandering and attacking.
 *
 * @author Meekal, Daryl, Jerry
 */
public abstract class EnemyActor extends Actor implements Attackable{
    private Map<Integer, Behaviour> behaviours = new TreeMap<>();
    private  ArrayList<Class<? extends Item>> itemsToBeDropped = new ArrayList<>();
    private  ArrayList<Double> itemsToBeDroppedChance = new ArrayList<>();
    private ItemDrop itemDrop = new ItemDrop();
    private int runesDropped;
    private final int WANDER_BEHAVIOUR_ID = 999;
    private final int ATTACK_BEHAVIOUR_ID = 1;

    /**
     * Constructor for creating an EnemyActor object.
     *
     * @param name        Name of the enemy actor.
     * @param displayChar Character to represent the enemy actor in the UI.
     * @param hitPoints   Enemy actor's starting number of hit points.
     */
    public EnemyActor(String name, char displayChar, int hitPoints, int runesDropped) {
        super(name, displayChar, hitPoints);
        setRunesDropped(runesDropped);
        this.behaviours.put(WANDER_BEHAVIOUR_ID, new WanderBehaviour());
        this.behaviours.put(ATTACK_BEHAVIOUR_ID, new AttackBehaviour());
        this.addCapability(EntityTypes.ENEMY);
    }

    /**
     * Gets the number of runes dropped by the enemy actor.
     *
     * @return the number of runes dropped
     */
    public int getRunesDropped(){
        return this.runesDropped;
    }

    /**
     * Sets the number of runes dropped by the enemy actor upon defeat.
     *
     * @param runesDropped the number of runes to be dropped
     */
    public void setRunesDropped(int runesDropped) {
        this.runesDropped = runesDropped;
    }

    /**
     * Gets the map of behaviors associated with this enemy actor.
     *
     * @return the map of behaviors
     */
    public Map<Integer, Behaviour> getBehaviours() {
        return behaviours;
    }

    /**
     * Adds a behavior to the enemy actor's list of behaviors.
     *
     * @param priority  the priority of the behavior
     * @param behaviour the behavior to be added
     */
    public void addBehaviour(int priority, Behaviour behaviour) {
        this.behaviours.put(priority, behaviour);
    }
    public void removeBehaviour(int priority){
        this.behaviours.remove(priority);
    }

    /**
     * Gets the list of item classes to be dropped by the enemy actor.
     *
     * @return the list of item classes to be dropped
     */
    public ArrayList<Class<? extends Item>> getItemsToBeDropped() {
        return itemsToBeDropped;
    }

    /**
     * Sets the list of item classes to be dropped by the enemy actor.
     *
     * @param itemsToBeDropped the list of item classes to be dropped
     */
    public void setItemsToBeDropped(ArrayList<Class<? extends Item>> itemsToBeDropped) {
        this.itemsToBeDropped = itemsToBeDropped;
    }

    /**
     * Gets the list of drop chances for the items to be dropped by the enemy actor.
     *
     * @return the list of drop chances
     */
    public ArrayList<Double> getItemsToBeDroppedChance() {
        return itemsToBeDroppedChance;
    }

    /**
     * Sets the list of drop chances for the items to be dropped by the enemy actor.
     *
     * @param itemsToBeDroppedChance the list of drop chances
     */
    public void setItemsToBeDroppedChance(ArrayList<Double> itemsToBeDroppedChance) {
        this.itemsToBeDroppedChance = itemsToBeDroppedChance;
    }

    /**
     * Gets the ItemDrop object associated with the enemy actor.
     *
     * @return the ItemDrop object
     */
    public ItemDrop getItemDrop() {
        return itemDrop;
    }

    /**
     * Sets the ItemDrop object associated with the enemy actor.
     *
     * @param itemDrop the ItemDrop object to be set
     */
    public void setItemDrop(ItemDrop itemDrop) {
        this.itemDrop = itemDrop;
    }

    /**
     * Adds an item to be dropped by the enemy actor with a specified drop chance.
     *
     * @param item   the item to be dropped
     * @param chance the drop chance for the item
     */
    public void addDroppableItem(Item item, double chance){
        this.itemsToBeDropped.add(item.getClass());
        this.itemsToBeDroppedChance.add(chance);
    }

    /**
     * Overrides the unconscious method to handle additional actions upon an enemy actor becoming unconscious.
     * This includes dropping items and runes.
     *
     * @param actor the actor performing the action
     * @param map   the game map where the action is executed
     * @return the outcome of the unconscious action
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        dropItems(this, map);
        dropRune(this, map);
        return super.unconscious(actor, map);
    }

    /**
     * Drops runes at the location of the enemy actor upon defeat, if any runes are to be dropped.
     *
     * @param actor the actor representing the enemy
     * @param map   the game map where the action is executed
     */
    public void dropRune(Actor actor, GameMap map){
        Location currentLocation = map.locationOf(actor);
        if (getRunesDropped() > 0){
            Runes runes = new Runes(getRunesDropped());
            map.at(currentLocation.x(), currentLocation.y()).addItem(runes);

        }

    }

    /**
     * Drops items when the actor becomes unconscious.
     *
     * @param actor                The actor that became unconscious.
     * @param map                  The map where the actor fell unconscious.
     */
    public void dropItems(Actor actor, GameMap map) {
        getItemDrop().dropItems(actor, map, getItemsToBeDropped(), getItemsToBeDroppedChance());
    }

    /**
     * Determines the action to be performed during the enemy actor's turn.
     *
     * @param actions    Collection of possible Actions for this actor.
     * @param lastAction The Action this actor took last turn.
     * @param map        The map containing the actor.
     * @param display    The I/O object to which messages may be written.
     * @return The valid action that can be performed in that iteration or null if no valid action is found.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : getBehaviours().values()) {
            Action action = behaviour.getAction(this, map);
            if (action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * Determines the allowable actions when the enemy actor is attacked by another actor.
     *
     * @param otherActor The actor that might be performing an attack.
     * @param direction  String representing the direction of the other actor.
     * @param map        Current GameMap.
     * @return A list of allowable actions for the enemy actor.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this, direction));
        }
        return actions;
    }
}