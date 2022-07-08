package utils;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BinaryTest {
    @Test
    public void testWritePosition(){
        byte number=Binary.writePosition((byte) 0, (byte) 0,true);
        assertEquals((byte) 1,number);
        number=Binary.writePosition((byte) 1, (byte) 0,false);
        assertEquals((byte) 0,number);
        number=Binary.writePosition((byte) 0,(byte) 3,true);
        assertEquals((byte) 8,number);
        number=Binary.writePosition((byte) 7,(byte) 3,true);
        assertEquals((byte) 15,number);
        number=Binary.writePosition((byte) 15,(byte) 3,false);
        assertEquals((byte) 7,number);
    }
    @Test
    public void testReadPosition(){
        assertTrue(Binary.readPosition((byte) 1,0));
        assertTrue(Binary.readPosition((byte) 8,3));
        assertFalse(Binary.readPosition((byte) 0,0));
        assertFalse(Binary.readPosition((byte) 0,5));
        assertFalse(Binary.readPosition((byte) 3,5));
    }
    @Test
    private void testWriteBits(){
        List<Byte> bytes=new ArrayList<>();
        var result=Binary.writeBits(bytes,0,0,0b1010,4);
        assertEquals(4,result.inBytePointer());
        assertEquals(0,result.bytePointer());
        byte bits=bytes.get(0);
        assertEquals((byte) 0b1010,bits);
        result=Binary.writeBits(bytes,0,4,0b1_101010,6);
        assertEquals(2,result.inBytePointer());
        assertEquals(1,result.bytePointer());
        bits=bytes.get(0);
        assertEquals((byte)0b1010_1010,bits);
        bits=bytes.get(1);
        assertEquals((byte) 0b10,bits);
    }
}