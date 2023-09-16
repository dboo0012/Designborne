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
 * A Red Wolf  actor that has the ability to be spawned.
 */
public class RedWolf extends EnemyDropActor implements ActorSpawn {

    public RedWolf() {
        super("Red Wolf", 'r', 25);
        addDroppableItem(new HealingVial(), 0.1);
    }

    /**
     * Intrinsic weapon for ForestKeeper
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15, "bites", 80);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(EntityTypes.PLAYABLE) && !this.behaviours.containsKey(997)){
            this.behaviours.put(997, new FollowBehaviour(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);


    }

    /**
     * Spawn a Red Wolf with a 30% chance.
     * @return RedWolf or null
     */
    @Override
    public Actor spawn() {
        if (Math.random() < 0.30){
            return new RedWolf();
        }
        return null;
    }
}
