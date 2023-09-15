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
 * A Hollow Soldier actor that has the ability to be spawned.
 */
public class HollowSoldier extends EnemyDropActor implements ActorSpawn {

    public HollowSoldier() {
        super("Hollow Soldier", '&', 200);

        addDroppableItem(new RefreshingFlask(), 0.3); //0.3
        addDroppableItem(new HealingVial(), 0.2); //0.2
    }

    /**
     * Intrinsic weapon for WanderingUndead
     * @return a freshly-instantiated IntrinsicWeapon
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

