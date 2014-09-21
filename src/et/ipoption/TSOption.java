package et.ipoption;

import et.DataUtils;

public class TSOption extends IPOption {

	public static int FLAG_TS_ONLY = 0;

	public static int FLAG_TS_WITHIP = 1;

	public static int FLAG_TS_GIVENIP = 3;

	public TSOption() {
		this(40);
	}

	public TSOption(int length) {
		super(new byte[length]);
		setOption(IPOption.Timestamp);
		setLength(length);
	}

	public int getLength() {
		return data[1];
	}

	public void setLength(int length) {
		data[1] = 0;
		data[1] |= length & 0xff;
	}

	public int getPointer() {
		return data[2];
	}

	public void setPointer(int pointer) {
		data[2] = 0;
		data[2] |= pointer & 0xff;
	}

	public int getOverflow() {
		return 0x0f & (data[3] >> 4);
	}

	public void setOverflow(int overflow) {
		data[3] |= (0x000f & overflow) << 4;
	}

	public int getFlag() {
		return 0x0f & data[3];
	}

	public void setFlag(int flag) {
		data[3] |= 0x000f & flag;
	}

	public int getIP(int i) {
		return DataUtils.wordToInt(data, 4 + i * 4);
	}

	public void setIP(int i, int ip) {
		byte[] word = DataUtils.intToWord(ip);
		System.arraycopy(word, 0, data, 4 + i * 4, 4);
	}

	public int getTimestamp(int i) {
		return DataUtils.wordToInt(data, 8 + i * 4);
	}

	public void setTimestamp(int i, int ts) {
		byte[] word = DataUtils.intToWord(ts);
		System.arraycopy(word, 0, data, 8 + i * 4, 4);
	}
}
