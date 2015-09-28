package games.rolePlayingGames.tracking.trackable.impl.basic;

import games.rolePlayingGames.tracking.note.impl.example.BasicDamageNote;
import games.rolePlayingGames.tracking.trackable.character.AbstractCharacter;

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
 * Basic character.
 * 
 * @author Andrew
 */
public class BasicCharacter extends AbstractCharacter<BasicDamageNote> {

	/**
	 * Constructor.
	 * 
	 * @param iName
	 *            name.
	 */
	public BasicCharacter(final String iName) {
		super(iName);
	}

	@Override
	public String toFullString() {
		final StringBuilder oResult = new StringBuilder(getName());

		oResult.append(": Max Health-" + getMaximumHealth());

		for (final BasicDamageNote damageNote : getDamageNotes()) {
			oResult.append(", Damage-" + damageNote.toString());
		}

		return oResult.toString();
	}

	@Override
	public void edit() {
		// TODO Auto-generated method stub
		final JPanel editPanel = new JPanel(new GridLayout(0, 1));

		// name
		final JTextField nameField = new JTextField(getName());
		editPanel.add(new JLabel("Name: "));
		editPanel.add(nameField);

		// initiative
		final JFormattedTextField initiativeField = new JFormattedTextField(
				new NumberFormatter(NumberFormat.getIntegerInstance()));
		initiativeField.setValue(Integer.valueOf(getInitiative()));
		editPanel.add(new JLabel("Initiative: "));
		editPanel.add(initiativeField);

		// max health
		final JFormattedTextField maxHealthField = new JFormattedTextField(
				new NumberFormatter(NumberFormat.getIntegerInstance()));
		maxHealthField.setValue(Integer.valueOf(getMaximumHealth()));
		editPanel.add(new JLabel("Max Health: "));
		editPanel.add(maxHealthField);

		// current damage notes
		for (final BasicDamageNote damageNote : getDamageNotes()) {
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

		// new damage notes
		// TODO probably should count on actions in tracking table for adding
		// damage notes instead; these Basic classes are all prototypes anyway

		final int result = JOptionPane.showConfirmDialog(null, editPanel,
				"Edit this note", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// name
			final String newName = nameField.getText();

			if (!newName.equals(getName())) {
				setName(newName);
			} else {
				System.out.println("Name unchanged: [" + getName() + "]");
			}

			// initiative
			try {
				initiativeField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newInitiative = Integer.parseInt(initiativeField
					.getValue().toString());

			if (newInitiative != getInitiative()) {
				setInitiative(newInitiative);
			} else {
				System.out.println("Initiative unchanged: [" + getInitiative()
						+ "]");
			}

			// max health
			try {
				maxHealthField.commitEdit();
			} catch (final ParseException iException) {
				System.err.println(iException.getMessage());
			}
			final int newMaxHealth = Integer.parseInt(maxHealthField.getValue()
					.toString());

			if (newMaxHealth != getMaximumHealth()) {
				setMaximumHealth(newMaxHealth);
			} else {
				System.out.println("Max health unchanged: ["
						+ getMaximumHealth() + "]");
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

		oResult.append(": " + getTotalDamage() + "/" + getMaximumHealth());

		return oResult.toString();
	}

}
