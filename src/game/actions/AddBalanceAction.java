package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Runes;

/**
 * An action that adds the value of Runes to the actor's balance and removes the Runes from the actor's inventory.
 * Used to simulate adding currency to the actor's wallet.
 *
 * @author Meekal
 */
public class AddBalanceAction extends Action {
    private Runes runes;
    private int value;
    private String currencyName;

    /**
     * Constructor for AddBalanceAction.
     *
     * @param runes The Runes item to be added to the actor's balance.
     */
    public AddBalanceAction(Runes runes) {
        this.runes = runes;
        this.value = runes.getValue();
        this.currencyName = runes.toString();
    }

    /**
     * Performs the action of adding the value of Runes to the actor's balance and removing the Runes from the actor's inventory.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return A description of what happened as a result of the action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addBalance(value);
        actor.removeItemFromInventory(runes);
        return String.format("%s has added %d %s into wallet.", actor, value, currencyName);
    }

    /**
     * Provides a description of this action for display in the game menu.
     *
     * @param actor The actor performing the action.
     * @return The menu description of the action.
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("Add %d %s into wallet.", value, currencyName);
    }
}
