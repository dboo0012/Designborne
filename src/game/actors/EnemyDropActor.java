package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.ItemDrop;

import java.util.ArrayList;

public class EnemyDropActor extends EnemyActor implements ActorDropItem {
    //Pre-defined list of items to drop
    protected final ArrayList<Class<? extends Item>> itemsToBeDropped = new ArrayList<>();
    //Array of chances to drop, with indices matching itemsToBeDropped
    protected final ArrayList<Double> itemsToBeDroppedChance = new ArrayList<>();

    protected ItemDrop itemDrop = new ItemDrop();

    public EnemyDropActor(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
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
}
