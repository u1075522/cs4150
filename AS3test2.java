package cs4150;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class AS3test2 {
	public static void main(String arg[]) {
		Scanner scar = new Scanner(System.in);
		String[] firstline = scar.nextLine().split(" ");

		Long distance = Long.valueOf(Integer.parseInt(firstline[0]));
		int starnum = Integer.parseInt(firstline[1]);
		String[] positionKeeper = new String[starnum];

		for (int i = 0; i < starnum; i++) {
			String temp = scar.nextLine();
			positionKeeper[i] = temp;
		}
		String result = helper(0, starnum, positionKeeper, distance);

		String[] resultArr = result.split(" ");
		int resultInt = Integer.parseInt(resultArr[2]);
		if (result.equals("No")) {
			System.out.println("No");
		} else if (resultInt == 0) {
			System.out.println("No");
		} else {
//			resultInt++;
			if (resultInt > starnum / 2) {
				System.out.println(resultInt);
			} else {
				System.out.println("No");
			}
		}
		scar.close();
	}

	public static String helper(int start, int end, String[] strarr, Long distance) {
		if (end - start == 0) {
			return "No";
		} else if (end - start == 1) {
			// strarr[start] = strarr[start] + " " + "1";
			return strarr[start] + " " + "1";
		}

		else {
			int mid = (start + end + 1) / 2;
			String x = helper(start, mid, strarr, distance);
			String y = helper(mid, end, strarr, distance);
			if (x.equals("No") && y.equals("No")) {
				return "No";
			}
			// count occurrences of y in up sub-array
			else if (x == "No") {
				String[] yMajority = y.split(" ");
				Long xPos = Long.valueOf(Integer.parseInt(yMajority[0]));
				Long yPos = Long.valueOf(Integer.parseInt(yMajority[1]));
				int starCount = Integer.parseInt(yMajority[2]);
				for (int i = start; i < mid; i++) {
					String[] yMajorityInx = strarr[i].split(" ");
					Long xPos2 = Long.valueOf(Integer.parseInt(yMajorityInx[0]));
					Long yPos2 = Long.valueOf(Integer.parseInt(yMajorityInx[1]));
					if ((xPos - xPos2) * (xPos - xPos2) + (yPos - yPos2) * (yPos - yPos2) <= distance * distance) {
						starCount++;
					}
				}
				return xPos + " " + yPos + " " + starCount;
			}
			// count occurrences of x in up sub-array
			else if (y == "No") {
				String[] xMajority = x.split(" ");
				Long xPos = Long.valueOf(Integer.parseInt(xMajority[0]));
				Long yPos = Long.valueOf(Integer.parseInt(xMajority[1]));
				int starCount = Integer.parseInt(xMajority[2]);
				for (int i = start; i < mid; i++) {
					String[] xMajorityIny = strarr[i].split(" ");
					Long xPos2 = Long.valueOf(Integer.parseInt(xMajorityIny[0]));
					Long yPos2 = Long.valueOf(Integer.parseInt(xMajorityIny[1]));
					if ((xPos - xPos2) * (xPos - xPos2) + (yPos - yPos2) * (yPos - yPos2) <= (distance * distance)) {
						starCount++;
					}
				}
				return xPos + " " + yPos + " " + starCount;
			}
			// count occurrences of x, y or return No
			else {
				String[] yyMajority = y.split(" ");
				Long xxPos = Long.valueOf(Integer.parseInt(yyMajority[0]));
				Long yyPos = Long.valueOf(Integer.parseInt(yyMajority[1]));
				int starCount = Integer.parseInt(yyMajority[2]);

				String[] xMajority = x.split(" ");
				Long xPos = Long.valueOf(Integer.parseInt(xMajority[0]));
				Long yPos = Long.valueOf(Integer.parseInt(xMajority[1]));
				int starCount2 = Integer.parseInt(xMajority[2]);

				for (int i = start; i < mid; i++) {
					String[] yyMajorityInx = strarr[i].split(" ");
					Long xxPos2 = Long.valueOf(Integer.parseInt(yyMajorityInx[0]));
					Long yyPos2 = Long.valueOf(Integer.parseInt(yyMajorityInx[1]));
					if ((xxPos - xxPos2) * (xxPos - xxPos2)
							+ (yyPos - yyPos2) * (yyPos - yyPos2) <= (distance * distance)) {
						starCount++;
					}
				}

//				int test = 0;
//				if (end - mid == 2) {
//					test = end;
//				} else {
//					test = end + 1;
//				}
				for (int i = mid; i < end; i++) {
					String[] xMajorityIny = strarr[i].split(" ");
					Long xPos2 = Long.valueOf(Integer.parseInt(xMajorityIny[0]));
					Long yPos2 = Long.valueOf(Integer.parseInt(xMajorityIny[1]));
					if ((xPos - xPos2) * (xPos - xPos2) + (yPos - yPos2) * (yPos - yPos2) <= (distance * distance)) {

						starCount2++;
					}
				}

				if (starCount >= starCount2) {
					return xxPos + " " + yyPos + " " + starCount;
				} else {
					return xPos + " " + yPos + " " + starCount2;
				}

			}
		}
	}
}
