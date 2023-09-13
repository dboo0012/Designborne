package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import game.actions.AttackAction;
import game.actions.DeathAction;
import game.attributes.Status;
import game.grounds.Void;

import java.util.ArrayList;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Daryl Boon (32836899)
 *
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     * @param stamina     Player's starting stamina
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina));
    }

    /**
     * Every turn, the following is carried out.
     * <p>
     *     1. Check if the player is standing on a Void tile. If so, kill the player.
     *     2. Check if the player has a multi-turn Action. If so, execute the next Action.
     *     3. Recover stamina if the player is not at maximum stamina.
     *     4. Display the player's inventory.
     *     5. Display the console menu that includes the player's stats.
     * </p>
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        // Step on void
        if (map.locationOf(this).getGround() instanceof Void) {
            return new DeathAction(this, map);
        }

        // Slain by enemy
        if (!this.isConscious()){
            return new DeathAction(this, map);
        }

        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // Recover stamina
        if (this.getAttribute(BaseActorAttributes.STAMINA) < this.getAttributeMaximum(BaseActorAttributes.STAMINA)) {
            this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, (int) (this.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.01));
        }

        // Display inventory
        if (this.getItemInventory() != null) {
            ArrayList<Item> itemList = new ArrayList<>(this.getItemInventory()); // Get all the items in players inventory
            new Display().println("The Abstracted One inventory:");
            for (Item item : itemList) {
                new Display().println(String.format(" • %s", item.toString()));
            }
        }

        // return/print the console menu
        new Display().println("The Abstracted One stats:");
        new Display().println(String.format(" • HP: %s/%s", this.getAttribute(BaseActorAttributes.HEALTH), this.getAttributeMaximum(BaseActorAttributes.HEALTH)));
        new Display().println(String.format(" • Stamina: %s/%s", this.getAttribute(BaseActorAttributes.STAMINA), this.getAttributeMaximum(BaseActorAttributes.STAMINA)));
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * When the player falls unconscious, DeathAction is called to print fancy death message.
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return String
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        new DeathAction(this, map);
        return this + " ceased to exist.";
    }

    /**
     * Returns a collection of the Actions that the otherActor (mobs) can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction)); // Enemy attacks player
        }
        return actions;
    }
}
