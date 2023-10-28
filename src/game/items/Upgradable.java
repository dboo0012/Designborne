package game.items;

/**
 * Interface that allows an item to be upgraded.
 *
 * @author Daryl
 */
public interface Upgradable {
    /**
     * Upgrades the item.
     * @return the upgrade message
     */
    String upgrade();

    /**
     * Calculates and defines the upgrade price.
     * @return the upgrade price
     */
    int upgradePrice();

    /**
     * Boolean condition to define if the item can only be upgraded once.
     * @return {@code true} if the item can only be upgraded once, {@code false} otherwise.
     */
    boolean singleUpgrade();
}
