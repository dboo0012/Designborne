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
import game.actors.behaviours.FollowBehaviour;
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
 * A Forest Keeper  actor that has the ability to be spawned.
 */
public class ForestKeeper extends EnemyActor implements ActorSpawn, ActorDropItem{
    //Pre-defined list of items to drop
    private final ArrayList<Class<? extends Item>> itemsToBeDropped = new ArrayList<>();
    //Array of chances to drop, with indices matching itemsToBeDropped
    private final ArrayList<Double> itemsToBeDroppedChance = new ArrayList<>();

    private ItemDrop itemDrop = new ItemDrop();

    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    public ForestKeeper() {
        super("Hollow Soldier", '8', 125);
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(998, new AttackBehaviour());

        addDroppableItem(new RefreshingFlask(), 0.3);
        addDroppableItem(new HealingVial(), 0.2);

    }

    public void addDroppableItem(Item item, double chance){
        this.itemsToBeDropped.add(item.getClass());
        this.itemsToBeDroppedChance.add(chance);
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
     * Intrinsic weapon for WanderingUndead
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(25, "touches", 75);
    }

    /**
     * Spawn a Hollow Soldier with a 10% chance.
     * @return HollowSoldier or null
     */
    @Override
    public Actor spawn() {
        if (Math.random() < 0.15){
            return new ForestKeeper();
        }
        return null;
    }
}
