package cs4150;

import java.math.BigInteger;
import java.util.Scanner;

public class AS7test1 {
	public static void main(String arg[]) {
		Scanner scar = new Scanner(System.in);

		while (scar.hasNext()) {
			String[] str = scar.nextLine().split(" ");
			if (str[0] == "gcd" || str[0].equals("gcd")) {
				Long first = Long.parseLong(str[1]);
				Long second = Long.parseLong(str[2]);
				System.out.println(gcd(first, second));
			} else if (str[0] == "exp" || str[0].equals("exp")) {
				BigInteger first = new BigInteger(str[1]);
				BigInteger second = new BigInteger(str[2]);
				BigInteger third = new BigInteger(str[3]);
				System.out.println(exp(first, second, third));
			} else if (str[0] == "inverse" || str[0].equals("inverse")) {
				System.out.println(inverse(Integer.parseInt(str[1]), Integer.parseInt(str[2])));
			} else if (str[0] == "isprime" || str[0].equals("isprime")) {
				System.out.println(prime(Integer.parseInt(str[1])));
			} else if (str[0] == "key" || str[0].equals("key")) {
				long[] pt = new long[3];
				pt = RSA(Integer.parseInt(str[1]), Integer.parseInt(str[2]));
				System.out.println(pt[0] + " " + pt[1] + " " + pt[2]);
			}
		}

		scar.close();

	}

	public static String prime(long prime) {
		long p = prime - 1;
		BigInteger x = new BigInteger("2");
		BigInteger y = BigInteger.valueOf(p);
		BigInteger N = BigInteger.valueOf(prime);

		if (exp(x, y, N).compareTo(BigInteger.valueOf(1)) == 0) {
			return "yes";
		}
		return "no";
	}

	public static Long gcd(Long num1, Long num2) {
		if (num2 == 0) {
			return num1;
		} else {
			return gcd(num2, num1 % num2);
		}
	}

	public static BigInteger exp(BigInteger x, BigInteger y, BigInteger N) {
		if (y.intValue() == 0) {
			return BigInteger.valueOf(1);
		} else {
			BigInteger z = exp(x, y.divide(BigInteger.valueOf(2)), N);
			BigInteger m = new BigInteger("2");
			if (y.mod(m).compareTo(BigInteger.valueOf(0)) == 0) {
				BigInteger result = new BigInteger("0");
				result = z.multiply(z).mod(N);
				return result;
			} else {
				BigInteger result = new BigInteger("0");
				result = x.multiply(z.multiply(z)).mod(N);
				return result;
			}
		}
	}

	public static String inverse(long num1, long num2) {
		long[] result = ExtendedEuclid(num1, num2);
		if (result[2] == 1) {
			if (result[0] % num2 < 0) {
				return Long.toString(result[0] % num2 + num2);
			} else {
				return Long.toString(result[0] % num2);
			}
		} else {
			return "none";
		}
	}

	public static long[] ExtendedEuclid(long num1, long num2) {
		if (num2 == 0) {
			long[] result = new long[3];
			result[0] = 1;
			result[1] = 0;
			result[2] = num1;
			return result;
		} else {
			long[] result = ExtendedEuclid(num2, num1 % num2);
			long first = result[1];
			long second = result[0] - (num1 / num2) * result[1];
			result[0] = first;
			result[1] = second;

			return result;
		}
	}

	public static long[] RSA(long p, long q) {
		long[] result = new long[3];

		long N = p * q;
		long fy = (p - 1) * (q - 1);
		long num = 0;
		for (int i = 2; i < fy; i++) {
			if (gcd(Long.valueOf(i), Long.valueOf(fy)) == 1) {
				num = i;
				break;
			}
		}

		result[0] = N;
		result[1] = num;
		result[2] = Long.parseLong(inverse(num, fy));

		return result;
	}

}
