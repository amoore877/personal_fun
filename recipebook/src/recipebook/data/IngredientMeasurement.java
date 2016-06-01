package recipebook.data;

import java.math.BigDecimal;

/**
 * Measurement of an ingredient.
 * 
 * @author Andrew
 *
 */
public class IngredientMeasurement {

	/**
	 * Quantity.
	 */
	private BigDecimal myQuantity;

	/**
	 * Units.
	 */
	private Units myUnits;

	public IngredientMeasurement(BigDecimal iQuantity, Units iUnits) {
		myQuantity = iQuantity;
		myUnits = iUnits;
	}

	public BigDecimal getQuantity() {
		return myQuantity;
	}

	public void setQuantity(BigDecimal iQuantity) {
		this.myQuantity = iQuantity;
	}

	public Units getUnits() {
		return myUnits;
	}

	public void setUnits(Units iUnits) {
		this.myUnits = iUnits;
	}

	/**
	 * Units enum.
	 * 
	 * @author Andrew
	 *
	 */
	public enum Units {
		/**
		 * No units.
		 */
		UNITLESS("1"),

		/**
		 * Cups.
		 */
		CUPS("cups"),

		/**
		 * Ounces.
		 */
		OUNCES("oz"),

		/**
		 * Fluid ounces.
		 */
		FLUID_OUNCES("fl. oz"),

		/**
		 * Pounds.
		 */
		POUNDS("lbs"),

		/**
		 * Kilograms.
		 */
		KILOGRAMS("kg"),

		/**
		 * Grams.
		 */
		GRAMS("g"),

		/**
		 * Milligrams.
		 */
		MILLIGRAMS("mg"),

		/**
		 * Teaspoon.
		 */
		TEASPOON("tsp"),

		/**
		 * Tablespoon.
		 */
		TABLESPOON("tbsp"),

		/**
		 * Quart.
		 */
		QUART("qt"),

		/**
		 * Gallon.
		 */
		GALLON("gal"),

		/**
		 * Liter.
		 */
		LITER("L"),

		/**
		 * Deciliter.
		 */
		DECILITER("dL"),

		/**
		 * Milliliter.
		 */
		MILLILITER("mL"),

		/**
		 * As needed (small amounts).
		 */
		NEED("as needed");

		private final String myText;

		private Units(String iText) {
			myText = iText;
		}

		@Override
		public String toString() {
			return myText;
		}
	}
}
