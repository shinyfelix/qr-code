package utils;

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
        return ((number>>>position)&1)==1;
        //return (number&1<<position)!=0;
    }
    public static boolean readPosition(byte number,int position){
        return ((number>>>position)&1)==1;
        //return (number&1<<position)!=0;
    }
}
