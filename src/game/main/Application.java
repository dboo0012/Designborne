package game.main;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actions.MonologueAction;
import game.actors.*;
import game.actors.behaviours.AttackBehaviour;
import game.grounds.Gate;
import game.grounds.Graveyard;
import game.actions.FocusAction;
import game.grounds.*;
import game.grounds.Void;
import game.items.Bloodberry;
import game.items.OldKey;
import game.utilities.FancyMessageDisplay;
import game.weapons.BroadSword;
import game.weapons.GiantHammer;
import game.weapons.GreatKnife;
import game.weather.WeatherControl;

import java.util.ArrayList;
import java.util.List;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by: Daryl, Meekal, Jerry
 *
 */
public class Application {

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(), new Void());

        //Maps
        GameMap abandonedGroundMap = new GameMap(groundFactory, Maps.ABANDONED_VILLAGE);
        world.addGameMap(abandonedGroundMap);

        GameMap burialGroundMap = new GameMap(groundFactory, Maps.BURIAL_GROUND);
        world.addGameMap(burialGroundMap);

        GameMap ancientWoodsMap = new GameMap(groundFactory, Maps.ANCIENT_WOODS);
        world.addGameMap(ancientWoodsMap);

        GameMap bossMap = new GameMap(groundFactory, Maps.BOSS_MAP);
        world.addGameMap(bossMap);

        GameMap overgrownSanctuary = new GameMap(groundFactory, Maps.OVERGROWN_SANCTUARY);
        world.addGameMap(overgrownSanctuary);

        FancyMessageDisplay.createString(FancyMessage.TITLE);

        // Graveyard
        abandonedGroundMap.at(25, 10).setGround(new Graveyard(abandonedGroundMap, new WanderingUndead()));
        abandonedGroundMap.at(13, 3).setGround(new Graveyard(abandonedGroundMap, new WanderingUndead()));
        abandonedGroundMap.at(42, 5).setGround(new Graveyard(abandonedGroundMap, new WanderingUndead()));
        burialGroundMap.at(5, 10).setGround(new Graveyard(burialGroundMap, new HollowSoldier()));
        burialGroundMap.at(36, 2).setGround(new Graveyard(burialGroundMap, new HollowSoldier()));
        overgrownSanctuary.at(44,8).setGround(new Graveyard(overgrownSanctuary, new HollowSoldier()));
        overgrownSanctuary.at(45,8).setGround(new Graveyard(overgrownSanctuary, new HollowSoldier()));
        overgrownSanctuary.at(44,9).setGround(new Graveyard(overgrownSanctuary, new HollowSoldier()));


        // Empty Huts
        ancientWoodsMap.at(5, 2).setGround(new EmptyHuts(ancientWoodsMap, new ForestKeeper()));
        ancientWoodsMap.at(9, 2).setGround(new EmptyHuts(ancientWoodsMap, new ForestKeeper()));
        bossMap.at(30, 1).setGround(new EmptyHuts(bossMap, new ForestKeeper()));
        bossMap.at(32, 2).setGround(new EmptyHuts(bossMap, new ForestKeeper()));
        overgrownSanctuary.at(44,1).setGround(new EmptyHuts(overgrownSanctuary, new ForestKeeper()));
        overgrownSanctuary.at(45,1).setGround(new EmptyHuts(overgrownSanctuary, new ForestKeeper()));
        overgrownSanctuary.at(44,2).setGround(new EmptyHuts(overgrownSanctuary, new EldentreeGuardian()));
        overgrownSanctuary.at(45,2).setGround(new EmptyHuts(overgrownSanctuary, new EldentreeGuardian()));

        // Bushes
        ancientWoodsMap.at(3, 5).setGround(new Bush(ancientWoodsMap, new RedWolf()));
        ancientWoodsMap.at(3, 6).setGround(new Bush(ancientWoodsMap, new RedWolf()));
        ancientWoodsMap.at(3, 7).setGround(new Bush(ancientWoodsMap, new RedWolf()));
        ancientWoodsMap.at(3, 8).setGround(new Bush(ancientWoodsMap, new RedWolf()));
        bossMap.at(3, 10).setGround(new Bush(bossMap, new RedWolf()));
        bossMap.at(4, 10).setGround(new Bush(bossMap, new RedWolf()));
        bossMap.at(5, 10).setGround(new Bush(bossMap, new RedWolf()));
        overgrownSanctuary.at(33,5).setGround(new Bush(overgrownSanctuary, new RedWolf()));
        overgrownSanctuary.at(33,6).setGround(new Bush(overgrownSanctuary, new RedWolf()));
        overgrownSanctuary.at(32,5).setGround(new Bush(overgrownSanctuary, new LivingBranch()));
        overgrownSanctuary.at(32,6).setGround(new Bush(overgrownSanctuary, new LivingBranch()));


        // Player
        Player player = new Player("The Abstracted One", '@', 1500, 200); // [Revert] health/stamina
        world.addPlayer(player, abandonedGroundMap.at(29, 5));

        // Gate
        abandonedGroundMap.at(22, 3).setGround(new Gate(new Destination(burialGroundMap, "Burial Ground",22, 6))); // test: 12, 9, actual: 22, 3
        burialGroundMap.at(6, 2).setGround(new Gate(new Destination(abandonedGroundMap, "Abandoned Ground",29, 5))); // test: 20, 9,actual: 22, 6
        burialGroundMap.at(25, 12).setGround(new Gate(new Destination(ancientWoodsMap, "Ancient Woods", 20, 3))); // test: 28, 9, actual: 22, 9
        ancientWoodsMap.at(0, 8).setGround(new Gate(new Destination(burialGroundMap, "Burial Ground",22, 6))); // test: 40, 9, actual: 22, 9
        ancientWoodsMap.at(27, 6).setGround(new Gate(new Destination(bossMap, "Boss Map",0, 9))); // test: 40, 6, actual: 22, 6
        overgrownSanctuary.at(13, 12).setGround(new Gate(new Destination(bossMap, "Boss Map")));

        // Broadsword
        BroadSword broadSword = new BroadSword();
        abandonedGroundMap.at(29, 6).addItem(broadSword);

        // Bloodberry
        ancientWoodsMap.at(27, 5).addItem(new Bloodberry());
        ancientWoodsMap.at(30, 9).addItem(new Bloodberry());

        burialGroundMap.at(28, 5).addItem(new Bloodberry());

        //Boss
        Abxervyer abxervyer = new Abxervyer(ancientWoodsMap, overgrownSanctuary, new WeatherControl());
        bossMap.at(15, 1).addActor(abxervyer);

        //Traveller
        ancientWoodsMap.at(45, 8).addActor(new Traveller(abxervyer)); // 45, 8, ancient woods

        //Blacksmith
        abandonedGroundMap.at(28, 6).addActor(new Blacksmith(abxervyer));

        // TESTING CODE
//        abandonedGroundMap.at(29, 6).addActor(new WanderingUndead());
//        abandonedGroundMap.at(29, 6).addItem(new OldKey());
//        bossMap.at(15,3).addItem(new OldKey());
//        abandonedGroundMap.at(29, 6).addItem(new Bloodberry("Bloodberry", '*', true));
//        world.addPlayer(player, ancientWoodsMap.at(45, 7));
//        world.addPlayer(player, bossMap.at(15, 2));

//        //Testing Puddle
//        player.modifyAttribute(BaseActorAttributes.HEALTH, ActorAttributeOperations.DECREASE, 50);
//        player.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE, 50);
//        abandonedGroundMap.at(29, 5).setGround(new Puddle());

        // Extra features
//        HealingVial healingVial = new HealingVial("Healing Vial", 'a', true);
//        gameMap.at(32, 0).addItem(healingVial);
//        RefreshingFlask refreshingFlash = new RefreshingFlask("Refreshing Flask", 'u', true);
//        gameMap.at(56, 5).addItem(refreshingFlash);
//        OldKey oldKey = new OldKey("Old Key", '-', true);
//        gameMap.at(44, 11).addItem(oldKey);
//        OldKey oldKey1 = new OldKey();
//        abandonedGroundMap.at(29, 6).addItem(oldKey1);

        //Testing Abxervyer
//        abandonedGroundMap.at(29, 6).addActor(new Abxervyer(ancientWoodsMap, new WeatherControl()));

//        //Testing Traveller
//        abandonedGroundMap.at(29, 6).addActor(new Traveller());
//        player.addBalance(10000);

        //Testing new Gates
//        player.addItemToInventory(new OldKey());
//        abandonedGroundMap.at(29,6).addActor(new Abxervyer(ancientWoodsMap, overgrownSanctuary, new WeatherControl()));
//        abandonedGroundMap.at(29, 6).setGround(new Gate(List.of(new Destination(burialGroundMap, "Burial Ground"), new Destination(ancientWoodsMap, "Ancient Woods"))));
//        player.addBalance(1000);


        world.run();
    }
}
