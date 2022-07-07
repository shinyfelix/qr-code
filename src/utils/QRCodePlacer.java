package utils;

public class QRCodePlacer {
    public static void placeFinder(EmptyQrCodeCell[][] emptyQrCodeCells,int x,int y){
        placeRectangle(emptyQrCodeCells,x,y,8,8,EmptyQrCodeCell.OFF);
        placeRectangle(emptyQrCodeCells,x,y,7,7,EmptyQrCodeCell.ON);
        placeRectangle(emptyQrCodeCells,x+1,y+1,5,5,EmptyQrCodeCell.OFF);
        placeRectangle(emptyQrCodeCells,x+2,+3,3,3,EmptyQrCodeCell.ON);
    }
    public static void placeAlignment(EmptyQrCodeCell[][] emptyQrCodeCells,int x,int y){
        placeRectangle(emptyQrCodeCells,x,y,5,5,EmptyQrCodeCell.ON);
        placeRectangle(emptyQrCodeCells,x+1,y+1,3,3,EmptyQrCodeCell.OFF);
        placeRectangle(emptyQrCodeCells,x+2,y+2,1,1,EmptyQrCodeCell.ON);
    }
    public static void placeTimings(EmptyQrCodeCell[][] emptyQrCodeCells){
        for (int i = 8; i <emptyQrCodeCells.length-8 ; i++) {
            emptyQrCodeCells[i][6]=emptyQrCodeCells[6][i]=i%2==0?EmptyQrCodeCell.ON:EmptyQrCodeCell.OFF;
        }
    }
    public static void preMarkFormatInformation(EmptyQrCodeCell[][] emptyQrCodeCells){
        placeRectangle(emptyQrCodeCells,0,8,6,1,EmptyQrCodeCell.PRE_MARK);
        placeRectangle(emptyQrCodeCells,8,0,1,6,EmptyQrCodeCell.PRE_MARK);
        placeRectangle(emptyQrCodeCells,8,7,1,2,EmptyQrCodeCell.PRE_MARK);
        placeRectangle(emptyQrCodeCells,7,8,1,1,EmptyQrCodeCell.PRE_MARK);
        placeRectangle(emptyQrCodeCells,emptyQrCodeCells.length-8,8,8,1,EmptyQrCodeCell.PRE_MARK);
        placeRectangle(emptyQrCodeCells,8,emptyQrCodeCells.length-7,1,7,EmptyQrCodeCell.PRE_MARK);
    }
    public static void placeVersionInformation(EmptyQrCodeCell[][] emptyQrCodeCells,int versionInformation){
        for (int i = 0; i < 18; i++) {
            EmptyQrCodeCell cell=Binary.readPosition(versionInformation,i)?EmptyQrCodeCell.ON:EmptyQrCodeCell.OFF;
            emptyQrCodeCells[i/3][emptyQrCodeCells.length-11+i%3]=cell;
            emptyQrCodeCells[emptyQrCodeCells.length-11+i%3][i/3]=cell;

        }
    }
    public static void placeRectangle(EmptyQrCodeCell[][] emptyQrCodeCells,int x,int y,int width,int height,EmptyQrCodeCell type){
        for (int i = x; i <x+width ; i++) {
            for (int j = y; j <y+height ; j++) {
                emptyQrCodeCells[i][j]=type;
            }
        }
    }
}
