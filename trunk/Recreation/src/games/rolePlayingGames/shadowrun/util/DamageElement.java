package games.rolePlayingGames.shadowrun.util;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Elements of damage in Shadowrun.
 * 
 * @author Andrew
 *
 */
@XmlEnum
public enum DamageElement {
	/**
	 * Regular.
	 */
	REGULAR,
	/**
	 * Acid. Acid's damage is reduced by 1 every combat turn and re-applied.
	 * Every combat turn the defender's armor is reduced by 1 as well.
	 */
	ACID,
	/**
	 * Cold. Makes armor brittle. Make an armor test, or else armor becomes
	 * broken and needs repair.
	 */
	COLD,
	/**
	 * Electricity. Living things suffer -1 penalty on all actions and defense
	 * rolls, and -5 on initiative (once per combat turn).
	 */
	ELECTRIC,
	/**
	 * Fire. Must roll Armor + Fire Resist - Fire AP to not catch fire against
	 * the net hits rolled on the fire attack. When on fire, DV starts at 3 and
	 * increases by 1 every Combat Turn. Reduce DV by 1 per hit on Agility +
	 * Intuition.
	 */
	FIRE;
}
