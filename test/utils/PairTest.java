package utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PairTest {
    @Test
    public void test(){
        Pair<Integer,Integer> intPair=new Pair<>(1,3);
        assertEquals(1,intPair.getFirst());
        assertEquals(3,intPair.getSecond());
        Pair<String,Boolean> pair=new Pair<>("a",true);
        assertEquals("a",pair.getFirst());
        assertTrue(pair.getSecond());
    }

}