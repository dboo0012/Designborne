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

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Class representing an enemy actor in the game.
 *
 * The EnemyActor class extends the Actor class and represents an enemy character in the game. It includes behaviors such as wandering and attacking.
 */
public class EnemyActor extends Actor implements Attackable{

    private final Random rand = new Random();

    private Map<Integer, Behaviour> behaviours = new TreeMap<>();

    // Pre-defined list of items to drop
//    private final ArrayList<Class<? extends Item>> itemsToBeDropped = new ArrayList<>();
    // List of chances to drop, with indices matching itemsToBeDropped
//    private final int[] itemsToBeDroppedChance = new int[0];

    /**
     * Constructor for creating an EnemyActor object.
     *
     * @param name        Name of the enemy actor.
     * @param displayChar Character to represent the enemy actor in the UI.
     * @param hitPoints   Enemy actor's starting number of hit points.
     */
    public EnemyActor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(998, new AttackBehaviour());
        this.addCapability(EntityTypes.ENEMY);
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

    public void addBehaviour(int priority, Behaviour behaviour) {
        this.behaviours.put(priority, behaviour);
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
