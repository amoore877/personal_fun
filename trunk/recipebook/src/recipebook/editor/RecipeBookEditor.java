package recipebook.editor;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class RecipeBookEditor extends JFrame implements ActionListener, WindowListener {

	/**
	 * Constructor.
	 */
	public RecipeBookEditor() {
		getContentPane().setSize(new Dimension(800, 600));
		getContentPane().setPreferredSize(new Dimension(800, 600));
		getContentPane().setMinimumSize(new Dimension(800, 600));
		getContentPane().setMaximumSize(new Dimension(800, 600));
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

		JButton btnNewBook = new JButton("New Book");
		btnNewBook.setBounds(10, 11, 121, 23);
		controlPanel.add(btnNewBook);

		JButton btnOpenBook = new JButton("Open Book");
		btnOpenBook.setBounds(141, 11, 121, 23);
		controlPanel.add(btnOpenBook);

		JButton btnSaveBook = new JButton("Save Book");
		btnSaveBook.setBounds(699, 11, 121, 23);
		controlPanel.add(btnSaveBook);

		JButton btnCloseBook = new JButton("Close Book");
		btnCloseBook.setBounds(272, 11, 121, 23);
		controlPanel.add(btnCloseBook);

		recipeBookNameField = new JTextField();
		recipeBookNameField.setEditable(false);
		recipeBookNameField.setBounds(559, 11, 130, 20);
		controlPanel.add(recipeBookNameField);
		recipeBookNameField.setColumns(10);

		JLabel lblBook = new JLabel("Book:");
		lblBook.setBounds(498, 14, 51, 14);
		controlPanel.add(lblBook);

		JList recipeList = new JList();
		recipeList.setLocation(0, 73);
		recipeList.setSize(new Dimension(200, 215));
		recipeList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		getContentPane().add(recipeList);

		JScrollPane recipeViewPane = new JScrollPane();
		recipeViewPane.setLocation(210, 73);
		recipeViewPane.setSize(new Dimension(574, 215));
		getContentPane().add(recipeViewPane);

		recipeViewField = new JTextField();
		recipeViewField.setEditable(false);
		recipeViewPane.setViewportView(recipeViewField);
		recipeViewField.setColumns(10);

		JButton btnAddRecipe = new JButton("Add Recipe");
		btnAddRecipe.setBounds(794, 91, 121, 23);
		getContentPane().add(btnAddRecipe);

		JButton btnRemoveRecipe = new JButton("Remove Recipe");
		btnRemoveRecipe.setBounds(794, 143, 121, 23);
		getContentPane().add(btnRemoveRecipe);

		JLabel lblIngredients = new JLabel("Ingredients");
		lblIngredients.setBounds(10, 299, 77, 14);
		getContentPane().add(lblIngredients);

		JList ingredientsList = new JList();
		ingredientsList.setBounds(20, 324, 165, 159);
		getContentPane().add(ingredientsList);

		JButton btnAddIngredient = new JButton("Add");
		btnAddIngredient.setBounds(200, 321, 121, 23);
		getContentPane().add(btnAddIngredient);

		JButton btnRemoveIngredient = new JButton("Remove");
		btnRemoveIngredient.setBounds(200, 355, 121, 23);
		getContentPane().add(btnRemoveIngredient);

		JButton btnAddStep = new JButton("Add");
		btnAddStep.setBounds(521, 321, 121, 23);
		getContentPane().add(btnAddStep);

		JButton btnRemoveStep = new JButton("Remove");
		btnRemoveStep.setBounds(521, 355, 121, 23);
		getContentPane().add(btnRemoveStep);

		JList stepsList = new JList();
		stepsList.setBounds(341, 324, 165, 159);
		getContentPane().add(stepsList);

		JLabel lblSteps = new JLabel("Steps");
		lblSteps.setBounds(331, 299, 77, 14);
		getContentPane().add(lblSteps);

		JButton btnAddTag = new JButton("Add");
		btnAddTag.setBounds(842, 321, 121, 23);
		getContentPane().add(btnAddTag);

		JButton btnRemoveTag = new JButton("Remove");
		btnRemoveTag.setBounds(842, 355, 121, 23);
		getContentPane().add(btnRemoveTag);

		JList tagsList = new JList();
		tagsList.setBounds(662, 324, 165, 159);
		getContentPane().add(tagsList);

		JLabel lblTags = new JLabel("Tags");
		lblTags.setBounds(652, 299, 77, 14);
		getContentPane().add(lblTags);
	}

	/**
	 * Serial ID.
	 */
	private static final long serialVersionUID = 4787001417942411020L;
	private JTextField recipeViewField;
	private JTextField recipeBookNameField;

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
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new RecipeBookEditor().setVisible(true);
	
	}
}
