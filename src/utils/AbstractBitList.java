package utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class AbstractBitList implements BitList{
    @Override
    public abstract boolean writeBit(int position,boolean bit);
    @Override
    public abstract Optional<Boolean> readBit(int position);
    @Override
    public abstract void append(boolean bit);
    @Override
    public abstract int size();
    @Override
    public abstract void clear();

    @Override
    public boolean writeBits(int position, int bits) {
        for (int i = 0; i < 32; i++) {
            if (!writeBit(position+i,Binary.readPosition(bits,31-i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public Optional<Integer> readBits_int(int position) {
        int result=0;
        for (int i = 0; i < 32; i++) {
            var optional=readBit(position+i);
            if (optional.isEmpty())return Optional.empty();
            if (optional.get())result=Binary.writePosition(result,31-i,true);
        }
        return Optional.of(result);
    }

    @Override
    public boolean writeBits(int position, byte bits) {
        for (int i = 0; i < 8; i++) {
            if (!writeBit(position+i,Binary.readPosition(bits,7-i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public Optional<Byte> readBits_byte(int position) {
        byte result=0;
        for (int i = 0; i < 8; i++) {
            var optional=readBit(position+i);
            if (optional.isEmpty())return Optional.empty();
            if (optional.get())result=Binary.writePosition(result,7-i,true);
        }
        return Optional.of(result);
    }

    @Override
    public boolean writeMSBits(int position, int bits, int amount) {
        for (int i = 0; i < amount; i++) {
            if (!writeBit(position+i,Binary.readPosition(bits,31-i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public Optional<Integer> readMSBits(int position, int amount) {
        int result=0;
        for (int i = 0; i < amount; i++) {
            var optional=readBit(position+i);
            if (optional.isEmpty())return Optional.empty();
            if (optional.get())result=Binary.writePosition(result,31-i,true);
        }
        return Optional.of(result);
    }

    @Override
    public boolean writeLSBits(int position, int bits, int amount) {
        for (int i = 0; i < amount; i++) {
            if (!writeBit(position+i,Binary.readPosition(bits,amount-i))){
                return false;
            }
        }
        return true;
    }

    @Override
    public Optional<Integer> readLSBits(int position, int amount) {
        int result=0;
        for (int i = 0; i < amount; i++) {
            var optional=readBit(position+i);
            if (optional.isEmpty())return Optional.empty();
            if (optional.get())result=Binary.writePosition(result,amount-i,true);
        }
        return Optional.of(result);
    }

    @Override
    public void append(int bits) {
        for (int i = 0; i < 32; i++) {
            append(Binary.readPosition(bits,31-i));
        }
    }

    @Override
    public void append(byte bits) {
        for (int i = 0; i < 8; i++) {
            append(Binary.readPosition(bits,7-i));
        }
    }

    @Override
    public void appendMSB(int bits, int amount) {
        for (int i = 0; i < amount; i++) {
            append(Binary.readPosition(bits,31-i));
        }
    }

    @Override
    public void appendLSB(int bits, int amount) {
        for (int i = 0; i < amount; i++) {
            append(Binary.readPosition(bits,amount-i));
        }
    }

    @Override
    public void appendZeros(int amount) {
        for (int i = 0; i < amount; i++) {
            append(false);
        }
    }

    @Override
    public void append(BitList bitList) {
        bitList.forEach(this::append);
    }

    @Override
    public byte[] toByteArray() {
        int size=size();
        byte[] bytes=new byte[size/8+size%8==0?0:1];
        for (int i = 0; i < size/8; i++) {
            Optional<Byte> bits=readBits_byte(i*8);
            assert bits.isPresent();
            bytes[i]=bits.get();
        }
        int rest=size%8;
        if (rest!=0){
            Optional<Integer> bits=readLSBits(size-size%8,size%8);
            assert bits.isPresent();
            bytes[bytes.length-1]=(byte)(bits.get()<<(8-size%8));
        }
        return bytes;
    }
    @Override
    public int[] toIntArray(){
        int size=size();
        int[] ints=new int[size/32+size%32==0?0:1];
        for (int i = 0; i < size/32; i++) {
            Optional<Integer> bits=readBits_int(i*32);
            assert bits.isPresent();
            ints[i]=bits.get();
        }
        int rest=size%32;
        if (rest!=0){
            Optional<Integer> bits=readMSBits(size-size%32,size%32);
            assert bits.isPresent();
            ints[ints.length-1]=bits.get();
        }
        return ints;
    }

    @Override
    public Iterator<Boolean> iterator() {
        return new Iterator<>() {
            int position=0;
            @Override
            public boolean hasNext() {
                return position<size();
            }

            @Override
            public Boolean next() {
                if (!hasNext())throw new NoSuchElementException("No more elements");
                Optional<Boolean> bit=readBit(position);
                assert bit.isPresent();
                position++;
                return bit.get();
            }
        };
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        for (var bool :
                this) {
            stringBuilder.append(bool?"1":"0");
        }
        return stringBuilder.toString();
    }

}

