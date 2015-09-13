package ruu.fuzzy.hw6.fuzzifier.category;

import java.security.InvalidParameterException;
import java.util.ArrayList;

import ruu.fuzzy.hw6.fuzzifier.division.AbstractDivision;

/**
 * A category (some input or output) of a fuzzy problem.
 * 
 * @author Andrew
 *
 */
public abstract class AbstractCategory<D extends AbstractDivision> {
	/**
	 * Name of this category.
	 */
	private final String myName;

	/**
	 * Min value of this category's domain.
	 */
	private final double myMinDomainValue;

	/**
	 * Max value of this category's domain.
	 */
	private final double myMaxDomainValue;

	/**
	 * Divisions.
	 */
	private final ArrayList<D> myDivisions;

	/**
	 * Constructor.
	 * 
	 * @param iMinDomainValue
	 *            lowest value of this category's domain.
	 * @param iMaxDomainValue
	 *            highest value of this category's domain.
	 * @param iName
	 *            name of this category.
	 */
	public AbstractCategory(final double iMinDomainValue, final double iMaxDomainValue,
			final String iName) {
		if (iMaxDomainValue <= iMinDomainValue) {
			throw new InvalidParameterException("Max domain value ["
					+ iMaxDomainValue
					+ "] is less than or equal to min domain value ["
					+ iMinDomainValue + "]");
		}
		myName = iName;
		myMinDomainValue = iMinDomainValue;
		myMaxDomainValue = iMaxDomainValue;
		myDivisions = new ArrayList<D>();
	}

	/**
	 * Add a new division to this category.
	 * 
	 * @param iNewDivision
	 *            the new division to add.
	 * @throws InvalidParameterException
	 *             on invalid min/max combination with this category.
	 */
	public void addDivision(final D iNewDivision)
			throws InvalidParameterException {
		if (iNewDivision.getMinDomainValue() < myMinDomainValue) {
			throw new InvalidParameterException("Division's domain min value ["
					+ iNewDivision.getMinDomainValue()
					+ "] is less than this category's min domain value ["
					+ myMinDomainValue + "].");
		} else if (iNewDivision.getMaxDomainValue() > myMaxDomainValue) {
			throw new InvalidParameterException("Division's domain max value ["
					+ iNewDivision.getMaxDomainValue()
					+ "] is greater than this category's max domain value ["
					+ myMaxDomainValue + "].");
		} else {
			myDivisions.add(iNewDivision);
		}
	}

	/**
	 * @return a copy of the divisions of this category.
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<D> getDivisions() {
		return (ArrayList<D>) myDivisions.clone();
	}

	/**
	 * @return minimum value of this category's domain.
	 */
	public double getMinDomainValue() {
		return myMinDomainValue;
	}

	/**
	 * @return maximum value of this category's domain.
	 */
	public double getMaxDomainValue() {
		return myMaxDomainValue;
	}

	@Override
	public String toString() {
		return myName;
	}

}
