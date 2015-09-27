package games.rolePlayingGames.shadowrun.tracking.notes.quality;

/**
 * Types of qualities (positive, negative, neutral).
 * 
 * @author Andrew
 */
public enum QualityType {

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

	private QualityType(final String iValue) {
		value = iValue;
	}

	/**
	 * @return value.
	 */
	public String getValue() {
		return value;
	}

}
