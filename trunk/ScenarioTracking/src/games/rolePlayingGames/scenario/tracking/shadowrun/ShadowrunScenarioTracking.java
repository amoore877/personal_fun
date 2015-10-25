package games.rolePlayingGames.scenario.tracking.shadowrun;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

/**
 * Tracking a scenario for Shadowrun.
 * 
 * @author Andrew
 */
public class ShadowrunScenarioTracking extends JFrame implements
		ActionListener, WindowListener {

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = -3278488966357839811L;
	private final JTextField hitsField;
	private final JTextField rollTotalField;
	private final JTextField memoField;
	private final JTable trackingTable;

	public ShadowrunScenarioTracking() {
		// TODO take name of scenario

		// TODO check if files exist for this scenario

		// TODO if files exist for this scenario, offer to load or overwrite

		// TODO load

		// TODO overwrite/files did not exist

		// TODO double-check the files exist now

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		addWindowListener(this);

		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setSize(new Dimension(900, 700));
		getContentPane().setPreferredSize(new Dimension(900, 700));
		getContentPane().setMinimumSize(new Dimension(900, 700));
		getContentPane().setMaximumSize(new Dimension(900, 700));
		setTitle("Shadowrun Scenario Tracking");
		setResizable(false);
		getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		final JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.DARK_GRAY);
		mainPanel.setMinimumSize(new Dimension(900, 650));
		mainPanel.setMaximumSize(new Dimension(900, 650));
		mainPanel.setPreferredSize(new Dimension(900, 650));
		mainPanel.setSize(new Dimension(900, 650));
		getContentPane().add(mainPanel);
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		final JSplitPane mainSplitPane = new JSplitPane();
		mainSplitPane.setMaximumSize(new Dimension(900, 650));
		mainSplitPane.setMinimumSize(new Dimension(900, 650));
		mainSplitPane.setPreferredSize(new Dimension(900, 650));
		mainSplitPane.setSize(new Dimension(900, 650));
		mainSplitPane.setBackground(Color.BLACK);
		mainSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		mainPanel.add(mainSplitPane);

		final JPanel controlPanel = new JPanel();
		controlPanel.setBackground(Color.DARK_GRAY);
		mainSplitPane.setRightComponent(controlPanel);
		controlPanel.setLayout(new GridLayout(1, 0, 0, 0));

		final JPanel tableControlPanel = new JPanel();
		tableControlPanel.setBackground(Color.DARK_GRAY);
		tableControlPanel.setSize(new Dimension(300, 160));
		tableControlPanel.setPreferredSize(new Dimension(300, 160));
		tableControlPanel.setMinimumSize(new Dimension(300, 160));
		tableControlPanel.setMaximumSize(new Dimension(300, 160));
		controlPanel.add(tableControlPanel);
		tableControlPanel.setLayout(null);

		final JButton nextPassButton = new JButton("Next Pass");
		nextPassButton
				.setToolTipText("Subtracts 10 from everyone's initiative and unchecks \"Acted\" flag for all.");
		nextPassButton.setMaximumSize(new Dimension(80, 25));
		nextPassButton.setMinimumSize(new Dimension(80, 25));
		nextPassButton.setSize(new Dimension(80, 25));
		nextPassButton.setPreferredSize(new Dimension(80, 25));
		nextPassButton.setBounds(10, 11, 81, 23);
		tableControlPanel.add(nextPassButton);

		final JButton nextTurnButton = new JButton("Next Turn");
		nextTurnButton
				.setToolTipText("Sets all initiatives to 0 and unchecks \"Acted\" flag for all.");
		nextTurnButton.setMaximumSize(new Dimension(80, 25));
		nextTurnButton.setMinimumSize(new Dimension(80, 25));
		nextTurnButton.setPreferredSize(new Dimension(80, 25));
		nextTurnButton.setSize(new Dimension(80, 25));
		nextTurnButton.setBounds(101, 11, 89, 23);
		tableControlPanel.add(nextTurnButton);

		final JButton resortButton = new JButton("Resort");
		resortButton.setMaximumSize(new Dimension(80, 25));
		resortButton.setMinimumSize(new Dimension(80, 25));
		resortButton.setPreferredSize(new Dimension(80, 25));
		resortButton.setSize(new Dimension(80, 25));
		resortButton.setToolTipText("Resorts table by initiative score.");
		resortButton.setBounds(200, 11, 89, 23);
		tableControlPanel.add(resortButton);

		final JButton addActorButton = new JButton("Add");
		addActorButton.setMaximumSize(new Dimension(80, 25));
		addActorButton.setMinimumSize(new Dimension(80, 25));
		addActorButton.setPreferredSize(new Dimension(80, 25));
		addActorButton.setSize(new Dimension(80, 25));
		addActorButton
				.setToolTipText("Add some Actor(s) by name to the table, setting initiative to 0.");
		addActorButton.setBounds(10, 45, 89, 23);
		tableControlPanel.add(addActorButton);

		final JButton removeActorButton = new JButton("Remove");
		removeActorButton.setMaximumSize(new Dimension(80, 25));
		removeActorButton.setMinimumSize(new Dimension(80, 25));
		removeActorButton.setPreferredSize(new Dimension(80, 25));
		removeActorButton.setSize(new Dimension(80, 25));
		removeActorButton
				.setToolTipText("Remove the selected actor from the table.");
		removeActorButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(final ActionEvent e) {
			}
		});
		removeActorButton.setBounds(101, 45, 89, 23);
		tableControlPanel.add(removeActorButton);

		final JButton cleanupButton = new JButton("Cleanup");
		cleanupButton.setMaximumSize(new Dimension(80, 25));
		cleanupButton.setMinimumSize(new Dimension(80, 25));
		cleanupButton.setPreferredSize(new Dimension(80, 25));
		cleanupButton.setSize(new Dimension(80, 25));
		cleanupButton.setToolTipText("Remove all dead actors from the table.");
		cleanupButton.setBounds(200, 45, 89, 23);
		tableControlPanel.add(cleanupButton);

		final JLabel rollingLabel = new JLabel("Roll dice:");
		rollingLabel.setForeground(Color.WHITE);
		rollingLabel.setBackground(Color.DARK_GRAY);
		rollingLabel.setBounds(10, 79, 46, 14);
		tableControlPanel.add(rollingLabel);

		final JLabel diceToRollLabel = new JLabel("Dice to roll:");
		diceToRollLabel.setForeground(Color.WHITE);
		diceToRollLabel.setBackground(Color.DARK_GRAY);
		diceToRollLabel.setBounds(10, 104, 62, 14);
		tableControlPanel.add(diceToRollLabel);

		final JFormattedTextField diceToRollField = new JFormattedTextField();
		diceToRollField.setToolTipText("Number of dice to roll.");
		diceToRollField.setText("1");
		diceToRollField.setBounds(67, 101, 46, 20);
		tableControlPanel.add(diceToRollField);

		final JCheckBox edgeCheckbox = new JCheckBox("Use Edge?");
		edgeCheckbox.setBackground(Color.DARK_GRAY);
		edgeCheckbox.setForeground(Color.WHITE);
		edgeCheckbox.setToolTipText("Use edge on the roll?");
		edgeCheckbox.setBounds(119, 100, 97, 23);
		tableControlPanel.add(edgeCheckbox);

		final JLabel hitsLabel = new JLabel("Hits:");
		hitsLabel.setBackground(Color.DARK_GRAY);
		hitsLabel.setForeground(Color.WHITE);
		hitsLabel.setBounds(10, 129, 22, 14);
		tableControlPanel.add(hitsLabel);

		hitsField = new JTextField();
		hitsField.setToolTipText("Number of hits on the roll.");
		hitsField.setEditable(false);
		hitsField.setBounds(42, 126, 49, 20);
		tableControlPanel.add(hitsField);
		hitsField.setColumns(10);

		final JButton rollButton = new JButton("Roll");
		rollButton.setMaximumSize(new Dimension(80, 25));
		rollButton.setMinimumSize(new Dimension(80, 25));
		rollButton.setPreferredSize(new Dimension(80, 25));
		rollButton.setSize(new Dimension(80, 25));
		rollButton.setToolTipText("Roll dice with the given parameters.");
		rollButton.setBounds(200, 100, 89, 23);
		tableControlPanel.add(rollButton);

		final JLabel rollTotalLabel = new JLabel("Total:");
		rollTotalLabel.setForeground(Color.WHITE);
		rollTotalLabel.setBackground(Color.DARK_GRAY);
		rollTotalLabel.setBounds(101, 129, 28, 14);
		tableControlPanel.add(rollTotalLabel);

		rollTotalField = new JTextField();
		rollTotalField.setToolTipText("Total of the roll.");
		rollTotalField.setEditable(false);
		rollTotalField.setBounds(129, 126, 46, 20);
		tableControlPanel.add(rollTotalField);
		rollTotalField.setColumns(10);

		final JPanel memoPanel = new JPanel();
		memoPanel.setBackground(Color.DARK_GRAY);
		memoPanel.setSize(new Dimension(300, 160));
		memoPanel.setPreferredSize(new Dimension(300, 160));
		memoPanel.setMinimumSize(new Dimension(300, 160));
		memoPanel.setMaximumSize(new Dimension(300, 160));
		controlPanel.add(memoPanel);
		memoPanel.setLayout(null);

		final JLabel memoLabel = new JLabel("Memo:");
		memoLabel.setForeground(Color.WHITE);
		memoLabel.setBackground(Color.DARK_GRAY);
		memoLabel.setBounds(10, 11, 46, 14);
		memoPanel.add(memoLabel);

		memoField = new JTextField();
		memoField.setToolTipText("Type memo here.");
		memoField.setBounds(10, 36, 279, 20);
		memoPanel.add(memoField);
		memoField.setColumns(10);

		final JButton addMemoButton = new JButton("Add Memo");
		addMemoButton.setMaximumSize(new Dimension(80, 25));
		addMemoButton.setMinimumSize(new Dimension(80, 25));
		addMemoButton.setPreferredSize(new Dimension(80, 25));
		addMemoButton.setSize(new Dimension(80, 25));
		addMemoButton.setBounds(10, 67, 89, 23);
		memoPanel.add(addMemoButton);

		final JPanel filePanel = new JPanel();
		filePanel.setBackground(Color.DARK_GRAY);
		controlPanel.add(filePanel);
		filePanel.setLayout(new GridLayout(0, 1, 0, 20));

		final JButton saveButton = new JButton("Save");
		saveButton.setMaximumSize(new Dimension(80, 25));
		saveButton.setMinimumSize(new Dimension(80, 25));
		saveButton.setPreferredSize(new Dimension(80, 25));
		saveButton.setSize(new Dimension(80, 25));
		filePanel.add(saveButton);

		final JButton exitButton = new JButton("Exit");
		exitButton.setMaximumSize(new Dimension(30, 10));
		exitButton.setMinimumSize(new Dimension(30, 10));
		exitButton.setPreferredSize(new Dimension(30, 10));
		exitButton.setSize(new Dimension(30, 10));
		filePanel.add(exitButton);

		final JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setForeground(Color.DARK_GRAY);
		tableScrollPane.setBackground(Color.DARK_GRAY);
		mainSplitPane.setLeftComponent(tableScrollPane);

		trackingTable = new JTable();
		trackingTable.setForeground(Color.WHITE);
		trackingTable.setBackground(Color.DARK_GRAY);
		trackingTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tableScrollPane.setViewportView(trackingTable);
		mainSplitPane.setDividerLocation(0.75);
		// TODO Auto-generated constructor stub
	}

	public static void main(final String[] args) {
		// TODO need name of scenario
	}

	@Override
	public void windowOpened(final WindowEvent e) {
		// unimplemented
	}

	@Override
	public void windowClosing(final WindowEvent e) {
		final int reply = JOptionPane.showConfirmDialog(this,
				"Are you sure you wish to exit?", "Exit",
				JOptionPane.YES_NO_OPTION);

		if (reply == JOptionPane.YES_OPTION) {
			// TODO window is being closed; save information
			this.dispose();
		}
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

	@Override
	public void actionPerformed(final ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
