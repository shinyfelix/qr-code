package version;

import encoding.EncodingType;
import error_correction.ErrorCorrectionLevel;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import testutil.TestUtil;
import utils.EmptyQrCodeCell;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class VersionInformationTest {

    @Test
    void getVersionBits() {
        VersionInformation versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(7));
        assertEquals(TestUtil.assertAndGet(versionInformation.getVersionBits()),0b000111110010010100);
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(30));
        assertEquals(TestUtil.assertAndGet(versionInformation.getVersionBits()),0b011110110101110101);
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(31));
        assertEquals(TestUtil.assertAndGet(versionInformation.getVersionBits()),0b011111001001010000);
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(37));
        assertEquals(TestUtil.assertAndGet(versionInformation.getVersionBits()),0b100101010000101110);
    }
    @Test
    void ofBits(){
       VersionInformation versionInformation=TestUtil.assertAndGet(VersionInformation.ofBits(0b010100100110100110));
       assertEquals(20,versionInformation.getVersion());
       TestUtil.assertNotPresent(VersionInformation.ofBits(0b1));
    }
    @Test
    void ofVersion(){
        VersionInformation versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(1));
        assertEquals(1,versionInformation.getVersion());
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(40));
        assertEquals(40,versionInformation.getVersion());
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(20));
        assertEquals(20,versionInformation.getVersion());
        TestUtil.assertNotPresent(VersionInformation.ofVersion(0));
        TestUtil.assertNotPresent(VersionInformation.ofVersion(41));
    }
    @Test
    void ofSize(){
        VersionInformation versionInformation=TestUtil.assertAndGet(VersionInformation.ofSize(21));
        assertEquals(1,versionInformation.getVersion());
        TestUtil.assertNotPresent(VersionInformation.ofSize(17));
        TestUtil.assertNotPresent(VersionInformation.ofSize(22));
        TestUtil.assertNotPresent(VersionInformation.ofSize(26));
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofSize(29));
        assertEquals(3,versionInformation.getVersion());
    }
    @Test
    void getEmptyQrCode(){
        VersionInformation versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(1));
        EmptyQrCodeCell[][] actual=versionInformation.getEmptyQRCode();
        EmptyQrCodeCell[][] expected= TestUtil.readQRCode("test/version/version_1.test");
        assertTrue(Arrays.deepEquals(actual,expected));
    }
    @Test
    void getSize(){
        VersionInformation versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(1));
        assertEquals(21,versionInformation.getSize());
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(3));
        assertEquals(29,versionInformation.getSize());
    }
    @Test
    @Disabled("Failing until implemented properly")
    void ofCharacterCount(){
        VersionInformation versionInformation=TestUtil.assertAndGet(VersionInformation.ofCharacterCount(24, EncodingType.BYTE, ErrorCorrectionLevel.H));
        assertEquals(3,versionInformation.getVersion());
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofCharacterCount(154,EncodingType.BYTE,ErrorCorrectionLevel.L));

        assertEquals(5,versionInformation.getVersion());
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofCharacterCount(370,EncodingType.NUMERIC,ErrorCorrectionLevel.L));

        assertEquals(7,versionInformation.getVersion());
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofCharacterCount(154,EncodingType.ALPHANUMERIC,ErrorCorrectionLevel.M));

        assertEquals(6,versionInformation.getVersion());
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofCharacterCount(53,EncodingType.KANJI,ErrorCorrectionLevel.Q));

        assertEquals(7,versionInformation.getVersion());
    }

    @Test
    void getAlignmentPatterns(){
        VersionInformation versionInformation= TestUtil.assertAndGet(VersionInformation.ofVersion(1));
        assertTrue(versionInformation.getAlignmentPatterns().isEmpty());
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(2));
        assertEquals(1, versionInformation.getAlignmentPatterns().size());
    }
}