package recipebook.editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import recipebook.data.Recipe;
import recipebook.data.RecipeBook;
import recipebook.data.RecipeIngredient;
import recipebook.data.RecipeStep;

public class RecipeBookEditor extends JFrame implements ActionListener, WindowListener {

	/**
	 * Constants.
	 */
	private static final long serialVersionUID = 4787001417942411020L;
	private static final String REMOVE_TAG = "Remove Tag";
	private static final String ADD_TAG = "Add Tag";
	private static final String REMOVE_STEP = "Remove Step";
	private static final String ADD_STEP = "Add Step";
	private static final String REMOVE_ING = "Remove Ing.";
	private static final String ADD_ING = "Add Ing.";
	private static final String REMOVE_RECIPE = "Remove Recipe";
	private static final String ADD_RECIPE = "Add Recipe";
	private static final String CLOSE_BOOK = "Close Book";
	private static final String SAVE_BOOK = "Save Book";
	private static final String OPEN_BOOK = "Open Book";
	private static final String NEW_BOOK = "New Book";

	/**
	 * GUI field parts.
	 */
	private JTextField recipeViewField = new JTextField();;
	private JTextField recipeBookNameField = new JTextField();;
	private final JButton btnNewBook = new JButton(NEW_BOOK);
	private final JButton btnOpenBook = new JButton(OPEN_BOOK);
	private final JButton btnSaveBook = new JButton(SAVE_BOOK);
	private final JButton btnCloseBook = new JButton(CLOSE_BOOK);
	private final JList<Recipe> recipeList = new JList<Recipe>();
	private final JButton btnAddRecipe = new JButton(ADD_RECIPE);
	private final JButton btnRemoveRecipe = new JButton(REMOVE_RECIPE);
	private final JList<RecipeIngredient> ingredientsList = new JList<RecipeIngredient>();
	private final JButton btnAddIngredient = new JButton(ADD_ING);
	private final JButton btnRemoveIngredient = new JButton(REMOVE_ING);
	private final JButton btnAddStep = new JButton(ADD_STEP);
	private final JButton btnRemoveStep = new JButton(REMOVE_STEP);
	private final JList<RecipeStep> stepsList = new JList<RecipeStep>();
	private final JButton btnAddTag = new JButton(ADD_TAG);
	private final JButton btnRemoveTag = new JButton(REMOVE_TAG);
	private final JList<String> tagsList = new JList<String>();

	/**
	 * Logic field parts.
	 */
	private RecipeBook currBook;
	private File currFile;

	/**
	 * Constructor.
	 */
	public RecipeBookEditor() {
		setSize(new Dimension(1000, 545));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Recipe Book Editor");
		getContentPane().setLayout(null);

		JPanel controlPanel = new JPanel();
		controlPanel.setLocation(0, 0);
		controlPanel.setMaximumSize(new Dimension(800, 140));
		controlPanel.setSize(new Dimension(974, 62));
		controlPanel.setPreferredSize(new Dimension(800, 140));
		controlPanel.setMinimumSize(new Dimension(800, 140));
		getContentPane().add(controlPanel);
		controlPanel.setLayout(null);

		btnNewBook.setBounds(10, 11, 121, 23);
		btnNewBook.addActionListener(this);
		btnNewBook.setActionCommand(NEW_BOOK);
		controlPanel.add(btnNewBook);

		btnOpenBook.setBounds(141, 11, 121, 23);
		btnOpenBook.addActionListener(this);
		btnOpenBook.setActionCommand(OPEN_BOOK);
		controlPanel.add(btnOpenBook);

		btnSaveBook.setBounds(699, 11, 121, 23);
		btnSaveBook.addActionListener(this);
		btnSaveBook.setActionCommand(SAVE_BOOK);
		controlPanel.add(btnSaveBook);

		btnCloseBook.setBounds(272, 11, 121, 23);
		btnCloseBook.addActionListener(this);
		btnCloseBook.setActionCommand(CLOSE_BOOK);
		controlPanel.add(btnCloseBook);

		recipeBookNameField.setEditable(false);
		recipeBookNameField.setBounds(559, 11, 130, 20);
		controlPanel.add(recipeBookNameField);
		recipeBookNameField.setColumns(10);

		JLabel lblBook = new JLabel("Book:");
		lblBook.setBounds(498, 14, 51, 14);
		controlPanel.add(lblBook);

		recipeList.setLocation(0, 73);
		recipeList.setSize(new Dimension(200, 215));
		recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		recipeList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		getContentPane().add(recipeList);

		JScrollPane recipeViewPane = new JScrollPane();
		recipeViewPane.setLocation(210, 73);
		recipeViewPane.setSize(new Dimension(574, 215));
		getContentPane().add(recipeViewPane);

		recipeViewField.setEditable(false);
		recipeViewPane.setViewportView(recipeViewField);
		recipeViewField.setColumns(10);

		btnAddRecipe.setBounds(794, 91, 121, 23);
		btnAddRecipe.addActionListener(this);
		btnAddRecipe.setActionCommand(ADD_RECIPE);
		getContentPane().add(btnAddRecipe);

		btnRemoveRecipe.setBounds(794, 143, 121, 23);
		btnRemoveRecipe.addActionListener(this);
		btnRemoveRecipe.setActionCommand(REMOVE_RECIPE);
		getContentPane().add(btnRemoveRecipe);

		JLabel lblIngredients = new JLabel("Ingredients");
		lblIngredients.setBounds(10, 299, 77, 14);
		getContentPane().add(lblIngredients);

		ingredientsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ingredientsList.setBounds(20, 324, 165, 159);
		ingredientsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		getContentPane().add(ingredientsList);

		btnAddIngredient.setBounds(200, 321, 121, 23);
		btnAddIngredient.addActionListener(this);
		btnAddIngredient.setActionCommand(ADD_ING);
		getContentPane().add(btnAddIngredient);

		btnRemoveIngredient.setBounds(200, 355, 121, 23);
		btnRemoveIngredient.addActionListener(this);
		btnRemoveIngredient.setActionCommand(REMOVE_ING);
		getContentPane().add(btnRemoveIngredient);

		btnAddStep.setBounds(521, 321, 121, 23);
		btnAddStep.addActionListener(this);
		btnAddStep.setActionCommand(ADD_STEP);
		getContentPane().add(btnAddStep);

		btnRemoveStep.setBounds(521, 355, 121, 23);
		btnRemoveStep.addActionListener(this);
		btnRemoveStep.setActionCommand(REMOVE_STEP);
		getContentPane().add(btnRemoveStep);

		stepsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		stepsList.setBounds(341, 324, 165, 159);
		stepsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		getContentPane().add(stepsList);

		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setBounds(331, 299, 77, 14);
		getContentPane().add(lblSteps);

		btnAddTag.setBounds(842, 321, 121, 23);
		btnAddTag.addActionListener(this);
		btnAddTag.setActionCommand(ADD_TAG);
		getContentPane().add(btnAddTag);

		btnRemoveTag.setBounds(842, 355, 121, 23);
		btnRemoveTag.addActionListener(this);
		btnRemoveTag.setActionCommand(REMOVE_TAG);
		getContentPane().add(btnRemoveTag);

		tagsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tagsList.setBounds(662, 324, 165, 159);
		tagsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub

			}
		});
		getContentPane().add(tagsList);

		JLabel lblTags = new JLabel("Tags");
		lblTags.setBounds(652, 299, 77, 14);
		getContentPane().add(lblTags);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.equals(ADD_ING)) {
			// TODO
		} else if (command.equals(ADD_RECIPE)) {
			// TODO
		} else if (command.equals(ADD_STEP)) {
			// TODO
		} else if (command.equals(ADD_TAG)) {
			// TODO
		} else if (command.equals(CLOSE_BOOK)) {
			// TODO
		} else if (command.equals(NEW_BOOK)) {
			// TODO
		} else if (command.equals(OPEN_BOOK)) {
			// TODO
		} else if (command.equals(REMOVE_ING)) {
			// TODO
		} else if (command.equals(REMOVE_RECIPE)) {
			// TODO
		} else if (command.equals(REMOVE_STEP)) {
			// TODO
		} else if (command.equals(REMOVE_TAG)) {
			// TODO
		} else if (command.equals(SAVE_BOOK)) {
			// TODO
		} else {
			System.err.println("Unknown command: [" + command + "]");
		}
	}

	public static void main(String[] args) {
		new RecipeBookEditor().setVisible(true);
	}
}
