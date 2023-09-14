package game.utilities;

import edu.monash.fit2099.engine.displays.Display;

public class FancyMessageDisplay {
    public static void createString(String message){
        for (String line : message.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
    }
}
