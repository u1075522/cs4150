package cs4150;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.TreeSet;

public class AS5test1 {

	public static class Node {
		TreeSet<String> friends;
		int checked = 0;
		int day = 0;
		Node() {
			friends = new TreeSet<String>();
		}

	}

	@SuppressWarnings("unchecked")
	public static void main(String arg[]) {
		Scanner scar = new Scanner(System.in);

		// get the student number
		int studentNum = Integer.parseInt(scar.nextLine());
		HashMap<String, Node> personFriendsSet = new HashMap<String, Node>();
		TreeSet<String> noFriends = new TreeSet<String>();

		// for loop adding names to the hashmap
		for (int i = 0; i < studentNum; i++) {
			String tem = scar.nextLine();
			personFriendsSet.put(tem, new Node());
			noFriends.add(tem);
		}

		// get friends groups
		int friendsNum = Integer.parseInt(scar.nextLine());

		// for loop adding friends groups to the hashmap
		for (int j = 0; j < friendsNum; j++) {
			String friendsSet = scar.nextLine();
			String[] friendsSetArr = friendsSet.split(" ");
			// adding both of them
			personFriendsSet.get(friendsSetArr[0]).friends.add(friendsSetArr[1]);
			personFriendsSet.get(friendsSetArr[1]).friends.add(friendsSetArr[0]);
		}
		// Collections.sort(noFriends);

		int startPersonNum = Integer.parseInt(scar.nextLine());
		ArrayList<String> startpersons = new ArrayList<String>();
		for (int k = 0; k < startPersonNum; k++) {
			startpersons.add(scar.nextLine());

		}

		for (int l = 0; l < startpersons.size(); l++) {
			LinkedList<Node> queue = new LinkedList<Node>();
			TreeSet<String> tempset = new TreeSet<String>();
			tempset = (TreeSet<String>) noFriends.clone();
			System.out.println();
			System.out.print(startpersons.get(l));
			tempset.remove(startpersons.get(l));
			queue.add(personFriendsSet.get(startpersons.get(l)));
			personFriendsSet.get(startpersons.get(l)).checked++;
			personFriendsSet.get(startpersons.get(l)).day = 0;
			TreeSet<String> forPrinting = new TreeSet<String>();
			int daycount = 0;
			while (queue.size() != 0) {
				Node temp = queue.poll();
				//temp.checked++;
				//Collections.sort(temp.friends);
				if(temp.day != daycount) {
					daycount++;
					Iterator<String> itr2 = forPrinting.iterator();
					while(itr2.hasNext()) {
						System.out.print(" " + itr2.next());
					}
					forPrinting = new TreeSet<String>();
				}
				Iterator<String> itr = temp.friends.iterator();
				while(itr.hasNext()) {
					String singleFriend = itr.next();
					if(personFriendsSet.get(singleFriend).checked == l) {
						//System.out.print(" " + singleFriend);
						forPrinting.add(singleFriend);
						personFriendsSet.get(singleFriend).checked++;
						personFriendsSet.get(singleFriend).day = temp.day+1;

						tempset.remove(singleFriend);
						queue.add(personFriendsSet.get(singleFriend));
					}
				}
			}
			for (String str : tempset) {
				personFriendsSet.get(str).checked++;
				System.out.print(" " + str);
			}
		}
		scar.close();
	}
}
