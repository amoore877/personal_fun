package games.rolePlayingGames.shadowrun.util;

/**
 * Shadowrun flechette shotgun choke settings.
 * 
 * @author Andrew
 *
 */
public enum ChokeSetting {

	/**
	 * Narrow choke. -1 dodge.
	 */
	NARROW,
	/**
	 * Medium choke. Cannot call shots. Short range: -1 damage, -3 dodge, up to
	 * two targets within 2m Medium range: -3 damage, -3 defense, up to three
	 * targets within 4m. Long range: -5 damage, -1 accuracy, -3 dodge, up to
	 * four targets within 6m. Extreme range: -7 damage, -1 accuracy, -3 dodge,
	 * up to six targets within 8m.
	 */
	MEDIUM,
	/**
	 * Wide choke. Cannot call shots. Short range: -3 damage, -5 dodge, up to
	 * two targets within 3m. Medium range: -5 damage, -5 dodge, up to three
	 * targets within 6m. Long range: -7 damage, -1 accuracy, -5 dodge, up to
	 * four targets within 9m. Extreme range: -9 damage, -1 accuracy, -5 dodge,
	 * up to six targets within 12m.
	 */
	WIDE;
}
