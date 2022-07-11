package encoding;

import jdk.jshell.execution.Util;
import utils.ArrayBitList;
import utils.Binary;
import utils.BitList;
import utils.Utils;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Encoder {
    public static BitList encode(String input,EncodingType encodingType){
        return switch (encodingType){
            case NUMERIC -> numeric(input);
            case ALPHANUMERIC -> alphanumeric(input);
            case BYTE -> bytes(input);
            case KANJI -> kanji(input);
        };
    }

    private static BitList numeric(String input){
        String[] split=new String[input.length()/3+input.length()%3==0?0:1];
        for (int i = 0; i < input.length(); i++) {
            split[i/3]+=input.charAt(i);
        }
        BitList bitList=new ArrayBitList();
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
            bitList.appendLSB(number,amount);
        }
        return bitList;
    }
    private static BitList alphanumeric(String input){
        String[] split=new String[input.length()/2+input.length()%2];
        for (int i = 0; i < input.length(); i++) {
            split[i/2]+=input.charAt(i);
        }
        BitList bitList=new ArrayBitList();
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
            bitList.appendLSB(number,amount);
        }
        return bitList;
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
    private static BitList bytes(String input){
        return BitList.of(input.getBytes(StandardCharsets.ISO_8859_1));
    }
    private static BitList kanji(String input){
        throw new UnsupportedOperationException();
    }
}
