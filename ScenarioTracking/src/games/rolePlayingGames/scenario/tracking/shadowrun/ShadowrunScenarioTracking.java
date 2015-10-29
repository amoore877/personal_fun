package games.rolePlayingGames.scenario.tracking.shadowrun;

import games.rolePlayingGames.shadowrun.dice.ShadowrunRollResult;
import games.rolePlayingGames.shadowrun.dice.ShadowrunRoller;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultRowSorter;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.RowSorter;
import javax.swing.RowSorter.SortKey;
import javax.swing.ScrollPaneConstants;
import javax.swing.SortOrder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.NumberFormatter;

/**
 * Tracking a scenario for Shadowrun.
 * 
 * @author Andrew
 */
public class ShadowrunScenarioTracking extends JFrame implements
		ActionListener, WindowListener {

	private static final Color DEFAULT_FOREGROUND_COLOR = Color.WHITE;
	private static final Color DEFAULT_BACKGROUND_COLOR = Color.DARK_GRAY;
	private static final String RESET_TURN_STRING = "Reset Turn";
	private static final int LOWER_PANEL_HEIGHT = 210;
	private static final int LOWER_PANEL_WIDTH = 450;
	private static final int BUTTON_HEIGHT = 25;
	private static final int BUTTON_WIDTH = 100;
	private static final int SMALL_BUTTON_WIDTH = 70;
	private static final int MAIN_PANEL_HEIGHT = 650;
	private static final int FRAME_HEIGHT = 700;
	private static final int FRAME_WIDTH = 1000;
	private static final String DELIMITER = "#";
	private static final String STORY_NOTE_STRING = "STORY";
	private static final String COMBAT_NOTE_STRING = "COMBAT";
	private static final String TRACE_NOTE_STRING = "TRACE";
	private static final String EXIT_STRING = "Exit";
	private static final String SAVE_STRING = "Save";
	private static final String ADD_MEMO_STRING = "Add Memo";
	private static final String ROLL_STRING = "Roll";
	private static final String USE_EDGE_STRING = "Use Edge?";
	private static final String CLEANUP_STRING = "Cleanup";
	private static final String REMOVE_STRING = "Remove";
	private static final String ADD_STRING = "Add";
	private static final String RESORT_STRING = "Resort";
	private static final String NEXT_TURN_STRING = "Next Turn";
	private static final String NEXT_PASS_STRING = "Next Pass";
	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -3278488966357839811L;

	/**
	 * Text field to input dice to roll.
	 */
	private final JFormattedTextField diceToRollField;

	/**
	 * Check box to use edge or not on the roll.
	 */
	private final JCheckBox edgeCheckbox;

	/**
	 * Field to show hits result on roll.
	 */
	private final JTextField hitsField;

	/**
	 * Field to show sum of result on roll.
	 */
	private final JTextField rollTotalField;

	/**
	 * Field to input one-line memos.
	 */
	private final JTextField memoField;

	/**
	 * Check box for whether memo is for combat or not.
	 */
	private final JCheckBox combatMemoCheckbox;

	/**
	 * Table for tracking data.
	 */
	private final JTable trackingTable;

	/**
	 * Table model for tracking data.
	 */
	private final ShadowrunScenarioTrackingTableModel trackingTableModel = new ShadowrunScenarioTrackingTableModel();

	/**
	 * Text area for all memos.
	 */
	private final JTextArea memoTextArea;

	/**
	 * Current initiative pass.
	 */
	private int myInitiativePass = 1;

	/**
	 * Current combat turn.
	 */
	private int myCombatTurn = 1;

	public ShadowrunScenarioTracking() {
		// TODO take name of scenario

		// TODO check if files exist for this scenario

		// TODO if files exist for this scenario, offer to load or overwrite

		// TODO load

		// TODO overwrite/files did not exist

		// TODO double-check the files exist now

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(this);

		getContentPane().setBackground(DEFAULT_BACKGROUND_COLOR);
		getContentPane().setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		getContentPane().setPreferredSize(
				new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		getContentPane().setMinimumSize(
				new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		getContentPane().setMaximumSize(
				new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setTitle("Shadowrun Scenario Tracking");
		setResizable(false);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		final JPanel mainPanel = new JPanel();
		mainPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
		mainPanel.setMinimumSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainPanel.setMaximumSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainPanel
				.setPreferredSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainPanel.setSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		final JSplitPane mainSplitPane = new JSplitPane();
		mainSplitPane.setMaximumSize(new Dimension(FRAME_WIDTH,
				MAIN_PANEL_HEIGHT));
		mainSplitPane.setMinimumSize(new Dimension(FRAME_WIDTH,
				MAIN_PANEL_HEIGHT));
		mainSplitPane.setPreferredSize(new Dimension(FRAME_WIDTH,
				MAIN_PANEL_HEIGHT));
		mainSplitPane.setSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainSplitPane.setBackground(Color.BLACK);
		mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainPanel.add(mainSplitPane);

		final JPanel controlPanel = new JPanel();
		controlPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
		controlPanel.setSize(new Dimension(FRAME_WIDTH, LOWER_PANEL_HEIGHT));
		controlPanel.setPreferredSize(new Dimension(FRAME_WIDTH,
				LOWER_PANEL_HEIGHT));
		controlPanel.setMinimumSize(new Dimension(FRAME_WIDTH,
				LOWER_PANEL_HEIGHT));
		controlPanel.setMaximumSize(new Dimension(FRAME_WIDTH,
				LOWER_PANEL_HEIGHT));
		mainSplitPane.setRightComponent(controlPanel);
		controlPanel.setLayout(null);

		final JPanel tableControlPanel = new JPanel();
		tableControlPanel.setLocation(0, 0);
		tableControlPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
		tableControlPanel.setSize(new Dimension(LOWER_PANEL_WIDTH,
				LOWER_PANEL_HEIGHT));
		tableControlPanel.setPreferredSize(new Dimension(LOWER_PANEL_WIDTH,
				LOWER_PANEL_HEIGHT));
		tableControlPanel.setMinimumSize(new Dimension(LOWER_PANEL_WIDTH,
				LOWER_PANEL_HEIGHT));
		tableControlPanel.setMaximumSize(new Dimension(LOWER_PANEL_WIDTH,
				LOWER_PANEL_HEIGHT));
		controlPanel.add(tableControlPanel);
		tableControlPanel.setLayout(null);

		final JButton nextPassButton = new JButton(NEXT_PASS_STRING);
		nextPassButton.addActionListener(this);
		nextPassButton
				.setToolTipText("Subtracts 10 from everyone's initiative and unchecks \"Acted\" flag for all. Increments initiative pass.");
		nextPassButton
				.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextPassButton
				.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextPassButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextPassButton.setPreferredSize(new Dimension(BUTTON_WIDTH,
				BUTTON_HEIGHT));
		nextPassButton.setBounds(5, 5, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(nextPassButton);

		final JButton nextTurnButton = new JButton(NEXT_TURN_STRING);
		nextTurnButton.addActionListener(this);
		nextTurnButton
				.setToolTipText("Sets all initiatives to 0 and unchecks \"Acted\" flag for all. Sets initiative pass to 1.");
		nextTurnButton
				.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextTurnButton
				.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextTurnButton.setPreferredSize(new Dimension(BUTTON_WIDTH,
				BUTTON_HEIGHT));
		nextTurnButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextTurnButton.setBounds(184, 5, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(nextTurnButton);

		final JButton resortButton = new JButton(RESORT_STRING);
		resortButton.addActionListener(this);
		resortButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resortButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resortButton
				.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resortButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resortButton.setToolTipText("Resorts table by initiative score.");
		resortButton.setBounds(340, 45, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(resortButton);

		final JButton addActorButton = new JButton(ADD_STRING);
		addActorButton.addActionListener(this);
		addActorButton
				.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addActorButton
				.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addActorButton.setPreferredSize(new Dimension(BUTTON_WIDTH,
				BUTTON_HEIGHT));
		addActorButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addActorButton
				.setToolTipText("Add some Actor(s) by name to the table, setting initiative to 0.");
		addActorButton.setBounds(5, 45, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(addActorButton);

		final JButton removeActorButton = new JButton(REMOVE_STRING);
		removeActorButton.setEnabled(false);
		removeActorButton.addActionListener(this);
		removeActorButton.setMaximumSize(new Dimension(BUTTON_WIDTH,
				BUTTON_HEIGHT));
		removeActorButton.setMinimumSize(new Dimension(BUTTON_WIDTH,
				BUTTON_HEIGHT));
		removeActorButton.setPreferredSize(new Dimension(BUTTON_WIDTH,
				BUTTON_HEIGHT));
		removeActorButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		removeActorButton
				.setToolTipText("Remove the selected actor(s) from the table.");
		removeActorButton.setBounds(184, 45, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(removeActorButton);

		final JButton cleanupButton = new JButton(CLEANUP_STRING);
		cleanupButton.addActionListener(this);
		cleanupButton
				.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		cleanupButton
				.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		cleanupButton.setPreferredSize(new Dimension(BUTTON_WIDTH,
				BUTTON_HEIGHT));
		cleanupButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		cleanupButton.setToolTipText("Remove all dead actors from the table.");
		cleanupButton.setBounds(340, 81, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(cleanupButton);

		final JLabel diceToRollLabel = new JLabel("Dice:");
		diceToRollLabel.setForeground(DEFAULT_FOREGROUND_COLOR);
		diceToRollLabel.setBackground(DEFAULT_BACKGROUND_COLOR);
		diceToRollLabel.setBounds(5, 122, 33, 14);
		tableControlPanel.add(diceToRollLabel);

		final NumberFormatter diceToRollNumberFormatter = new NumberFormatter();
		diceToRollNumberFormatter.setMinimum(1);
		diceToRollField = new JFormattedTextField(diceToRollNumberFormatter);
		diceToRollField.setToolTipText("Number of dice to roll.");
		diceToRollField.setText("1");
		diceToRollField.setBounds(48, 119, 46, 20);
		tableControlPanel.add(diceToRollField);

		edgeCheckbox = new JCheckBox(USE_EDGE_STRING);
		edgeCheckbox.setBackground(DEFAULT_BACKGROUND_COLOR);
		edgeCheckbox.setForeground(DEFAULT_FOREGROUND_COLOR);
		edgeCheckbox.setToolTipText("Use edge on the roll?");
		edgeCheckbox.setBounds(96, 118, 89, 23);
		tableControlPanel.add(edgeCheckbox);

		final JLabel hitsLabel = new JLabel("Hits:");
		hitsLabel.setBackground(DEFAULT_BACKGROUND_COLOR);
		hitsLabel.setForeground(DEFAULT_FOREGROUND_COLOR);
		hitsLabel.setBounds(5, 150, 33, 14);
		tableControlPanel.add(hitsLabel);

		hitsField = new JTextField();
		hitsField.setToolTipText("Number of hits on the roll.");
		hitsField.setEditable(false);
		hitsField.setBounds(48, 147, 123, 20);
		tableControlPanel.add(hitsField);
		hitsField.setColumns(30);

		final JButton rollButton = new JButton(ROLL_STRING);
		rollButton.addActionListener(this);
		rollButton.setMaximumSize(new Dimension(SMALL_BUTTON_WIDTH,
				BUTTON_HEIGHT));
		rollButton.setMinimumSize(new Dimension(SMALL_BUTTON_WIDTH,
				BUTTON_HEIGHT));
		rollButton.setPreferredSize(new Dimension(SMALL_BUTTON_WIDTH,
				BUTTON_HEIGHT));
		rollButton.setSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		rollButton.setToolTipText("Roll dice with the given parameters.");
		rollButton.setBounds(191, 117, SMALL_BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(rollButton);

		final JLabel rollTotalLabel = new JLabel("Total:");
		rollTotalLabel.setForeground(DEFAULT_FOREGROUND_COLOR);
		rollTotalLabel.setBackground(DEFAULT_BACKGROUND_COLOR);
		rollTotalLabel.setBounds(181, 151, 47, 14);
		tableControlPanel.add(rollTotalLabel);

		rollTotalField = new JTextField();
		rollTotalField.setToolTipText("Total of the roll.");
		rollTotalField.setEditable(false);
		rollTotalField.setBounds(238, 147, 46, 20);
		tableControlPanel.add(rollTotalField);
		rollTotalField.setColumns(10);

		final JButton resetTurnButton = new JButton(RESET_TURN_STRING);
		resetTurnButton.setBounds(340, 5, 100, 25);
		tableControlPanel.add(resetTurnButton);
		resetTurnButton
				.setToolTipText("Reset the Combat Turn and Initiative Pass counters.");
		resetTurnButton.addActionListener(this);

		final JPanel memoPanel = new JPanel();
		memoPanel.setLocation(LOWER_PANEL_WIDTH, 0);
		memoPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
		memoPanel.setSize(new Dimension(LOWER_PANEL_WIDTH, LOWER_PANEL_HEIGHT));
		memoPanel.setPreferredSize(new Dimension(LOWER_PANEL_WIDTH,
				LOWER_PANEL_HEIGHT));
		memoPanel.setMinimumSize(new Dimension(LOWER_PANEL_WIDTH,
				LOWER_PANEL_HEIGHT));
		memoPanel.setMaximumSize(new Dimension(LOWER_PANEL_WIDTH,
				LOWER_PANEL_HEIGHT));
		controlPanel.add(memoPanel);
		memoPanel.setLayout(null);

		final JLabel memoLabel = new JLabel("Memo:");
		memoLabel.setForeground(DEFAULT_FOREGROUND_COLOR);
		memoLabel.setBackground(DEFAULT_BACKGROUND_COLOR);
		memoLabel.setBounds(10, 11, 46, 14);
		memoPanel.add(memoLabel);

		memoField = new JTextField();
		memoField.setToolTipText("Type memo here.");
		memoField.setBounds(10, 36, 430, 20);
		memoPanel.add(memoField);
		memoField.setColumns(10);

		final JButton addMemoButton = new JButton(ADD_MEMO_STRING);
		addMemoButton.setEnabled(false);
		addMemoButton.addActionListener(this);
		addMemoButton
				.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addMemoButton
				.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addMemoButton.setPreferredSize(new Dimension(BUTTON_WIDTH,
				BUTTON_HEIGHT));
		addMemoButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addMemoButton.setBounds(10, 67, 89, 23);
		memoPanel.add(addMemoButton);

		combatMemoCheckbox = new JCheckBox("Combat Memo?");
		combatMemoCheckbox.setForeground(DEFAULT_FOREGROUND_COLOR);
		combatMemoCheckbox.setBackground(DEFAULT_BACKGROUND_COLOR);
		combatMemoCheckbox.setBounds(105, 67, 120, 23);
		memoPanel.add(combatMemoCheckbox);

		memoField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(final DocumentEvent e) {
				checkContents();
			}

			@Override
			public void insertUpdate(final DocumentEvent e) {
				checkContents();
			}

			@Override
			public void changedUpdate(final DocumentEvent e) {
				checkContents();
			}

			/**
			 * Check contents of memo field. If empty, disable Add Memo button.
			 * Else, enable Add Memo button.
			 */
			private void checkContents() {
				if (memoField.getText().isEmpty()) {
					addMemoButton.setEnabled(false);
				} else {
					addMemoButton.setEnabled(true);
				}
			}
		});

		final JPanel filePanel = new JPanel();
		filePanel.setBounds(2 * LOWER_PANEL_WIDTH, 0, 100, LOWER_PANEL_HEIGHT);
		filePanel.setBackground(DEFAULT_BACKGROUND_COLOR);
		controlPanel.add(filePanel);

		final JButton saveButton = new JButton(SAVE_STRING);
		saveButton.setLocation(10, 11);
		saveButton.addActionListener(this);
		filePanel.setLayout(null);
		saveButton.setMaximumSize(new Dimension(SMALL_BUTTON_WIDTH,
				BUTTON_HEIGHT));
		saveButton.setMinimumSize(new Dimension(SMALL_BUTTON_WIDTH,
				BUTTON_HEIGHT));
		saveButton.setPreferredSize(new Dimension(SMALL_BUTTON_WIDTH,
				BUTTON_HEIGHT));
		saveButton.setSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		filePanel.add(saveButton);

		final JButton exitButton = new JButton(EXIT_STRING);
		exitButton.setLocation(10, 157);
		exitButton.addActionListener(this);
		exitButton.setMaximumSize(new Dimension(SMALL_BUTTON_WIDTH,
				BUTTON_HEIGHT));
		exitButton.setMinimumSize(new Dimension(SMALL_BUTTON_WIDTH,
				BUTTON_HEIGHT));
		exitButton.setPreferredSize(new Dimension(SMALL_BUTTON_WIDTH,
				BUTTON_HEIGHT));
		exitButton.setSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		filePanel.add(exitButton);

		final JSplitPane tableAndMemoSplitPane = new JSplitPane();
		tableAndMemoSplitPane.setOneTouchExpandable(true);
		tableAndMemoSplitPane.setForeground(DEFAULT_BACKGROUND_COLOR);
		tableAndMemoSplitPane.setBackground(DEFAULT_BACKGROUND_COLOR);
		mainSplitPane.setLeftComponent(tableAndMemoSplitPane);

		final JScrollPane tableScrollPane = new JScrollPane();
		tableAndMemoSplitPane.setLeftComponent(tableScrollPane);
		tableScrollPane.setForeground(DEFAULT_BACKGROUND_COLOR);
		tableScrollPane.setBackground(DEFAULT_BACKGROUND_COLOR);

		trackingTable = new JTable();

		// table model

		trackingTable.setModel(trackingTableModel);
		// table selection
		trackingTable.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					// enable/disable remove button on table selection
					@Override
					public void valueChanged(final ListSelectionEvent e) {
						if (trackingTable.getSelectedRows().length != 0) {
							removeActorButton.setEnabled(true);
						} else {
							removeActorButton.setEnabled(false);
						}
					}
				});

		// sorter
		trackingTable.setAutoCreateRowSorter(true);

		// combox for status enum
		final JComboBox<ShadowrunCharacterStatus> statusCombox = new JComboBox<ShadowrunCharacterStatus>(
				ShadowrunCharacterStatus.values());
		trackingTable
				.getColumnModel()
				.getColumn(ShadowrunScenarioTrackingTableModel.STATUS_COL_INDEX)
				.setCellEditor(new DefaultCellEditor(statusCombox));

		// on cell edit, repaint
		trackingTableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(final TableModelEvent e) {
				repaint();
			}
		});

		// renderer
		trackingTable.setDefaultRenderer(Object.class,
				new ShadowrunScenarioTrackingTableCellRenderer());
		trackingTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// column widths
		trackingTable.getColumnModel()
				.getColumn(ShadowrunScenarioTrackingTableModel.ACTED_COL_INDEX)
				.setPreferredWidth(15);
		trackingTable
				.getColumnModel()
				.getColumn(
						ShadowrunScenarioTrackingTableModel.INITIATIVE_COL_INDEX)
				.setPreferredWidth(25);
		trackingTable.getColumnModel()
				.getColumn(ShadowrunScenarioTrackingTableModel.NAME_COL_INDEX)
				.setPreferredWidth(30);
		trackingTable
				.getColumnModel()
				.getColumn(ShadowrunScenarioTrackingTableModel.STATUS_COL_INDEX)
				.setPreferredWidth(25);

		// add table to scroll pane
		tableScrollPane.setViewportView(trackingTable);

		final JScrollPane memoScrollPane = new JScrollPane();
		memoScrollPane.setAutoscrolls(true);
		memoScrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableAndMemoSplitPane.setRightComponent(memoScrollPane);

		memoTextArea = new JTextArea();
		memoTextArea.setForeground(DEFAULT_FOREGROUND_COLOR);
		memoTextArea.setBackground(DEFAULT_BACKGROUND_COLOR);
		memoTextArea.setToolTipText("List of memos for the scenario");
		memoScrollPane.setViewportView(memoTextArea);
		tableAndMemoSplitPane.setDividerLocation(FRAME_WIDTH - 5);
		mainSplitPane.setDividerLocation(0.7);

		pack();
		setVisible(true);
	}

	@Override
	public void actionPerformed(final ActionEvent iEvent) {
		if (iEvent.getActionCommand().equals(ADD_MEMO_STRING)) {
			addMemo();
		} else if (iEvent.getActionCommand().equals(ADD_STRING)) {
			// add character(s)
			addCharacters();
		} else if (iEvent.getActionCommand().equals(CLEANUP_STRING)) {
			// TODO cleanup dead characters
		} else if (iEvent.getActionCommand().equals(EXIT_STRING)) {
			// exit program
			confirmExit();
		} else if (iEvent.getActionCommand().equals(NEXT_PASS_STRING)) {
			// next initiative pass
			nextPass();
		} else if (iEvent.getActionCommand().equals(NEXT_TURN_STRING)) {
			// next combat turn
			nextTurn();
		} else if (iEvent.getActionCommand().equals(REMOVE_STRING)) {
			// remove selected character(s)
			removeCharacters();
		} else if (iEvent.getActionCommand().equals(RESET_TURN_STRING)) {
			// reset turn/pass counters
			resetTurn();
		} else if (iEvent.getActionCommand().equals(RESORT_STRING)) {
			// resort the table by initiative
			resort();
		} else if (iEvent.getActionCommand().equals(ROLL_STRING)) {
			// roll dice
			rollDice();
		} else if (iEvent.getActionCommand().equals(SAVE_STRING)) {
			// save
			save();
		} else {
			System.err.println("Unknown action");
		}
	}

	/**
	 * Add character(s) to table.
	 */
	private void addCharacters() {
		// add character(s)
		final String name = JOptionPane.showInputDialog(this,
				"Name for new character(s)?", "Add Character(s)",
				JOptionPane.QUESTION_MESSAGE);
		if ((name != null) && !(name.isEmpty())) {
			final String numOfCharactersString = JOptionPane.showInputDialog(
					this, "Number of characters to add (1-10)?", 1);

			if ((numOfCharactersString != null)
					&& !(numOfCharactersString.isEmpty())) {
				try {
					final int numOfCharacters = Integer
							.parseInt(numOfCharactersString);

					if (numOfCharacters < 1) {
						showError(new Exception(
								"Bad input for number of new characters: ["
										+ numOfCharacters + "]. Too small."));
					} else if (numOfCharacters > 10) {
						showError(new Exception(
								"Bad input for number of new characters: ["
										+ numOfCharacters + "]. Too big."));
					} else {
						if (numOfCharacters > 1) {
							for (int currCharacter = 1; currCharacter <= numOfCharacters; currCharacter++) {
								trackingTableModel.addCharacter(name + " "
										+ currCharacter);
							}
						} else {
							trackingTableModel.addCharacter(name);
						}
					}
				} catch (final NumberFormatException iException) {
					showError(iException);
				}
			} else {
				showError(new Exception(
						"Bad input for number of new characters"));
			}
		} else {
			showError(new Exception("Bad name input for new character"));
		}
	}

	/**
	 * Reset combat turn/pass.
	 */
	private void resetTurn() {
		// confirm
		final int result = JOptionPane.showConfirmDialog(this,
				"Reset combat turns and passes?", "Reset combat?",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// reset all acted flags
			resetActedFlags();

			// reset all initiatives to 0
			trackingTableModel.resetInitiative();

			// reset initiative pass
			myInitiativePass = 1;

			// reset combat turn
			myCombatTurn = 1;

			// add memo
			appendMemo("Reset combat", true);
		}
	}

	/**
	 * Next combat turn.
	 */
	private void nextTurn() {
		// confirm
		final int result = JOptionPane.showConfirmDialog(this,
				"Go to next combat turn?", "Next turn?",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// reset all acted flags
			resetActedFlags();

			// reset all initiatives to 0
			trackingTableModel.resetInitiative();

			// reset initiative pass
			myInitiativePass = 1;

			// increment combat turn
			myCombatTurn++;

			// add memo
			appendMemo("New combat turn", true);
		}
	}

	/**
	 * Next initiative pass.
	 */
	private void nextPass() {
		// confirm
		final int result = JOptionPane.showConfirmDialog(this,
				"Go to next initiative pass?", "Next pass?",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// reset all acted flags
			resetActedFlags();

			// decrease all initiatives by 10
			trackingTableModel.nextInitiativePass();

			// increment initiative pass
			myInitiativePass++;

			// add memo
			appendMemo("New initiative pass", true);
		}
	}

	/**
	 * Reset all acted flags to false.
	 */
	private void resetActedFlags() {
		// TODO reset all acted flags
	}

	/**
	 * Add a memo.
	 */
	private void addMemo() {
		// add memo
		final String memoText = memoField.getText();

		final boolean isCombatMemo = combatMemoCheckbox.isSelected();

		appendMemo(memoText, isCombatMemo);
	}

	/**
	 * Roll dice.
	 */
	private void rollDice() {
		try {
			final int diceToRoll = Integer.valueOf(diceToRollField.getText());

			final boolean useEdge = edgeCheckbox.isSelected();

			final ShadowrunRollResult rollResult = new ShadowrunRollResult(
					ShadowrunRoller.rollDice(diceToRoll, useEdge));

			rollTotalField.setText(String.valueOf(rollResult.getSum()));

			if (rollResult.isCriticalGlitch()) {
				hitsField.setText("Critical Glitch!");
			} else {
				final StringBuilder hitsText = new StringBuilder(
						String.valueOf(rollResult.getHits()));

				if (rollResult.isGlitch()) {
					hitsText.append(" Glitch!");
				}

				hitsField.setText(hitsText.toString());
			}

		} catch (final NumberFormatException iException) {
			showError(iException);
		}
	}

	/**
	 * Removal of characters.
	 */
	private void removeCharacters() {
		final int[] selectedRowsInt = trackingTable.getSelectedRows();

		final Integer[] selectedRowsInteger = new Integer[selectedRowsInt.length];
		int index = 0;
		for (final int currRow : selectedRowsInt) {
			selectedRowsInteger[index++] = currRow;
		}

		// sort highest index to lowest (to avoid issues in removing rows)
		Arrays.sort(selectedRowsInteger, new Comparator<Integer>() {

			@Override
			public int compare(final Integer o1, final Integer o2) {
				return o2 - o1;
			}
		});

		if (selectedRowsInteger.length != 0) {
			final StringBuilder message = new StringBuilder(
					"Are you sure you wish to remove the following characters:");

			for (final int currRow : selectedRowsInteger) {
				try {
					message.append("\n"
							+ trackingTableModel
									.getValueAt(
											currRow,
											ShadowrunScenarioTrackingTableModel.NAME_COL_INDEX)
									.toString());

				} catch (final ArrayIndexOutOfBoundsException iException) {
					showError(iException);
				}
			}

			final int result = JOptionPane.showConfirmDialog(this,
					message.toString(), "Removal Confirmation",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				for (final int currRow : selectedRowsInteger) {
					try {
						trackingTableModel.removeRow(currRow);
					} catch (final ArrayIndexOutOfBoundsException iException) {
						showError(iException);
					}
				}
			}
		} else {
			System.err.println("No selected rows");
		}
	}

	/**
	 * Sort the table. Sorts by initiative, descending.
	 */
	private void resort() {
		// TODO clean up code
		final DefaultRowSorter<?, ?> sorter = (DefaultRowSorter<?, ?>) trackingTable
				.getRowSorter();
		final List<SortKey> trackingTableSortKeyList = new ArrayList<SortKey>();
		trackingTableSortKeyList.add(new RowSorter.SortKey(
				ShadowrunScenarioTrackingTableModel.INITIATIVE_COL_INDEX,
				SortOrder.DESCENDING));
		sorter.setSortKeys(trackingTableSortKeyList);
		sorter.sort();
	}

	/**
	 * Save function.
	 */
	private void save() {
		// TODO save
	}

	/**
	 * Append a memo.
	 * 
	 * @param iMemoText
	 *            text.
	 * @param iIsCombatMemo
	 *            whether or not the memo is related to combat.
	 */
	private void appendMemo(final String iMemoText, final boolean iIsCombatMemo) {
		String textToAdd;
		if (iIsCombatMemo) {
			textToAdd = "\n" + COMBAT_NOTE_STRING + DELIMITER + "TURN"
					+ myCombatTurn + "PASS" + myInitiativePass + " "
					+ iMemoText;
		} else {
			textToAdd = "\n" + STORY_NOTE_STRING + DELIMITER + " " + iMemoText;
		}

		memoTextArea.append(textToAdd);
	}

	/**
	 * Main.
	 * 
	 * @param args
	 *            arguments.
	 */
	public static void main(final String[] args) {
		// TODO need name of scenario
		new ShadowrunScenarioTracking();
	}

	/**
	 * Show error message.
	 * 
	 * @param iException
	 *            exception that appeared.
	 */
	private void showError(final Exception iException) {
		// show error message, any exception that is caught
		JOptionPane.showMessageDialog(this, iException.getMessage(), "Error!",
				JOptionPane.ERROR_MESSAGE);
		iException.printStackTrace();
	}

	@Override
	public void windowOpened(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public void windowClosing(final WindowEvent e) {
		confirmExit();
	}

	/**
	 * Confirm exit.
	 */
	private void confirmExit() {
		final int reply = JOptionPane.showConfirmDialog(this,
				"Are you sure you wish to exit?", EXIT_STRING,
				JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {
			// window is being closed; save information
			save();
			this.dispose();
			System.exit(0);
		}
	}

	/**
	 * Override dispose. Close buffers.
	 */
	@Override
	public void dispose() {
		// TODO close buffers
		super.dispose();
	}

	@Override
	public void windowClosed(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public void windowIconified(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public void windowDeiconified(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public void windowActivated(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public void windowDeactivated(final WindowEvent e) {
		// unimplemented
	}
}
