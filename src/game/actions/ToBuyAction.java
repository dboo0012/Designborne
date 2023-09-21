package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

public class ToBuyAction extends Action {

    private final Actor buyer;
    private final Actor seller;

    public ToBuyAction(Actor buyer, Actor seller){
        this.buyer = buyer;
        this.seller = seller;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s purchases from %s", buyer.toString(), seller.toString());
    }
}
