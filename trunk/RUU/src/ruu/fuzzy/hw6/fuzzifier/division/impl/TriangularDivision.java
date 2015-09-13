package ruu.fuzzy.hw6.fuzzifier.division.impl;

import java.security.InvalidParameterException;

import ruu.fuzzy.hw6.fuzzifier.division.AbstractDivision;

/**
 * Triangular division value. In the midpoint between the min and max domain
 * values is the domain value for which the confidence of membership is 1.0.
 * Confidence is 0 at or below the min domain value, and at or above the max
 * domain value.
 * 
 * @author Andrew
 *
 */
public class TriangularDivision extends AbstractDivision {

	/**
	 * Midpoint between the min value and the max value.
	 */
	private final double myMidpoint;

	/**
	 * Constructor.
	 * 
	 * @param iMaxDomainValue
	 *            right-most domain point on the triangle.
	 * @param iMinDomainValue
	 *            left-most domain point on the triangle.
	 * @param iName
	 *            name of the division.
	 * @throws InvalidParameterException
	 *             on invalid min/max combination.
	 */
	public TriangularDivision(final double iMinDomainValue,
			final double iMaxDomainValue, final String iName)
			throws InvalidParameterException {
		super(iMaxDomainValue, iMinDomainValue, iName);
		myMidpoint = (iMaxDomainValue + iMinDomainValue) / 2;
	}

	@Override
	public double getConfidenceValue(final double iDomainValue) {
		if ((iDomainValue >= getMaxDomainValue())
				|| (iDomainValue <= getMinDomainValue())) {
			return 0;
		} else if (iDomainValue == myMidpoint) {
			return 1;
		} else if (iDomainValue < myMidpoint) {
			return (iDomainValue - getMinDomainValue())
					/ (myMidpoint - getMinDomainValue());
		} else if (iDomainValue > myMidpoint) {
			return (getMaxDomainValue() - iDomainValue)
					/ (getMaxDomainValue() - myMidpoint);
		}
		return 0;
	}

	/**
	 * @return midpoint of the domain for this division.
	 */
	public double getMidpoint() {
		return myMidpoint;
	}
}
