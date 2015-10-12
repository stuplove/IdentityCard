package IdentityCard;

public class Random {
	public static void main(String[] args) throws Exception {
		System.out.println("return : " + getIdentityCardId());
	}

	private static String getIdentityCardId() throws Exception {
		String IdentityCard = "";
		int count = 0;

		do {
			count++;
			// 取得隨機英文數字
			IdentityCard = "" + getCountiesChar();
			IdentityCard += getRandomNum(1, 2);

			// 配上8碼隨機0~9數字
			for (int i = 0; i < 8; i++) {
				IdentityCard += getNum();
			}
		} // 驗證失敗則繼續隨機產生
		while (!Check.checkIdentityCard(IdentityCard));

		System.out.println("try random IdentityCardId count = " + count);

		return IdentityCard;
	}

	static char getCountiesChar() {
		return (char) getRandomNum(65, 90);
	}

	public static int digit(int number, int n) {
		return (int) (number / Math.pow(10, n) % 10);
	}

	static int getNum() {
		return getRandomNum(0, 9);
	}

	static int getRandomNum(int MIN, int MAX) {
		return new java.util.Random().nextInt(MAX - MIN + 1) + MIN;
	}
}
