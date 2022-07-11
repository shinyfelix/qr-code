package utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import testutil.TestUtil;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBitListTest {

    @Test
    void writeBits() {
        BitList bitList=new ArrayBitList();
        assertFalse(bitList.writeBits(0,0));
        bitList.append(true);
        assertFalse(bitList.writeBits(0,0));
        bitList=new ArrayBitList();
        bitList.appendZeros(32);
        assertTrue(bitList.writeBits(0,Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE,TestUtil.assertAndGet(bitList.readBits_int(0)));
        bitList.appendZeros(8);
        assertTrue(bitList.writeBits(32,Byte.MAX_VALUE));
        assertEquals(Byte.MAX_VALUE,TestUtil.assertAndGet(bitList.readBits_byte(32)));
        assertTrue(bitList.writeBits(8,0));
        assertEquals(0,TestUtil.assertAndGet(bitList.readBits_int(8)));
    }

    @Test
    void readBits_int_byte() {
        BitList bitList= new ArrayBitList();
        TestUtil.assertNotPresent(bitList.readBits_int(0));
        bitList.appendZeros(8);
        assertEquals((byte) 0,TestUtil.assertAndGet(bitList.readBits_byte(0)));
        bitList.appendZeros(24);
        assertEquals(0,TestUtil.assertAndGet(bitList.readBits_int(0)));
        TestUtil.assertNotPresent(bitList.readBits_int(1));
        bitList.writeBits(0,0b10101010111);
        assertEquals(0b10101010111,TestUtil.assertAndGet(bitList.readBits_int(0)));
        assertEquals((byte)0b10101010111,TestUtil.assertAndGet(bitList.readBits_byte(24)));
    }

    @Test
    void writeMSBits() {
        BitList bitList=new ArrayBitList();
        bitList.appendZeros(10);
        bitList.writeMSBits(0,0b11110000111100001111000011110000,10);
        assertEquals(0b1111000011,TestUtil.assertAndGet(bitList.readLSBits(0,10)));
    }

    @Test
    void readMSBits() {
        BitList bitList=new ArrayBitList();
        bitList.appendZeros(32);
        bitList.writeBits(0,0b11110000111100001111000011110000);
        assertEquals(0b11110000111100000000000000000000,TestUtil.assertAndGet(bitList.readMSBits(0,12)));
        assertEquals(0b11110000000000000000000000000000,TestUtil.assertAndGet(bitList.readMSBits(0,4)));
    }

    @Test
    void writeLSBits() {
        BitList bitList=new ArrayBitList();
        bitList.appendZeros(6);
        bitList.writeLSBits(0,0b101010,6);
        assertEquals(0b101010,TestUtil.assertAndGet(bitList.readLSBits(0,6)));
    }

    @Test
    void readLSBits() {
        BitList bitList=new ArrayBitList();
        bitList.appendZeros(32);
        bitList.writeBits(0,0b11110000111100001111000011110000);
        assertEquals(0b11110000,TestUtil.assertAndGet(bitList.readLSBits(0,8)));

    }

    @Test
    void append() {
        BitList bitList=new ArrayBitList();
        bitList.append(true);
        assertTrue(TestUtil.assertAndGet(bitList.readBit(0)));
        bitList.append((byte) 0b10101010);
        assertEquals((byte) 0b10101010,TestUtil.assertAndGet(bitList.readBits_byte(1)));
        bitList.append(0b1);
        assertEquals(0b1,TestUtil.assertAndGet(bitList.readBits_int(9)));
    }

    @Test
    void appendMSB() {
        BitList bitList=new ArrayBitList();
        bitList.appendMSB(0b11110000111100001111000011110000,10);
        assertEquals(0b1111000011,TestUtil.assertAndGet(bitList.readLSBits(0,10)));
    }

    @Test
    void appendLSB() {
        BitList bitList=new ArrayBitList();
        bitList.appendLSB(0b11110000111100001111000011110000,10);
        assertEquals(0b11110000,TestUtil.assertAndGet(bitList.readLSBits(0,10)));
    }

    @Test
    void appendZeros() {
        BitList bitList=new ArrayBitList();
        bitList.appendZeros(1);
        assertFalse(TestUtil.assertAndGet(bitList.readBit(0)));
        bitList.appendZeros(31);
        assertEquals(0,TestUtil.assertAndGet(bitList.readBits_int(0)));
    }

    @Test
    void toByteArray() {
        byte[] expected=new byte[]{
                0b01110000,0b00000000,0b00000000,0b00000000
        };
        BitList bitList=new ArrayBitList();
        bitList.append(0b011100000000000000000000000000000);
        byte[] actual=bitList.toByteArray();
        assertArrayEquals(expected,actual);
    }

    @Test
    void toIntArray() {
        int[] expected=new int[]{
                0b011100000000000000000000000000000
        };
        BitList bitList=new ArrayBitList();
        bitList.append((byte) 0b01110000);
        int[] actual=bitList.toIntArray();
        assertArrayEquals(expected,actual);
    }

    @Test
    void iterator() {
        BitList bitList=new ArrayBitList();
        bitList.append(Integer.MAX_VALUE);
        for (var b :
                bitList) {
            assertTrue(b);
        }
    }

    @Test
    void testToString() {
        BitList bitList=new ArrayBitList();
        for (int i = 0; i < 32; i++) {
            bitList.append(i%2==0);
        }
        String string=bitList.toString();
        assertEquals("10101010101010101010101010101010",string);
    }

    @Test
    void write_readBit() {
        BitList bitList=new ArrayBitList();
        assertFalse(bitList.writeBit(0,true));
        bitList.appendZeros(1);
        bitList.writeBit(0,true);
        assertTrue(TestUtil.assertAndGet(bitList.readBit(0)));
        bitList.writeBit(0,false);
        assertFalse(TestUtil.assertAndGet(bitList.readBit(0)));
    }
    @Test
    void clear(){
        BitList bitList=new ArrayBitList();
        bitList.appendZeros(100);
        bitList.clear();
        assertEquals(0,bitList.size());
    }

    @Test
    void size() {
        BitList bitList=new ArrayBitList();
        assertEquals(0,bitList.size());
        bitList.append(true);
        assertEquals(1,bitList.size());
        bitList.append(Byte.MAX_VALUE);
        assertEquals(9,bitList.size());
        bitList.append(Integer.MAX_VALUE);
        assertEquals(41,bitList.size());
        bitList.appendZeros(59);
        assertEquals(100,bitList.size());
        bitList.appendMSB(Integer.MAX_VALUE,20);
        assertEquals(120,bitList.size());
        bitList.appendLSB(Integer.MAX_VALUE,10);
        assertEquals(130,bitList.size());
    }
}