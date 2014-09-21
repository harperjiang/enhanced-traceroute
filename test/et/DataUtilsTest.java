package et;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DataUtilsTest {

	@Test
	public void testWordToInt() {
		byte[] data = new byte[] { 1, 2, 3, 4 };
		assertEquals(0x01020304, DataUtils.wordToInt(data, 0));
		data = new byte[] { (byte) 128, (byte) 153, 23, (byte) 185 };
		assertEquals(0x809917B9, DataUtils.wordToInt(data, 0));
	}

	@Test
	public void testIntToWord() {
		assertArrayEquals(new byte[] { (byte) 128, (byte) 153, 23, (byte) 185 },
				DataUtils.intToWord(0x809917B9));
	}

}
