package cs4150;

import java.util.HashMap;
import java.util.Scanner;

public class AS9test2 {
	public static void main(String arg[]) {
		Scanner scar = new Scanner(System.in);
		// get the total number of hotels
		int hotelNum = Integer.parseInt(scar.nextLine()) + 1;
		// creating an array to keep data
		int[] hotelKeeper = new int[hotelNum];

		// a loop to get all data into the hotelkeeper array
		for (int i = 0; i < hotelNum; i++) {
			String temp = scar.nextLine();
			hotelKeeper[i] = Integer.parseInt(temp);
		}
		scar.close();

		int[][] resultArr = new int[hotelNum][hotelNum];
//		for (int i = 0; i < hotelNum; i++) {
//			for (int j = 0; j < hotelNum; j++) {
//				resultArr[i][j] = 160000;
//			}
//		}

		for (int i = 0; i < hotelNum; i++) {
			for (int j = i; j < hotelNum; j++) {

				// getting the first line as the basic case
				if (i == 0) {
					if (j == 0) {
						resultArr[i][j] = 0;
					} else {
						resultArr[i][j] = (400 - (hotelKeeper[j] - hotelKeeper[i]))
								* (400 - (hotelKeeper[j] - hotelKeeper[i]));
					}
				} else {

					// if it comes from above level, no adding, if not, adding previous
					if (i == j) {
						resultArr[i][j] = resultArr[i - 1][j];
					}

					else {
						int temp = (400 - (hotelKeeper[j] - hotelKeeper[i])) * (400 - (hotelKeeper[j] - hotelKeeper[i]))
								+ resultArr[i][i];
						// if it is bigger or equal than above level, no change
						if (temp >= resultArr[i - 1][j]) {
							resultArr[i][j] = resultArr[i - 1][j];
						}
						// change it otherwise
						else {
							resultArr[i][j] = temp;
						}
					}

				}
			}
		}

		System.out.println(resultArr[hotelNum - 1][hotelNum - 1]);
	}
}
