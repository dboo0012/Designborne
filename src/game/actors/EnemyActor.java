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

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Class representing an enemy actor in the game.
 *
 * The EnemyActor class extends the Actor class and represents an enemy character in the game. It includes behaviors such as wandering and attacking.
 */
public abstract class EnemyActor extends Actor implements Attackable{

    private final Random rand = new Random();

    protected Map<Integer, Behaviour> behaviours = new TreeMap<>();
    //Pre-defined list of items to drop
    protected final ArrayList<Class<? extends Item>> itemsToBeDropped = new ArrayList<>();
    //Array of chances to drop, with indices matching itemsToBeDropped
    protected final ArrayList<Double> itemsToBeDroppedChance = new ArrayList<>();
    protected ItemDrop itemDrop = new ItemDrop();
    protected int runesDropped;
    /**
     * Constructor for creating an EnemyActor object.
     *
     * @param name        Name of the enemy actor.
     * @param displayChar Character to represent the enemy actor in the UI.
     * @param hitPoints   Enemy actor's starting number of hit points.
     */
    public EnemyActor(String name, char displayChar, int hitPoints, int runesDropped) {
        super(name, displayChar, hitPoints);
        this.runesDropped = runesDropped;
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(998, new AttackBehaviour());
        this.addCapability(EntityTypes.ENEMY);
    }

    public int getRunesDropped(){
        return this.runesDropped;
    }

    public void addDroppableItem(Item item, double chance){
        this.itemsToBeDropped.add(item.getClass());
        this.itemsToBeDroppedChance.add(chance);
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        dropItems(this, map);
        dropRune(this, map);
        return super.unconscious(actor, map);
    }

    public void dropRune(Actor actor, GameMap map){
        Location currentLocation = map.locationOf(actor);
        if (runesDropped > 0){
            Runes runes = new Runes(runesDropped);
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
        itemDrop.dropItems(actor, map, this.itemsToBeDropped, this.itemsToBeDroppedChance);
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
        for (Behaviour behaviour : behaviours.values()) {
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
// TODO: Every actor would drop runes, why still need enemydropactor class?

//    @Override
//    public String unconscious(Actor actor, GameMap map) {
//        dropRunes(actor, map);
//        return super.unconscious(actor, map);
//    }

//    public void dropRunes(Actor actor, GameMap map){
//        Location currentLocation = map.locationOf(actor);
//
//        map.at(currentLocation.x(), currentLocation.y()).addItem(new Runes(runesDropped));
//    }

    //    /**
//     * Handles what happens when the actor is unconscious due to the action of another actor.
//     *
//     * @param actor The perpetrator actor.
//     * @param map   The map where the actor fell unconscious.
//     * @return A string describing what happened when the actor is unconscious.
//     */
//    public String unconscious(Actor actor, GameMap map) {
//        map.removeActor(this);
//        return this + " met their demise at the hands of " + actor;
//    }

//    /**
//     * Drops items when the actor becomes unconscious.
//     *
//     * @param actor                The actor that became unconscious.
//     * @param map                  The map where the actor fell unconscious.
//     * @param itemsToBeDropped     A list of items to be dropped.
//     * @param itemsToBeDroppedChance An array of chances to drop items, with indices matching itemsToBeDropped.
//     */
//    public void dropItems(Actor actor, GameMap map, ArrayList<Class<? extends Item>> itemsToBeDropped, int[] itemsToBeDroppedChance) {
//        Location currentLocation = map.locationOf(this);
//
//        for (int i = 0; i < itemsToBeDropped.size(); i++) {
//            if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
//                Class<? extends Item> item = itemsToBeDropped.get(i);
//                int chanceToSpawn = itemsToBeDroppedChance[i];
//                System.out.println(item + "|" + chanceToSpawn);
//                if (rand.nextInt(100) < chanceToSpawn) {
//                    try {
//                        map.at(currentLocation.x(), currentLocation.y()).addItem(item.getConstructor().newInstance());
//                    } catch (InstantiationException | NoSuchMethodException | IllegalAccessException |
//                             InvocationTargetException e) {
//                        // Handle exceptions if necessary
//                    }
//
//                }
//            }
//        }
//    }
}
