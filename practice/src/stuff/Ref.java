package stuff;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ref {

	public static void main(String[] args) {
		System.out.println("");
		List<Object> x = new ArrayList<>();
		List<Object> yl = new ArrayList<>();
		x.add(2, new Object());
		x.addAll(yl);

		String y = "";
		String a = "" + y.charAt(5);
		y.indexOf("substring");
		y.trim();
		y.substring(2);// , end)
		y.toCharArray();

		Map<Object, Object> z = new HashMap<>();
		z.entrySet();
		z.keySet();
		z.values();
	}

}
