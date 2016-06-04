package recipebook.data;

/**
 * Separators for the various data.
 * 
 * @author Andrew
 *
 */
public class Separators {

	/**
	 * Separator between recipes.
	 * 
	 * Example for file containing N recipes:<br>
	 * Recipe1<br>
	 * ~~~~~<br>
	 * Recipe2<br>
	 * ~~~~~<br>
	 * RecipeN
	 */
	public static final String RECIPE_SEPARATOR = "~~~~~";

	/**
	 * Separator between name = serving, ingredient, step, and tag blocks.
	 * 
	 * Example for a single recipe:<br>
	 * RecipeNameServing<br>
	 * =====<br>
	 * Ingredients<br>
	 * =====<br>
	 * Steps<br>
	 * =====<br>
	 * Tags
	 */
	public static final String RECIPE_BLOCK_SEPARATOR = "=====";

	/**
	 * Separator between different printed objects.
	 */
	public static final String DATA_SEPARATOR = "@@@@@";

	/**
	 * Separator between data parts of an object.
	 */
	public static final String DATA_PART_SEPARATOR = "#####";
}
