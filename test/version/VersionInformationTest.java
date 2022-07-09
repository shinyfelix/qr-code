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
        VersionInformation versionInformation=VersionInformation.ofVersion(7).get();
        assertEquals(versionInformation.getVersionBits().get(),0b000111110010010100);
        versionInformation=VersionInformation.ofVersion(30).get();
        assertEquals(versionInformation.getVersionBits().get(),0b011110110101110101);
        versionInformation=VersionInformation.ofVersion(31).get();
        assertEquals(versionInformation.getVersionBits().get(),0b011111001001010000);
        versionInformation=VersionInformation.ofVersion(37).get();
        assertEquals(versionInformation.getVersionBits().get(),0b100101010000101110);
    }
    @Test
    void ofBits(){
        Optional<VersionInformation> versionInformation=VersionInformation.ofBits(0b010100100110100110);
        assertTrue(versionInformation.isPresent());
        assertEquals(20,versionInformation.get().getVersion());
        versionInformation=VersionInformation.ofBits(0b1);
        assertTrue(versionInformation.isEmpty());
    }
    @Test
    void ofVersion(){
        Optional<VersionInformation> versionInformation=VersionInformation.ofVersion(1);
        assertTrue(versionInformation.isPresent());
        assertEquals(1,versionInformation.get().getVersion());
        versionInformation=VersionInformation.ofVersion(40);
        assertTrue(versionInformation.isPresent());
        assertEquals(40,versionInformation.get().getVersion());
        versionInformation=VersionInformation.ofVersion(20);
        assertTrue(versionInformation.isPresent());
        assertEquals(20,versionInformation.get().getVersion());
        versionInformation=VersionInformation.ofVersion(0);
        assertTrue(versionInformation.isEmpty());
        versionInformation=VersionInformation.ofVersion(41);
        assertTrue(versionInformation.isEmpty());
    }
    @Test
    void ofSize(){
        Optional<VersionInformation> versionInformation=VersionInformation.ofSize(21);
        assertTrue(versionInformation.isPresent());
        assertEquals(1,versionInformation.get().getVersion());
        versionInformation=VersionInformation.ofSize(17);
        assertTrue(versionInformation.isEmpty());
        versionInformation=VersionInformation.ofSize(22);
        assertTrue(versionInformation.isEmpty());
        versionInformation=VersionInformation.ofSize(29);
        assertTrue(versionInformation.isPresent());
        assertEquals(3,versionInformation.get().getVersion());
    }
    @Test
    void getEmptyQrCode(){
        Optional<VersionInformation> versionInformation=VersionInformation.ofVersion(1);
        assertTrue(versionInformation.isPresent());
        EmptyQrCodeCell[][] actual=versionInformation.get().getEmptyQRCode();
        EmptyQrCodeCell[][] expected= TestUtil.readQRCode("test/version/version_1.test");
        assertTrue(Arrays.deepEquals(actual,expected));
    }
    @Test
    void getSize(){
        Optional<VersionInformation> versionInformation=VersionInformation.ofVersion(1);
        assertTrue(versionInformation.isPresent());
        assertEquals(21,versionInformation.get().getSize());
        versionInformation=VersionInformation.ofVersion(3);
        assertTrue(versionInformation.isPresent());
        assertEquals(29,versionInformation.get().getSize());
    }
    @Test
    @Disabled("Failing until implemented properly")
    void ofCharacterCount(){
        Optional<VersionInformation> versionInformation=VersionInformation.ofCharacterCount(24, EncodingType.BYTE, ErrorCorrectionLevel.H);
        assertTrue(versionInformation.isPresent());
        assertEquals(3,versionInformation.get().getVersion());
        versionInformation=VersionInformation.ofCharacterCount(154,EncodingType.BYTE,ErrorCorrectionLevel.L);
        assertTrue(versionInformation.isPresent());
        assertEquals(5,versionInformation.get().getVersion());
        versionInformation=VersionInformation.ofCharacterCount(370,EncodingType.NUMERIC,ErrorCorrectionLevel.L);
        assertTrue(versionInformation.isPresent());
        assertEquals(7,versionInformation.get().getVersion());
        versionInformation=VersionInformation.ofCharacterCount(154,EncodingType.ALPHANUMERIC,ErrorCorrectionLevel.M);
        assertTrue(versionInformation.isPresent());
        assertEquals(6,versionInformation.get().getVersion());
        versionInformation=VersionInformation.ofCharacterCount(53,EncodingType.KANJI,ErrorCorrectionLevel.Q);
        assertTrue(versionInformation.isPresent());
        assertEquals(7,versionInformation.get().getVersion());
    }

    @Test
    void getAlignmentPatterns(){
        VersionInformation versionInformation= TestUtil.assertAndGet(VersionInformation.ofVersion(1));
        assertTrue(versionInformation.getAlignmentPatterns().isEmpty());
        versionInformation=TestUtil.assertAndGet(VersionInformation.ofVersion(2));
        assertEquals(1, versionInformation.getAlignmentPatterns().size());
    }
}