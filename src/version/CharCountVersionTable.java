package version;

import encoding.EncodingType;
import error_correction.ErrorCorrectionLevel;

import java.util.*;

public class CharCountVersionTable {
    private static final Map<Integer,Integer> errorLNumeric=new HashMap<>();
    private static final Map<Integer,Integer> errorMNumeric=new HashMap<>();
    private static final Map<Integer,Integer> errorQNumeric=new HashMap<>();
    private static final Map<Integer,Integer> errorHNumeric=new HashMap<>();
    private static final Map<Integer,Integer> errorLAlphanumeric=new HashMap<>();
    private static final Map<Integer,Integer> errorMAlphanumeric=new HashMap<>();
    private static final Map<Integer,Integer> errorQAlphanumeric=new HashMap<>();
    private static final Map<Integer,Integer> errorHAlphanumeric=new HashMap<>();
    private static final Map<Integer,Integer> errorLByte=new HashMap<>();
    private static final Map<Integer,Integer> errorMByte=new HashMap<>();
    private static final Map<Integer,Integer> errorQByte=new HashMap<>();
    private static final Map<Integer,Integer> errorHByte=new HashMap<>();
    private static final Map<Integer,Integer> errorLKanji=new HashMap<>();
    private static final Map<Integer,Integer> errorMKanji=new HashMap<>();
    private static final Map<Integer,Integer> errorQKanji=new HashMap<>();
    private static final Map<Integer,Integer> errorHKanji=new HashMap<>();
    private static final Map<ErrorCorrectionLevel,Map<Integer,Integer>> numeric=new HashMap<>();
    private static final Map<ErrorCorrectionLevel,Map<Integer,Integer>> alphanumeric=new HashMap<>();
    private static final Map<ErrorCorrectionLevel,Map<Integer,Integer>> Byte=new HashMap<>();
    private static final Map<ErrorCorrectionLevel,Map<Integer,Integer>> kanji=new HashMap<>();

    private static final Map<EncodingType,Map<ErrorCorrectionLevel,Map<Integer,Integer>>> map=new HashMap<>();
    static {
        map.put(EncodingType.NUMERIC,numeric);
        map.put(EncodingType.ALPHANUMERIC,alphanumeric);
        map.put(EncodingType.BYTE,Byte);
        map.put(EncodingType.KANJI,kanji);

        numeric.put(ErrorCorrectionLevel.L,errorLNumeric);
        numeric.put(ErrorCorrectionLevel.M,errorMNumeric);
        numeric.put(ErrorCorrectionLevel.Q,errorQNumeric);
        numeric.put(ErrorCorrectionLevel.H,errorHNumeric);

        alphanumeric.put(ErrorCorrectionLevel.L,errorLAlphanumeric);
        alphanumeric.put(ErrorCorrectionLevel.M,errorMAlphanumeric);
        alphanumeric.put(ErrorCorrectionLevel.Q,errorQAlphanumeric);
        alphanumeric.put(ErrorCorrectionLevel.H,errorHAlphanumeric);

        Byte.put(ErrorCorrectionLevel.L,errorLByte);
        Byte.put(ErrorCorrectionLevel.M,errorMByte);
        Byte.put(ErrorCorrectionLevel.Q,errorQByte);
        Byte.put(ErrorCorrectionLevel.H,errorHByte);

        kanji.put(ErrorCorrectionLevel.L,errorLKanji);
        kanji.put(ErrorCorrectionLevel.M,errorMKanji);
        kanji.put(ErrorCorrectionLevel.Q,errorQKanji);
        kanji.put(ErrorCorrectionLevel.H,errorHKanji);

        errorLNumeric.put(1,41);
        errorLNumeric.put(2,77);
        errorLNumeric.put(3,127);
        errorLNumeric.put(4,187);
        errorLNumeric.put(5,255);

        errorLNumeric.put(6,322);
        errorLNumeric.put(7,370);
        errorLNumeric.put(8,461);
        errorLNumeric.put(9,552);
        errorLNumeric.put(10,652);

        errorLNumeric.put(11,772);
        errorLNumeric.put(12,883);
        errorLNumeric.put(13,1022);
        errorLNumeric.put(14,1101);
        errorLNumeric.put(15,1250);

        errorLNumeric.put(16,1408);
        errorLNumeric.put(17,1548);
        errorLNumeric.put(18,1725);
        errorLNumeric.put(19,1903);
        errorLNumeric.put(20,2061);

        errorLNumeric.put(21,2232);
        errorLNumeric.put(22,2409);
        errorLNumeric.put(23,2620);
        errorLNumeric.put(24,2812);
        errorLNumeric.put(25,3057);

        errorLNumeric.put(26,3283);
        errorLNumeric.put(27,3517);
        errorLNumeric.put(28,3669);
        errorLNumeric.put(29,3909);
        errorLNumeric.put(30,4158);

        errorLNumeric.put(31,4417);
        errorLNumeric.put(32,4686);
        errorLNumeric.put(33,4965);
        errorLNumeric.put(34,5253);
        errorLNumeric.put(35,5529);

        errorLNumeric.put(36,5836);
        errorLNumeric.put(37,6153);
        errorLNumeric.put(38,6479);
        errorLNumeric.put(39,6743);
        errorLNumeric.put(40,7089);


    }
    public static int getCharacterCount(int version, EncodingType encodingType, ErrorCorrectionLevel errorCorrectionLevel){
        return map.get(encodingType).get(errorCorrectionLevel).get(version);
    }
    public static Optional<Integer> getVersion(int charCount, EncodingType encodingType, ErrorCorrectionLevel errorCorrectionLevel){
        Map<Integer,Integer> charVersion=map.get(encodingType).get(errorCorrectionLevel);
        List<Map.Entry<Integer,Integer>> list=charVersion.entrySet().stream().sorted(Map.Entry.comparingByKey()).toList();
        for (var entry :
                list) {
            if (entry.getValue()>=charCount)return Optional.of(entry.getKey());
        }
        return Optional.empty();
    }
}
