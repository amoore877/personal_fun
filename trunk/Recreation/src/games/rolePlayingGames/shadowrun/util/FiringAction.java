package games.rolePlayingGames.shadowrun.util;

/**
 * The types of firing actions that can be done in Shadowrun with firearms.
 * 
 * @author Andrew
 *
 */
public enum FiringAction {

	/**
	 * Single shot. Simple action. Must be SS weapon. Must rechamber afterwards.
	 * No recoil.
	 */
	SS,
	/**
	 * Regular semi-automatic. Simple action. Must be SA weapon. Fires 1 round.
	 */
	SA,
	/**
	 * Semi-automatic burst. Complex action. Must be SA weapon. Fires 3 rounds
	 * for -2 dodge.
	 */
	SB,
	/**
	 * Regular burst fire. Simple action. Must be BF weapon. Fires 3 rounds for
	 * -2 dodge.
	 */
	BF,
	/**
	 * Long burst. Complex action. Must be BF weapon. Fires 6 rounds for -5
	 * dodge.
	 */
	LB,
	/**
	 * Simple full auto. Simple action. Must be FA weapon. Fires 6 rounds for -5
	 * dodge.
	 */
	FA_SIMPLE,
	/**
	 * Complex full auto. Complex action. Must be FA weapon. Fires 10 rounds for
	 * -9 dodge.
	 */
	FA_COMPLEX,
	/**
	 * Suppressing fire. Complex action that lasts until end of Combat Turn as
	 * long as attacker doesn't move or do anything else. Must be FA weapon.
	 * Fires 20 rounds. Affects 10m x 2m area. Enemies in area must duck or
	 * cover, and suffer penalties equal to hits on roll. Must dodge with
	 * Reaction + Edge if out of cover. No recoil.
	 */
	SUPPRESS;

}
