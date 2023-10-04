package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.items.HealingVial;
import game.items.OldKey;

/**
 * A Wandering Undead actor that has the ability to be spawned.
 *
 * @author Daryl
 */
public class WanderingUndead extends EnemyActor implements ActorSpawn{
    /**
     * Constructor.
     */
    public WanderingUndead() {
        super("Wandering Undead", 't', 100, 50);

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
