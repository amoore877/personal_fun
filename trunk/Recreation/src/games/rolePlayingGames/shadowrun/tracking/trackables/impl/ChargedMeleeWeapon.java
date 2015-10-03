package games.rolePlayingGames.shadowrun.tracking.trackables.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.AbstractMeleeWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.IAmmoFedWeapon;
import games.rolePlayingGames.shadowrun.util.DamageElement;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public final class ChargedMeleeWeapon extends AbstractMeleeWeapon implements
		IAmmoFedWeapon {

	/**
	 * Capacity of clip.
	 */
	private final int myClipCapacity;

	/**
	 * Ammo in current clip.
	 */
	private int myAmmoInClip;

	/**
	 * Spare clips.
	 */
	private int mySpareClips;

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name of the weapon.
	 * @param iBenefits
	 *            benefits of weapon, beyond being a weapon.
	 * @param iAccuracy
	 *            accuracy.
	 * @param iDamageValue
	 *            damage value.
	 * @param iArmorPiercing
	 *            armor piercing.
	 * @param iDamageElement
	 *            damage element.
	 * @param iReach
	 *            reach of the weapon.
	 * @param iClipCapacity
	 *            clip capacity.
	 * @param iSpareClips
	 *            spare clips available.
	 */
	public ChargedMeleeWeapon(final String iName, final String iBenefits,
			final int iAccuracy, final int iDamageValue,
			final int iArmorPiercing, final DamageElement iDamageElement,
			final int iReach, final int iClipCapacity, final int iSpareClips) {
		super(iName, iBenefits, iAccuracy, iDamageValue, iArmorPiercing,
				iDamageElement, iReach);
		myClipCapacity = iClipCapacity;
		myAmmoInClip = iClipCapacity;
		mySpareClips = iSpareClips;
	}

	@Override
	public final int getClipCapacity() {
		return myClipCapacity;
	}

	@Override
	public final int getAmmoInClip() {
		return myAmmoInClip;
	}

	/**
	 * Set ammo in clip.
	 * 
	 * @param iAmmoInClip
	 *            new ammo in clip.
	 */
	private void setAmmoInClip(final int iAmmoInClip) {
		myAmmoInClip = iAmmoInClip;
	}

	@Override
	public final int getSpareClips() {
		return mySpareClips;
	}

	/**
	 * Set spare clips.
	 * 
	 * @param iSpareClips
	 *            new spare clips.
	 */
	private void setSpareClips(final int iSpareClips) {
		mySpareClips = iSpareClips;
	}

	@Override
	public final void reload() {
		mySpareClips--;
		myAmmoInClip = myClipCapacity;
	}

	@Override
	public void use() {
		// TODO more?
		setAmmoInClip(getAmmoInClip() - 1);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getName());

		oResult.append(" Acc:" + getAccuracy());

		oResult.append(" DV:" + getDamageValue());

		oResult.append(" AP:" + getArmorPiercing());

		oResult.append(" Reach:" + getReach());

		if (!getBenefits().isEmpty()) {
			oResult.append(" Extra: " + getBenefits());
		}

		if (!getDamageElement().equals(DamageElement.REGULAR)) {
			oResult.append(" Element:" + getDamageElement().toString());
		}

		return oResult.toString();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		// name
		final JTextField nameField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Name", getName());

		// ammo in clip
		final JFormattedTextField ammoInClipField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Ammo In Clip", getAmmoInClip());

		// spare clips
		final JFormattedTextField spareClipsField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Spare Clips", getSpareClips());

		// benefits
		final JTextField benefitsField = ShadowrunTrackingUtil.addStringField(
				editPanel, "Benefits", getBenefits());

		// current damage notes
		ShadowrunTrackingUtil.addDamageButtons(editPanel, this);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this weapon", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// name
			final String newName = nameField.getText();

			if (!newName.equals(getName())) {
				setName(newName);
			} else {
				System.out.println("Name unchanged: [" + getName() + "]");
			}

			// ammo in clip
			try {
				ammoInClipField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newAmmoInClip = Integer.parseInt(ammoInClipField
					.getValue().toString());

			if (newAmmoInClip != getAmmoInClip()) {
				setAmmoInClip(newAmmoInClip);
			} else {
				System.out.println("Ammo in Clip unchanged: ["
						+ getAmmoInClip() + "]");
			}

			// spare clips
			try {
				spareClipsField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newSpareClips = Integer.parseInt(spareClipsField
					.getValue().toString());

			if (newSpareClips != getSpareClips()) {
				setSpareClips(newSpareClips);
			} else {
				System.out.println("Spare Clips unchanged: [" + getSpareClips()
						+ "]");
			}

			// benefits
			final String newBenefits = benefitsField.getText();

			if (!newBenefits.equals(getName())) {
				setBenefits(newBenefits);
			} else {
				System.out.println("Benefits unchanged: [" + getBenefits()
						+ "]");
			}
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public String toString() {
		final StringBuilder oResult = new StringBuilder(getName());

		if (getTotalDamage() != 0) {
			oResult.append(" DAMAGED");
		} else {
			oResult.append(" Acc:" + getAccuracy());

			oResult.append(" DV:" + getDamageValue());

			oResult.append(" AP:" + getArmorPiercing());

			oResult.append(" Reach:" + getReach());

			if (!getDamageElement().equals(DamageElement.REGULAR)) {
				oResult.append(" Element:" + getDamageElement().toString());
			}

			if (getAmmoInClip() == 0 && getSpareClips() == 0) {
				// no ammo left
				oResult.append(" EMPTY");
			}
		}

		return oResult.toString();
	}

}
