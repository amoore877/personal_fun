package recipebook.data;

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

	@Override
	public String toString() {
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
}
