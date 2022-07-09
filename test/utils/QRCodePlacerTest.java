package utils;

import org.junit.jupiter.api.Test;
import testutil.TestUtil;
import version.VersionInformation;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static utils.EmptyQrCodeCell.*;

public class QRCodePlacerTest {


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
        EmptyQrCodeCell[][] empty=new EmptyQrCodeCell[21][21];
        QRCodePlacer.fillWithEmpty(empty);
        QRCodePlacer.placeTimings(empty);
        EmptyQrCodeCell[][] expected= TestUtil.readQRCode("test/utils/timing.test");
        assertTrue(Arrays.deepEquals(empty,expected));
    }

    @Test
    void preMarkFormatInformation() {
        EmptyQrCodeCell[][] empty=new EmptyQrCodeCell[19][19];
        QRCodePlacer.fillWithEmpty(empty);
        QRCodePlacer.preMarkFormatInformation(empty);
        EmptyQrCodeCell[][] expected= TestUtil.readQRCode("test/utils/format.test");
        assertTrue(Arrays.deepEquals(empty,expected));
    }

    @Test
    void placeVersionInformation() {
        VersionInformation versionInformation=VersionInformation.ofVersion(7).get();
        int bits=versionInformation.getVersionBits().get();
        EmptyQrCodeCell[][] empty=new EmptyQrCodeCell[21][21];
        QRCodePlacer.fillWithEmpty(empty);
        QRCodePlacer.placeVersionInformation(empty,bits);
        EmptyQrCodeCell[][] expected= TestUtil.readQRCode("test/utils/version.test");
        assertTrue(Arrays.deepEquals(empty,expected));
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