package games.rolePlayingGames.shadowrun.util;


/**
 * Firing range types/categories.
 * 
 * @author Andrew
 *
 */
public enum FiringRangeType {

	/**
	 * Short range. No penalty.
	 */
	SHORT(0),
	/**
	 * Medium range. -1 penalty.
	 */
	MEDIUM(-1),
	/**
	 * Long range. -3 penalty.
	 */
	LONG(-3),
	/**
	 * Extreme range. -6 penalty.
	 */
	EXTREME(-6);

	/**
	 * Value.
	 */
	private final int value;

	/**
	 * Constructor.
	 * 
	 * @param iValue
	 *            value.
	 */
	private FiringRangeType(final int iValue) {
		value = iValue;
	}

	/**
	 * 
	 * @param iValue
	 * @return
	 */
	public static FiringRangeType valueOf(final int iValue) {
		for (final FiringRangeType firingRangeType : FiringRangeType.values()) {
			if (iValue == firingRangeType.getValue()) {
				return firingRangeType;
			}
		}

		throw new EnumConstantNotPresentException(FiringRangeType.class,
				String.valueOf(iValue));
	}

	/**
	 * @return value.
	 */
	public int getValue() {
		return value;
	}
}
