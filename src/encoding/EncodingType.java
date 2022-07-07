package encoding;

import java.util.Optional;

public enum EncodingType {
    NUMERIC((byte)0b1),
    ALPHANUMERIC((byte)0b10),
    BYTE((byte)0b100),
    KANJI((byte)0b1000),
    STRUCTURED_APPEND((byte)0b11),
    ECI((byte) 0b111),
    FNC1_FIRST_POS((byte) 0b101),
    FNC1_SECOND_POS((byte) 0b1001),
    END_OF_MESSAGE((byte) 0);
    private final byte code;
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
            case 0b11->Optional.of(STRUCTURED_APPEND);
            case 0b111->Optional.of(ECI);
            case 0b101->Optional.of(FNC1_FIRST_POS);
            case 0b1001->Optional.of(FNC1_SECOND_POS);
            case 0->Optional.of(END_OF_MESSAGE);
            default -> Optional.empty();
        };
    }
}
