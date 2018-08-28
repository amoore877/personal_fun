package leetcode.w86;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Prob2VisitRooms {
	public static void main(String[] args) {
		List<List<Integer>> rooms = new ArrayList<>();
		List<Integer> room = new ArrayList<>();
		room.add(1);
		rooms.add(room);
		room = new ArrayList<>();
		room.add(2);
		rooms.add(room);
		room = new ArrayList<>();
		room.add(3);
		rooms.add(room);
		room = new ArrayList<>();
		rooms.add(room);

		new Prob2VisitRooms().canVisitAllRooms(rooms);
	}

	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		Set<Integer> keys = new HashSet<>();
		keys.add(0);

		traverseRooms(keys, 0, rooms);

		if (keys.size() == rooms.size()) {
			return true;
		} else {
			return false;
		}
	}

	private void traverseRooms(Set<Integer> keys, int startRoom, List<List<Integer>> rooms) {
		for (Integer key : rooms.get(startRoom)) {
			if (!keys.contains(key)) {
				keys.add(key);
				traverseRooms(keys, key, rooms);
			}
		}
	}
}
