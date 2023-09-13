package edu.monash.fit2099.engine.capabilities;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * A collection of Capability objects.
 * The original purpose of Capabilities was to allow game clients to check whether Actors could do particular Actions,
 * whether Items provided (or required) certain abilities, whether terrain was passable under particular
 * circumstances, etc.  Consider Capabilities an all-purpose mechanism for enabling game capabilities, statuses, etc.
 * Don't be too literal about the name. You can keep all sorts of things in here.
 * PURPLE, FLAT, HOUSE_RAVENCLAW, TEAM_HERBIVORE, etc.  
 * Any Enum type can be used to represent a Capability, so these classes can and should be defined in the game client.
 */
public class CapabilitySet {
	/**
	 * The set of capability (uniques only)
	 */
	private final Set<Enum<?>> capabilitySet = new HashSet<>();

	/**
	 * Check if this instance has capability
	 * @param capability enum
	 * @return true if it has the capability, false otherwise
	 */
	public boolean hasCapability(Enum<?> capability) {
		return capabilitySet.contains(capability);
	}

	/**
	 * Attach capability/status to the instance
	 * @param capability enum
	 */
	public void addCapability(Enum<?> capability) {
		if(!hasCapability(capability)){
			capabilitySet.add(capability);
		}
	}

	/**
	 * Detach capability/status from the instance
	 * @param capability enum
	 */
	public void removeCapability(Enum<?> capability) {
		if(hasCapability(capability)){
			capabilitySet.remove(capability);
		}
	}

	/**
	 * Get unmodifiable capabilities list to avoid privacy leak
	 * @return unmodifiable list of capabilities
	 */
	public List<Enum<?>> capabilitiesList(){
		return List.copyOf(capabilitySet);
	}

	/**
	 * Get unmodifiable capabilities by a specific enum type
	 * @param <T> Enum type
	 * @param enumType Class type, to be filtered later
	 * @return list of enums based on type, empty list if type is not in the set.
	 */
	@SuppressWarnings("unchecked")
	public <T extends Enum<?>> List<T> findCapabilitiesByType(Class<T> enumType){
		return capabilitySet.stream()
				.filter(c -> c.getDeclaringClass().equals(enumType))
				.map(response -> (T) response)
				.collect(Collectors.toList());
	}
}
