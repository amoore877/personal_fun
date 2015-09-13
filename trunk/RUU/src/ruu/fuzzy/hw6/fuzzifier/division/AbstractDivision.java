package ruu.fuzzy.hw6.fuzzifier.division;

import java.security.InvalidParameterException;

/**
 * A fuzzy division of some shape, with some min and max domain value.
 * 
 * @author Andrew
 */
public abstract class AbstractDivision {

	/**
	 * Min domain value.
	 */
	private final double myMinDomainValue;

	/**
	 * Max domain value.
	 */
	private final double myMaxDomainValue;

	/**
	 * Name of the category.
	 */
	private final String myName;

	/**
	 * Constructor.
	 * 
	 * @param iMaxDomainValue
	 *            maximum domain value.
	 * @param iMinDomainValue
	 *            minimum domain value.
	 * @param iName
	 *            name of the division.
	 * @throws InvalidParameterException
	 *             on invalid min/max combination.
	 */
	public AbstractDivision(final double iMaxDomainValue,
			final double iMinDomainValue, final String iName)
			throws InvalidParameterException {
		if (iMaxDomainValue <= iMinDomainValue) {
			throw new InvalidParameterException("Max domain value ["
					+ iMaxDomainValue
					+ "] is less than or equal to min domain value ["
					+ iMinDomainValue + "]");
		}

		myMaxDomainValue = iMaxDomainValue;
		myMinDomainValue = iMinDomainValue;
		myName = iName;
	}

	/**
	 * @return min domain value of the shape.
	 */
	public double getMinDomainValue() {
		return myMinDomainValue;
	}

	/**
	 * @return max domain value of the shape.
	 */
	public double getMaxDomainValue() {
		return myMaxDomainValue;
	}

	@Override
	public String toString() {
		return myName;
	}

	/**
	 * Given the domain value, give the confidence that the domain value is in
	 * this division.
	 * 
	 * @param iDomainValue
	 *            the domain value whose confidence we want.
	 * @return the confidence that the domain value is in this division.
	 */
	public abstract double getConfidenceValue(double iDomainValue);
}
