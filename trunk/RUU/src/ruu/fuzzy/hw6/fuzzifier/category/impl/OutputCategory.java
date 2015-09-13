package ruu.fuzzy.hw6.fuzzifier.category.impl;

import ruu.fuzzy.hw6.fuzzifier.category.AbstractCategory;
import ruu.fuzzy.hw6.fuzzifier.category.IOutputCategory;
import ruu.fuzzy.hw6.fuzzifier.division.AbstractDivision;

/**
 * Output category.
 * 
 * @author Andrew
 *
 */
public final class OutputCategory<D extends AbstractDivision> extends
		AbstractCategory<D> implements IOutputCategory {

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
	public OutputCategory(final double iMinDomainValue,
			final double iMaxDomainValue, final String iName) {
		super(iMinDomainValue, iMaxDomainValue, iName);
	}

}
