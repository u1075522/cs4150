package cs4150;

import java.util.HashMap;
import java.util.Scanner;

public class AS10test1 {

	static HashMap<String, Integer> cache;

	public static void main(String arg[]) {
		Scanner scar = new Scanner(System.in);
		cache = new HashMap<String, Integer>();
		int numRow = scar.nextInt();
		int k = scar.nextInt();

		int[][] datakeeper = new int[numRow][2];

		for (int i = 0; i < numRow; i++) {
			datakeeper[i][0] = scar.nextInt();
			datakeeper[i][1] = scar.nextInt();
		}
		// for arr[x][y][z], x is row x, y is y have been closed, k = -1 is no close,
		// k = 0 is left close, k = 1 is right close. z is for keeping data
		// int[][][] arr = new int[numRow][k + 1][3];

		// arr[0][0][0] = datakeeper[0][0] + datakeeper[0][1];
		// arr[0][1][1] = datakeeper[0][1]

		int result = maxValue(numRow, 0, -1, k, datakeeper);
		System.out.println(result);
		scar.close();
	}

	public static int maxValue(int N, int r, int unclosedRoom, int k, int[][] datakeeper) {
		String str = r + "," + unclosedRoom + "," + k;
		int max = 0;
		if (cache.containsKey(str)) {
			return cache.get(str);
		}
		if (r < N) {
			if (k == N - r) {
				if (unclosedRoom == 0) {
					max = datakeeper[r][0] + maxValue(N, r + 1, 0, k - 1, datakeeper);
				} else if (unclosedRoom == 1) {
					max = datakeeper[r][1] + maxValue(N, r + 1, 1, k - 1, datakeeper);
				} else {
					max = Math.max(datakeeper[r][0] + maxValue(N, r + 1, 0, k - 1, datakeeper),
							datakeeper[r][1] + maxValue(N, r + 1, 1, k - 1, datakeeper));
				}
			} else if (k < N - r) {
				if (unclosedRoom == 0) {
					max = Math.max(datakeeper[r][0] + maxValue(N, r + 1, 0, k - 1, datakeeper),
							datakeeper[r][0] + datakeeper[r][1] + maxValue(N, r + 1, -1, k, datakeeper));
				} else if (unclosedRoom == 1) {
					max = Math.max(datakeeper[r][1] + maxValue(N, r + 1, 1, k - 1, datakeeper),
							datakeeper[r][0] + datakeeper[r][1] + maxValue(N, r + 1, -1, k, datakeeper));
				} else {
					max = Math.max(datakeeper[r][0] + datakeeper[r][1] + maxValue(N, r + 1, -1, k, datakeeper),
							Math.max(datakeeper[r][0] + maxValue(N, r + 1, 0, k - 1, datakeeper),
									datakeeper[r][1] + maxValue(N, r + 1, 1, k - 1, datakeeper)));
				}
			}
			cache.put(str, max);
			return max;
		} else {
			return 0;
		}
	}
}
