package games.rolePlayingGames.dice;

/**
 * Types of die.
 * 
 * @author Andrew
 */
public enum DieType {

	/**
	 * Heads or tails.
	 */
	D2(2),
	/**
	 * D4.
	 */
	D4(4),
	/**
	 * D6, standard die.
	 */
	D6(6),
	/**
	 * D8.
	 */
	D8(8),
	/**
	 * D10.
	 */
	D10(10),
	/**
	 * D12.
	 */
	D12(12),
	/**
	 * D20.
	 */
	D20(20),
	/**
	 * DND 0-99. Handle as special case. D100 minus 1.
	 */
	D99(99),
	/**
	 * D100.
	 */
	D100(100);

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
	private DieType(final int iValue) {
		value = iValue;
	}

	/**
	 * 
	 * @param iValue
	 * @return
	 */
	public static DieType valueOf(final int iValue) {
		for (final DieType dieType : DieType.values()) {
			if (iValue == dieType.getValue()) {
				return dieType;
			}
		}

		throw new EnumConstantNotPresentException(DieType.class,
				String.valueOf(iValue));
	}

	/**
	 * @return value.
	 */
	public int getValue() {
		return value;
	}
}
