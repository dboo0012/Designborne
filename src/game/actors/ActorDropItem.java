package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

import java.util.ArrayList;

public interface ActorDropItem {
    void dropItems(Actor actor, GameMap map, ArrayList<Class<? extends Item>> itemsToBeDropped, int[] itemsToBeDroppedChance);
}
