package edu.monash.fit2099.engine.weapons;

import edu.monash.fit2099.engine.items.*;

/**
 * Class representing items that can be used as a weapon.
 */
public abstract class WeaponItem extends Item implements Weapon {
	private static final float DEFAULT_DAMAGE_MULTIPLIER = 1.0f;
	private final int damage;
	private int hitRate;
	private final String verb;
	private float damageMultiplier;

	/** Constructor.
	 *
	 * @param name name of the item
	 * @param displayChar character to use for display when item is on the ground
	 * @param damage amount of damage this weapon does
	 * @param verb verb to use for this weapon, e.g. "hits", "zaps"
	 * @param hitRate the probability/chance to hit the target.
	 */
	public WeaponItem(String name, char displayChar, int damage, String verb, int hitRate) {
		super(name, displayChar, true);
		this.damage = damage;
		this.verb = verb;
		this.hitRate = hitRate;
		this.damageMultiplier = DEFAULT_DAMAGE_MULTIPLIER;
	}

	/**
	 * This method overwrites the existing damage multiplier value with the new one.
	 * For example, this could be used to reset the damage multiplier back to 1.0f.
	 * @param newDamageMultiplier the new damage multiplier value to overwrite the existing one.
	 */
	public void updateDamageMultiplier(float newDamageMultiplier) {
		this.damageMultiplier = newDamageMultiplier;
	}

	/**
	 * This method increases the existing damage multiplier value with the given input.
	 * It will not overwrite the existing damage multiplier value.
	 * @param damageMultiplier the new damage multiplier value to be added to the existing value.
	 */
	public void increaseDamageMultiplier(float damageMultiplier) {
		this.damageMultiplier += damageMultiplier;
	}

	/**
	 * This method overwrites the existing hit rate value with the given input.
	 * For example, this could be used to reset the hit rate of the weapon back to the initial hit rate.
	 * @param hitRate the new hit rate to overwrite the existing one.
	 */
	public void updateHitRate(int hitRate) {
		this.hitRate = hitRate;
	}

	/**
	 * This method increases the existing hit rate value with the given input.
	 * It will not overwrite the existing hit rate value.
	 * @param hitRate the new hit rate to be added to the existing value
	 */
	public void increaseHitRate(int hitRate) {
		this.hitRate += hitRate;
	}

	/**
	 * Accessor for damage done by this weapon.
	 *
	 * @return the damage
	 */
	public int damage() {
		return Math.round(damage * damageMultiplier);
	}

	/**
	 * Returns the verb used for attacking with this weapon, so that it can be displayed
	 * in the UI.
	 *
	 * @return a third-person present tense verb, e.g. "hits", "zaps"
	 */
	public String verb() {
		return verb;
	}


	/**
	 * Returns the chance to hit the target in integer. Use it altogether with nextInt() method.
	 * @return Integer, such as
	 */
	public int chanceToHit() {
		return hitRate;
	}
}
