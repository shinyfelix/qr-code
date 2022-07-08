package write;

import encoding.EncodingType;
import error_correction.ErrorCorrectionLevel;
import qr_code.QRCode;

public class QRCodeWriter {
    public static QRCode write(String input, ErrorCorrectionLevel errorCorrectionLevel){
        EncodingType encodingType=EncodingType.getBestEncodingType(input);

        throw new UnsupportedOperationException();
    }
}
