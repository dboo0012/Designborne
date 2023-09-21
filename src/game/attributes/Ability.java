package game.attributes;

/**
 * Use this enum to represent abilities.
 * Example #1: if the player is capable jumping over walls, you can attach Ability.WALL_JUMP to the player class
 */
public enum Ability {
    CAN_ENTER_FLOOR,
    TRAVEL,
    TRADE,
    TRADABLE, // Item with this ability can be traded
    CONSUME, //Can consume items like runes, healing vial, refreshing flask
}
