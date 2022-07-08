package utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static utils.EmptyQrCodeCell.*;

class EmptyQrCodeCellTest {
    private static final EmptyQrCodeCell[][] emptyQrCodeCells=new EmptyQrCodeCell[][]{
            {
                ON,OFF,PRE_MARK,EMPTY
            },
            {
                OFF,PRE_MARK,EMPTY,ON
            },
            {
                PRE_MARK,EMPTY,ON,OFF
            },
            {
                EMPTY,ON,EMPTY,ON,
            },
    };
    private static final boolean[][] qr=new boolean[][]{
            {
                true,false,false,false
            },
            {
                false,false,false,true
            },
            {
                false,false,true,false
            },
            {
                false,true,false,true
            },
    };
    private static final boolean[][] mask=new boolean[][]{
            {
                true,true,true,false
            },
            {
                true,true,false,true
            },
            {
                true,false,true,true
            },
            {
                false,true,false,true
            },
    };
    @Test
    void toQRCode() {
        assertTrue(Arrays.deepEquals(EmptyQrCodeCell.toQRCode(emptyQrCodeCells),qr));

    }

    @Test
    void toMask() {
        assertTrue(Arrays.deepEquals(EmptyQrCodeCell.toMask(emptyQrCodeCells),mask));
    }
}