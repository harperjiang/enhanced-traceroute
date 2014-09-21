package et;

import org.savarese.vserv.tcpip.TCPPacket;

import et.ipoption.IPOption;
import et.tcpoption.TCPOption;

public class ETCPPacket extends TCPPacket {

	public ETCPPacket(int size) {
		super(size);
	}

	public void setIPOption(IPOption option) {
		byte[] data = option.getData();
		System.arraycopy(data, 0, _data_, 20, data.length);
	}

	public IPOption getIPOption() {
		int length = getIPHeaderByteLength();
		byte[] data = new byte[length - 20];
		System.arraycopy(_data_, 20, 0, 0, data.length);
		return IPOption.parse(data);
	}

	public void setTCPOption(TCPOption option) {
		byte[] data = option.getData();
		System.arraycopy(data, 0, _data_, getIPHeaderByteLength() + 20,
				data.length);
	}

	public TCPOption getTCPOption() {
		int length = getTCPHeaderByteLength();
		byte[] data = new byte[length - 20];
		System.arraycopy(_data_, getIPHeaderByteLength() + 20, 0, 0,
				data.length);
		return TCPOption.parse(data);
	}
}
