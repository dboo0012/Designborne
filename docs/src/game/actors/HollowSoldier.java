package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.grounds.Void;
import game.actions.AttackAction;
import game.actions.VoidDeathAction;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.WanderBehaviour;
import game.items.HealingVial;
import game.items.RefreshingFlask;

import java.util.HashMap;
import java.util.Map;

/**
 * A Hollow Soldier actor that has the ability to be spawned.
 */
public class HollowSoldier extends Actor implements ActorSpawn {
    private Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     * Constructor.
     */
    public HollowSoldier() {
        super("Hollow Soldier", '&', 200);
        this.behaviours.put(999, new WanderBehaviour());
        this.behaviours.put(1, new AttackBehaviour());
    }

    /**
     * At each turn, checks if the actor is standing on a Void ground. If so, the actor dies.
     * Otherwise, the actor will perform a valid action according to its behaviour.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return Action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        while (map.contains(this)){
            if (map.locationOf(this).getGround() instanceof Void) { // Step on Void
                return new VoidDeathAction();
            } else {
                for (Behaviour behaviour : behaviours.values()) {
                    Action action = behaviour.getAction(this, map);
                    if (action != null){return action;}
                }
            }
        }
        return new DoNothingAction();
    }

    /**
     * When the actor is knocked unconscious, there is a 30% chance of dropping a Refreshing Flask or a 20% chance of dropping a Healing Vial.
     * @param actor the perpetrator
     * @param map where the actor fell unconscious
     * @return String that says the actor is dead
     */
    @Override
    public String unconscious(Actor actor, GameMap map) {
        // Chances of dropping Refreshing Flask (30%)
        if (Math.random() < 0.3){
            map.at(map.locationOf(this).x(), map.locationOf(this).y())
                    .addItem(new RefreshingFlask("Refreshing Flask", 'u', true));
        }
        // Chances of dropping Heal Vial (20%)
        if (Math.random() < 0.20){
            map.at(map.locationOf(this).x(), map.locationOf(this).y())
                    .addItem(new HealingVial("Healing Vial", 'a', true));
        }
        map.removeActor(this);
        return this + " met their demise in the hand of " + actor;
    }

    /**
     * The hollow soldier can be attacked by any player in the vicinity.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new AttackAction(this, direction));
        return actions;
    }

    /**
     * The intrinsic weapon of the hollow soldier.
     * @return IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50, "smacks", 50);
    }

    /**
     * Spawn a Hollow Soldier with a 10% chance.
     * @return HollowSoldier or null
     */
    @Override
    public Actor spawn() {
        if (Math.random() < 0.1){
            return new HollowSoldier();
        }
        return null;
    }
}

