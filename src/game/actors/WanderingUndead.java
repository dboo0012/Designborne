package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.grounds.Void;
import game.actions.AttackAction;
import game.actions.VoidDeathAction;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.WanderBehaviour;
import game.items.HealingVial;
import game.items.ItemDrop;
import game.items.OldKey;
import game.items.RefreshingFlask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Wandering Undead actor that has the ability to be spawned.
 */
public class WanderingUndead extends Actor implements ActorSpawn {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    private final ArrayList<Class<? extends Item>> itemsToBeDropped = new ArrayList<>(List.of(OldKey.class, HealingVial.class));
    //Array of chances to drop, with indices matching itemsToBeDropped
    private final double[] itemsToBeDroppedChance = {0.25, 0.2};

    private ItemDrop itemDrop = new ItemDrop();

    /**
     * Constructor.
     */
    public WanderingUndead() {
        super("Wandering Undead", 't', 100);
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(1, new AttackBehaviour());
    }

    /**
     * At each turn, checks if the actor is standing on a Void ground. If so, the actor dies.
     * Otherwise, the actor will perform a valid action according to its behaviour.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        while (map.contains(this)){
            if (map.locationOf(this).getGround() instanceof Void) { // Step on Void
                return new VoidDeathAction();
            } else {
            for (Behaviour behaviour : behaviours.values()) {
                Action action = behaviour.getAction(this, map);
                if (action != null){return action;}
            }
            }
        }
        return new DoNothingAction();
    }

    @Override
    public String unconscious(Actor actor, GameMap map) {
        dropItems(this, map);
        return super.unconscious(actor, map);
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
     * The wandering undead can be attacked by any player in the vicinity.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(this, direction));
        return actions;
    }

    /**
     * The intrinsic weapon of the wandering undead.
     * @return IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "smacks", 50);
    }

    /**
     * Spawn a wandering undead with a 25% chance.
     * @return WanderingUndead or null
     */
    @Override
    public Actor spawn() {
        if (Math.random() < 0.25){
            return new WanderingUndead();
        }
        return null;
    }
}
