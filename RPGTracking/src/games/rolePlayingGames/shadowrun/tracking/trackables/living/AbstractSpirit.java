package games.rolePlayingGames.shadowrun.tracking.trackables.living;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.notes.damage.spirit.AbstractSpiritDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.QualityNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.SpiritPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.SpiritStunDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.StatusEffectNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Abstract Shadowrun character.
 * 
 * @author Andrew
 *
 */
public abstract class AbstractSpirit extends
		AbstractLivingBeing<AbstractSpiritDamageNote> implements ISpirit {

	/**
	 * Services.
	 */
	private int myServices;

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 * @param iEssence
	 *            essence, rounded down.
	 * @param iBody
	 *            body.
	 * @param iWillpower
	 *            willpower.
	 * @param iSpecial
	 *            magic/resonance.
	 * @param iStatusEffects
	 *            status effects.
	 * @param iInventory
	 *            inventory.
	 * @param iQualities
	 *            qualities.
	 * @param iServices
	 *            services.
	 */
	public AbstractSpirit(final String iName, final int iEssence,
			final int iBody, final int iWillpower, final int iSpecial,
			final ArrayList<StatusEffectNote> iStatusEffects,
			final ArrayList<AbstractShadowrunItem> iInventory,
			final ArrayList<QualityNote> iQualities, final int iServices) {
		super(iName, iEssence, iBody, iWillpower, iSpecial, iStatusEffects,
				iInventory, iQualities);

		myServices = iServices;
	}

	@Override
	public final int getTotalStunDamage() {
		int oResult = 0;

		for (final AbstractSpiritDamageNote damageNote : getDamageNotes()) {
			if (damageNote instanceof SpiritStunDamageNote) {
				final int currentDamage = damageNote.getDamage();
				final int currentHealed = damageNote.getHealed();

				oResult += Math.max(0, currentDamage - currentHealed);
			}
		}

		return oResult;
	}

	@Override
	public final String toString() {
		final StringBuilder oResult = new StringBuilder();

		oResult.append("Name=" + getName());

		if ((getTotalDamage() >= getMaximumHealth())
				|| (getTotalStunDamage() >= getMaximumStunHealth())
				|| (getServices() <= 0)) {
			oResult.append("BANISHED");
		}

		return oResult.toString();
	}

	@Override
	public abstract String toFullString();

	@Override
	public final void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));
		// name
		final JTextField nameField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Name", getName());

		// services
		final JFormattedTextField servicesField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Services", getServices());

		// current damage notes
		ShadowrunTrackingUtil.addDamageButtons(editPanel, this);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this Spirit", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// name
			ShadowrunTrackingUtil.examineChangedString(nameField, "Name",
					(s) -> setName(s), () -> getName());

			// services
			ShadowrunTrackingUtil.examineChangedInt(servicesField, "Services",
					(i) -> setServices(i), () -> getServices());
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public final int getTotalDamage() {
		int oResult = 0;

		for (final AbstractSpiritDamageNote damageNote : getDamageNotes()) {
			if (damageNote instanceof SpiritPhysicalDamageNote) {
				final int currentDamage = damageNote.getDamage();
				final int currentHealed = damageNote.getHealed();

				oResult += Math.max(0, currentDamage - currentHealed);
			}
		}

		return oResult;
	}

	@Override
	public final int getServices() {
		return myServices;
	}

	@Override
	public final void useServices(final int iServices) {
		myServices -= iServices;
	}

	/**
	 * Set services.
	 * 
	 * @param iServices
	 *            new services.
	 */
	protected final void setServices(final int iServices) {
		myServices = iServices;
	}
}
