package utils;

import org.junit.jupiter.api.Test;

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
}