package version;

import org.junit.jupiter.api.Test;

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
    }
}