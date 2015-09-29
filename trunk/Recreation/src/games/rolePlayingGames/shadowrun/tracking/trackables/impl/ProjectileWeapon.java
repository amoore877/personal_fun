package games.rolePlayingGames.shadowrun.tracking.trackables.impl;

import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.AbstractAmmoFedWeapon;
import games.rolePlayingGames.shadowrun.util.DamageElement;

import java.awt.GridLayout;
import java.text.ParseException;

import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A thrown/slow-moving projectile weapon in Shadowrun. It is assumed that clip
 * capacity is 1.
 * 
 * @author Andrew
 *
 */
public final class ProjectileWeapon extends AbstractAmmoFedWeapon {

	/**
	 * Constructor. Clip capacity assumed to be 1.
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
	 * @param iSpareClips
	 *            spare clips available.
	 */
	public ProjectileWeapon(final String iName, final String iBenefits,
			final int iAccuracy, final int iDamageValue,
			final int iArmorPiercing, final DamageElement iDamageElement,
			final int iSpareClips) {
		super(iName, iBenefits, iAccuracy, iDamageValue, iArmorPiercing,
				iDamageElement, 1, iSpareClips);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getName());

		oResult.append(" Acc:" + getAccuracy());

		oResult.append(" DV:" + getDamageValue());

		oResult.append(" AP:" + getArmorPiercing());

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

		// accuracy
		final JFormattedTextField accuracyField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Accuracy", getAccuracy());

		// DV
		final JFormattedTextField damageValueField = ShadowrunTrackingUtil
				.addIntField(editPanel, "DV", getDamageValue());

		// AP
		final JFormattedTextField armorPierceField = ShadowrunTrackingUtil
				.addIntField(editPanel, "AP", getArmorPiercing());

		// damage element
		final JComboBox<DamageElement> damageElementBox = ShadowrunTrackingUtil
				.addEnumComboBox(editPanel, "Element", DamageElement.values(),
						getDamageElement());

		// clip capacity
		final JFormattedTextField clipCapacityField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Capacity", getClipCapacity());

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

			// accuracy
			try {
				accuracyField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newAccuracy = Integer.parseInt(accuracyField.getValue()
					.toString());

			if (newAccuracy != getAccuracy()) {
				setAccuracy(newAccuracy);
			} else {
				System.out.println("Accuracy unchanged: [" + getAccuracy()
						+ "]");
			}

			// damage value
			try {
				damageValueField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newDamageValue = Integer.parseInt(damageValueField
					.getValue().toString());

			if (newDamageValue != getDamageValue()) {
				setDamageValue(newDamageValue);
			} else {
				System.out.println("Damage Value unchanged: ["
						+ getDamageValue() + "]");
			}

			// armor piercing
			try {
				armorPierceField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newArmorPierce = Integer.parseInt(armorPierceField
					.getValue().toString());

			if (newArmorPierce != getArmorPiercing()) {
				setArmorPiercing(newArmorPierce);
			} else {
				System.out.println("Armor Piercing unchanged: ["
						+ getArmorPiercing() + "]");
			}

			// damage element
			DamageElement newDamageElement = (DamageElement) damageElementBox
					.getSelectedItem();
			if (newDamageElement == null) {
				newDamageElement = DamageElement.REGULAR;
			}

			if (!getDamageElement().equals(newDamageElement)) {
				setDamageElement(newDamageElement);
			} else {
				System.out.println("Damage Element unchanged: ["
						+ getDamageElement() + "]");
			}

			// clip capacity
			try {
				clipCapacityField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newClipCapacity = Integer.parseInt(clipCapacityField
					.getValue().toString());

			if (newClipCapacity != getClipCapacity()) {
				setClipCapacity(newClipCapacity);
			} else {
				System.out.println("Clip Capacity unchanged: ["
						+ getClipCapacity() + "]");
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

		if (getAmmoInClip() == 0 && getSpareClips() == 0) {
			// no ammo left
			oResult.append(" EMPTY");
		} else if (getTotalDamage() != 0) {
			oResult.append(" DAMAGED");
		} else {
			oResult.append(" Acc:" + getAccuracy());

			oResult.append(" DV:" + getDamageValue());

			oResult.append(" AP:" + getArmorPiercing());

			if (!getDamageElement().equals(DamageElement.REGULAR)) {
				oResult.append(" Element:" + getDamageElement().toString());
			}
		}

		return oResult.toString();
	}

	/**
	 * Reduce ammo in clip by 1. Should ensure that ammo is available
	 * beforehand.
	 */
	@Override
	public void use() {
		setAmmoInClip(getAmmoInClip() - 1);
	}
}
