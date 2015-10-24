package games.rolePlayingGames.shadowrun.tracking;

import games.rolePlayingGames.shadowrun.tracking.notes.damage.IShadowrunDamageNote;
import games.rolePlayingGames.shadowrun.tracking.notes.impl.DeviceMatrixDamageNote;
import games.rolePlayingGames.shadowrun.tracking.trackables.matrix.IMatrixDamageableTrackable;
import games.rolePlayingGames.tracking.trackable.IDestructibleTrackable;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

/**
 * Common utilities for Shadowrun tracking.
 * 
 * @author Andrew
 */
public class ShadowrunTrackingUtil {

	private ShadowrunTrackingUtil() {
	}

	/**
	 * Create a panel to hold the component and its label, and add to the parent
	 * panel.
	 * 
	 * @param iGrandParentPanel
	 *            grandparent panel for new components.
	 * @param iComponent
	 *            component we are ultimately interested in.
	 * @param iLabel
	 *            label for the component.
	 */
	private static void addComponentAndLabel(final JPanel iGrandParentPanel,
			final JComponent iComponent, final JLabel iLabel) {
		final JPanel panel = new JPanel(new GridLayout(1, 0));

		panel.add(iLabel);
		panel.add(iComponent);

		iGrandParentPanel.add(panel);
	}

	/**
	 * Construct a text field and label for some String value and insert into
	 * the panel.
	 * 
	 * @param iParentPanel
	 *            parent panel for new components.
	 * @param iValueName
	 *            name of the String value.
	 * @param iValue
	 *            initial value.
	 * @return text field for the value that is part of the panel.
	 */
	public static JTextField addStringField(final JPanel iParentPanel,
			final String iValueName, final String iValue) {
		final JTextField oField = new JTextField(iValue);

		addComponentAndLabel(iParentPanel, oField, new JLabel(iValueName + ":"));

		return oField;
	}

	/**
	 * Construct a text field and label for some int value and insert into the
	 * panel. Assume minimum of 0 and no maximum.
	 * 
	 * @param iParentPanel
	 *            parent panel for new components.
	 * @param iValueName
	 *            name of the int value.
	 * @param iValue
	 *            initial value.
	 * @return text field for the value that is part of the panel.
	 */
	public static JFormattedTextField addIntField(final JPanel iParentPanel,
			final String iValueName, final int iValue) {
		return addIntField(iParentPanel, iValueName, iValue, 0);
	}

	/**
	 * Construct a text field and label for some int value and insert into the
	 * panel. Assume no maximum.
	 * 
	 * @param iParentPanel
	 *            parent panel for new components.
	 * @param iValueName
	 *            name of the int value.
	 * @param iValue
	 *            initial value.
	 * @param iMinimum
	 *            minimum value.
	 * @return text field for the value that is part of the panel.
	 */
	public static JFormattedTextField addIntField(final JPanel iParentPanel,
			final String iValueName, final int iValue, final Integer iMinimum) {
		return addIntField(iParentPanel, iValueName, iValue, iMinimum, null);
	}

	/**
	 * Construct a text field and label for some int value and insert into the
	 * panel.
	 * 
	 * @param iParentPanel
	 *            parent panel for new components.
	 * @param iValueName
	 *            name of the int value.
	 * @param iValue
	 *            initial value.
	 * @param iMinimum
	 *            minimum value.
	 * @param iMaximum
	 *            maximum value.
	 * @return text field for the value that is part of the panel.
	 */
	public static JFormattedTextField addIntField(final JPanel iParentPanel,
			final String iValueName, final int iValue, final Integer iMinimum,
			final Integer iMaximum) {
		final NumberFormatter numberFormatter = new NumberFormatter(
				NumberFormat.getIntegerInstance());
		numberFormatter.setMinimum(iMinimum);
		numberFormatter.setMaximum(iMaximum);
		final JFormattedTextField oField = new JFormattedTextField();
		oField.setValue(iValue);

		addComponentAndLabel(iParentPanel, oField, new JLabel(iValueName + ":"));

		return oField;
	}

	/**
	 * Add damage note editing buttons to the given panel based on the given
	 * destructible trackable.
	 * 
	 * @param iParentPanel
	 *            parent panel for new components.
	 * @param iTrackableToEdit
	 *            the destructible trackable to edit damage for; likely the
	 *            class calling this function.
	 */
	public static <D extends IShadowrunDamageNote> void addDamageButtons(
			final JPanel iParentPanel,
			final IDestructibleTrackable<D> iTrackableToEdit) {
		for (final D damageNote : iTrackableToEdit.getDamageNotes()) {
			final JButton damageEditButton = new JButton("Edit: "
					+ damageNote.toString());
			damageEditButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(final ActionEvent iEvent) {
					damageNote.edit();
					damageEditButton.setText("Edit: " + damageNote.toString());
				}
			});
			iParentPanel.add(damageEditButton);

			final JButton damageRemoveButton = new JButton("Remove: "
					+ damageNote.toString());
			damageRemoveButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(final ActionEvent iEvent) {
					System.out.println("Removing damage note: "
							+ damageNote.toFullString());

					iTrackableToEdit.removeDamageNote(damageNote);

					iParentPanel.remove(damageEditButton);
					iParentPanel.remove(damageRemoveButton);
				}
			});
			iParentPanel.add(damageRemoveButton);
		}
	}

	/**
	 * Add matrix damage note editing buttons to the given panel based on the
	 * given matrix trackable.
	 * 
	 * @param iParentPanel
	 *            parent panel for new components.
	 * @param iTrackableToEdit
	 *            the matrix trackable to edit damage for; likely the class
	 *            calling this function.
	 */
	public static <D extends IShadowrunDamageNote> void addMatrixDamageButtons(
			final JPanel iParentPanel,
			final IMatrixDamageableTrackable iTrackableToEdit) {
		for (final DeviceMatrixDamageNote damageNote : iTrackableToEdit
				.getMatrixDamageNotes()) {
			final JButton damageEditButton = new JButton("Edit: "
					+ damageNote.toString());
			damageEditButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(final ActionEvent iEvent) {
					damageNote.edit();
					damageEditButton.setText("Edit: " + damageNote.toString());
				}
			});
			iParentPanel.add(damageEditButton);

			final JButton damageRemoveButton = new JButton("Remove: "
					+ damageNote.toString());
			damageRemoveButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(final ActionEvent iEvent) {
					System.out.println("Removing damage note: "
							+ damageNote.toFullString());

					iTrackableToEdit.removeMatrixDamageNote(damageNote);

					iParentPanel.remove(damageEditButton);
					iParentPanel.remove(damageRemoveButton);
				}
			});
			iParentPanel.add(damageRemoveButton);
		}
	}

	/**
	 * Construct a combox and label for some enum value and insert into the
	 * panel.
	 * 
	 * @param iParentPanel
	 *            parent panel for new components.
	 * @param iValueName
	 *            name of the int value.
	 * @param iAllValues
	 *            all possible values.
	 * @param iCurrentValue
	 *            initial value.
	 * @return combox with the enum as options.
	 */
	public static <E extends Enum<E>> JComboBox<E> addEnumComboBox(
			final JPanel iParentPanel, final String iValueName,
			final E[] iAllValues, final E iCurrentValue) {
		final JComboBox<E> oEnumBox = new JComboBox<E>(iAllValues);
		oEnumBox.setSelectedItem(iCurrentValue);

		addComponentAndLabel(iParentPanel, oEnumBox, new JLabel(iValueName
				+ ":"));

		return oEnumBox;
	}

	/**
	 * Examine the text field and determine if a change has been made (and needs
	 * to be applied) or not.
	 * 
	 * @param iTextField
	 *            text field to examine.
	 * @param iName
	 *            name of the value being examined.
	 * @param iWorker
	 *            worker to set value.
	 * @param iProducer
	 *            producer to get value.
	 */
	public static void examineChangedString(final JTextField iTextField,
			final String iName, final IEditStringWorker iWorker,
			final IEditStringProducer iProducer) {
		final String newString = iTextField.getText();

		if (!newString.equals(iProducer.getString())) {
			iWorker.setString(newString);
		} else {
			System.out.println(iName + " unchanged: [" + iProducer.getString()
					+ "]");
		}
	}

	/**
	 * Examine the formatted text field and determine if a change has been made
	 * (and needs to be applied) or not.
	 * 
	 * @param iFormattedTextField
	 *            text field to examine.
	 * @param iName
	 *            name of the value being examined.
	 * @param iWorker
	 *            worker to set value.
	 * @param iProducer
	 *            producer to get value.
	 */
	public static void examineChangedInt(
			final JFormattedTextField iFormattedTextField, final String iName,
			final IEditIntWorker iWorker, final IEditIntProducer iProducer) {
		try {
			iFormattedTextField.commitEdit();
		} catch (final ParseException iException) {
			System.err.println(iException.getMessage());
		}
		final int newValue = Integer.parseInt(iFormattedTextField.getValue()
				.toString());

		if (newValue != iProducer.getInt()) {
			iWorker.setInt(newValue);
		} else {
			System.out.println(iName + " unchanged: [" + iProducer.getInt()
					+ "]");
		}
	}

	/**
	 * Examine the checkbox and determine if a change has been made (and needs
	 * to be applied) or not.
	 * 
	 * @param iCheckBox
	 *            checkbox to examine.
	 * @param iName
	 *            name of the value being examined.
	 * @param iWorker
	 *            worker to set value.
	 * @param iProducer
	 *            producer to get value.
	 */
	public static void examineChangedBoolean(final JCheckBox iCheckBox,
			final String iName, final IEditBoolWorker iWorker,
			final IEditBoolProducer iProducer) {
		final boolean newBool = iCheckBox.isSelected();

		if (newBool != iProducer.getBoolean()) {
			iWorker.setBoolean(newBool);
		} else {
			System.out.println(iName + " unchanged: [" + iProducer.getBoolean()
					+ "]");
		}
	}
}
