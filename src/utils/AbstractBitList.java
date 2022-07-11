package utils;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Optional;

public abstract class AbstractBitList implements BitList{
    /**
     * Write a bit in the list at the specified position
     * @implSpec This operation should only fail (and return false), when position is greater or equal to the current size (returned by the size method)
     * @param position the position you want to write the bit at
     * @param bit the bit you want to write
     * @return whether the action was a success
     */
    @Override
    public abstract boolean writeBit(int position,boolean bit);

    /**
     * Read a bit at the specified position
     * @implSpec This operation should only fail (and return false), when position is greater or equal to the current size (returned by the size method)
     * @param position the position you want to read the bit at
     * @return the result if the action was a success. An empty Optional else.
     */
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
        if (position+31>=size())return false;
        for (int i = 0; i < 32; i++) {
            if (!writeBit(position+i,Binary.readPosition(bits,31-i))){
                throw new ContractViolationException("Failed write operation, even though size>position");
            }
        }
        return true;
    }

    @Override
    public Optional<Integer> readBits_int(int position) {
        if (position+31>=size())return Optional.empty();
        int result=0;
        for (int i = 0; i < 32; i++) {
            var optional=readBit(position+i);
            if (optional.orElseThrow(()->new ContractViolationException("Failed read operation, even though size>position")))
                result=Binary.writePosition(result,31-i,true);
        }
        return Optional.of(result);
    }

    @Override
    public boolean writeBits(int position, byte bits) {
        if (position+7>=size())return false;
        for (int i = 0; i < 8; i++) {
            if (!writeBit(position+i,Binary.readPosition(bits,7-i))){
                throw new ContractViolationException("Failed write operation, even though size>position");
            }
        }
        return true;
    }

    @Override
    public Optional<Byte> readBits_byte(int position) {
        byte result=0;
        for (int i = 0; i < 8; i++) {
            var optional=readBit(position+i);
            if (optional.orElseThrow(()->new ContractViolationException("Failed read operation, even though size>position")))
                result=Binary.writePosition(result,7-i,true);
        }
        return Optional.of(result);
    }

    @Override
    public boolean writeMSBits(int position, int bits, int amount) {
        if (position+amount-1>size())return false;
        for (int i = 0; i < amount; i++) {
            if (!writeBit(position+i,Binary.readPosition(bits,31-i))){
                throw new ContractViolationException("Failed write operation, even though size>position");
            }
        }
        return true;
    }

    @Override
    public Optional<Integer> readMSBits(int position, int amount) {
        int result=0;
        for (int i = 0; i < amount; i++) {
            var optional=readBit(position+i);
            if (optional.orElseThrow(()->new ContractViolationException("Failed read operation, even though size>position")))
                result=Binary.writePosition(result,31-i,true);
        }
        return Optional.of(result);
    }

    @Override
    public boolean writeLSBits(int position, int bits, int amount) {
        if (position+amount-1>size())return false;
        for (int i = 0; i < amount; i++) {
            if (!writeBit(position+i,Binary.readPosition(bits,amount-i-1))){
                throw new ContractViolationException("Failed write operation, even though size>position");
            }
        }
        return true;
    }

    @Override
    public Optional<Integer> readLSBits(int position, int amount) {
        int result=0;
        for (int i = 0; i < amount; i++) {
            var optional=readBit(position+i);
            if (optional.orElseThrow(()->new ContractViolationException("Failed read operation, even though size>position")))
                result=Binary.writePosition(result,amount-1-i,true);
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
            append(Binary.readPosition(bits,amount-1-i));
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
        byte[] bytes=new byte[size/8+(size%8==0?0:1)];
        for (int i = 0; i < size/8; i++) {
            Optional<Byte> bits=readBits_byte(i*8);
            if (bits.isEmpty()){
                throw new ContractViolationException("Failed read operation, even though size>position");
            }
            bytes[i]=bits.get();
        }
        int rest=size%8;
        if (rest!=0){
            Optional<Integer> bits=readLSBits(size-size%8,size%8);
            if (bits.isEmpty()){
                throw new ContractViolationException("Failed read operation, even though size>position");
            }
            bytes[bytes.length-1]=(byte)(bits.get()<<(8-size%8));
        }
        return bytes;
    }
    @Override
    public int[] toIntArray(){
        int size=size();
        int[] ints=new int[size/32+(size%32==0?0:1)];
        for (int i = 0; i < size/32; i++) {
            Optional<Integer> bits=readBits_int(i*32);
            if (bits.isEmpty()){
                throw new ContractViolationException("Failed read operation, even though size>position");
            }
            ints[i]=bits.get();
        }
        int rest=size%32;
        if (rest!=0){
            Optional<Integer> bits=readMSBits(size-size%32,size%32);
            if (bits.isEmpty()){
                throw new ContractViolationException("Failed read operation, even though size>position");
            }
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
                if (bit.isEmpty()){
                    throw new ContractViolationException("Failed read operation, even though size>position");
                }
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

