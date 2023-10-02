package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Trader;
import game.attributes.EntityTypes;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weapons.BroadSword;
import game.weapons.GreatKnife;

public class Traveller extends Trader {
    public Traveller(){
        super("Traveller", 'ඞ', 1);

        addItemToInventory(new HealingVial().setPrice(100));
        addItemToInventory(new RefreshingFlask().setPrice(75));
        addItemToInventory(new BroadSword().setPrice(250));
        addItemToInventory(new GreatKnife().setPrice(300));
    }
}
