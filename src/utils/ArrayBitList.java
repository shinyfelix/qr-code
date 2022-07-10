package utils;

import java.util.ArrayList;
import java.util.Optional;

public class ArrayBitList extends AbstractBitList{
    private int size;
    private final ArrayList<Integer> list;
    public ArrayBitList(){
        size=0;
        list=new ArrayList<>();
    }
    @Override
    public boolean writeBit(int position, boolean bit) {
        if (position>=size) return false;
        int written=Binary.writePosition(list.get(position/32),position%32,bit);
        list.set(position/32,written);
        return true;
    }

    @Override
    public Optional<Boolean> readBit(int position) {
        if (position>=size)return Optional.empty();
        boolean bit=Binary.readPosition(list.get(position/32),position%32);
        return Optional.of(bit);
    }

    @Override
    public void append(boolean bit) {
        if (size%32==0){
            size++;
            if (bit){
                list.add(0);
                writeBit(size-1,true);
            }
            else list.add(0);

        }
        else {
            size++;
            writeBit(size-1,bit);
        }
    }

    @Override
    public int size() {
        return size;
    }
}
