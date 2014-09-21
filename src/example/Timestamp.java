package example;

import java.net.InetAddress;

import org.savarese.vserv.tcpip.TCPPacket;

import com.savarese.rocksaw.net.RawSocket;

import et.ETCPPacket;
import et.IPUtils;
import et.ipoption.TSOption;
import et.tcpoption.TCPOption;

public class Timestamp {

	public static void main(String[] args) throws Exception {

		RawSocket socket = new RawSocket();
		socket.open(RawSocket.PF_INET, RawSocket.getProtocolByName("tcp"));
		socket.setIPHeaderInclude(true);

		ETCPPacket syn = new ETCPPacket(60);

		// Set IP Packet info

		syn.setData(new byte[60]);
		// Maximum length
		syn.setIPVersion(4);
		syn.setIPHeaderLength(5);
		syn.setTypeOfService(0);
		syn.setIdentification(4232);
		syn.setIPFlags(0);
		syn.setFragmentOffset(0);
		
		syn.setTTL(22);
		syn.setProtocol(RawSocket.getProtocolByName("tcp"));

		syn.setSourceAsWord(IPUtils.ipAsWord("128.153.23.179"));
		syn.setDestinationAsWord(IPUtils.ipAsWord("54.218.96.244"));

		TSOption option = new TSOption();
		option.setFlag(TSOption.FLAG_TS_GIVENIP);
		option.setIP(0, IPUtils.ipAsWord("128.153.16.1"));
		option.setPointer(9);
		// syn.setIPOption(option);

		// Set TCP info

		syn.setSourcePort(40445);
		syn.setDestinationPort(80);
		syn.setSequenceNumber(15);
		syn.setTCPHeaderLength(10);
		syn.addControlFlags(TCPPacket.MASK_SYN);
		syn.setUrgentPointer(0);
		syn.setWindowSize(5840);

		TCPOption tcpOption = new TCPOption(20);
		tcpOption.add(TCPOption.MSS, 1460);
		tcpOption.add(TCPOption.SACKP);
		tcpOption.add(TCPOption.TS, (int) (System.currentTimeMillis() / 1000),
				0);
		tcpOption.add(TCPOption.NOP);
		tcpOption.add(TCPOption.WSCALE, 2);

		syn.setTCPOption(tcpOption);

		syn.computeTCPChecksum();
		syn.computeIPChecksum();
		
		byte[] data = new byte[60];
		syn.getData(data);
		socket.write(InetAddress.getByName("54.218.96.244"), data);
	}
}
