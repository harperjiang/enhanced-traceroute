package et;

import static org.junit.Assert.*;

import org.junit.Test;

public class IPUtilsTest extends IPUtils {

	@Test
	public void testIpAsWord() {
		assertEquals(0x809917B9, IPUtils.ipAsWord("128.153.23.185"));
	}

	@Test
	public void testWordAsIp() {
		assertEquals("128.153.23.185", IPUtils.wordAsIp(0x809917B9));
	}

}
