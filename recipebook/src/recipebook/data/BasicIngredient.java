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
	 * Constructor. Trim and replace new lines with spaces. If name matches
	 * "whatever" "we" "w/e" or "w\e" ignoring case, {@link #WHATEVER} will be
	 * used.
	 * 
	 * @param iName
	 */
	public BasicIngredient(String iName) {
		setName(iName);
	}

	public String getName() {
		return myName;
	}

	/**
	 * Trim and replace new lines with spaces. If name matches "whatever" "we"
	 * "w/e" or "w\e" ignoring case, {@link #WHATEVER} will be used.
	 * 
	 * @param iName
	 */
	public void setName(String iName) {
		// TODO empty name
		String trimmedName = iName.replace("\n", " ").trim();
		if (trimmedName.equalsIgnoreCase(WHATEVER) || trimmedName.equalsIgnoreCase("we")
				|| trimmedName.equalsIgnoreCase("w\\e") || trimmedName.equalsIgnoreCase("w/e")) {
			myName = WHATEVER;
		} else {
			myName = trimmedName;
		}
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof BasicIngredient) {
			if (myName.equalsIgnoreCase(((BasicIngredient) other).getName())) {
				return true;
			}
		}

		return false;
	}
}
