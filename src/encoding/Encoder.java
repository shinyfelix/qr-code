package encoding;

import jdk.jshell.execution.Util;
import utils.Binary;
import utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Encoder {
    public static byte[] encode(String input,EncodingType encodingType){
        return switch (encodingType){
            case NUMERIC -> numeric(input);
            case ALPHANUMERIC -> alphanumeric(input);
            case BYTE -> bytes(input);
            case KANJI -> kanji(input);
        };
    }

    private static byte[] numeric(String input){
        String[] split=new String[input.length()/3+input.length()%3==0?0:1];
        for (int i = 0; i < input.length(); i++) {
            split[i/3]+=input.charAt(i);
        }
        List<Byte> bytes=new ArrayList<>();
        int inBytePointer=0;
        int bytePointer=0;
        for (var s :
                split) {
            int number=Integer.parseInt(s);
            int amount;
            if (number>=100){
                amount=10;
            }
            else if (number<10){
                amount=4;
            }
            else {
                amount=7;
            }
            var result=Binary.writeBits(bytes,bytePointer,inBytePointer,number,amount);
            inBytePointer=result.inBytePointer();
            bytePointer= result.bytePointer();
        }
        return Utils.ByteListToByteArray(bytes);
    }
    private static byte[] alphanumeric(String input){
        String[] split=new String[input.length()/2+input.length()%2];
        for (int i = 0; i < input.length(); i++) {
            split[i/2]+=input.charAt(i);
        }
        List<Byte> bytes=new ArrayList<>();
        int inBytePointer=0;
        int bytePointer=0;
        for (var s:split){
            int number;
            int amount;
            if (s.length()==2){
                number=45*calculateAlphaNumValue(s.charAt(0))+s.charAt(1);
                amount=11;
            }
            else{
                number=calculateAlphaNumValue(s.charAt(0));
                amount=6;
            }
            var result=Binary.writeBits(bytes,bytePointer,inBytePointer,number,amount);
            inBytePointer=result.inBytePointer();
            bytePointer=result.bytePointer();
        }
        return Utils.ByteListToByteArray(bytes);
    }
    private static int calculateAlphaNumValue(char c){
        if (c>='0'&&c<='9'){
            return c-'0';
        }
        if (c>='A'&&c<='Z'){
            return c-'A';
        }
        return switch (c){
            case ' '->36;
            case '$'->37;
            case '%'->38;
            case '*'->39;
            case '+'->40;
            case '-'->41;
            case '.'->42;
            case '/'->43;
            case ':'->44;
            default -> throw new IllegalStateException();
        };
    }
    private static byte[] bytes(String input){
        throw new UnsupportedOperationException();
    }
    private static byte[] kanji(String input){
        throw new UnsupportedOperationException();
    }
}
