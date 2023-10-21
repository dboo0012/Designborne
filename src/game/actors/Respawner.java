package game.actors;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.positions.NumberRange;
import game.attributes.EntityTypes;
import game.attributes.GroundTypes;
import game.attributes.ItemTypes;
import game.attributes.Status;
import game.items.Runes;

import java.util.ArrayList;
import java.util.List;

public class Respawner {

    private Actor player;
    private List<GameMap> activeGameMaps;

    public Respawner(Actor player, List<GameMap> activeGameMaps){
        this.player = player;
        this.activeGameMaps = activeGameMaps;

    }

    public void respawn(Location deathLocation){
        //Respawn
        player.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, player.getAttributeMaximum(BaseActorAttributes.HEALTH));
        player.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE, player.getAttributeMaximum(BaseActorAttributes.STAMINA));
        activeGameMaps.get(0).at(29, 5).addActor(player); //add actor back to spawn point

        //Remove enemies except boss, remove runes, lock gates
        for (GameMap map: activeGameMaps){
            resetMap(map); //remove any actor that isn't a boss or player
        }



    }

    public void resetMap(GameMap map){
        NumberRange x_range = map.getXRange();
        NumberRange y_range = map.getYRange();

        //For each Location in the GameMap
        for (int x: x_range){
            for (int y: y_range){
                Location currentLocation = map.at(x, y);
                Ground currentGround = currentLocation.getGround();

                //Remove any actor that isn't a boss or player
                if (map.isAnActorAt(currentLocation)){
                    Actor actor = map.getActorAt(currentLocation);
                    if (!actor.hasCapability(EntityTypes.BOSS) && !actor.hasCapability(EntityTypes.PLAYABLE)){
                        map.removeActor(actor);
                    } else if (actor.hasCapability(EntityTypes.BOSS)){ //Reset boss health to maximum
                        actor.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.UPDATE, actor.getAttributeMaximum(BaseActorAttributes.HEALTH));
                    }
                }

                //Lock all gates
                if (currentGround.hasCapability(GroundTypes.GATE) && !currentGround.hasCapability(Status.LOCKED)){
                    currentGround.addCapability(Status.LOCKED);
                }

                //Remove all runes
                List<Item> currentLocationItems = currentLocation.getItems();
                List<Item> itemsToRemove = new ArrayList<>();
                for (Item item: currentLocationItems){
                    if (item.hasCapability(ItemTypes.RUNES)){
                        itemsToRemove.add(item);
                    }
                }

                for (Item item: itemsToRemove){
                    currentLocation.removeItem(item);
                }

            }
        }
    }
}
