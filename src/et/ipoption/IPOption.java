package et.ipoption;

public abstract class IPOption {

	public static final int Timestamp = 68;

	public static final int RecordRoute = 7;

	protected byte[] data;

	public IPOption(byte[] data) {
		this.data = data;
	}

	public byte[] getData() {
		return data;
	}

	public static IPOption parse(byte[] input) {
		throw new RuntimeException("Not implemented");
	}

	public int getOption() {
		return data[0];
	}

	public void setOption(int option) {
		data[0] = 0;
		data[0] |= option & 0xff;
	}

	public int getLength() {
		return data[1];
	}

	public void setLength(int length) {
		data[1] = 0;
		data[1] |= length & 0xff;
	}

}
