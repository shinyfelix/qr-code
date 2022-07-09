package utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class UtilsTest {
    @Test
    public void tryParseInt(){
        Optional<Integer> optional=Utils.tryParseInt("123");
        assertTrue(optional.isPresent());
        assertEquals(123,optional.get());
        optional=Utils.tryParseInt("aaa212");
        assertTrue(optional.isEmpty());
    }

    @Test
    void byteListToByteArray() {
        byte[] bytes=Utils.ByteListToByteArray(List.of((byte)1,(byte)2,(byte)3));
        assertArrayEquals(new byte[]{1, 2, 3}, bytes);
    }
}