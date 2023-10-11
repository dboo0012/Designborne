package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.UpgradeAction;
import game.attributes.Ability;
import game.attributes.EntityTypes;
import game.items.Upgradable;

public class Blacksmith extends Actor {
    public Blacksmith() {
        super("Blacksmith", 'B', 1);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    public ActionList getItems(Actor sellingActor){
        ActionList actions = new ActionList(); //List of actions

        for (int i = 0; i < sellingActor.getItemInventory().size(); i++){ // For each item to sell
            Item item = sellingActor.getItemInventory().get(i); // Get Item

            if (item.hasCapability(Ability.UPGRADE)){ // IF that item is tradeable
                Upgradable upgradableItem = ((Upgradable) item); //[Code Smell (Downcasting)]
                int price = upgradableItem.upgradePrice();
                boolean singleUpgrade = upgradableItem.singleUpgrade();
                actions.add(new UpgradeAction(item, upgradableItem, price, singleUpgrade)); // Create upgrade action),
            }
        }

        return actions;
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasCapability(EntityTypes.PLAYABLE)){ // Only player can upgrade items
            actions.add(getItems(otherActor)); // Add the items that the player can upgrade
        }

        return actions;
    }
}
