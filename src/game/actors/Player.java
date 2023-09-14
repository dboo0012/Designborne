package game.actors;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.attributes.Ability;
import game.main.FancyMessage;
import game.attributes.EntityTypes;
import game.attributes.Status;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor {
    private int maxStamina;
    private int currentStamina;

    /**
     * Constructor for the Player class.
     *
     * @param name         The name of the player.
     * @param displayChar  The character used to display the player on the map.
     * @param hitPoints    The initial hit points of the player.
     * @param stamina      The initial stamina of the player.
     */
    public Player(String name, char displayChar, int hitPoints, int stamina) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(stamina)); // New stamina attribute
        this.addCapability(EntityTypes.PLAYABLE);
        this.addCapability(Ability.CAN_ENTER_FLOOR);
        this.addCapability(Ability.TRAVEL);
    }

    /**
     * Method to recover player's stamina.
     * It increases the player's stamina by 1% of the maximum stamina if it's below the maximum.
     */
    public void recoverStamina() {
        maxStamina = getAttributeMaximum(BaseActorAttributes.STAMINA);
        currentStamina = getAttribute(BaseActorAttributes.STAMINA);
        int recoverAmount = maxStamina / 100; // 1% of max stamina
        if (currentStamina < maxStamina){
            this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE, recoverAmount);
        }
        System.out.println(currentStamina);
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

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);
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
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return super.unconscious(map);
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
        for (String line : FancyMessage.YOU_DIED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        return super.unconscious(actor, map);
    }
}
