package encoding;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum EncodingType {
    NUMERIC((byte)0b1),
    ALPHANUMERIC((byte)0b10),
    BYTE((byte)0b100),
    KANJI((byte)0b1000);
    private final byte code;
    private static final Pattern NUMERIC_PATTERN=Pattern.compile("\\d+");
    private static final Pattern ALPHANUMERIC_PATTERN=Pattern.compile("[\\d\\p{Upper} $%*+\\-./:]+");
    EncodingType(byte code){
        this.code=code;
    }
    public byte getCode(){
        return code;
    }
    public Optional<EncodingType> of(byte code){
        return switch (code){
            case 0b1->Optional.of(NUMERIC);
            case 0b10->Optional.of(ALPHANUMERIC);
            case 0b100->Optional.of(BYTE);
            case 0b1000->Optional.of(KANJI);
            default -> Optional.empty();
        };
    }
    public static EncodingType getBestEncodingType(String input){
        Matcher numericMatcher=NUMERIC_PATTERN.matcher(input);
        if (numericMatcher.matches())return NUMERIC;
        Matcher alphanumericMatcher=ALPHANUMERIC_PATTERN.matcher(input);
        if (alphanumericMatcher.matches())return ALPHANUMERIC;
        return BYTE;
    }
}
