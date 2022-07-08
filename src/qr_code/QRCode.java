package qr_code;

import utils.EmptyQrCodeCell;

import java.util.Arrays;
import java.util.Optional;

public class QRCode {
    private final boolean[][] qrCode;
    private QRCode(boolean[][] qrCode){
        this.qrCode=qrCode;
    }
    public boolean[][] getQrCodeArray(){
        return qrCode;
    }
    public static Builder builder(EmptyQrCodeCell[][] emptyQrCodeCells){
        return new Builder(emptyQrCodeCells);
    }
    public static class Builder{
        private final EmptyQrCodeCell[][] emptyQrCodeCells;
        private Builder(EmptyQrCodeCell[][] emptyQrCodeCells){
            this.emptyQrCodeCells=emptyQrCodeCells;
        }
        public Builder enterData(byte[][] data){
            throw new UnsupportedOperationException();
        }
        public QRCode build(){
            selectMaskPattern();
            placeFormatString();
            return new QRCode(EmptyQrCodeCell.toQRCode(emptyQrCodeCells));
        }
        private void selectMaskPattern(){

        }
        private void placeFormatString(){

        }
    }
    public static Optional<QRCode> ofUnverified(boolean[][] qrCode){
        throw new UnsupportedOperationException();
    }
}
