package utils;

import version.VersionInformation;

public enum EmptyQrCodeCell {
    EMPTY, ON, OFF, PRE_MARK;

    public static boolean[][] toQRCode(EmptyQrCodeCell[][] emptyQrCode) {
        boolean[][] qrCode = new boolean[emptyQrCode.length][emptyQrCode[0].length];
        for (int i = 0; i < emptyQrCode.length; i++) {
            if (emptyQrCode[i].length != emptyQrCode.length)
                throw new IllegalArgumentException("Qr code is not a square");
            for (int j = 0; j < emptyQrCode[i].length; j++) {
                qrCode[i][j] = emptyQrCode[i][j] == ON;
            }
        }
        return qrCode;
    }

    public static boolean[][] toMask(EmptyQrCodeCell[][] emptyQrCode) {
        boolean[][] qrCode = new boolean[emptyQrCode.length][emptyQrCode[0].length];
        for (int i = 0; i < emptyQrCode.length; i++) {
            if (emptyQrCode[i].length != emptyQrCode.length)
                throw new IllegalArgumentException("Qr code is not a square");
            for (int j = 0; j < emptyQrCode[i].length; j++) {
                qrCode[i][j] = emptyQrCode[i][j] != EMPTY;
            }
        }
        return qrCode;
    }
}
