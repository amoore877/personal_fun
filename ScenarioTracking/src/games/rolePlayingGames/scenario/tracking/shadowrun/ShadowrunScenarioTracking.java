package games.rolePlayingGames.scenario.tracking.shadowrun;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;

import javax.swing.DefaultCellEditor;
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
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.text.NumberFormatter;

import games.rolePlayingGames.scenario.tracking.AbstractScenarioTracking;
import games.rolePlayingGames.scenario.tracking.AbstractScenarioTrackingTableModel;
import games.rolePlayingGames.scenario.tracking.CharacterStatus;
import games.rolePlayingGames.shadowrun.dice.ShadowrunInitiativeRoller;
import games.rolePlayingGames.shadowrun.dice.ShadowrunRollResult;
import games.rolePlayingGames.shadowrun.dice.ShadowrunRoller;

/**
 * Tracking a scenario for Shadowrun.
 * 
 * @author Andrew
 */
public final class ShadowrunScenarioTracking extends AbstractScenarioTracking {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -3278488966357839811L;

	/**
	 * Height of control panels in lower section.
	 */
	private static final int LOWER_PANEL_HEIGHT = 210;

	/**
	 * Width of control panels in lower section.
	 */
	private static final int LOWER_PANEL_WIDTH = 550;

	/**
	 * Regular button height.
	 */
	private static final int BUTTON_HEIGHT = 25;

	/**
	 * Regular button width.
	 */
	private static final int BUTTON_WIDTH = 150;

	/**
	 * Small button width.
	 */
	private static final int SMALL_BUTTON_WIDTH = 70;

	/**
	 * Main panel height.
	 */
	private static final int MAIN_PANEL_HEIGHT = 650;

	/**
	 * Frame height.
	 */
	private static final int FRAME_HEIGHT = 700;

	/**
	 * Frame width.
	 */
	private static final int FRAME_WIDTH = 1200;

	/**
	 * Default foreground for much of the GUI.
	 */
	private static final Color DEFAULT_FOREGROUND_COLOR = Color.WHITE;

	/**
	 * Default background for much of the GUI.
	 */
	private static final Color DEFAULT_BACKGROUND_COLOR = Color.DARK_GRAY;

	/**
	 * Use edge string.
	 */
	private static final String USE_EDGE_STRING = "Use Edge?";

	/**
	 * Next turn string.
	 */
	private static final String NEXT_TURN_STRING = "Next Turn";

	/**
	 * Next pass string.
	 */
	private static final String NEXT_PASS_STRING = "Next Pass";

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
	 * Table model for tracking data.
	 */
	private final ShadowrunScenarioTrackingTableModel trackingTableModel;

	/**
	 * Current initiative pass.
	 */
	private int myInitiativePass = 1;

	/**
	 * Current combat turn.
	 */
	private int myCombatTurn = 1;

	/**
	 * Text field to display turn/pass info.
	 */
	private final JTextField myTurnInfoTextField;

	/**
	 * Formatter for initiative dice field.
	 */
	private static final NumberFormatter INITIATIVE_DICE_NUMBER_FORMATTER = new NumberFormatter(
			NumberFormat.getIntegerInstance());
	static {
		INITIATIVE_DICE_NUMBER_FORMATTER.setMinimum(1);
		INITIATIVE_DICE_NUMBER_FORMATTER.setMaximum(5);
	}

	/**
	 * Formatter for initiative base field.
	 */
	private static final NumberFormatter INITIATIVE_BASE_FORMATTER = new NumberFormatter(
			NumberFormat.getIntegerInstance());

	/**
	 * Constructor.
	 * 
	 * @param iScenarioName
	 *            name for scenario.
	 */
	public ShadowrunScenarioTracking(final String iScenarioName) {
		super(iScenarioName);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(this);

		getContentPane().setBackground(DEFAULT_BACKGROUND_COLOR);
		getContentPane().setSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		getContentPane().setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		getContentPane().setMinimumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		getContentPane().setMaximumSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
		setTitle("Shadowrun Scenario Tracking");
		setResizable(false);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		trackingTableModel = new ShadowrunScenarioTrackingTableModel();

		final JPanel mainPanel = new JPanel();
		mainPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
		mainPanel.setMinimumSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainPanel.setMaximumSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainPanel.setPreferredSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainPanel.setSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		final JSplitPane mainSplitPane = new JSplitPane();
		mainSplitPane.setMaximumSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainSplitPane.setMinimumSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainSplitPane.setPreferredSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainSplitPane.setSize(new Dimension(FRAME_WIDTH, MAIN_PANEL_HEIGHT));
		mainSplitPane.setBackground(Color.BLACK);
		mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainPanel.add(mainSplitPane);

		final JPanel controlPanel = new JPanel();
		controlPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
		controlPanel.setSize(new Dimension(FRAME_WIDTH, LOWER_PANEL_HEIGHT));
		controlPanel.setPreferredSize(new Dimension(FRAME_WIDTH, LOWER_PANEL_HEIGHT));
		controlPanel.setMinimumSize(new Dimension(FRAME_WIDTH, LOWER_PANEL_HEIGHT));
		controlPanel.setMaximumSize(new Dimension(FRAME_WIDTH, LOWER_PANEL_HEIGHT));
		mainSplitPane.setRightComponent(controlPanel);
		controlPanel.setLayout(null);

		final JPanel tableControlPanel = new JPanel();
		tableControlPanel.setLocation(0, 0);
		tableControlPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
		tableControlPanel.setSize(new Dimension(LOWER_PANEL_WIDTH, LOWER_PANEL_HEIGHT));
		tableControlPanel.setPreferredSize(new Dimension(LOWER_PANEL_WIDTH, LOWER_PANEL_HEIGHT));
		tableControlPanel.setMinimumSize(new Dimension(LOWER_PANEL_WIDTH, LOWER_PANEL_HEIGHT));
		tableControlPanel.setMaximumSize(new Dimension(LOWER_PANEL_WIDTH, LOWER_PANEL_HEIGHT));
		controlPanel.add(tableControlPanel);
		tableControlPanel.setLayout(null);

		final JButton nextPassButton = new JButton(NEXT_PASS_STRING);
		nextPassButton.addActionListener(this);
		nextPassButton.setToolTipText(
				"Subtracts 10 from everyone's initiative and unchecks \"Acted\" flag for all. Increments initiative pass.");
		nextPassButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextPassButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextPassButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextPassButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextPassButton.setBounds(5, 5, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(nextPassButton);

		final JButton nextTurnButton = new JButton(NEXT_TURN_STRING);
		nextTurnButton.addActionListener(this);
		nextTurnButton.setToolTipText(
				"Sets all initiatives to 0 and unchecks \"Acted\" flag for all. Sets initiative pass to 1.");
		nextTurnButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextTurnButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextTurnButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextTurnButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		nextTurnButton.setBounds(191, 5, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(nextTurnButton);

		final JButton resortButton = new JButton(RESORT_STRING);
		resortButton.addActionListener(this);
		resortButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resortButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resortButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resortButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resortButton.setToolTipText("Resorts table by initiative score.");
		resortButton.setBounds(390, 45, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(resortButton);

		final JButton addActorButton = new JButton(ADD_STRING);
		addActorButton.addActionListener(this);
		addActorButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addActorButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addActorButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addActorButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addActorButton.setToolTipText("Add some Actor(s) by name to the table, setting initiative to 0.");
		addActorButton.setBounds(5, 45, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(addActorButton);

		final JButton removeActorButton = new JButton(REMOVE_STRING);
		removeActorButton.setEnabled(false);
		removeActorButton.addActionListener(this);
		removeActorButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		removeActorButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		removeActorButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		removeActorButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		removeActorButton.setToolTipText("Remove the selected actor(s) from the table.");
		removeActorButton.setBounds(191, 45, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(removeActorButton);

		final JButton clearSelectionButton = new JButton(CLEAR_SELECTION_STRING);
		clearSelectionButton.addActionListener(this);
		clearSelectionButton.setMaximumSize(new Dimension(BUTTON_WIDTH * 2, BUTTON_HEIGHT));
		clearSelectionButton.setMinimumSize(new Dimension(BUTTON_WIDTH * 2, BUTTON_HEIGHT));
		clearSelectionButton.setPreferredSize(new Dimension(BUTTON_WIDTH * 2, BUTTON_HEIGHT));
		clearSelectionButton.setSize(new Dimension(BUTTON_WIDTH * 2, BUTTON_HEIGHT));
		clearSelectionButton.setToolTipText("Clear table selection(s).");
		clearSelectionButton.setBounds(5, 81, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(clearSelectionButton);

		final JButton rollInitiativeButton = new JButton(ROLL_INITIATIVE_STRING);
		rollInitiativeButton.addActionListener(this);
		rollInitiativeButton.setMaximumSize(new Dimension(BUTTON_WIDTH * 2, BUTTON_HEIGHT));
		rollInitiativeButton.setMinimumSize(new Dimension(BUTTON_WIDTH * 2, BUTTON_HEIGHT));
		rollInitiativeButton.setPreferredSize(new Dimension(BUTTON_WIDTH * 2, BUTTON_HEIGHT));
		rollInitiativeButton.setSize(new Dimension(BUTTON_WIDTH * 2, BUTTON_HEIGHT));
		rollInitiativeButton.setToolTipText("Roll Initiative For Selected Character(s).");
		rollInitiativeButton.setBounds(191, 81, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(rollInitiativeButton);
		rollInitiativeButton.setEnabled(false);

		final JButton cleanupButton = new JButton(CLEANUP_STRING);
		cleanupButton.addActionListener(this);
		cleanupButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		cleanupButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		cleanupButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		cleanupButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		cleanupButton.setToolTipText("Remove all dead actors from the table.");
		cleanupButton.setBounds(390, 81, BUTTON_WIDTH, BUTTON_HEIGHT);
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
		hitsField.setBounds(48, 147, 92, 20);
		tableControlPanel.add(hitsField);
		hitsField.setColumns(30);

		final JButton rollButton = new JButton(ROLL_STRING);
		rollButton.addActionListener(this);
		rollButton.setMaximumSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		rollButton.setMinimumSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		rollButton.setPreferredSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		rollButton.setSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		rollButton.setToolTipText("Roll dice with the given parameters.");
		rollButton.setBounds(191, 117, SMALL_BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(rollButton);

		final JLabel rollTotalLabel = new JLabel("Total:");
		rollTotalLabel.setForeground(DEFAULT_FOREGROUND_COLOR);
		rollTotalLabel.setBackground(DEFAULT_BACKGROUND_COLOR);
		rollTotalLabel.setBounds(150, 150, 47, 14);
		tableControlPanel.add(rollTotalLabel);

		rollTotalField = new JTextField();
		rollTotalField.setToolTipText("Total of the roll.");
		rollTotalField.setEditable(false);
		rollTotalField.setBounds(201, 147, 46, 20);
		tableControlPanel.add(rollTotalField);
		rollTotalField.setColumns(10);

		final JButton resetTurnButton = new JButton(RESET_COMBAT_STRING);
		resetTurnButton.addActionListener(this);
		resetTurnButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resetTurnButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resetTurnButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resetTurnButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		resetTurnButton.setToolTipText("Reset the Combat Turn and Initiative Pass counters.");
		resetTurnButton.setBounds(390, 5, BUTTON_WIDTH, BUTTON_HEIGHT);
		tableControlPanel.add(resetTurnButton);

		myTurnInfoTextField = new JTextField();
		myTurnInfoTextField.setEditable(false);
		myTurnInfoTextField.setBounds(390, 147, 150, 20);
		tableControlPanel.add(myTurnInfoTextField);
		myTurnInfoTextField.setColumns(30);
		setTurnInfoFieldText();

		final JPanel memoPanel = new JPanel();
		memoPanel.setLocation(LOWER_PANEL_WIDTH, 0);
		memoPanel.setBackground(DEFAULT_BACKGROUND_COLOR);
		memoPanel.setSize(new Dimension(LOWER_PANEL_WIDTH, LOWER_PANEL_HEIGHT));
		memoPanel.setPreferredSize(new Dimension(LOWER_PANEL_WIDTH, LOWER_PANEL_HEIGHT));
		memoPanel.setMinimumSize(new Dimension(LOWER_PANEL_WIDTH, LOWER_PANEL_HEIGHT));
		memoPanel.setMaximumSize(new Dimension(LOWER_PANEL_WIDTH, LOWER_PANEL_HEIGHT));
		controlPanel.add(memoPanel);
		memoPanel.setLayout(null);

		final JLabel memoLabel = new JLabel("Memo:");
		memoLabel.setForeground(DEFAULT_FOREGROUND_COLOR);
		memoLabel.setBackground(DEFAULT_BACKGROUND_COLOR);
		memoLabel.setBounds(10, 11, 46, 14);
		memoPanel.add(memoLabel);

		getMemoField().setToolTipText("Type memo here.");
		getMemoField().setBounds(10, 36, 530, 20);
		memoPanel.add(getMemoField());
		getMemoField().setColumns(10);

		final JButton addMemoButton = new JButton(ADD_MEMO_STRING);
		addMemoButton.setEnabled(false);
		addMemoButton.addActionListener(this);
		addMemoButton.setMaximumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addMemoButton.setMinimumSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addMemoButton.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addMemoButton.setSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		addMemoButton.setToolTipText("Add memo to the log.");
		addMemoButton.setBounds(10, 67, BUTTON_WIDTH, BUTTON_HEIGHT);
		memoPanel.add(addMemoButton);

		getCombatMemoCheckBox().setForeground(DEFAULT_FOREGROUND_COLOR);
		getCombatMemoCheckBox().setBackground(DEFAULT_BACKGROUND_COLOR);
		getCombatMemoCheckBox().setBounds(166, 68, 120, 23);
		memoPanel.add(getCombatMemoCheckBox());

		getMemoField().getDocument().addDocumentListener(new DocumentListener() {

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
				if (getMemoField().getText().isEmpty()) {
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
		saveButton.setMaximumSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		saveButton.setMinimumSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		saveButton.setPreferredSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		saveButton.setSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		filePanel.add(saveButton);

		final JButton exitButton = new JButton(EXIT_STRING);
		exitButton.setLocation(10, 157);
		exitButton.addActionListener(this);
		exitButton.setMaximumSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		exitButton.setMinimumSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
		exitButton.setPreferredSize(new Dimension(SMALL_BUTTON_WIDTH, BUTTON_HEIGHT));
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

		// table model

		getTrackingTable().setModel(trackingTableModel);
		// table selection and removal button
		getTrackingTable().getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			// enable/disable remove button on table selection
			@Override
			public void valueChanged(final ListSelectionEvent e) {
				if (getTrackingTable().getSelectedRows().length != 0) {
					removeActorButton.setEnabled(true);
					rollInitiativeButton.setEnabled(true);
				} else {
					removeActorButton.setEnabled(false);
					rollInitiativeButton.setEnabled(false);
				}
			}
		});

		// sorter
		getTrackingTable().setAutoCreateRowSorter(true);

		// combox for status enum
		// use this somewhat roundabout method of added the enum values due to
		// error with WindowsBuilder not recognizing the constructor that takes
		// initial values
		final JComboBox<CharacterStatus> statusCombox = new JComboBox<CharacterStatus>();
		for (final CharacterStatus status : CharacterStatus.values()) {
			statusCombox.addItem(status);
		}
		getTrackingTable().getColumnModel().getColumn(trackingTableModel.getStatusColumnIndex())
				.setCellEditor(new DefaultCellEditor(statusCombox));

		// on cell edit, repaint
		trackingTableModel.addTableModelListener(new TableModelListener() {

			@Override
			public void tableChanged(final TableModelEvent e) {
				repaint();
			}
		});

		// renderer
		// objects (seems to only handle JCombobox and String)
		getTrackingTable().setDefaultRenderer(Object.class,
				new ShadowrunScenarioTrackingTableCellRenderer(trackingTableModel.getStatusColumnIndex()));
		getTrackingTable().setDefaultRenderer(Integer.class,
				new ShadowrunScenarioTrackingTableCellRenderer(trackingTableModel.getStatusColumnIndex()));
		getTrackingTable().setDefaultRenderer(Boolean.class,
				new ShadowrunScenarioTrackingTableCellRenderer(trackingTableModel.getStatusColumnIndex()));
		getTrackingTable().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// column widths
		getTrackingTable().getColumnModel().getColumn(trackingTableModel.getActedColIndex()).setPreferredWidth(45);
		getTrackingTable().getColumnModel().getColumn(trackingTableModel.getActedColIndex()).setMaxWidth(45);
		getTrackingTable().getColumnModel().getColumn(trackingTableModel.getInitiativeColIndex()).setPreferredWidth(55);
		getTrackingTable().getColumnModel().getColumn(trackingTableModel.getInitiativeColIndex()).setMaxWidth(55);
		getTrackingTable().getColumnModel().getColumn(trackingTableModel.getNameColumnIndex()).setPreferredWidth(120);
		getTrackingTable().getColumnModel().getColumn(trackingTableModel.getNameColumnIndex()).setMaxWidth(120);
		getTrackingTable().getColumnModel().getColumn(ShadowrunScenarioTrackingTableModel.MAT_DAM_COL_INDEX)
				.setPreferredWidth(55);
		getTrackingTable().getColumnModel().getColumn(ShadowrunScenarioTrackingTableModel.MAT_DAM_COL_INDEX)
				.setMaxWidth(55);
		getTrackingTable().getColumnModel().getColumn(trackingTableModel.getStatusColumnIndex()).setPreferredWidth(45);
		getTrackingTable().getColumnModel().getColumn(trackingTableModel.getStatusColumnIndex()).setMaxWidth(45);
		getTrackingTable().getColumnModel().getColumn(ShadowrunScenarioTrackingTableModel.STUN_DAM_COL_INDEX)
				.setPreferredWidth(80);
		getTrackingTable().getColumnModel().getColumn(ShadowrunScenarioTrackingTableModel.STUN_DAM_COL_INDEX)
				.setMaxWidth(80);

		// add table to scroll pane
		tableScrollPane.setViewportView(getTrackingTable());

		final JScrollPane memoScrollPane = new JScrollPane();
		memoScrollPane.setAutoscrolls(true);
		memoScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tableAndMemoSplitPane.setRightComponent(memoScrollPane);

		getMemoTextArea().setForeground(DEFAULT_FOREGROUND_COLOR);
		getMemoTextArea().setBackground(DEFAULT_BACKGROUND_COLOR);
		getMemoTextArea().setToolTipText("List of memos for the scenario");
		memoScrollPane.setViewportView(getMemoTextArea());
		tableAndMemoSplitPane.setDividerLocation(FRAME_WIDTH - 5);
		mainSplitPane.setDividerLocation(0.7);

		// check if files exist for this scenario
		final File logFile = new File(getScenarioName() + CSV_EXTENSION_STRING);
		final File tableFile = new File(getScenarioName() + TXT_EXTENSION_STRING);

		try {
			if (logFile.exists() || tableFile.exists()) {
				// at least one exists
				loadLogFile(logFile);

				loadTableFile(tableFile);
			} else {
				// neither exist, so create
				logFile.createNewFile();
				tableFile.createNewFile();
			}

		} catch (final IOException iException) {
			showError(iException);
			dispose();
		}

		pack();
		setVisible(true);
	}

	/**
	 * Set text in turn info field.
	 */
	private void setTurnInfoFieldText() {
		myTurnInfoTextField.setText("Turn: " + myCombatTurn + " Pass: " + myInitiativePass);
	}

	@Override
	protected void loadTableFile(final File tableFile) {
		try {
			if (tableFile.exists()) {
				// table file exists
				// offer to load or overwrite
				final int result = JOptionPane.showConfirmDialog(null,
						"Table file [" + tableFile.getName() + "] found. Load?", "Load existing table file?",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (result == JOptionPane.OK_OPTION) {
					// load
					final BufferedReader tableFileReader = new BufferedReader(new FileReader(tableFile));
					String line = tableFileReader.readLine();
					while (line != null) {
						// split line by delimiter
						final String[] lineArray = line.split(TABLE_DELIMITER);

						// row to insert
						final Object[] newRow = new Object[getTableModel().getNumOfCols()];

						newRow[getTableModel().getActedColIndex()] = Boolean
								.parseBoolean(lineArray[getTableModel().getActedColIndex()]);
						newRow[getTableModel().getInitiativeColIndex()] = Integer
								.parseInt(lineArray[getTableModel().getInitiativeColIndex()]);
						newRow[getTableModel().getNameColumnIndex()] = lineArray[getTableModel().getNameColumnIndex()];
						newRow[ShadowrunScenarioTrackingTableModel.PHYS_DAM_COL_INDEX] = lineArray[ShadowrunScenarioTrackingTableModel.PHYS_DAM_COL_INDEX];
						newRow[ShadowrunScenarioTrackingTableModel.STUN_DAM_COL_INDEX] = lineArray[ShadowrunScenarioTrackingTableModel.STUN_DAM_COL_INDEX];
						newRow[ShadowrunScenarioTrackingTableModel.MAT_DAM_COL_INDEX] = lineArray[ShadowrunScenarioTrackingTableModel.MAT_DAM_COL_INDEX];
						newRow[getTableModel().getNoteColumnIndex()] = lineArray[getTableModel().getNoteColumnIndex()];
						newRow[getTableModel().getStatusColumnIndex()] = CharacterStatus
								.valueOf(lineArray[getTableModel().getStatusColumnIndex()]);

						// add to model
						getTableModel().addRow(newRow);

						// new line
						line = tableFileReader.readLine();
					}

					tableFileReader.close();
				} else {
					// delete and recreate
					tableFile.delete();
					tableFile.createNewFile();
				}
			}
		} catch (final IOException iException) {
			showError(iException);
		} catch (final ArrayIndexOutOfBoundsException iException) {
			showError(iException);
		} catch (final NumberFormatException iException) {
			showError(iException);
		} catch (final IllegalArgumentException iException) {
			showError(iException);
		}
	}

	@Override
	public void actionPerformed(final ActionEvent iEvent) {
		if (iEvent.getActionCommand().equals(ADD_MEMO_STRING)) {
			addMemo();
		} else if (iEvent.getActionCommand().equals(ADD_STRING)) {
			// add character(s)
			addCharacters();
		} else if (iEvent.getActionCommand().equals(CLEANUP_STRING)) {
			// cleanup dead characters
			// confirm
			final int result = JOptionPane.showConfirmDialog(this, "Remove dead characters?", "Bring out the dead?",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				getTableModel().removeDeadCharacters();
			}
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
		} else if (iEvent.getActionCommand().equals(ROLL_INITIATIVE_STRING)) {
			// roll initiative for selected character(s)
			final JPanel inputPanel = new JPanel(new GridLayout(0, 1));

			final JPanel diceNumberPanel = new JPanel(new GridLayout(1, 0));
			diceNumberPanel.add(new JLabel("Number of Initiative Dice:"));

			final JFormattedTextField initiativeDiceField = new JFormattedTextField(INITIATIVE_DICE_NUMBER_FORMATTER);
			initiativeDiceField.setValue(1);
			diceNumberPanel.add(initiativeDiceField);

			inputPanel.add(diceNumberPanel);

			final JPanel initiativeBasePanel = new JPanel(new GridLayout(1, 0));
			initiativeBasePanel.add(new JLabel("Initiative Base:"));

			final JFormattedTextField initiativeBaseField = new JFormattedTextField(INITIATIVE_BASE_FORMATTER);
			initiativeBaseField.setValue(0);
			initiativeBasePanel.add(initiativeBaseField);

			inputPanel.add(initiativeBasePanel);

			final int result = JOptionPane.showConfirmDialog(this, inputPanel, "Please input initiative information",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

			if (result == JOptionPane.OK_OPTION) {
				rollInitiative(new ShadowrunInitiativeRoller(Integer.parseInt(initiativeBaseField.getText()),
						Integer.parseInt(initiativeDiceField.getText())));
			} else {
				System.out.println("Initiative rolling cancelled.");
			}
		} else if (iEvent.getActionCommand().equals(RESET_COMBAT_STRING)) {
			// reset turn/pass counters
			resetCombat();
		} else if (iEvent.getActionCommand().equals(RESORT_STRING)) {
			// resort the table by initiative
			resort();
		} else if (iEvent.getActionCommand().equals(ROLL_STRING)) {
			// roll dice
			rollDice();
		} else if (iEvent.getActionCommand().equals(SAVE_STRING)) {
			// save
			save();
		} else if (iEvent.getActionCommand().equals(CLEAR_SELECTION_STRING)) {
			// clear selection(s)
			getTrackingTable().getSelectionModel().clearSelection();
		} else {
			showError(new Exception("Unknown action"));
		}
	}

	/**
	 * Reset combat turn/pass.
	 */
	private void resetCombat() {
		// confirm
		final int result = JOptionPane.showConfirmDialog(this, "Reset combat turns and passes?", "Reset combat?",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// reset all acted flags
			getTableModel().resetActedFlags();

			// reset all initiatives to 0
			getTableModel().resetInitiative();

			// reset initiative pass
			myInitiativePass = 1;

			// reset combat turn
			myCombatTurn = 1;

			// add memo
			appendMemo("Reset combat", true);

			// show turn info
			setTurnInfoFieldText();
		}
	}

	/**
	 * Next combat turn.
	 */
	private void nextTurn() {
		// confirm
		final int result = JOptionPane.showConfirmDialog(this, "Go to next combat turn?", "Next turn?",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// reset all acted flags
			getTableModel().resetActedFlags();

			// reset all initiatives to 0
			getTableModel().resetInitiative();

			// reset initiative pass
			myInitiativePass = 1;

			// increment combat turn
			myCombatTurn++;

			// add memo
			appendMemo("New combat turn", true);

			// show turn info
			setTurnInfoFieldText();
		}
	}

	/**
	 * Next initiative pass.
	 */
	private void nextPass() {
		// confirm
		final int result = JOptionPane.showConfirmDialog(this, "Go to next initiative pass?", "Next pass?",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);

		if (result == JOptionPane.OK_OPTION) {
			// reset all acted flags
			trackingTableModel.resetActedFlags();

			// decrease all initiatives by 10
			trackingTableModel.nextInitiativePass();

			// increment initiative pass
			myInitiativePass++;

			// add memo
			appendMemo("New initiative pass", true);

			// show turn info
			setTurnInfoFieldText();
		}
	}

	@Override
	protected void rollDice() {
		try {
			final int diceToRoll = Integer.valueOf(diceToRollField.getText());

			final boolean useEdge = edgeCheckbox.isSelected();

			final ShadowrunRollResult rollResult = new ShadowrunRollResult(
					ShadowrunRoller.rollDice(diceToRoll, useEdge));

			rollTotalField.setText(String.valueOf(rollResult.getSum()));

			if (rollResult.isCriticalGlitch()) {
				hitsField.setText("Critical Glitch!");
			} else {
				final StringBuilder hitsText = new StringBuilder(String.valueOf(rollResult.getHits()));

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
	 * Append a memo.
	 * 
	 * @param iMemoText
	 *            text.
	 * @param iIsCombatMemo
	 *            whether or not the memo is related to combat.
	 */
	@Override
	protected void appendMemo(final String iMemoText, final boolean iIsCombatMemo) {
		String textToAdd;
		if (iIsCombatMemo) {
			textToAdd = "\n" + COMBAT_NOTE_STRING + LOG_DELIMITER + "TURN" + myCombatTurn + "PASS" + myInitiativePass
					+ " " + iMemoText;
		} else {
			textToAdd = "\n" + STORY_NOTE_STRING + LOG_DELIMITER + " " + iMemoText;
		}

		getMemoTextArea().append(textToAdd);
	}

	@Override
	protected AbstractScenarioTrackingTableModel getTableModel() {
		return trackingTableModel;
	}

	/**
	 * Main.
	 * 
	 * @param args
	 *            arguments.
	 */
	public static void main(final String[] args) {
		if (args.length != 0) {
			new ShadowrunScenarioTracking(args[0]);
		} else {
			System.err.println("Requires name of scenario.");
		}
	}
}