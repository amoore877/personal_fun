package games.rolePlayingGames.shadowrun.tracking.notes.status;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Types of status effects (positive, negative, neutral).
 * 
 * @author Andrew
 */
@XmlEnum
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
