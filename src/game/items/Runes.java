package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.addBalanceAction;
import game.attributes.Ability;

public class Runes extends Item {

    private int value;
    public Runes(int value){
        super("Runes", '$', true);
        this.value = value;
    }

    public void setValue(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }

    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = super.allowableActions(owner);
            if (owner.hasCapability(Ability.CONSUME)) {
                System.out.println("Owner" + owner + "| Value: " + getValue());
                actions.add(new addBalanceAction(this));
            }
        return actions;
    }
}
