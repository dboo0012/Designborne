package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.MonologueAction;
import game.actions.UpgradeAction;
import game.attributes.Ability;
import game.attributes.EntityTypes;
import game.items.Upgradable;
import game.utilities.HasItem;
import game.weapons.GreatKnife;

import java.sql.SQLOutput;
import java.util.ArrayList;

/**
 * Class representing the Blacksmith.
 *
 * @author Daryl
 */
public class Blacksmith extends Actor implements Monologue{
    private ArrayList<String> monologueOptions;
    private Abxervyer abxervyer;

    /**
     * Constructor.
     * @param abxervyer The Abxervyer boss
     */
    public Blacksmith(Abxervyer abxervyer) {
        super("Blacksmith", 'B', 1);
        this.abxervyer = abxervyer;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Returns a list of Upgrade Actions for all the items that can be upgraded in the actor's inventory.
     * @param otherActor The actor that is upgrading the items
     * @return The list of actions to upgrade for all the items in the otherActor's inventory
     */
    public ActionList getItems(Actor otherActor){
        ActionList actions = new ActionList(); //List of actions

        for (int i = 0; i < otherActor.getItemInventory().size(); i++){ // For each item to sell
            Item item = otherActor.getItemInventory().get(i); // Get Item

            if (item.hasCapability(Ability.UPGRADE)){ // IF that item is tradeable
                Upgradable upgradableItem = ((Upgradable) item); //[Code Smell (Downcasting)]
                int price = upgradableItem.upgradePrice();
                boolean singleUpgrade = upgradableItem.singleUpgrade();
                actions.add(new UpgradeAction(item, upgradableItem, price, singleUpgrade)); // Create upgrade action),
            }
        }

        return actions;
    }

    /**
     * Adds the monologues that does not require any conditions.
     */
    public void monologue(){
        monologueOptions.add("I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.");
        monologueOptions.add("It’s dangerous to go alone. Take my creation with you on your adventure!");
        monologueOptions.add("Ah, it’s you. Let’s get back to make your weapons stronger.");
    }

    /**
     * Initiates the list of monologues.
     */
    @Override
    public ArrayList<String> setMonologueList() {
        return this.monologueOptions = new ArrayList<String>();
    }

    /**
     * Checks and add/remove the monologues that require conditions.
     * @param otherActor the actor that the monologue is directed to
     */
    @Override
    public void monologueConditions(Actor otherActor){
        boolean bossAlive = abxervyer.isConscious();

        String monologue1 = "Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. " +
                "Use my creation to slay them and proceed further!";
        if (bossAlive) {
            monologueOptions.add(monologue1);
        }

        String monologue2 = "Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.";
        if (!bossAlive){
            monologueOptions.remove(monologue1);
            monologueOptions.add(monologue2);
        }

        boolean hasGreatKnife = new HasItem(otherActor, new GreatKnife()).actorHasItem();

        String monologue3 = "Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.";
        if(hasGreatKnife){ // Actor has GreatKnife in inventory
            monologueOptions.add(monologue3);
        } else{
            monologueOptions.remove(monologue3);
        }
    }

    /**
     * Returns a list of actions that the other Actor can perform to this Actor.
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return a list of actions that the other Actor can perform to this Actor
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        setMonologueList();
        monologue();
        monologueConditions(otherActor);

        if (otherActor.hasCapability(EntityTypes.PLAYABLE)){ // Only player can upgrade items
            actions.add(getItems(otherActor)); // Add the items that the player can upgrade
            actions.add(new MonologueAction(this, monologueOptions));
        }

        return actions;
    }
}
