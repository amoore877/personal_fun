package recipebook.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Recipe book holding many recipes.
 * 
 * @author Andrew
 *
 */
public class RecipeBook {

	/**
	 * List of recipes.
	 */
	private final List<Recipe> myRecipes = new ArrayList<Recipe>();

	/**
	 * Empty constructor.
	 */
	public RecipeBook() {
	}

	/**
	 * Construct from save string.
	 * 
	 * @param input
	 */
	public RecipeBook(String input) {
		String[] inputParts = input.split(Separators.RECIPE_SEPARATOR);

		// parse out recipes
		for (String recipeString : inputParts) {
			myRecipes.add(new Recipe(recipeString));
		}
	}

	/**
	 * Output to a save string.
	 * 
	 * @return
	 */
	public String toSaveString() {
		StringBuilder saveString = new StringBuilder();

		if (!myRecipes.isEmpty()) {
			saveString.append(myRecipes.get(0).toSaveString());
			for (int recipesIndex = 1; recipesIndex < myRecipes.size(); recipesIndex++) {
				saveString.append(Separators.RECIPE_SEPARATOR);
				saveString.append(myRecipes.get(recipesIndex).toSaveString());
			}
		}

		return saveString.toString();
	}

	public List<Recipe> getRecipes() {
		return myRecipes;
	}
}
