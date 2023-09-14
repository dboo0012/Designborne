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
 * A Hollow Soldier actor that has the ability to be spawned.
 */
public class HollowSoldier extends EnemyActor implements ActorSpawn, ActorDropItem {

    //Pre-defined list of items to drop
    private final ArrayList<Class<? extends Item>> itemsToBeDropped = new ArrayList<>();
    //Array of chances to drop, with indices matching itemsToBeDropped
    private final ArrayList<Double> itemsToBeDroppedChance = new ArrayList<>();

    private ItemDrop itemDrop = new ItemDrop();

    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    public HollowSoldier() {
        super("Hollow Soldier", '&', 200);
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
        return new IntrinsicWeapon(50, "smacks", 50);
    }

    /**
     * Spawn a Hollow Soldier with a 10% chance.
     * @return HollowSoldier or null
     */
    @Override
    public Actor spawn() {
        if (Math.random() < 0.1){
            return new HollowSoldier();
        }
        return null;
    }
}

