package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.grounds.Void;
import game.actions.AttackAction;
import game.actions.VoidDeathAction;
import game.actors.behaviours.AttackBehaviour;
import game.actors.behaviours.WanderBehaviour;
import game.items.HealingVial;
import game.items.ItemDrop;
import game.items.OldKey;
import game.items.RefreshingFlask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A Wandering Undead actor that has the ability to be spawned.
 */
public class WanderingUndead extends EnemyDropActor implements ActorSpawn{

    /**
     * Constructor.
     */
    public WanderingUndead() {
        super("Wandering Undead", 't', 100);

        addDroppableItem(new OldKey(), 0.25); //0.25
        addDroppableItem(new HealingVial(), 0.2); //0.2
    }


    /**
     * The intrinsic weapon of the wandering undead.
     * @return IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "smacks", 50);
    }

    /**
     * Spawn a wandering undead with a 25% chance.
     * @return WanderingUndead or null
     */
    @Override
    public Actor spawn() {
        if (Math.random() < 0.25){
            return new WanderingUndead();
        }
        return null;
    }
}
