package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.attributes.Ability;
import game.items.Upgradable;

public class UpgradeAction extends Action {
    private Item item;
    private boolean singleUpgrade;
    private Upgradable upgradableItem;
    private int upgradePrice;
    public UpgradeAction(Item item, Upgradable upgradableItem, int upgradePrice, boolean singleUpgrade) {
        this.item = item;
        this.upgradableItem = upgradableItem;
        this.upgradePrice = upgradePrice;
        this.singleUpgrade = singleUpgrade;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Upgrade the weapon if the actor has enough balance
        if(actor.getBalance() >= upgradePrice) {
            actor.deductBalance(upgradePrice);
            String upgrade = upgradableItem.upgrade();
            if (singleUpgrade) {
                item.removeCapability(Ability.UPGRADE);
            }
            return String.format("%s\n%s upgraded %s for $%d",upgrade, actor, upgradableItem, upgradePrice);
        }else{
            return String.format("%s does not have enough money to upgrade %s", actor, upgradableItem);
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("Upgrade %s for $%d", upgradableItem, upgradePrice);
    }
}
