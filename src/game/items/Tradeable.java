package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actors.ItemInventory;

public interface Tradeable {

    int getPrice();

    Item setPrice(int price);

    Item spawn();

    boolean isPriceAffected(Actor seller);

    boolean isScam(Actor seller);
    int affectedPrice(Actor seller);
}
