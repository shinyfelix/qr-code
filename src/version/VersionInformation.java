package version;

import encoding.EncodingType;
import error_correction.ErrorCorrectionLevel;
import utils.Binary;
import utils.EmptyQrCodeCell;
import utils.Pair;
import utils.QRCodePlacer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class VersionInformation {
    private final int size;
    private final int version;
    private static final int generator=0b1111100100101_00000;
    private Optional<EmptyQrCodeCell[][]> cachedEmptyQRCode;
    private final Optional<Integer> versionBits;
    private VersionInformation(int size,int version){
        this.size=size;
        this.version=version;
        cachedEmptyQRCode=Optional.empty();
        if (version<=6)versionBits=Optional.empty();
        else versionBits=Optional.of(calculateVersionBits());
    }
    private int calculateVersionBits(){
        int version=this.version;
        boolean keep_generating=true;
        int bits=0;
        int leftZeros = 0;
        int i = 5;
        while (!Binary.readPosition(version, i)) {
            i--;
            leftZeros += 1;
        }
        while (keep_generating) {
            int correctly_paddedGenerator = generator >>> leftZeros;
            while (!Binary.readPosition(version, 17 - leftZeros)) version <<= 1;
            bits = version ^ correctly_paddedGenerator;
            keep_generating=false;
            for (int j = 12; j < 18; j++) {
                if (Binary.readPosition(bits, j)) {
                    keep_generating = true;
                    leftZeros=17-j;
                    break;
                }
            }
            version=bits;
        }
        bits+=4096*this.version;
        return bits;
    }
    private List<Integer> getAlignmentCoords(){
        return switch (version){
            case 2->List.of(6,18);
            case 3->List.of(6,22);
            case 4->List.of(6,26);
            case 5->List.of(6,30);
            case 6->List.of(6,34);
            case 7->List.of(6,22,38);
            case 8->List.of(6,24,42);
            case 9->List.of(6,26,46);
            case 10->List.of(6,28,50);
            case 11->List.of(6,30,54);
            case 12->List.of(6,32,58);
            case 13->List.of(6,34,62);
            case 14->List.of(6,26,46,66);
            case 15->List.of(6,26,48,70);
            case 16->List.of(6,26,50,74);
            case 17->List.of(6,30,54,78);
            case 18->List.of(6,30,56,82);
            case 19->List.of(6,30,58,86);
            case 20->List.of(6,34,62,90);
            case 21->List.of(6,28,50,72,94);
            case 22->List.of(6,26,50,74,98);
            case 23->List.of(6,30,54,78,102);
            case 24->List.of(6,28,54,80,106);
            case 25->List.of(6,32,58,84,110);
            case 26->List.of(6,30,58,86,114);
            case 27->List.of(6,34,62,90,118);
            case 28->List.of(6,26,50,74,98,122);
            case 29->List.of(6,30,54,78,102,126);
            case 30->List.of(6,26,52,78,104,130);
            case 31->List.of(6,30,56,82,108,134);
            case 32->List.of(6,34,60,86,112,138);
            case 33->List.of(6,30,58,86,114,142);
            case 34->List.of(6,34,62,90,118,146);
            case 35->List.of(6,40,54,78,102,126,150);
            case 36->List.of(6,24,50,76,102,128,154);
            case 37->List.of(6,28,54,80,106,132,158);
            case 38->List.of(6,32,58,84,110,136,162);
            case 39->List.of(6,26,54,82,110,138,166);
            case 40->List.of(6,30,58,86,114,142,170);
            default -> Collections.emptyList();
        };
    }
    public List<Pair<Integer,Integer>> getAlignmentPatterns(){
        List<Pair<Integer,Integer>> alignments=new ArrayList<>();
        List<Integer> coords= getAlignmentCoords();
        for (var i :
                coords) {
            for (var j :
                    coords) {
                if ((i > 8 || (j > 8 && j < size - 8)) && (i < size - 8 || j > 8)) {
                    alignments.add(new Pair<>(i,j));
                }
            }
        }
        return alignments;
    }

    public EmptyQrCodeCell[][] getEmptyQRCode(){
        if (cachedEmptyQRCode.isPresent())return cachedEmptyQRCode.get();
        EmptyQrCodeCell[][] emptyQRCode=new EmptyQrCodeCell[size][size];
        QRCodePlacer.fillWithEmpty(emptyQRCode);
        QRCodePlacer.placeFinder(emptyQRCode,0,0);
        QRCodePlacer.placeFinder(emptyQRCode,size-8,0);
        QRCodePlacer.placeFinder(emptyQRCode,0,size-8);
        List<Pair<Integer,Integer>> alignments= getAlignmentPatterns();
        for (var pair :
                alignments) {
            QRCodePlacer.placeAlignment(emptyQRCode,pair.getFirst(),pair.getSecond());
        }
        QRCodePlacer.placeTimings(emptyQRCode);
        QRCodePlacer.placeRectangle(emptyQRCode,8,size-8,1,1,EmptyQrCodeCell.ON);
        QRCodePlacer.preMarkFormatInformation(emptyQRCode);
        versionBits.ifPresent(integer -> QRCodePlacer.placeVersionInformation(emptyQRCode, integer));
        cachedEmptyQRCode=Optional.of(emptyQRCode);
        return emptyQRCode;
    }

    public Optional<Integer> getVersionBits(){
        return versionBits;
    }
    public int getSize(){
        return size;
    }
    public int getVersion(){
        return version;
    }
    public static Optional<VersionInformation> ofSize(int size){
        if ((size-21)%4!=0)return Optional.empty();
        int version=(size-21)/4;
        if (version<1||version>40)return Optional.empty();
        return Optional.of(new VersionInformation(size,version));
    }
    public static Optional<VersionInformation> ofVersion(int version){
        if (version<1||version>40)return Optional.empty();
        return Optional.of(new VersionInformation(version*4+21,version));
    }

    public static Optional<VersionInformation> ofBits(int bits){
        int version=bits>>>12;
        Optional<VersionInformation> versionInformation=VersionInformation.ofVersion(version);
        if (versionInformation.isEmpty())return Optional.empty();
        Optional<Integer> otherVersionBits=versionInformation.get().getVersionBits();
        if (otherVersionBits.isEmpty()||otherVersionBits.get()!=bits)return Optional.empty();
        return versionInformation;
    }

    public static Optional<VersionInformation> ofCharacterCount(int count, EncodingType encodingType, ErrorCorrectionLevel errorCorrectionLevel){
        return ofVersion(switch (encodingType){
            case BYTE -> switch (errorCorrectionLevel){
                case H -> {
                    if (count<=7)yield 1;
                    if (count<=14)yield 2;
                    if (count<=24)yield 3;
                    if (count<=34)yield 4;
                    if (count<=44)yield 5;

                    if (count<=58)yield 6;
                    if (count<=64)yield 7;
                    if (count<=84)yield 8;
                    if (count<=98)yield 9;
                    if (count<=119)yield 10;

                    if (count<=137)yield 11;
                    if (count<=155)yield 12;
                    if (count<=177)yield 13;
                    if (count<=194)yield 14;
                    if (count<=220)yield 15;

                    if (count<=250)yield 16;
                    if (count<=280)yield 17;
                    if (count<=310)yield 18;
                    if (count<=338)yield 19;
                    if (count<=382)yield 20;

                    if (count<=403)yield 21;
                    if (count<=439)yield 22;
                    if (count<=461)yield 23;
                    if (count<=511)yield 24;
                    if (count<=535)yield 25;

                    if (count<=593)yield 26;
                    if (count<=625)yield 27;
                    if (count<=658)yield 28;
                    if (count<=698)yield 29;
                    if (count<=742)yield 30;

                    if (count<=790)yield 31;
                    if (count<=842)yield 32;
                    if (count<=898)yield 33;
                    if (count<=958)yield 34;
                    if (count<=983)yield 35;

                    if (count<=1051)yield 36;
                    if (count<=1093)yield 37;
                    if (count<=1139)yield 38;
                    if (count<=1219)yield 39;
                    if (count<=1273)yield 40;
                    yield 0;
                }
                case L, M, Q -> 0;
            };
            case NUMERIC, ALPHANUMERIC, KANJI -> 0;
        });
    }
}
