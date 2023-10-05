package game.weather;

/**
 * A class that controls the game's weather conditions.
 *
 * @author Jerry
 */
public class WeatherControl {
    private static Weather currentWeather;
    private static int turnsSinceLastChange;
    private static final int TURNS_PER_WEATHER_CHANGE = 3; // Change weather every 3 turns

    /**
     * Initializes the WeatherControl with an initial sunny weather condition.
     */
    public WeatherControl() {
        setCurrentWeather(Weather.SUNNY); // Initial weather condition
        turnsSinceLastChange = 0;
    }

    /**
     * Gets the current weather condition.
     *
     * @return The current weather condition (either SUNNY or RAINY).
     */
    public static Weather getCurrentWeather() {
        return currentWeather;
    }

    /**
     * Sets the current weather condition.
     *
     * @param weather The weather condition to set.
     */
    public void setCurrentWeather(Weather weather) {
    	currentWeather = weather;
    }

    /**
     * Updates the game's weather condition based on the number of turns.
     */
    public void updateWeather() {
        turnsSinceLastChange++;
        if (turnsSinceLastChange >= TURNS_PER_WEATHER_CHANGE) {
            // Change the weather
            toggleWeather();
            turnsSinceLastChange = 0;
            }
        }

    /**
     * Toggles between SUNNY and RAINY weather conditions.
     */
    private void toggleWeather() {
        // Toggle between SUNNY and RAINY
        currentWeather = (currentWeather == Weather.SUNNY) ? Weather.RAINY : Weather.SUNNY;
    }
}
