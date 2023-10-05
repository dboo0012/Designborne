package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;
import game.items.RefreshingFlask;

/**
 * A Hollow Soldier actor that has the ability to be spawned.
 *
 * @author Daryl
 */
public class HollowSoldier extends EnemyActor implements ActorSpawn {

    public HollowSoldier() {
        super("Hollow Soldier", '&', 200, 100);

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

