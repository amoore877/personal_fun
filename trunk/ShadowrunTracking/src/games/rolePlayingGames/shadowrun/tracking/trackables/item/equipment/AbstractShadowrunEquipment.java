package games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment;

import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;

/**
 * Abstract Shadowrun equipment. Some item that has benefits of sorts.
 * 
 * @author Andrew
 *
 */
public abstract class AbstractShadowrunEquipment extends AbstractShadowrunItem
		implements IShadowrunEquipment {

	/**
	 * Benefits of the armor besides being armor.
	 */
	private String myBenefits;

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name of item.
	 * @param iBody
	 *            body attribute.
	 * @param iArmor
	 *            armor attribute.
	 * @param iBenefits
	 *            description of any other benefits beyond obvious.
	 */
	public AbstractShadowrunEquipment(final String iName, final int iBody,
			final int iArmor, final String iBenefits) {
		super(iName, iBody, iArmor);

		if (iBenefits == null) {
			myBenefits = "";
		} else {
			myBenefits = iBenefits;
		}
	}

	@Override
	public abstract String toFullString();

	@Override
	public abstract void edit();

	@Override
	public String getBenefits() {
		return myBenefits;
	}

	/**
	 * Set the benefits.
	 * 
	 * @param iBenefits
	 *            iBenefits.
	 */
	protected final void setBenefits(final String iBenefits) {
		if (iBenefits == null) {
			myBenefits = "";
		} else {
			myBenefits = iBenefits;
		}
	}

	@Override
	public abstract String toString();

}
