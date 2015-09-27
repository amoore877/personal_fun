package games.rolePlayingGames.shadowrun.tracking.notes.status;

/**
 * Types of status effects (positive, negative, neutral).
 * 
 * @author Andrew
 */
public enum StatusEffectType {

	/**
	 * Positive.
	 */
	POSITIVE("+"),
	/**
	 * Negative.
	 */
	NEGATIVE("-"),
	/**
	 * Neutral.
	 */
	NEUTRAL("n");

	/**
	 * Value.
	 */
	private final String value;

	private StatusEffectType(final String iValue) {
		value = iValue;
	}

	/**
	 * @return value.
	 */
	public String getValue() {
		return value;
	}

}
