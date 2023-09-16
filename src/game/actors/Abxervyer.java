package game.actors;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.behaviours.FollowBehaviour;
import game.attributes.EntityTypes;
import game.attributes.Status;
import game.items.HealingVial;


/**
 * A Abxervyer actor that has the ability to be spawned.
 */
public class Abxervyer extends EnemyActor  {

    public Abxervyer() {
        super("Abxervyer", 'Y', 2000, 5000);
        addDroppableItem(new HealingVial(), 0.2); //0.2
        addCapability(EntityTypes.BOSS);
    }

    /**
     * Intrinsic weapon for Abxervyer
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(80, "punches", 25);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(EntityTypes.PLAYABLE) && !this.behaviours.containsKey(997)){
            this.behaviours.put(997, new FollowBehaviour(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);


    }

}
