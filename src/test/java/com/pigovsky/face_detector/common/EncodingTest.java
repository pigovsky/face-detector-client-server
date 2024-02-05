package com.pigovsky.face_detector.common;
import org.junit.Test;
import org.junit.Assert;

public class EncodingTest {
    @Test
    public void testEncodeDecodeInt() {
        for (int value : new int[] {493987432, 0xFFFFFF, 0xFFFFFFFF}) {
            byte[] encoded = Encoding.encodeInt(value, 4);
            int decoded = Encoding.decodeInt(encoded);

            Assert.assertEquals(value, decoded);
        }
    }
}
