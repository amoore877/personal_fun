package games.rolePlayingGames.shadowrun.tracking.trackables.item;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.device.ItemPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.util.ShadowrunCommonUtils;
import games.rolePlayingGames.tracking.trackable.item.AbstractItem;

import java.util.ArrayList;

/**
 * Abstract Shadowrun item. Some item, which can be destroyed.
 * 
 * @author Andrew
 */
public abstract class AbstractShadowrunItem extends AbstractItem implements
		IShadowrunItem {

	/**
	 * Body/structure.
	 */
	private int myBody;

	/**
	 * Armor.
	 */
	private int myArmor;

	/**
	 * Physical damage notes.
	 */
	private final ArrayList<ItemPhysicalDamageNote> myPhysicalDamage = new ArrayList<ItemPhysicalDamageNote>();

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name of item.
	 * @param iBody
	 *            body attribute.
	 * @param iArmor
	 *            armor attribute.
	 */
	public AbstractShadowrunItem(final String iName, final int iBody,
			final int iArmor) {
		super(iName);
		myBody = iBody;
		myArmor = iArmor;
	}

	@Override
	public final int getMaximumHealth() {
		return ShadowrunCommonUtils.getMaximumHealth(getBody());
	}

	/**
	 * Physical damage.
	 */
	@Override
	public final int getTotalDamage() {
		int oTotalDamage = 0;

		for (final ItemPhysicalDamageNote damageNote : myPhysicalDamage) {
			final int currentDamage = damageNote.getDamage();
			final int currentHealed = damageNote.getHealed();

			oTotalDamage += Math.max(0, (currentDamage - currentHealed));
		}

		return oTotalDamage;
	}

	/**
	 * Clone of damage notes.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public final ArrayList<ItemPhysicalDamageNote> getDamageNotes() {
		return (ArrayList<ItemPhysicalDamageNote>) myPhysicalDamage.clone();
	}

	@Override
	public final void removeDamageNote(final ItemPhysicalDamageNote iDamageNote) {
		if (iDamageNote != null) {
			myPhysicalDamage.remove(iDamageNote);
		}
	}

	@Override
	public final void addDamageNote(final ItemPhysicalDamageNote iDamageNote) {
		if (iDamageNote != null) {
			myPhysicalDamage.add(iDamageNote);
		}
	}

	@Override
	public final int getBody() {
		return myBody;
	}

	@Override
	public final int getArmor() {
		return myArmor;
	}

	/**
	 * Set body.
	 * 
	 * @param iBody
	 *            new body.
	 */
	protected final void setBody(final int iBody) {
		myBody = iBody;
	}

	/**
	 * Set armor.
	 * 
	 * @param iArmor
	 *            new value.
	 */
	protected final void setArmor(final int iArmor) {
		myArmor = iArmor;
	}

}
