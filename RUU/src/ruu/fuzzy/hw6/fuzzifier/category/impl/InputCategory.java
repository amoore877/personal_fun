package ruu.fuzzy.hw6.fuzzifier.category.impl;

import ruu.fuzzy.hw6.fuzzifier.category.AbstractCategory;
import ruu.fuzzy.hw6.fuzzifier.category.IInputCategory;
import ruu.fuzzy.hw6.fuzzifier.division.AbstractDivision;

/**
 * Input category.
 * 
 * @author Andrew
 *
 */
public final class InputCategory<D extends AbstractDivision> extends
		AbstractCategory<D> implements IInputCategory {

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
	public InputCategory(final double iMinDomainValue,
			final double iMaxDomainValue, final String iName) {
		super(iMinDomainValue, iMaxDomainValue, iName);
	}

}
