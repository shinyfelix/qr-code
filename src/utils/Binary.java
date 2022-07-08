package utils;

import java.util.List;

public class Binary {
    public static byte writePosition(byte number,int position,boolean one){
        if (position>=8||position<0)throw new IndexOutOfBoundsException("position :"+position+" is out of bounds");
        if (one){
            return (byte) (number | (1<<position));
        }
        else return (byte) (number & ~(1<<position));
    }
    public static int writePosition(int number,int position,boolean one){
        if (position>=32||position<0)throw new IndexOutOfBoundsException("position :"+position+" is out of bounds");
        if (one){
            return (number | (1<<position));
        }
        else return (number & ~(1<<position));
    }
    public static boolean readPosition(int number,int position){
        return (number&1<<position)!=0;
    }
    public static boolean readPosition(byte number,int position){
        return (number&1<<position)!=0;
    }
    public static PointerResult writeBits(List<Byte> dest,int bytePointer,int inBytePointer,int bits,int amount){
        for (int i = amount-1; i>=0; i--) {
            PointerResult pointerResult=writeBit(dest,bytePointer,inBytePointer,Binary.readPosition(bits,i));
            bytePointer=pointerResult.bytePointer();
            inBytePointer=pointerResult.inBytePointer();
        }
        return new PointerResult(bytePointer,inBytePointer);
    }

    public static PointerResult writeBit(List<Byte> dest,int bytePointer,int inBytePointer,boolean bit){
        byte dest_byte=dest.get(bytePointer);
        dest_byte=Binary.writePosition(dest_byte,inBytePointer,bit);
        dest.set(bytePointer,dest_byte);
        inBytePointer=(inBytePointer+1)%8;
        if (inBytePointer==0){
            bytePointer++;
        }
        return new PointerResult(bytePointer,inBytePointer);
    }
    public record PointerResult(int bytePointer,int inBytePointer){}
}
