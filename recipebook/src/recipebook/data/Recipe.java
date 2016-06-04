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
	 * Block index for name + servings.
	 */
	private static final int NAME_SERVINGS_BLOCK_INDEX = 0;

	/**
	 * Block index for ingredients.
	 */
	private static final int INGREDIENTS_BLOCK_INDEX = 1;

	/**
	 * Block index for steps.
	 */
	private static final int STEPS_BLOCK_INDEX = 2;

	/**
	 * Block index for tags.
	 */
	private static final int TAGS_BLOCK_INDEX = 3;

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

	/**
	 * Construct from save string.
	 * 
	 * @param input
	 */
	public Recipe(String input) {
		String[] inputParts = input.split(Separators.RECIPE_BLOCK_SEPARATOR);

		// TODO element count

		// name and servings
		String[] nameServingsParts = inputParts[NAME_SERVINGS_BLOCK_INDEX].split(Separators.DATA_PART_SEPARATOR);
		// TODO element count
		// name
		myName = nameServingsParts[0];
		// servings
		// TODO number format exception
		myServings = Integer.parseInt(nameServingsParts[1]);

		// ingredients
		String[] ingredientsParts = inputParts[INGREDIENTS_BLOCK_INDEX].split(Separators.DATA_SEPARATOR);
		for (int ingredientsIndex = 0; ingredientsIndex < ingredientsParts.length; ingredientsIndex++) {
			myIngredients.add(new RecipeIngredient(ingredientsParts[ingredientsIndex]));
		}

		// steps
		String[] stepsParts = inputParts[STEPS_BLOCK_INDEX].split(Separators.DATA_SEPARATOR);
		for (int stepsIndex = 0; stepsIndex < stepsParts.length; stepsIndex++) {
			mySteps.add(new RecipeStep(stepsParts[stepsIndex]));
		}

		// tags
		String[] tagsParts = inputParts[TAGS_BLOCK_INDEX].split(Separators.DATA_SEPARATOR);
		for (int tagsIndex = 0; tagsIndex < tagsParts.length; tagsIndex++) {
			myTags.add(tagsParts[tagsIndex]);
		}
	}

	/**
	 * Output to a save string.
	 * 
	 * @return
	 */
	public String toSaveString() {
		// name and servings
		StringBuilder saveString = new StringBuilder(myName);
		saveString.append(Separators.DATA_PART_SEPARATOR);
		saveString.append(myServings);

		// ingredients
		saveString.append(Separators.RECIPE_BLOCK_SEPARATOR);
		if (!myIngredients.isEmpty()) {
			saveString.append(myIngredients.get(0).toSaveString());
			for (int ingredientsIndex = 1; ingredientsIndex < myIngredients.size(); ingredientsIndex++) {
				saveString.append(Separators.DATA_SEPARATOR);
				saveString.append(myIngredients.get(ingredientsIndex).toSaveString());
			}
		}

		// steps
		saveString.append(Separators.RECIPE_BLOCK_SEPARATOR);
		if (!mySteps.isEmpty()) {
			saveString.append(mySteps.get(0).toSaveString());
			for (int stepsIndex = 1; stepsIndex < mySteps.size(); stepsIndex++) {
				saveString.append(Separators.DATA_SEPARATOR);
				saveString.append(mySteps.get(stepsIndex).toSaveString());
			}
		}

		// tags
		saveString.append(Separators.RECIPE_BLOCK_SEPARATOR);
		if (!myTags.isEmpty()) {
			saveString.append(myTags.get(0));
			for (int tagsIndex = 1; tagsIndex < myTags.size(); tagsIndex++) {
				saveString.append(Separators.DATA_SEPARATOR);
				saveString.append(myTags.get(tagsIndex));
			}
		}

		return saveString.toString();
	}

	/**
	 * Construct from object data.
	 * 
	 * @param iName
	 * @param iServings
	 */
	public Recipe(String iName, int iServings) {
		myName = iName;
		myServings = iServings;
	}

	/**
	 * @return total recipe time, in minutes.
	 */
	public int getTotalTime() {
		int totalTime = 0;
		for (RecipeStep step : mySteps) {
			totalTime += step.getTime();
		}

		return totalTime;
	}

	public String getName() {
		return myName;
	}

	public void setName(String iName) {
		this.myName = iName;
	}

	public int getServings() {
		return myServings;
	}

	public void setServings(int iServings) {
		this.myServings = iServings;
	}

	public List<String> getTags() {
		return myTags;
	}

	public List<RecipeStep> getSteps() {
		return mySteps;
	}

	public List<RecipeIngredient> getIngredients() {
		return myIngredients;
	}
}
