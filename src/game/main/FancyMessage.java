package game.main;

/**
 * Fancy messages used to print the game title
 * Font obtained from: <a href="https://patorjk.com/software/taag/#p=display&f=Georgia11&t=">link</a>
 * Font: Georgia11
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 */
public class FancyMessage {

    /**
     * The title of the game
     */
    public static String TITLE =
            "`7MM\"\"\"Yb. `7MM\"\"\"YMM   .M\"\"\"bgd `7MMF' .g8\"\"\"bgd `7MN.   `7MF'`7MM\"\"\"Yp,   .g8\"\"8q. `7MM\"\"\"Mq.  `7MN.   `7MF'`7MM\"\"\"YMM  \n" +
            "  MM    `Yb. MM    `7  ,MI    \"Y   MM .dP'     `M   MMN.    M    MM    Yb .dP'    `YM. MM   `MM.   MMN.    M    MM    `7  \n" +
            "  MM     `Mb MM   d    `MMb.       MM dM'       `   M YMb   M    MM    dP dM'      `MM MM   ,M9    M YMb   M    MM   d    \n" +
            "  MM      MM MMmmMM      `YMMNq.   MM MM            M  `MN. M    MM\"\"\"bg. MM        MM MMmmdM9     M  `MN. M    MMmmMM    \n" +
            "  MM     ,MP MM   Y  , .     `MM   MM MM.    `7MMF' M   `MM.M    MM    `Y MM.      ,MP MM  YM.     M   `MM.M    MM   Y  , \n" +
            "  MM    ,dP' MM     ,M Mb     dM   MM `Mb.     MM   M     YMM    MM    ,9 `Mb.    ,dP' MM   `Mb.   M     YMM    MM     ,M \n" +
            ".JMMmmmdP' .JMMmmmmMMM P\"Ybmmd\"  .JMML. `\"bmmmdPY .JML.    YM  .JMMmmmd9    `\"bmmd\"' .JMML. .JMM..JML.    YM  .JMMmmmmMMM \n" +
            "\n";

    /**
     * The YOU DIED Message
     */
    public static String YOU_DIED =
            "`YMM'   `MM' .g8\"\"8q. `7MMF'   `7MF'    `7MM\"\"\"Yb. `7MMF'`7MM\"\"\"YMM  `7MM\"\"\"Yb.   \n" +
                    "  VMA   ,V .dP'    `YM. MM       M        MM    `Yb. MM    MM    `7    MM    `Yb. \n" +
                    "   VMA ,V  dM'      `MM MM       M        MM     `Mb MM    MM   d      MM     `Mb \n" +
                    "    VMMP   MM        MM MM       M        MM      MM MM    MMmmMM      MM      MM \n" +
                    "     MM    MM.      ,MP MM       M        MM     ,MP MM    MM   Y  ,   MM     ,MP \n" +
                    "     MM    `Mb.    ,dP' YM.     ,M        MM    ,dP' MM    MM     ,M   MM    ,dP' \n" +
                    "   .JMML.    `\"bmmd\"'    `bmmmmd\"'      .JMMmmmdP' .JMML..JMMmmmmMMM .JMMmmmdP'   \n";

    /**
     * Message shown when ABEXERVYER SLAIN
     */
    public static String ABEXERVYER_SLAIN = "\n" +
            "░█████╗░██████╗░███████╗██╗░░██╗███████╗██████╗░██╗░░░██╗███████╗██████╗░  ░██████╗██╗░░░░░░█████╗░██╗███╗░░██╗\n" +
            "██╔══██╗██╔══██╗██╔════╝╚██╗██╔╝██╔════╝██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ██╔════╝██║░░░░░██╔══██╗██║████╗░██║\n" +
            "███████║██████╦╝█████╗░░░╚███╔╝░█████╗░░██████╔╝░╚████╔╝░█████╗░░██████╔╝  ╚█████╗░██║░░░░░███████║██║██╔██╗██║\n" +
            "██╔══██║██╔══██╗██╔══╝░░░██╔██╗░██╔══╝░░██╔══██╗░░╚██╔╝░░██╔══╝░░██╔══██╗  ░╚═══██╗██║░░░░░██╔══██║██║██║╚████║\n" +
            "██║░░██║██████╦╝███████╗██╔╝╚██╗███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ██████╔╝███████╗██║░░██║██║██║░╚███║\n" +
            "╚═╝░░╚═╝╚═════╝░╚══════╝╚═╝░░╚═╝╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚═════╝░╚══════╝╚═╝░░╚═╝╚═╝╚═╝░░╚══╝！";
    /**
     * Message shown when Player is respawned
     */
    public static String RESPAWN = "\n" +
            "\n" +
            "██████╗░██╗░░░░░░█████╗░██╗░░░██╗███████╗██████╗░  ██████╗░███████╗░██████╗██████╗░░█████╗░░██╗░░░░░░░██╗███╗░░██╗\n" +
            "██╔══██╗██║░░░░░██╔══██╗╚██╗░██╔╝██╔════╝██╔══██╗  ██╔══██╗██╔════╝██╔════╝██╔══██╗██╔══██╗░██║░░██╗░░██║████╗░██║\n" +
            "██████╔╝██║░░░░░███████║░╚████╔╝░█████╗░░██████╔╝  ██████╔╝█████╗░░╚█████╗░██████╔╝███████║░╚██╗████╗██╔╝██╔██╗██║\n" +
            "██╔═══╝░██║░░░░░██╔══██║░░╚██╔╝░░██╔══╝░░██╔══██╗  ██╔══██╗██╔══╝░░░╚═══██╗██╔═══╝░██╔══██║░░████╔═████║░██║╚████║\n" +
            "██║░░░░░███████╗██║░░██║░░░██║░░░███████╗██║░░██║  ██║░░██║███████╗██████╔╝██║░░░░░██║░░██║░░╚██╔╝░╚██╔╝░██║░╚███║\n" +
            "╚═╝░░░░░╚══════╝╚═╝░░╚═╝░░░╚═╝░░░╚══════╝╚═╝░░╚═╝  ╚═╝░░╚═╝╚══════╝╚═════╝░╚═╝░░░░░╚═╝░░╚═╝░░░╚═╝░░░╚═╝░░╚═╝░░╚══╝";


}
