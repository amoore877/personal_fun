package recipebook.data;

import java.util.ArrayList;
import java.util.List;

/**
 * A complete recipe.
 * 
 * @author Andrew
 *
 */
public class Recipe {

	/**
	 * Name of recipe.
	 */
	private String myName;

	/**
	 * Search tags for recipe.
	 */
	private final List<String> myTags = new ArrayList<String>();

	/**
	 * Steps for the recipe.
	 */
	private final List<RecipeStep> mySteps = new ArrayList<RecipeStep>();

	/**
	 * Recipe ingredients.
	 */
	private final List<RecipeIngredient> myIngredients = new ArrayList<RecipeIngredient>();

	/**
	 * Servings of this recipe.
	 */
	private int myServings;

	// TODO get total time from steps

	// TODO add/remove tags

	// TODO add/remove steps

	// TODO add/remove ingredients
}
