package game.actors;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actions.AttackAction;
import game.actors.behaviours.FollowBehaviour;
import game.attributes.EntityTypes;
import game.attributes.Status;


/**
 * A Forest Keeper  actor that has the ability to be spawned.
 */
public class ForestKeeper extends EnemyDropActor implements ActorSpawn {

    public ForestKeeper() {
        super("Forest Keeper", '8', 125);

    }


    /**
     * Intrinsic weapon for WanderingUndead
     * @return a freshly-instantiated IntrinsicWeapon
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(25, "touches", 75);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        if (otherActor.hasCapability(EntityTypes.PLAYABLE) && !this.behaviours.containsKey(997)){
            this.behaviours.put(997, new FollowBehaviour(otherActor));
        }
        return super.allowableActions(otherActor, direction, map);


    }

    /**
     * Spawn a Forest Keepet with a 15% chance.
     * @return ForestKeeper or null
     */
    @Override
    public Actor spawn() {
        if (Math.random() < 0.15){
            return new ForestKeeper();
        }
        return null;
    }
}
