package recipebook.data;

/**
 * Basic ingredient (just the ingredient).
 * 
 * @author Andrew
 *
 */
public class BasicIngredient {

	/**
	 * Constant for "whatever works for you" for a part of a recipe (skip when
	 * searching).
	 */
	public static final String WHATEVER = "whatever";

	/**
	 * Name of ingredient.
	 */
	private String myName;

	/**
	 * Constructor. If name matches "whatever" "we" "w/e" or "w\e" ignoring
	 * case, {@link #WHATEVER} will be used.
	 * 
	 * @param iName
	 */
	public BasicIngredient(String iName) {
		if (iName.equalsIgnoreCase(WHATEVER) || iName.equalsIgnoreCase("we") || iName.equalsIgnoreCase("w\\e")
				|| iName.equalsIgnoreCase("w/e")) {
			myName = WHATEVER;
		} else {
			myName = iName;
		}
	}

	public String getName() {
		return myName;
	}

	public void setName(String iName) {
		this.myName = iName;
	}
}
