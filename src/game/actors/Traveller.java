package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.MonologueAction;
import game.attributes.Ability;
import game.attributes.EntityTypes;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.weapons.BroadSword;
import game.weapons.GiantHammer;
import game.weapons.GreatKnife;

import java.util.ArrayList;

/**
 * A Traveller actor.
 *
 * @author Meekal
 */
public class Traveller extends Trader implements Monologue{
    private ArrayList monologueOptions;
    private Abxervyer abxervyer;
    /**
     * Constructor for a Traveller.
     */
    public Traveller(Abxervyer abxervyer){
        super("Traveller", 'ඞ', 1);
        this.abxervyer = abxervyer;

        // The items sold by the Traveller
        addItemToInventory(new HealingVial().setPrice(100));
        addItemToInventory(new RefreshingFlask().setPrice(75));
        addItemToInventory(new BroadSword().setPrice(250));
        addItemToInventory(new GreatKnife().setPrice(300));
    }

    @Override
    public void monologue() {
        monologueOptions = new ArrayList<String>();
        monologueOptions.add("Of course, I will never give you up, valuable customer!");
        monologueOptions.add("I promise I will never let you down with the quality of the items that I sell.");
        monologueOptions.add("You can always find me here. I'm never gonna run around and desert you, dear customer!");
        monologueOptions.add("I'm never gonna make you cry with unfair prices.");
        monologueOptions.add("Trust is essential in this business. I promise I’m never gonna say goodbye to a valuable customer like you.");
        monologueOptions.add("Don't worry, I’m never gonna tell a lie and hurt you.");
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);

        monologue();

        boolean bossAlive = abxervyer.isConscious();

        String monologue1 = "You know the rules of this world, and so do I. Each area is ruled by a lord. Defeat the lord of this area, Abxervyer, and you may proceed to the next area.";
        if(bossAlive){ // Boss not defeated
            monologueOptions.add(monologue1);
        } else{ // Boss defeated
            monologueOptions.remove(monologue1);
        }

        // Has Giant Hammer
        boolean hasGiantHammer = false;
        for(Item item: otherActor.getItemInventory()){
            if(item instanceof GiantHammer){
                hasGiantHammer = true;
                break;
            }
        }
        if(hasGiantHammer){ // Actor has old key in inventory
            monologueOptions.add("Ooh, that’s a fascinating weapon you got there. I will pay a good price for it. You wouldn't get this price from any other guy.");
        }

        if (!bossAlive && hasGiantHammer){
            monologueOptions.add("Congratulations on defeating the lord of this area. I noticed you still hold on to that hammer. Why don’t you sell it to me? We've known each other for so long. I can tell you probably don’t need that weapon any longer.");
        }

        if (otherActor.hasCapability(EntityTypes.PLAYABLE)){
            actions.add(new MonologueAction(monologueOptions, abxervyer));
        }

        return actions;
    }
}
