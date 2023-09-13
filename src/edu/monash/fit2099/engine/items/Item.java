package edu.monash.fit2099.engine.items;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Printable;
import edu.monash.fit2099.engine.positions.Location;

/**
 * Abstract base class representing a physical object in the game world.
 *
 */
public abstract class Item extends GameEntity implements Printable {

	private final String name;
	private char displayChar;
	protected boolean portable;

	/***
	 * Constructor.
	 *
	 * @param name the name of this Item
	 * @param displayChar the character to use to represent this item if it is on the ground
	 * @param portable true if and only if the Item can be picked up
	 */
	public Item(String name, char displayChar, boolean portable) {
		this.name = name;
		this.displayChar = displayChar;
		this.portable = portable;
	}

	/**
	 * Inform a carried Item of the passage of time.
	 *
	 * This method is called once per turn, if the Item is being carried.
	 * @param currentLocation The location of the actor carrying this Item.
	 * @param actor The actor carrying this Item.
	 */
	public void tick(Location currentLocation, Actor actor) {
	}

	/**
	 * Inform an Item on the ground of the passage of time.
	 * This method is called once per turn, if the item rests upon the ground.
	 * @param currentLocation The location of the ground on which we lie.
	 */
	public void tick(Location currentLocation) {
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public char getDisplayChar() {
		return displayChar;
	}

	/**
	 * Update display character
	 * @param displayChar e.g., 'a', 'c', '#'
	 */
	protected final void setDisplayChar(char displayChar){
		this.displayChar = displayChar;
	}

	/**
	 * Create and return an action to pick this Item up.
	 * If this Item is not portable, returns null.
	 *
	 * @return a new PickUpItemAction if this Item is portable, null otherwise.
	 */
	public PickUpAction getPickUpAction(Actor actor) {
		if(portable)
			return new PickUpAction(this);
		return null;
	}

	/**
	 * Create and return an action to drop this Item.
	 * If this Item is not portable, returns null.
	 * @return a new DropItemAction if this Item is portable, null otherwise.
	 */
	public DropAction getDropAction(Actor actor) {
		if(portable)
			return new DropAction(this);
		return null;
	}

	/**
	 * Switch portability from 'true' to 'false', set portability to true allows the item to be picked up or dropped by actor.
	 * @see PickUpAction to pick-up item
	 * @see DropAction to drop item
	 */
	public void togglePortability(){
		this.portable = !this.portable;
	}

	/**
	 * List of allowable actions that can be performed on the item when it is on the ground
	 * Example #1: a trap can return an action to disarm the trap.
	 * 
	 * @param location the location of the ground on which the item lies
	 * @return an unmodifiable list of Actions
	 */
	public ActionList allowableActions(Location location) {
		return new ActionList();
	}

	/**
	 * List of allowable actions that the item can perform to the current actor
	 * Example #1: a healing item can have a special skill that can increase the current actor's hitpoints.
	 * 
	 * @param owner the actor that owns the item
	 * @return an unmodifiable list of Actions
	 */
	public ActionList allowableActions(Actor owner){
		return new ActionList();
	}

	/**
	 * List of allowable actions that the item allows its owner do to other actor.
	 * Example #1: a weapon can return an attacking action to the other actor.
	 * Example #2: if the weapon has a special ability, it can return an action to use the special ability.
	 * Example #3: a food can return an action to feed the other actor.
	 * 
	 * @param otherActor the other actor
	 * @param location the location of the other actor
	 * @return an unmodifiable list of Actions
	 */
	public ActionList allowableActions(Actor otherActor, Location location){
		return new ActionList();
	}
}
