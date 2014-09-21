package et.tcpoption;

import java.util.Arrays;

import et.DataUtils;

public class TCPOption {

	public static final int EOPL = 0;

	public static final int NOP = 1;

	public static final int MSS = 2;

	public static final int WSCALE = 3;

	public static final int SACKP = 4;

	public static final int SACK = 5;

	public static final int TS = 8;

	private byte[] data;

	private int pointer;

	public TCPOption(int length) {
		data = new byte[length];
		Arrays.fill(data, (byte) 0);
		pointer = 0;
	}

	public byte[] getData() {
		return data;
	}

	public void add(int option, int... value) {
		data[pointer++] |= 0xff & option;
		switch (option) {
		case EOPL:
		case NOP:
			break;
		case MSS:
			data[pointer++] |= 4 & 0xff;
			data[pointer++] |= (value[0] & 0xff00) >> 8;
			data[pointer++] |= value[0] & 0xff;
			break;
		case WSCALE:
			data[pointer++] |= 3 & 0xff;
			data[pointer++] |= value[0] & 0xff;
			break;
		case SACKP:
			data[pointer++] |= 2 & 0xff;
			break;
		case SACK:
			break;
		case TS:
			data[pointer++] |= 10 & 0xff;
			byte[] bytes = DataUtils.intToWord(value[0]);
			System.arraycopy(bytes, 0, data, pointer, 4);
			pointer += 4;
			bytes = DataUtils.intToWord(value[1]);
			System.arraycopy(bytes, 0, data, pointer, 4);
			pointer += 4;
			break;
		default:
			throw new IllegalArgumentException("Invalid option");
		}
	}

	public static TCPOption parse(byte[] data2) {
		throw new IllegalArgumentException("Not implemented");
	}
}
