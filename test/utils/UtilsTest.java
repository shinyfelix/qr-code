package utils;

import org.junit.jupiter.api.Test;
import testutil.TestUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    public void tryParseInt(){
        int number= TestUtil.assertAndGet(Utils.tryParseInt("123"));
        assertEquals(123,number);
        TestUtil.assertNotPresent(Utils.tryParseInt("aaa212"));
    }

    @Test
    void byteListToByteArray() {
        byte[] bytes=Utils.ByteListToByteArray(List.of((byte)1,(byte)2,(byte)3));
        assertArrayEquals(new byte[]{1, 2, 3}, bytes);
    }
}