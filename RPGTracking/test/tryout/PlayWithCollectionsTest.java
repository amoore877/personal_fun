package tryout;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;

public class PlayWithCollectionsTest {

	private class TestInteger {

		private int value;

		public TestInteger(final int iValue) {
			value = iValue;
		}

		protected int getValue() {
			return value;
		}

		protected void setValue(final int value) {
			this.value = value;
		}
	}

	@Test
	public void multiArrayTest() {
		final int firstValue = 5;
		final int secondValue = 10;

		final TestInteger first = new TestInteger(firstValue);
		final TestInteger second = new TestInteger(secondValue);

		final ArrayList<TestInteger> list1 = new ArrayList<TestInteger>();
		list1.add(first);
		final ArrayList<TestInteger> list2 = new ArrayList<TestInteger>();
		list2.add(first);
		list2.add(second);

		assertTrue(list1.get(0).getValue() == firstValue);
		assertTrue(list2.get(0).getValue() == firstValue);
		assertTrue(list2.get(1).getValue() == secondValue);

		final int thirdValue = 21;

		first.setValue(thirdValue);

		assertTrue(list1.get(0).getValue() == thirdValue);
		assertTrue(list2.get(0).getValue() == thirdValue);
	}

	@Test
	public void multiMapTest() {
		final int firstValue = 5;
		final int secondValue = 10;

		final TestInteger first = new TestInteger(firstValue);
		final TestInteger second = new TestInteger(secondValue);

		final HashMap<String, TestInteger> map1 = new HashMap<String, TestInteger>();
		map1.put("first", first);
		final HashMap<String, TestInteger> map2 = new HashMap<String, TestInteger>();
		map2.put("first", first);
		map2.put("second", second);

		assertTrue(map1.get("first").getValue() == firstValue);
		assertTrue(map2.get("first").getValue() == firstValue);
		assertTrue(map2.get("second").getValue() == secondValue);

		final int thirdValue = 21;

		first.setValue(thirdValue);

		assertTrue(map1.get("first").getValue() == thirdValue);
		assertTrue(map2.get("first").getValue() == thirdValue);

		final int fourthValue = 42;

		final Collection<TestInteger> collection = map1.values();

		for (final TestInteger integer : collection) {
			if (integer.getValue() == thirdValue) {
				integer.setValue(fourthValue);
			}
		}

		assertTrue(map1.get("first").getValue() == fourthValue);
		assertTrue(map2.get("first").getValue() == fourthValue);
	}
}
