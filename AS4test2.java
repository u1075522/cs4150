package cs4150;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeMap;

public class AS4test2 {
	public static class Node {
		public String startcity;
		public int cost;
		public TreeMap<String, Integer> cityroad = new TreeMap<String, Integer>();

		public Node(String start, int cost) {
			startcity = start;
			this.cost = cost;
		}
		
		public void cityroadAdding(String cityname, int costs) {
			this.cityroad.put(cityname, costs);
		}
	}

	public static class Sink {
		HashMap<String, Node> citylist;
		HashMap<String, Integer> forCompare;

		Sink() {
			citylist = new HashMap<String, Node>();
			forCompare = new HashMap<String, Integer>();
		}

		public void search(String start, String end, int cost) {
			TreeMap<String, Integer> test = citylist.get(start).cityroad;
			for (String str : test.keySet()) {
				int cost2 = test.get(str);
				if (!forCompare.containsKey(str)
						|| (forCompare.containsKey(str) && forCompare.get(str) > cost + cost2)) {
					forCompare.put(str, cost + cost2);
					search(str, end, cost + cost2);
				}
			}
		}
		
		public void citylistaddingcity(String foradding, Node nd) {
			this.citylist.put(foradding, nd);
		}
		
		public void citylistRoadadding(String cityname, String roadname) {
			this.citylist.get(cityname).cityroadAdding(roadname, this.citylist.get(roadname).cost);
		}
	}

	public static void main(String arg[]) {
		Scanner scar = new Scanner(System.in);
		Sink graph = new Sink();

		// this is the number of cities it contains
		int numcity = Integer.parseInt(scar.nextLine());

		for (int i = 0; i < numcity; i++) {
			String citytollstr = scar.nextLine();
			String[] citytollarr = citytollstr.split(" ");
			Node tempNode = new Node(citytollarr[0], Integer.parseInt(citytollarr[1]));
			graph.citylistaddingcity(citytollarr[0], tempNode);
		}

		// this is the number of roads it contains
		int numroad = Integer.parseInt(scar.nextLine());

		// this for loop go through all roads
		for (int j = 0; j < numroad; j++) {
			String roadstr = scar.nextLine();
			String[] roadarr = roadstr.split(" ");
			graph.citylistRoadadding(roadarr[0], roadarr[1]);
		}

		// this is the number of trips
		scar.nextLine();
		// this for loop go through all trips and print out the result
		while(scar.hasNextLine()) {
			String searching = scar.nextLine();
			String[] searcharr = searching.split(" ");
			graph.forCompare = new HashMap<String, Integer>();
			graph.search(searcharr[0], searcharr[1], 0);
			if (searcharr[0].equals(searcharr[1])) {
				System.out.println("0");
			} else if (graph.forCompare.containsKey(searcharr[1])) {
				System.out.println(graph.forCompare.get(searcharr[1]));
			} else {
				System.out.println("No");
			}
		}
		scar.close();
	}

}
