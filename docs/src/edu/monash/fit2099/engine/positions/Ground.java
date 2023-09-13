package edu.monash.fit2099.engine.positions;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Printable;

/**
 * Class representing terrain type
 */
public abstract class Ground extends GameEntity implements Printable {

    private char displayChar;

	/**
	 * Constructor.
	 *
	 * @param displayChar character to display for this type of terrain
	 */
	public Ground(char displayChar) {
		this.displayChar = displayChar;
	}

	@Override
	public char getDisplayChar() {
		return displayChar;
	}

	protected final void setDisplayChar(char displayChar){
		this.displayChar = displayChar;
	}

	/**
	 * Returns an empty Action list.
	 *
	 * @param actor the Actor acting
	 * @param location the current Location
	 * @param direction the direction of the Ground from the Actor
	 * @return a new, empty collection of Actions
	 */
	public ActionList allowableActions(Actor actor, Location location, String direction){
		return new ActionList();
	}

	/**
	 * Override this to implement impassable terrain, or terrain that is only passable if conditions are met.
	 *
	 * @param actor the Actor to check
	 * @return true
	 */
	public boolean canActorEnter(Actor actor) {
		return true;
	}

	/**
	 * Ground can also experience the joy of time.
	 * @param location The location of the Ground 
	 */
	public void tick(Location location) {
	}
	
	/**
	 * Override this to implement terrain that blocks thrown objects but not movement, or vice versa
	 * @return true
	 */
	public boolean blocksThrownObjects() {
		return false;
	}
}
