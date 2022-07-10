package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayBitListTest {
    @Test
    public void test(){
        ArrayBitList arrayBitList=new ArrayBitList();
        arrayBitList.append(0b10101010101010101010101010101010);
        String expected="10101010101010101010101010101010";
        assertEquals(expected,arrayBitList.toString());
        arrayBitList.append(0b10101010101010101010101010101010);
        expected+=expected;
        assertEquals(expected,arrayBitList.toString());
    }


}