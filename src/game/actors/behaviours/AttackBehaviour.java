package game.actors.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;
import game.actions.AttackAction;

import java.util.ArrayList;
import java.util.Random;

/**
 * Attacking behaviour of an NPC.
 *
 * @author Daryl
 */
public class AttackBehaviour implements Behaviour {
    private final Random random = new Random();

    /**
     * Check each exit for a Player, and attack if found.
     * If no Player is found, move to a random exit location if possible.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return action to be performed
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.getActor() instanceof Player) {
                actions.add(new AttackAction(exit.getDestination().getActor(), destination.toString()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }

    }
}
