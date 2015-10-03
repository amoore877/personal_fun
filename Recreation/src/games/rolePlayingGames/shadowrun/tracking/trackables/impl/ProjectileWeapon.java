package games.rolePlayingGames.shadowrun.tracking.trackables.impl;

import games.rolePlayingGames.shadowrun.dice.ShadowrunRollResult;
import games.rolePlayingGames.shadowrun.dice.ShadowrunRoller;
import games.rolePlayingGames.shadowrun.tracking.ShadowrunTrackingUtil;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.AbstractAmmoFedWeapon;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.equipment.weapon.IRangedWeapon;
import games.rolePlayingGames.shadowrun.util.ChokeSetting;
import games.rolePlayingGames.shadowrun.util.ChokeSettingToStatisticTable;
import games.rolePlayingGames.shadowrun.util.DamageElement;
import games.rolePlayingGames.shadowrun.util.FiringRangeType;
import games.rolePlayingGames.shadowrun.util.RangeTable;
import games.rolePlayingGames.shadowrun.util.RangedWeaponType;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
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
public final class ProjectileWeapon extends AbstractAmmoFedWeapon implements
		IRangedWeapon {

	/**
	 * Ranged weapon types for this weapon.
	 */
	private final Set<RangedWeaponType> myRangedWeaponTypes;

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
	 * @param iRangedWeaponTypes
	 *            ranged weapon types for this weapon.
	 */
	public ProjectileWeapon(final String iName, final String iBenefits,
			final int iAccuracy, final int iDamageValue,
			final int iArmorPiercing, final DamageElement iDamageElement,
			final int iSpareClips,
			final Set<RangedWeaponType> iRangedWeaponTypes) {
		super(iName, iBenefits, iAccuracy, iDamageValue, iArmorPiercing,
				iDamageElement, 1, iSpareClips);
		myRangedWeaponTypes = iRangedWeaponTypes;
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

		// ammo in clip
		final JFormattedTextField ammoInClipField = ShadowrunTrackingUtil
				.addIntField(editPanel, "Ammo In Clip", getAmmoInClip(), 0,
						getClipCapacity());

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

	@Override
	public void use() {
		final int ammoInClip = getAmmoInClip();
		// if no bullets left, can't fire
		if (ammoInClip <= 0) {
			JOptionPane.showMessageDialog(null, "Out of ammo! Can't fire.",
					"Out of ammo", JOptionPane.ERROR_MESSAGE);
		} else {

			final JPanel firingPanel = new JPanel(new GridLayout(0, 1));

			// show bullets left in clip
			firingPanel.add(new JLabel("Ammo in Clip: " + ammoInClip));

			// select range weapon type
			final JComboBox<RangedWeaponType> rangedWeaponTypeBox = ShadowrunTrackingUtil
					.addEnumComboBox(firingPanel, "Ranged Weapon Type:",
							(RangedWeaponType[]) (getRangedWeaponTypes()
									.toArray()), getRangedWeaponTypes()
									.iterator().next());

			// display range info
			final JLabel rangeInfoLabel = new JLabel();
			firingPanel.add(rangeInfoLabel);
			// add listener for the range label text to listen to ranged weapon
			// type
			rangedWeaponTypeBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(final ActionEvent e) {
					// display range info for the weapon type
					final RangedWeaponType rangedWeaponType = (RangedWeaponType) (rangedWeaponTypeBox
							.getSelectedItem());
					final HashMap<FiringRangeType, String> firingRangeMap = RangeTable
							.getAllRangesForWeapon(rangedWeaponType);

					final StringBuilder rangeInfo = new StringBuilder();
					rangeInfo.append(FiringRangeType.SHORT.toString());
					rangeInfo.append(":"
							+ firingRangeMap.get(FiringRangeType.SHORT) + ",");
					rangeInfo.append(FiringRangeType.MEDIUM.toString());
					rangeInfo.append(":"
							+ firingRangeMap.get(FiringRangeType.MEDIUM) + ",");
					rangeInfo.append(FiringRangeType.LONG.toString());
					rangeInfo.append(":"
							+ firingRangeMap.get(FiringRangeType.LONG) + ",");
					rangeInfo.append(FiringRangeType.EXTREME.toString());
					rangeInfo.append(":"
							+ firingRangeMap.get(FiringRangeType.EXTREME));

					rangeInfoLabel.setText(rangeInfo.toString());
				}
			});

			// select range
			final JComboBox<FiringRangeType> rangeBox = ShadowrunTrackingUtil
					.addEnumComboBox(firingPanel, "Range:",
							FiringRangeType.values(), FiringRangeType.SHORT);

			// select choke (if flechette shotgun)
			final JComboBox<ChokeSetting> chokeBox = ShadowrunTrackingUtil
					.addEnumComboBox(firingPanel, "Choke:",
							ChokeSetting.values(), ChokeSetting.NARROW);
			// add listener for choke to listen to ranged weapon type
			rangedWeaponTypeBox.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(final ActionEvent e) {
					// enable/disable choke settings based on weapon type
					final RangedWeaponType rangedWeaponType = (RangedWeaponType) (rangedWeaponTypeBox
							.getSelectedItem());
					if (RangedWeaponType.FLECHETTE_SHOTGUN
							.equals(rangedWeaponType)) {
						chokeBox.setEnabled(true);
					} else {
						chokeBox.setEnabled(false);
					}
				}
			});

			// select some ranged weapon type, to ensure listeners set off
			rangedWeaponTypeBox.setSelectedIndex(0);

			final int result = JOptionPane.showConfirmDialog(null, firingPanel,
					"Firing " + getName(), JOptionPane.OK_CANCEL_OPTION,
					JOptionPane.PLAIN_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				// parse out selected info
				final FiringRangeType firingRange = (FiringRangeType) (rangeBox
						.getSelectedItem());
				final int rangePenalty = firingRange.getValue();

				final int realBulletUsage = 1;

				final int damagePenalty;
				final int accuracyPenalty;
				final int realDodgePenalty;
				if (RangedWeaponType.FLECHETTE_SHOTGUN
						.equals(rangedWeaponTypeBox.getSelectedItem())) {
					final ChokeSetting choke = (ChokeSetting) (chokeBox
							.getSelectedItem());
					damagePenalty = ChokeSettingToStatisticTable
							.getDamagePenaltyForChokeSetting(choke, firingRange);
					accuracyPenalty = ChokeSettingToStatisticTable
							.getAccuracyPenaltyForChokeSetting(firingRange);
					realDodgePenalty = ChokeSettingToStatisticTable
							.getDodgePenaltyForChokeSetting(choke);
				} else {
					// not a flechette shotgun, so no penalties to damage or
					// accuracy and no additional penalties to dodge
					realDodgePenalty = 0;
					damagePenalty = 0;
					accuracyPenalty = 0;
				}

				displayAttackPanel(rangePenalty, realBulletUsage,
						damagePenalty, accuracyPenalty, realDodgePenalty);

			} else if (result == JOptionPane.CANCEL_OPTION) {
				System.out.println("Cancel selected.");
			} else {
				System.err.println("Unknown option selected.");
			}
		}
	}

	/**
	 * Show attack panel.
	 * 
	 * @param iRangePenalty
	 *            range penalty.
	 * @param iBulletUsage
	 *            bullet usage.
	 * @param iDamagePenalty
	 *            damage penalty.
	 * @param iAccuracyPenalty
	 *            accuracy penalty.
	 * @param iDodgePenalty
	 *            dodge penalty.
	 */
	private void displayAttackPanel(final int iRangePenalty,
			final int iBulletUsage, final int iDamagePenalty,
			final int iAccuracyPenalty, final int iDodgePenalty) {
		// subtract bullets from ammo in clip
		setAmmoInClip(getAmmoInClip() - iBulletUsage);
		/**
		 * display, based on options, Acc, DV, AP, Attack penalty, Dodge
		 * penalty, Benefits
		 */
		final int realAccuracy = getAccuracy() + iAccuracyPenalty;
		final int realDamage = getDamageValue() + iDamagePenalty;
		final int armorPiercing = getArmorPiercing();
		final int attackPenalty = iRangePenalty;
		// bring up rolling option
		final JPanel attackPanel = new JPanel(new GridLayout(0, 1));

		final StringBuilder attackString = new StringBuilder();
		attackString.append("<html>");
		attackString.append("Acc: " + realAccuracy + "<br>");
		attackString.append("DV: " + realDamage + "<br>");
		attackString.append("AP: " + armorPiercing + "<br>");
		attackString.append("Attack penalty: " + attackPenalty + "<br>");
		attackString.append("Dodge penalty: " + iDodgePenalty + "<br>");
		attackString.append("Benefits: " + getBenefits());
		attackString.append("</html>");

		attackPanel.add(new JLabel(attackString.toString()));

		final JFormattedTextField diceToRollField = ShadowrunTrackingUtil
				.addIntField(attackPanel, "Dice to roll:", 1, 1);

		final int result = JOptionPane.showConfirmDialog(null, attackPanel,
				"Firing " + getName(), JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			final int numOfDice = Integer.parseInt(diceToRollField.getValue()
					.toString());

			final ShadowrunRollResult rollResult = new ShadowrunRollResult(
					ShadowrunRoller.rollDice(numOfDice, false));
			/**
			 * display roll results, in non-modal dialog, true roll (limit on
			 * Acc), DV, AP, Element, Dodge penalty, benefits
			 */
			final JPanel attackResultPanel = new JPanel(new GridLayout(0, 1));

			final StringBuilder attackResultString = new StringBuilder();
			attackResultString.append("<html>");
			if (rollResult.isCriticalGlitch()) {
				attackResultString.append("CRITICAL GLITCH!");
			} else {
				if (rollResult.isGlitch()) {
					attackResultString.append("GLITCH");
				}
				attackResultString
						.append("Hits: "
								+ Math.min(realAccuracy, rollResult.getHits())
								+ "<br>");
				attackResultString.append("DV: " + realDamage + "<br>");
				attackResultString.append("AP: " + armorPiercing + "<br>");
				if (!DamageElement.REGULAR.equals(getDamageElement())) {
					attackResultString.append("Element: " + getDamageElement());
				}
				attackResultString.append("Dodge penalty: " + iDodgePenalty
						+ "<br>");
				attackResultString.append("Benefits: " + getBenefits());
			}
			attackResultString.append("</html>");

			attackResultPanel.add(new JLabel(attackResultString.toString()));

			final JOptionPane attackResultPane = new JOptionPane(
					attackResultPanel, JOptionPane.INFORMATION_MESSAGE,
					JOptionPane.OK_CANCEL_OPTION);
			final JDialog attackResultDialog = attackResultPane.createDialog(
					null, "Results for firing " + getName());
			attackResultDialog.setModal(false);
			attackResultDialog.setVisible(true);
		} else if (result == JOptionPane.CANCEL_OPTION) {
			System.out.println("Cancel selected.");
		} else {
			System.err.println("Unknown option selected.");
		}
	}

	@Override
	public Set<RangedWeaponType> getRangedWeaponTypes() {
		return myRangedWeaponTypes;
	}
}
