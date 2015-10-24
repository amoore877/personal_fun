package games.rolePlayingGames.shadowrun.tracking.notes.quality;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Types of qualities (positive, negative, neutral).
 * 
 * @author Andrew
 */
@XmlEnum
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
