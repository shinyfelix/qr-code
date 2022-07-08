package utils;

import java.util.List;
import java.util.Optional;

public class Utils {
    public static Optional<Integer> tryParseInt(String string){
        try {
            return Optional.of(Integer.parseInt(string));
        }catch (IllegalArgumentException i){
            return Optional.empty();
        }
    }
    public static byte[] ByteListToByteArray(List<Byte> bytes){
        byte[] byteArray=new byte[bytes.size()];
        for (int i = 0; i < bytes.size(); i++) {
            byteArray[i]=bytes.get(i);
        }
        return byteArray;
    }
}
