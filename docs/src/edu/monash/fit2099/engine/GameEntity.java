package edu.monash.fit2099.engine;

import edu.monash.fit2099.engine.capabilities.CapabilitySet;

import java.util.List;

/**
 * Base class for all objects that can exist in the game.
 * It allows entities to have capabilities.
 * Example #1: an actor that has the Status.PARALYZED capability cannot take any action.
 * Example #2: an actor that has the Ability.ENTER_DOORS capability can enter a door.
 * Note that the capabilities are not limited to Status or Ability. It can be of any enumeration type.
 */
public abstract class GameEntity {

    /**
     * Current capabilities/statuses of this entity
     */
    protected final CapabilitySet capabilitySet = new CapabilitySet();

    /**
     * Add a capability to the current entity.
     * Example #1: addCapability(Status.SLEEP) will add the Status.SLEEP capability to the current entity.
     * @param capability the Capability to add
     */
    public void addCapability(Enum<?> capability) {
        capabilitySet.addCapability(capability);
    }

    /** 
     * Remove a capability from this Actor.
     * Example #1: removeCapability(Ability.OPEN_GATES) will remove the Ability.OPEN_GATES capability from the current entity.
     * @param capability the Capability to remove
     */
    public void removeCapability(Enum<?> capability) {
        capabilitySet.removeCapability(capability);
    }

    /**
     * Check whether the current entity has a specific capability.
     * Example #1: hasCapability(Status.BERSERK) will return true if the current entity has the Status.BERSERK capability.
     * @param capability the capability to check against
     * @return true if the current entity has the capability, false otherwise
     */
    public boolean hasCapability(Enum<?> capability) {
        return capabilitySet.hasCapability(capability);
    }

    /**
     * Get unmodifiable capabilities list
     * Example #1: if the current entity has the Status.POISONED and Ability.FALL_FROM_CLIFF capabilities, this method will return a list of
     * [Status.POISONED, Ability.FALL_FROM_CLIFF]
     * @return a list of unmodifiable capabilities
     */
    public List<Enum<?>> capabilitiesList() { return capabilitySet.capabilitiesList();	}

    /**
     * Get unmodifiable capabilities by a specific enum type
     * Example #1: If we have an enumeration type called Status, we can get all the statuses that the current entity possesses by calling
     * findCapabilitiesByType(Status.class)
     * @param enumType Class type, to be filtered later
     * @param <T> any enumeration type
     * @return list of enums based on type, empty list if type is not in the set.
     */
    public <T extends Enum<?>> List<T> findCapabilitiesByType(Class<T> enumType){
        return capabilitySet.findCapabilitiesByType(enumType);
    }
}
