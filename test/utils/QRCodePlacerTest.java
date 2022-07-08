package utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static utils.EmptyQrCodeCell.*;

class QRCodePlacerTest {


    @Test
    void placeFinder() {
        EmptyQrCodeCell[][] empty=new EmptyQrCodeCell[9][9];
        QRCodePlacer.placeFinder(empty,0,0);
    }

    @Test
    void placeAlignment() {
    }

    @Test
    void placeTimings() {
    }

    @Test
    void preMarkFormatInformation() {
    }

    @Test
    void placeVersionInformation() {
    }

    @Test
    void placeRectangle() {
        EmptyQrCodeCell[][] empty=new EmptyQrCodeCell[3][3];
        QRCodePlacer.placeRectangle(empty,0,0,1,2,EmptyQrCodeCell.ON);
        EmptyQrCodeCell[][] expected=new EmptyQrCodeCell[][]{
                {
                    ON,EMPTY,EMPTY
                },
                {
                    ON,EMPTY,EMPTY
                },
                {
                    EMPTY,EMPTY,EMPTY
                },
        };
        assertTrue(Arrays.deepEquals(empty,expected));
    }
}