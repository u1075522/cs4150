package cs4150;

import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AS6test1 {
	public static void main(String arg[]) {
		Scanner scar = new Scanner(System.in);
		while (scar.hasNextLine()) {
			String[] nums = scar.nextLine().split(" ");
			if (nums[0].equals("0") && nums[1].equals("0")) {
				break;
			}
			int nodesnum = Integer.parseInt(nums[0]);
			int numinters = Integer.parseInt(nums[1]);
			HashMap<String, Node> hash = new HashMap<String, Node>();
			for (int i = 0; i < numinters; i++) {
				String[] lines = scar.nextLine().split(" ");
				if (!hash.containsKey(lines[0])) {
					Node first = new Node(lines[0]);
					hash.put(lines[0], first);
				}
				if (!hash.containsKey(lines[1])) {
					Node second = new Node(lines[1]);
					hash.put(lines[1], second);
				}
				hash.get(lines[0]).pathsWithWeight.put(lines[1], Double.parseDouble(lines[2]));
				hash.get(lines[1]).pathsWithWeight.put(lines[0], Double.parseDouble(lines[2]));
			}
			PQComparator comparator = new PQComparator();
			PriorityQueue<Node> queue = new PriorityQueue<Node>(comparator);
			hash.get("0").changeOfSize = 1.0f;
			queue.add(hash.get("0"));
			while (!queue.isEmpty()) {
				Node tempnode = queue.poll();
				if (tempnode.NodeName == Integer.toString(nodesnum - 1)) {
					break;
				}
				for (String path : tempnode.pathsWithWeight.keySet()) {
					if (hash.get(path).changeOfSize < (tempnode.changeOfSize * tempnode.pathsWithWeight.get(path))) {
						queue.remove(hash.get(path));
						hash.get(path).changeOfSize = (tempnode.changeOfSize * tempnode.pathsWithWeight.get(path));
						hash.get(path).pathsWithWeight.remove(tempnode.NodeName);
						queue.add(hash.get(path));
					}
				}

			}
			String resultkey = Integer.toString(nodesnum - 1);
			Double resultDouble = hash.get(resultkey).changeOfSize;
			System.out.println(new DecimalFormat("#0.0000").format(resultDouble));
		}

		scar.close();
	}

	public static class Node {
		// a boolean to ensure that this is the first time to check this node
		boolean checked = false;
		// current length
		double changeOfSize;
		String NodeName;
		// paths and corresponding weight
		HashMap<String, Double> pathsWithWeight = new HashMap<String, Double>();

		Node(String value) {
			changeOfSize = 0.0f;
			this.NodeName = value;
		}
	}

	public static class PQComparator implements Comparator<Node> {

		@Override
		public int compare(Node o1, Node o2) {
			return Double.compare(o2.changeOfSize, o1.changeOfSize);

		}

	}
}