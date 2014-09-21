package et;


public class DataUtils {

	public static int wordToInt(byte[] src, int offset) {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			int val = (src[offset + i] << (3 - i) * 8) & 0xff << (3 - i) * 8;
			sum |= val;
		}
		return sum;
	}

	public static byte[] intToWord(int val) {
		byte[] result = new byte[4];
		for (int i = 0; i < 4; i++) {
			result[i] = 0;
			result[i] |= ((val & (0xff << (3 - i) * 8)) >> (3 - i) * 8);
		}
		return result;
	}

}
