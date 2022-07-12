package write;

import encoding.Encoder;
import encoding.EncodingType;
import error_correction.ErrorCorrection;
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
        int oldSize=bits.size();
        int pads=errorCorrectionInformation.getCodeWordsAmount()*8-oldSize;
        bits.appendZeros(pads);
        int paddingBytes=pads/8;
        boolean is17=false;
        for (int i = oldSize/8; i <paddingBytes+oldSize/8 ; i++) {
            if (is17)
                bits.writeBits(i*8,(byte)17);
            else bits.writeBits(i*8,(byte) 236);
            is17=!is17;
        }
        BitList errorCorrection= ErrorCorrection.writeErrorCorrection(bits,errorCorrectionLevel);
        BitList data=Shuffler.shuffle(bits,errorCorrection);
        QRCode.Builder builder=QRCode.builder(versionInformation.getEmptyQRCode());
        builder.enterData(data);
        return builder.build();
    }
}
