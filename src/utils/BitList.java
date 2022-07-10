package utils;

import java.util.List;
import java.util.Optional;

/**
 * TODO: append bits, append BitList. Append empty.
 */
public interface BitList extends Iterable<Boolean>{
    /**
     * Write a bit in the list at the specified position
     * @param position the position you want to write the bit at
     * @param bit the bit you want to write
     * @return Whether the action was a success
     */
    boolean writeBit(int position,boolean bit);

    /**
     * Read a bit at the specified position
     * @param position the position you want to write the bit at
     * @return the result if the action was a success. An empty Optional else.
     */
    Optional<Boolean> readBit(int position);

    /**
     * Write the bits at the specified position.<br>
     * Example:<br>
     * list:<br>
     * 0000000000000000000000000000000000000000<br>
     * bits:<br>
     * 0b11101111111111111111111111111111<br>
     * position:<br>
     * 3<br>
     * resulting list:<br>
     * 0001110111111111111111111111111111100000
     * @param position position in the list to write the bits
     * @param bits the bits you want write
     * @return Whether the action was a success
     */
    boolean writeBits(int position,int bits);
    Optional<Integer> readBits_int(int position);
    void writeBits(int position,byte bits);
    Optional<Byte> readBits_byte(int position);

    /**
     * Write the specified amount of most significant bits.
     * Example:<br>
     * list:<br>
     * 00000000000000000000<br>
     * bits:<br>
     * 0b11101111111111111111111111111111<br>
     * position:<br>
     * 3<br>
     * amount:<br>
     * 6<br>
     * resulting list:<br>
     * 00011101100000000000
     * @param position position in the list to write the bits
     * @param bits the bits you want write
     * @param amount the amount of bits you want to write
     * @return Whether the action was a success
     */
    boolean writeMSBits(int position,int bits,int amount);
    Optional<Integer> readMSBits(int position,int amount);
    /**
     * Write the specified amount of least significant bits.
     * Example:<br>
     * list:<br>
     * 00000000000000000000<br>
     * bits:<br>
     * 0b11101111111111111111111111110101<br>
     * position:<br>
     * 3<br>
     * amount:<br>
     * 6<br>
     * resulting list:<br>
     * 00011010100000000000
     * @param position position in the list to write the bits
     * @param bits the bits you want write
     * @param amount the amount of bits you want to write
     * @return Whether the action was a success
     */
    boolean writeLSBits(int position,int bits,int amount);
    Optional<Integer> readLSBits(int position,int amount);

    void writeMSBitsReversed(int position,int bits,int amount);
    Optional<Integer> readMSBitsReversed(int position,int amount);
    void writeLSBitsReversed(int position,int bits,int amount);
    Optional<Integer> readLSBitsReversed(int position,int amount);
    byte[] toByteArray();
    int[] toIntArray();
    int size();


    /**
     *
     * @return the list represented as a bit-string
     */
    @Override
    String toString();


}
