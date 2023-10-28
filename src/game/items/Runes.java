package game.items;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.AddBalanceAction;
import game.attributes.Ability;
import game.attributes.ItemTypes;

/**
 * A Runes item that can be used to increase the player's balance.
 *
 * @author Daryl, Meekal
 */
public class Runes extends Item {
    private int value;

    /**
     * Constructor for Runes.
     * @param value the value of the runes
     */
    public Runes(int value){
        super("Runes", '$', true);
        this.value = value;
        addCapability(ItemTypes.RUNES);
    }

    /**
     * Sets the value of the runes.
     *
     * @param value the value of the runes
     */
    public void setValue(int value){
        this.value = value;
    }

    /**
     * Gets the value of the runes.
     *
     * @return the value of the runes
     */
    public int getValue(){
        return value;
    }

    /**
     * A list of actions that can be performed on the item.
     * @param owner the actor that owns the item
     * @return a list of Actions
     */
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = super.allowableActions(owner);
            if (owner.hasCapability(Ability.CONSUME)) {
                actions.add(new AddBalanceAction(this));
            }
        return actions;
    }
}
