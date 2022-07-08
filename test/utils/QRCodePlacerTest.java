package utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static utils.EmptyQrCodeCell.*;

class QRCodePlacerTest {


    @Test
    void placeFinder() {
        EmptyQrCodeCell[][] empty=new EmptyQrCodeCell[9][9];
        QRCodePlacer.fillWithEmpty(empty);
        QRCodePlacer.placeFinder(empty,0,0);
        EmptyQrCodeCell[][] expected=new EmptyQrCodeCell[][]{
                {
                    ON,ON,ON,ON,ON,ON,ON,OFF,EMPTY
                },
                {
                    ON,OFF,OFF,OFF,OFF,OFF,ON,OFF,EMPTY
                },
                {
                    ON,OFF,ON,ON,ON,OFF,ON,OFF,EMPTY
                },
                {
                    ON,OFF,ON,ON,ON,OFF,ON,OFF,EMPTY
                },
                {
                    ON,OFF,ON,ON,ON,OFF,ON,OFF,EMPTY
                },
                {
                    ON,OFF,OFF,OFF,OFF,OFF,ON,OFF,EMPTY
                },
                {
                    ON,ON,ON,ON,ON,ON,ON,OFF,EMPTY
                },
                {
                    OFF,OFF,OFF,OFF,OFF,OFF,OFF,OFF,EMPTY
                },
                {
                    EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,
                },
        };
        assertTrue(Arrays.deepEquals(empty,expected));
    }

    @Test
    void placeAlignment() {
        EmptyQrCodeCell[][] empty=new EmptyQrCodeCell[6][6];
        QRCodePlacer.fillWithEmpty(empty);
        QRCodePlacer.placeAlignment(empty,0,0);
        EmptyQrCodeCell[][] expected=new EmptyQrCodeCell[][]{
                {
                    ON,ON,ON,ON,ON,EMPTY
                },
                {
                    ON,OFF,OFF,OFF,ON,EMPTY
                },
                {
                    ON,OFF,ON,OFF,ON,EMPTY
                },
                {
                    ON,OFF,OFF,OFF,ON,EMPTY
                },
                {
                    ON,ON,ON,ON,ON,EMPTY
                },
                {
                    EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,EMPTY,
                },
        };
        assertTrue(Arrays.deepEquals(empty,expected));
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
        QRCodePlacer.fillWithEmpty(empty);
        QRCodePlacer.placeRectangle(empty,0,0,1,2,EmptyQrCodeCell.ON);
        EmptyQrCodeCell[][] expected=new EmptyQrCodeCell[][]{
                {
                    ON,ON,EMPTY
                },
                {
                    EMPTY,EMPTY,EMPTY
                },
                {
                    EMPTY,EMPTY,EMPTY
                },
        };
        assertTrue(Arrays.deepEquals(empty,expected));
    }
}