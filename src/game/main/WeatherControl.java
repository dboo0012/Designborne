package game.main;

public class WeatherControl {
    private Weather currentWeather;
    private int turnsSinceLastChange;
    private static final int TURNS_PER_WEATHER_CHANGE = 3; // Change weather every 3 turns

    public WeatherControl() {
        currentWeather = Weather.SUNNY; // Initial weather condition
        turnsSinceLastChange = 0;
    }

    public Weather getCurrentWeather() {
        return currentWeather;
    }

    public void updateWeather() {
        turnsSinceLastChange++;
        if (turnsSinceLastChange >= TURNS_PER_WEATHER_CHANGE) {
            // Change the weather
            toggleWeather();
            turnsSinceLastChange = 0;
        }
    }

    private void toggleWeather() {
        // Toggle between SUNNY and RAINY
        currentWeather = (currentWeather == Weather.SUNNY) ? Weather.RAINY : Weather.SUNNY;
    }
}
