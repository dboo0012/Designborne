package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Runes;

public class addBalanceAction extends Action {
    private Runes runes;
    private int value;
    private String currencyName;
    public addBalanceAction(Runes runes){
        this.runes = runes;
        this.value = runes.getValue();
        this.currencyName = runes.toString();

        System.out.println("Value: " + runes.getValue());
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.addBalance(value);
        actor.removeItemFromInventory(runes);
        return String.format("%s has added %d %s into wallet.", actor, value, currencyName);
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("Add %d %s into wallet.", value, currencyName);
    }
}
