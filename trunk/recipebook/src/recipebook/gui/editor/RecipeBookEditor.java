package recipebook.gui.editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
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
import recipebook.gui.RecipeBookFrame;
import recipebook.gui.RecipeBookGUIUtil;

public final class RecipeBookEditor extends RecipeBookFrame {

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
	private final DefaultListModel<Recipe> recipeListModel = new DefaultListModel<Recipe>();
	private final JList<Recipe> recipeList = new JList<Recipe>(recipeListModel);
	private final JButton btnAddRecipe = new JButton(ADD_RECIPE);
	private final JButton btnRemoveRecipe = new JButton(REMOVE_RECIPE);
	private final DefaultListModel<RecipeIngredient> ingredientsListModel = new DefaultListModel<RecipeIngredient>();
	private final JList<RecipeIngredient> ingredientsList = new JList<RecipeIngredient>(ingredientsListModel);
	private final JButton btnAddIngredient = new JButton(ADD_ING);
	private final JButton btnRemoveIngredient = new JButton(REMOVE_ING);
	private final JButton btnAddStep = new JButton(ADD_STEP);
	private final JButton btnRemoveStep = new JButton(REMOVE_STEP);
	private final DefaultListModel<RecipeStep> stepsListModel = new DefaultListModel<RecipeStep>();
	private final JList<RecipeStep> stepsList = new JList<RecipeStep>(stepsListModel);
	private final JButton btnAddTag = new JButton(ADD_TAG);
	private final JButton btnRemoveTag = new JButton(REMOVE_TAG);
	private final DefaultListModel<String> tagsListModel = new DefaultListModel<String>();
	private final JList<String> tagsList = new JList<String>(tagsListModel);
	private final JTextField servingsField = new JTextField();

	/**
	 * Logic field parts.
	 */
	private RecipeBook currBook = null;
	private File currFile = null;

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

		btnNewBook.setBounds(10, 11, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
		btnNewBook.addActionListener(this);
		btnNewBook.setActionCommand(NEW_BOOK);
		controlPanel.add(btnNewBook);

		btnOpenBook.setBounds(141, 11, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
		btnOpenBook.addActionListener(this);
		btnOpenBook.setActionCommand(OPEN_BOOK);
		controlPanel.add(btnOpenBook);

		btnSaveBook.setBounds(699, 11, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
		btnSaveBook.addActionListener(this);
		btnSaveBook.setActionCommand(SAVE_BOOK);
		controlPanel.add(btnSaveBook);

		btnCloseBook.setBounds(272, 11, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
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
				// make sure valid index
				if (recipeList.getSelectedValue() != null) {
					Recipe recipe = recipeList.getSelectedValue();

					recipeViewField.setText(recipe.toPrintString());

					for (RecipeIngredient ingredient : recipe.getIngredients()) {
						ingredientsListModel.addElement(ingredient);
					}

					for (RecipeStep step : recipe.getSteps()) {
						stepsListModel.addElement(step);
					}

					for (String tag : recipe.getTags()) {
						tagsListModel.addElement(tag);
					}

					btnAddIngredient.setEnabled(true);
					btnAddStep.setEnabled(true);
					btnAddTag.setEnabled(true);

					ingredientsList.setEnabled(true);
					stepsList.setEnabled(true);
					tagsList.setEnabled(true);

					servingsField.setEnabled(true);
					servingsField.setText(String.valueOf(recipe.getServings()));
				} else {
					recipeViewField.setText("");

					btnAddIngredient.setEnabled(false);
					btnAddStep.setEnabled(false);
					btnAddTag.setEnabled(false);

					ingredientsList.setEnabled(false);
					stepsList.setEnabled(false);
					tagsList.setEnabled(false);

					servingsField.setEnabled(false);
				}

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

		btnAddRecipe.setBounds(794, 91, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
		btnAddRecipe.addActionListener(this);
		btnAddRecipe.setActionCommand(ADD_RECIPE);
		getContentPane().add(btnAddRecipe);

		btnRemoveRecipe.setBounds(794, 143, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
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
				if (ingredientsList.getSelectedValue() != null) {
					btnRemoveIngredient.setEnabled(true);
				} else {
					btnRemoveIngredient.setEnabled(false);
				}
			}
		});
		getContentPane().add(ingredientsList);

		btnAddIngredient.setBounds(200, 321, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
		btnAddIngredient.addActionListener(this);
		btnAddIngredient.setActionCommand(ADD_ING);
		getContentPane().add(btnAddIngredient);

		btnRemoveIngredient.setBounds(200, 355, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
		btnRemoveIngredient.addActionListener(this);
		btnRemoveIngredient.setActionCommand(REMOVE_ING);
		getContentPane().add(btnRemoveIngredient);

		btnAddStep.setBounds(521, 321, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
		btnAddStep.addActionListener(this);
		btnAddStep.setActionCommand(ADD_STEP);
		getContentPane().add(btnAddStep);

		btnRemoveStep.setBounds(521, 355, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
		btnRemoveStep.addActionListener(this);
		btnRemoveStep.setActionCommand(REMOVE_STEP);
		getContentPane().add(btnRemoveStep);

		stepsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		stepsList.setBounds(341, 324, 165, 159);
		stepsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (stepsList.getSelectedValue() != null) {
					btnRemoveStep.setEnabled(true);
				} else {
					btnRemoveStep.setEnabled(false);
				}
			}
		});
		getContentPane().add(stepsList);

		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setBounds(331, 299, 77, 14);
		getContentPane().add(lblSteps);

		btnAddTag.setBounds(842, 321, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
		btnAddTag.addActionListener(this);
		btnAddTag.setActionCommand(ADD_TAG);
		getContentPane().add(btnAddTag);

		btnRemoveTag.setBounds(842, 355, RecipeBookGUIUtil.BUTTON_WIDTH, RecipeBookGUIUtil.BUTTON_HEIGHT);
		btnRemoveTag.addActionListener(this);
		btnRemoveTag.setActionCommand(REMOVE_TAG);
		getContentPane().add(btnRemoveTag);

		tagsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tagsList.setBounds(662, 324, 165, 159);
		tagsList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (tagsList.getSelectedValue() != null) {
					btnRemoveTag.setEnabled(true);
				} else {
					btnRemoveTag.setEnabled(false);
				}
			}
		});
		getContentPane().add(tagsList);

		JLabel lblTags = new JLabel("Tags");
		lblTags.setBounds(652, 299, 77, 14);
		getContentPane().add(lblTags);

		JLabel lblServings = new JLabel("Servings:");
		lblServings.setBounds(794, 211, 46, 14);
		getContentPane().add(lblServings);

		// TODO formatter
		// TODO listener
		servingsField.setColumns(10);
		servingsField.setBounds(842, 208, 40, 20);
		getContentPane().add(servingsField);

		bookOpenedClosed(false);
	}

	@Override
	protected void save() {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
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
			removeIngredient();
		} else if (command.equals(REMOVE_RECIPE)) {
			removeRecipe();
		} else if (command.equals(REMOVE_STEP)) {
			removeStep();
		} else if (command.equals(REMOVE_TAG)) {
			removeTag();
		} else if (command.equals(SAVE_BOOK)) {
			// TODO
		} else {
			System.err.println("Unknown command: [" + command + "]");
		}
	}

	private void removeRecipe() {
		Recipe recipe = recipeList.getSelectedValue();

		if (recipe != null) {
			final int reply = JOptionPane.showConfirmDialog(this,
					"Are you sure you wish to remove [" + recipe.getName() + "]?", "Remove Recipe",
					JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				currBook.getRecipes().remove(recipe);
				recipeListModel.removeElement(recipe);
			}
		} else {
			System.err.println("Null recipe selected.");
		}
	}

	private void removeTag() {
		String tag = tagsList.getSelectedValue();

		if (tag != null) {
			final int reply = JOptionPane.showConfirmDialog(this, "Are you sure you wish to remove [" + tag + "]?",
					"Remove Tag", JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				Recipe recipe = recipeList.getSelectedValue();
				recipe.getTags().remove(tag);
				recipeViewField.setText(recipe.toPrintString());
				tagsListModel.clear();
				for (String refreshedTag : recipe.getTags()) {
					tagsListModel.addElement(refreshedTag);
				}
			}
		} else {
			System.err.println("Null tag selected.");
		}
	}

	private void removeStep() {
		RecipeStep step = stepsList.getSelectedValue();

		if (step != null) {
			final int reply = JOptionPane.showConfirmDialog(this,
					"Are you sure you wish to remove [" + step.toPrintString() + "]?", "Remove Step",
					JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				Recipe recipe = recipeList.getSelectedValue();
				recipe.getSteps().remove(step);
				recipeViewField.setText(recipe.toPrintString());
				stepsListModel.clear();
				for (RecipeStep refreshedStep : recipe.getSteps()) {
					stepsListModel.addElement(refreshedStep);
				}
			}
		} else {
			System.err.println("Null step selected.");
		}
	}

	private void removeIngredient() {
		RecipeIngredient ingredient = ingredientsList.getSelectedValue();

		if (ingredient != null) {
			final int reply = JOptionPane.showConfirmDialog(this,
					"Are you sure you wish to remove [" + ingredient.toPrintString() + "]?", "Remove Ingredient",
					JOptionPane.YES_NO_OPTION);

			if (reply == JOptionPane.YES_OPTION) {
				Recipe recipe = recipeList.getSelectedValue();
				recipe.getIngredients().remove(ingredient);
				recipeViewField.setText(recipe.toPrintString());
				ingredientsListModel.clear();
				for (RecipeIngredient refreshedIngredient : recipe.getIngredients()) {
					ingredientsListModel.addElement(refreshedIngredient);
				}
			}
		} else {
			System.err.println("Null ingredient selected.");
		}
	}

	/**
	 * Clean up after book has been open/closed and set appropriate components
	 * to enabled/disabled.
	 * 
	 * @param opened
	 *            true if book was opened, false if book was closed.
	 */
	private void bookOpenedClosed(boolean opened) {
		btnAddRecipe.setEnabled(opened);
		btnCloseBook.setEnabled(opened);
		btnSaveBook.setEnabled(opened);

		recipeBookNameField.setEnabled(opened);

		recipeList.setEnabled(opened);

		if (!opened) {
			// specific to book being closed
			recipeBookNameField.setText("");

			recipeListModel.clear();
		} else {
			// specific to book being opened
			recipeBookNameField.setText(currFile.getName());

			for (Recipe recipe : currBook.getRecipes()) {
				recipeListModel.addElement(recipe);
			}
		}
	}

	public static void main(String[] args) {
		new RecipeBookEditor().setVisible(true);
	}
}
