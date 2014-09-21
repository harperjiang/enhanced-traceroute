package et;

public class IPUtils {

	public static int ipAsWord(String ip) {
		String[] parts = ip.split("\\.");
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			int b = Integer.parseInt(parts[i]);
			sum |= (b << ((3 - i) * 8)) & (0xff << (3 - i) * 8);
		}
		return sum;
	}

	public static String wordAsIp(int word) {
		StringBuilder ipb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			ipb.append(((word & (0xff << (3 - i) * 8)) >> (3 - i) * 8) & 0xff)
					.append(".");
		}
		ipb.deleteCharAt(ipb.length() - 1);
		return ipb.toString();
	}
}
