package recipebook.data;

import java.math.BigDecimal;

import recipebook.data.IngredientMeasurement.Units;

/**
 * Recipe ingredient (ingredient + measurement).
 * 
 * @author Andrew
 *
 */
public class RecipeIngredient {

	/**
	 * Ingredient.
	 */
	private BasicIngredient myIngredient;

	/**
	 * Measurement.
	 */
	private IngredientMeasurement myMeasurement;

	/**
	 * Construct from save string.
	 * 
	 * @param input
	 */
	public RecipeIngredient(String input) {
		String[] inputParts = input.split(Separators.DATA_PART_SEPARATOR);
		// TODO check part count

		// ingredient
		String ingredientName = inputParts[0];
		myIngredient = new BasicIngredient(ingredientName);

		// ingredient quantity
		// TODO num format exception
		BigDecimal ingredientQuantity = BigDecimal.valueOf(Double.parseDouble(inputParts[1]));
		// ingredient units
		// TODO enum exception
		Units ingredientUnits = Units.valueOf(inputParts[2]);

		myMeasurement = new IngredientMeasurement(ingredientQuantity, ingredientUnits);
	}

	/**
	 * Output to a save string.
	 * 
	 * @return
	 */
	public String toSaveString() {
		StringBuilder saveString = new StringBuilder(myIngredient.getName());
		saveString.append(Separators.DATA_PART_SEPARATOR);
		saveString.append(myMeasurement.getQuantity().toString());
		saveString.append(Separators.DATA_PART_SEPARATOR);
		saveString.append(myMeasurement.getUnits().toString());

		return saveString.toString();
	}

	/**
	 * Construct from object data.
	 * 
	 * @param iIngredient
	 * @param iMeasurement
	 */
	public RecipeIngredient(BasicIngredient iIngredient, IngredientMeasurement iMeasurement) {
		myIngredient = iIngredient;
		myMeasurement = iMeasurement;
	}

	public BasicIngredient getIngredient() {
		return myIngredient;
	}

	public void setIngredient(BasicIngredient iIngredient) {
		this.myIngredient = iIngredient;
	}

	public IngredientMeasurement getMeasurement() {
		return myMeasurement;
	}

	public void setMeasurement(IngredientMeasurement iMeasurement) {
		this.myMeasurement = iMeasurement;
	}

	/**
	 * Printing string.
	 */
	public String toPrintString() {
		StringBuilder out = new StringBuilder(myIngredient.getName());
		out.append(", ");

		Units units = myMeasurement.getUnits();
		if (units.equals(Units.NEED)) {
			// "as needed" so just print out
			out.append(units);
			return out.toString();
		} else {
			// not "as needed"
			out.append(myMeasurement.getQuantity().toString());
			out.append(" ");
			out.append(units);
			return out.toString();
		}
	}

	/**
	 * Convenience for lists.
	 */
	@Override
	public String toString() {
		return toPrintString();
	}
}
