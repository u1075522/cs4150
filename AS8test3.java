package cs4150;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class AS8test3 {
	public static void main(String arg[]) {
		Scanner scar = new Scanner(System.in);

		String[] firstLine = scar.nextLine().split(" ");
		ArrayList<Integer>[] dataKeeper = new ArrayList[Integer.parseInt(firstLine[1])];

		for (int k = 0; k < dataKeeper.length; k++) {
			dataKeeper[k] = new ArrayList<Integer>();
		}

		int[] resultArr = new int[Integer.parseInt(firstLine[1])];

		for (int p = 0; p < Integer.parseInt(firstLine[0]); p++) {
			String[] line = scar.nextLine().split(" ");
			int money = Integer.parseInt(line[0]);
			int min = Integer.parseInt(line[1]);

			dataKeeper[min].add(money);
		}

		ArrayList<Integer> sorting = new ArrayList<Integer>();

		for (int i = resultArr.length - 1; i > -1; i--) {
			sorting.addAll(dataKeeper[i]);
			Collections.sort(sorting);
			int max = 0;
			if (!sorting.isEmpty()) {
				max = sorting.get(sorting.size() - 1);
				resultArr[i] = max;
				sorting.remove(sorting.size() - 1);
			}

		}

		int result = 0;
		for (int j = 0; j < resultArr.length; j++) {
			result += resultArr[j];
		}

		System.out.println(result);
		scar.close();
	}
}
