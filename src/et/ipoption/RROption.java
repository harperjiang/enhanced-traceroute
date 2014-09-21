package et.ipoption;

import et.DataUtils;

public class RROption extends IPOption {

	public RROption() {
		this(40);
	}

	public RROption(int length) {
		super(new byte[length]);
		setOption(IPOption.RecordRoute);
		setLength(40);
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

	public int getIP(int i) {
		return DataUtils.wordToInt(data, 3 + i * 4);
	}

	public void setIP(int i, int ip) {
		byte[] word = DataUtils.intToWord(ip);
		System.arraycopy(word, 0, data, 3 + i * 4, 4);
	}
}
