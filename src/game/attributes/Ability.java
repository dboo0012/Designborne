package game.attributes;

/**
 * Use this enum to represent abilities.
 * Example #1: if the player is capable jumping over walls, you can attach Ability.WALL_JUMP to the player class
 */
public enum Ability {
    /**
     * The ability to enter a floor
     */
    CAN_ENTER_FLOOR,
    /**
     * The ability to travel
     */
    TRAVEL,
    /**
     * The ability to Trade
     */
    TRADE,
    /**
     * Item with this ability can be traded
     */
    TRADABLE,
    /**
     * Can consume items like runes, healing vial, refreshing flask
     */
    CONSUME,
    /**
     * Can upgrade items
     */
    UPGRADE,
    /**
     * Has ability to walk on void
     */
    VOID_IMMUNITY
}
