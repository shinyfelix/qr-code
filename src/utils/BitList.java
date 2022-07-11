package utils;

import java.util.List;
import java.util.Optional;

/**
 *
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
     * @param position the position you want to read the bit at
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

    /**
     * reads 32 bits at the specified position and returns the result as an int.
     * @param position the position you want to read the bits
     * @return the result if the action was a success. An empty Optional else.
     */
    Optional<Integer> readBits_int(int position);

    /**
     * Write the bits at the specified position.
     * @param position position in the list to write the bits
     * @param bits the bits you want write
     * @return Whether the action was a success
     */
    boolean writeBits(int position,byte bits);

    /**
     reads 8 bits at the specified position and returns the result as a byte.
     * @param position the position you want to read the bits
     * @return the result if the action was a success. An empty Optional else.
     */
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

    /**
     * Reads the specified amount of bits. The result is returned as an int.
     * The result is located at the most significant bits of the integer.
     * The rest is padded with zeros
     * @param position the position you want to read the bits
     * @param amount the amount of bits you want ot read
     * @return the result if the action was a success. An empty Optional else.
     */
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
    /**
     * Reads the specified amount of bits. The result is returned as an int.
     * The result is located at the least significant bits of the integer.
     * The rest is padded with zeros
     * @param position the position you want to read the bits
     * @param amount the amount of bits you want ot read
     * @return the result if the action was a success. An empty Optional else.
     */
    Optional<Integer> readLSBits(int position,int amount);

    /**
     * Append one bit to the list
     * @param bit The bit you want to append
     */
    void append(boolean bit);

    /**
     * Append bits to the list
     * @param bits bits you want to append
     */
    void append(int bits);
    /**
     * Append bits to the list
     * @param bits bits you want to append
     */
    void append(byte bits);
    /**
     * Append the specified amount of most significant bits to the list.
     * @param bits bits you want to append
     * @param amount the amount of bits you want to append
     */
    void appendMSB(int bits, int amount);
    /**
     * Append the specified amount of least significant bits to the list.
     * @param bits bits you want to append
     * @param amount the amount of bits you want to append
     */
    void appendLSB(int bits, int amount);

    /**
     * Append the specified amount of zeros to the list
     * @param amount the amount of zeros you want to append
     */
    void appendZeros(int amount);

    /**
     * Append another bitlist to the list
     * @param bitList the bitlist you want to append
     */
    void append(BitList bitList);

    /**
     *
     * @return the list as a byte array
     */
    byte[] toByteArray();

    /**
     * @return the list as an int array
     */
    int[] toIntArray();

    /**
     * @return the size of the list.
     */
    int size();

    /**
     * Clears the entire BitLists content.
     */
    void clear();


    /**
     *
     * @return the list represented as a bit-string
     */
    @Override
    String toString();


}
