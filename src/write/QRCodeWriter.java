package write;

import encoding.Encoder;
import encoding.EncodingType;
import error_correction.ErrorCorrectionInformation;
import error_correction.ErrorCorrectionLevel;
import qr_code.QRCode;
import utils.ArrayBitList;
import utils.BitList;
import version.VersionInformation;

public class QRCodeWriter {
    public static QRCode write(String input, ErrorCorrectionLevel errorCorrectionLevel){
        EncodingType encodingType=EncodingType.getBestEncodingType(input);
        int charCount=input.length();
        VersionInformation versionInformation=VersionInformation.ofCharacterCount(charCount,encodingType,errorCorrectionLevel).orElseThrow(()->new IllegalArgumentException("too many characters"));
        BitList bits= new ArrayBitList();
        bits.appendLSB(encodingType.getCode(),4);
        bits.appendLSB(charCount,versionInformation.getCharacterCountBits());
        bits.append(Encoder.encode(input,encodingType));
        ErrorCorrectionInformation errorCorrectionInformation= versionInformation.getErrorCorrectionInformation(errorCorrectionLevel,encodingType);
        throw new UnsupportedOperationException();
    }
}
