package games.rolePlayingGames.shadowrun.tracking.trackables.impl;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.device.ItemPhysicalDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.item.AbstractShadowrunItem;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 * The most basic Shadowrun item. Something that can be destroyed.
 * 
 * @author Andrew
 *
 */
public final class ShadowrunBasicItem extends AbstractShadowrunItem {

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
	public ShadowrunBasicItem(final String iName, final int iBody,
			final int iArmor) {
		super(iName, iBody, iArmor);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getName());

		oResult.append(": Max Health-" + getMaximumHealth());

		for (final ItemPhysicalDamageNote damageNote : getDamageNotes()) {
			oResult.append(", Damage-" + damageNote.toString());
		}

		oResult.append(" Body: " + getBody() + " Armor: " + getArmor());

		return oResult.toString();
	}

	@Override
	public void edit() {
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		// name
		final JTextField nameField = new JTextField(getName());
		editPanel.add(new JLabel("Name: "));
		editPanel.add(nameField);

		// body
		final JFormattedTextField bodyField = new JFormattedTextField(
				new NumberFormatter(NumberFormat.getIntegerInstance()));
		bodyField.setValue(Integer.valueOf(getBody()));
		editPanel.add(new JLabel("Body: "));
		editPanel.add(bodyField);

		// armor
		final JFormattedTextField armorField = new JFormattedTextField(
				new NumberFormatter(NumberFormat.getIntegerInstance()));
		armorField.setValue(Integer.valueOf(getArmor()));
		editPanel.add(new JLabel("Armor: "));
		editPanel.add(armorField);

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this item", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		// current damage notes
		for (final ItemPhysicalDamageNote damageNote : getDamageNotes()) {
			final JButton damageEditButton = new JButton("Edit: "
					+ damageNote.toString());
			damageEditButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(final ActionEvent iEvent) {
					damageNote.edit();
					damageEditButton.setText("Edit: " + damageNote.toString());
				}
			});
			editPanel.add(damageEditButton);

			final JButton damageRemoveButton = new JButton("Remove: "
					+ damageNote.toString());
			damageRemoveButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(final ActionEvent iEvent) {
					System.out.println("Removing damage note: "
							+ damageNote.toFullString());

					removeDamageNote(damageNote);

					editPanel.remove(damageEditButton);
					editPanel.remove(damageRemoveButton);
				}
			});
			editPanel.add(damageRemoveButton);
		}

		if (result == JOptionPane.OK_OPTION) {
			// name
			final String newName = nameField.getText();

			if (!newName.equals(getName())) {
				setName(newName);
			} else {
				System.out.println("Name unchanged: [" + getName() + "]");
			}

			// body
			try {
				bodyField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newBody = Integer.parseInt(bodyField.getValue()
					.toString());

			if (newBody != getBody()) {
				setBody(newBody);
			} else {
				System.out.println("Body unchanged: [" + getBody() + "]");
			}

			// armor
			try {
				armorField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newArmor = Integer.parseInt(armorField.getValue()
					.toString());

			if (newArmor != getArmor()) {
				setBody(newArmor);
			} else {
				System.out.println("Armor unchanged: [" + getArmor() + "]");
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
			oResult.append(" " + getTotalDamage() + "/" + getMaximumHealth());
		}

		return oResult.toString();
	}
}
