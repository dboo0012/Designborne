package game.attributes;

/**
 * An enumeration representing the trade characteristics for items in the game.
 * These characteristics define how items can be traded or interacted with in trade-related actions.
 */
public enum TradeCharacteristics {
    /**
     * Indicates that an item is non-scammable, meaning it cannot be used in scam actions.
     */
    NON_SCAMMABLE,

    /**
     * Indicates that an item can be used to steal runes during a trade action.
     */
    STEAL_RUNES,

    /**
     * Indicates that an item can be used to steal other items during a trade action.
     */
    STEAL_ITEMS
}
