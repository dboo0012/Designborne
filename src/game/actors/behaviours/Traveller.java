package game.actors.behaviours;

import game.actors.Trader;
import game.attributes.EntityTypes;

public class Traveller extends Trader {
    public Traveller(){
        super("Traveller", 'ඞ', 1);
        this.addCapability(EntityTypes.TRADER);
    }
}
