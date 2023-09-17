package game.items;

import edu.monash.fit2099.engine.items.Item;

public interface Tradeable{

    int getPrice();

    Tradeable setPrice(int price);

    Tradeable spawn();
}
