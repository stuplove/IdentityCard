package IdentityCard;

import java.util.HashMap;
import java.util.Map;

public class Check {
	private static java.util.Scanner scn;
	static String IdentityCard = "B120863158";

	public static void main(String[] args) {

		String in = input();
		if (in != null)
			IdentityCard = in;

		try {
			System.out.println(checkIdentityCard(IdentityCard) ? "Success" : "Fail");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	private static String input() {
		scn = new java.util.Scanner(System.in);
		System.out.print("請輸入要驗證的身分證字號：");
		String enuma = scn.next();

		System.out.println("你輸入的身分證字號為： " + enuma);
		return enuma;
	}

	final static int[] DecryptionNum = { 1, 9, 8, 7, 6, 5, 4, 3, 2, 1, 1 };

	public static boolean checkIdentityCard(String identityCard) throws Exception {
		// 輸入為身分證字號
		// 判斷第一碼英文轉換來的數字，初始化數字陣列
		// 對數字陣列做公式計算
		// 判斷最後結果是否正確

		int[] checkNum = new int[DecryptionNum.length];// 11

		String[] identityCardStrArray = identityCard.split("");

		String counties = identityCardStrArray[0];
		Integer countiesNum = getCountiesInt(counties);
		if (countiesNum == null)
			throw new Exception("Error 請輸入大寫英文");

		initCheckNum(checkNum, countiesNum, identityCardStrArray);

		return checkMod(checkNum, DecryptionNum);
	}

	private static void initCheckNum(int[] checkNum, int countiesNum, String[] temp) {
		checkNum[0] = digit(countiesNum, 1);
		checkNum[1] = digit(countiesNum, 0);

		for (int i = 1; i < temp.length; i++) {
			checkNum[i + 1] = Integer.valueOf(temp[i]);
		}

	}

	private static boolean checkMod(int[] checkNumArray, int[] xxNumArray) {
		int total = 0;
		for (int i = 0; i < checkNumArray.length; i++) {
			total += checkNumArray[i] * xxNumArray[i];
		}

		return (total % 10) == 0;
	}

	final static String[] countiesStr = { "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q",
			"R", "S", "T", "U", "V", "X", "Y", "W", "Z", "I", "O" };
	final static Map<String, Integer> countiesMap = new HashMap<String, Integer>();

	static {
		int start = 10;
		int end = start + countiesStr.length - 1;
		for (int i = start; i <= end; i++) {
			countiesMap.put(countiesStr[i - 10], i);
			countiesMap.put(countiesStr[i - 10].toLowerCase(), i);
		}
	}

	private static Integer getCountiesInt(String counties) {

		return countiesMap.get(counties);
	}

	public static int digit(int number, int n) {
		return (int) (number / Math.pow(10, n) % 10);
	}
}
