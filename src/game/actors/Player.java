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
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.attributes.*;
import game.grounds.Gate;
import game.items.Runes;
import game.main.FancyMessage;
import game.utilities.FancyMessageDisplay;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Daryl, Meekal, Jerry
 *
 */
public class Player extends Actor {
    private int maxStamina;
    private int currentStamina;
    private List<GameMap> activeGameMaps;

    /**
     * Constructor for the Player class.
     *
     * @param name         The name of the player.
     * @param displayChar  The character used to display the player on the map.
     * @param hitPoints    The initial hit points of the player.
     * @param stamina      The initial stamina of the player.
     */
    public Player(String name, char displayChar, int hitPoints, int stamina, List<GameMap> activeGameMaps) {
        super(name, displayChar, hitPoints);
        this.activeGameMaps = activeGameMaps;

        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina)); // New stamina attribute

        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addCapability(EntityTypes.PLAYABLE);
        this.addCapability(Ability.CAN_ENTER_FLOOR);
        this.addCapability(Ability.TRAVEL);
        this.addCapability(Ability.CONSUME);
        this.addCapability(Ability.TRADE);
    }

    /**
     * Method to recover player's stamina.
     * It increases the player's stamina by 1% of the maximum stamina if it's below the maximum.
     */
    public void recoverStamina() {
        maxStamina = getAttributeMaximum(BaseActorAttributes.STAMINA);
        currentStamina = getAttribute(BaseActorAttributes.STAMINA);
        int recoverAmount = (int) (maxStamina * 0.01); // 1% of max stamina
        if (currentStamina < maxStamina){
            this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, recoverAmount);
        }
//        System.out.println(currentStamina);
    }

    /**
     * Retrieve the intrinsic weapon of the player.
     *
     * @return An IntrinsicWeapon representing the player's default attack.
     */
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15, "punches", 80);
    }

    /**
     * Override of the playTurn method to handle player's turn.
     * This method first recovers the player's stamina, and then displays a menu of available actions for the player.
     *
     * @param actions    Collection of possible Actions for the player.
     * @param lastAction The Action the player took last turn.
     * @param map        The map containing the player.
     * @param display    The I/O object to which messages may be written.
     * @return The chosen Action to perform during this turn.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        recoverStamina();
        // Handle multi-turn Actions
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        /// Display inventory
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
        new Display().println(String.format(" • Wallet: $%d", this.getBalance()));
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
    }

    /**
     * Override of the unconscious method to handle player's unconscious state.
     * It displays a special message and then invokes the super method to handle the unconscious state.
     *
     * @param actor The actor representing the player.
     * @param map   The map containing the player.
     * @return The result of handling the player's unconscious state.
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        String output = "";
        Location deathLocation = map.locationOf(this);

        output += super.unconscious(map); //remove actor from the map he died at
        respawn(deathLocation);

        return output;
    }

    /**
     * Override of the unconscious method to handle the player's unconscious state.
     * This method displays a special "You Died" message line by line, simulating a dramatic effect,
     * and then invokes the super method to handle the unconscious state.
     *
     * @param map The GameMap in which the player is located.
     * @return The result of handling the player's unconscious state from the superclass.
     */
    @Override
    public String unconscious(GameMap map) {
//        FancyMessageDisplay.createString(FancyMessage.YOU_DIED);
        Location deathLocation = map.locationOf(this);

        super.unconscious(map); //remove actor from the map he died at
        respawn(deathLocation);

        FancyMessageDisplay.createString(FancyMessage.RESPAWN);

        return "Player was respawned at Abandoned Village";
    }

    public void respawn(Location deathLocation){
        //Respawn
        this.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, this.getAttributeMaximum(BaseActorAttributes.HEALTH));
        this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE, this.getAttributeMaximum(BaseActorAttributes.STAMINA));
        activeGameMaps.get(0).at(29, 5).addActor(this); //add actor back to spawn point

        //Remove enemies except boss
        for (GameMap map: activeGameMaps){
            resetMaps(map); //remove any actor that isn't a boss or player
        }

        // Runes & Balance
        int runesAmount = this.getBalance();
        deathLocation.addItem(new Runes(runesAmount)); //drop runes at death location
        this.deductBalance(runesAmount); //reset balance

    }

    public void resetMaps(GameMap map){
        NumberRange x_range = map.getXRange();
        NumberRange y_range = map.getYRange();

        //For each Location in the GameMap
        for (int x: x_range){
            for (int y: y_range){
                Location currentLocation = map.at(x, y);
                Ground currentGround = currentLocation.getGround();

                //Remove any actor that isn't a boss or player
                if (map.isAnActorAt(currentLocation)){
                    Actor actor = map.getActorAt(currentLocation);
                    if (!actor.hasCapability(EntityTypes.BOSS) && !actor.hasCapability(EntityTypes.PLAYABLE)){
                        map.removeActor(actor);
                    } else if (actor.hasCapability(EntityTypes.BOSS)){ //Reset boss health to maximum
                        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, actor.getAttributeMaximum(BaseActorAttributes.HEALTH));
                    }
                }


                //Lock all gates
                if (currentGround.hasCapability(GroundTypes.GATE) && !currentGround.hasCapability(Status.LOCKED)){
                    currentGround.addCapability(Status.LOCKED);
                }

                //Remove all runes
                List<Item> currentLocationItems = currentLocation.getItems();
                List<Item> itemsToRemove = new ArrayList<>();
                for (Item item: currentLocationItems){
                    if (item.hasCapability(ItemTypes.RUNES)){
                        itemsToRemove.add(item);
                    }
                }

                for (Item item: itemsToRemove){
                    currentLocation.removeItem(item);
                }



            }
        }
    }



}