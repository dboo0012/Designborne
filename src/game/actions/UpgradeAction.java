package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Upgradable;

public class UpgradeAction extends Action {
    private Upgradable upgradableItem;
    private int upgradePrice;
    public UpgradeAction(Upgradable upgradableItem, int upgradePrice) {
        this.upgradableItem = upgradableItem;
        this.upgradePrice = upgradePrice;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if(actor.getBalance() >= upgradePrice){
            actor.deductBalance(upgradePrice);
            upgradableItem.upgrade();
        }
        return String.format("%s upgraded %s for $%d", actor, upgradableItem, upgradePrice);
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("Upgrade %s for $%d", upgradableItem, upgradePrice);
    }
}
