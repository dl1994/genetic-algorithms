// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.codecs.abstracts;

import hr.dlatecki.algorithms.gen_alg.codecs.interfaces.IByteArrayCodec;

/**
 * An abstract implementation of <code>IByteArrayCodec</code> which encodes/decodes an array of <code>double</code>s.
 * Each <code>double</code> value can be represented by a minimum of 8 and a maximum of 32 bits. This class has
 * implemented methods for storing and reading bits in and from an array of <code>byte</code>s. Only methods for
 * encoding/decoding <code>double</code>s into bits need to be implemented.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see IByteArrayCodec
 */
public abstract class AbstractDoubleArrayToBinaryCodec implements IByteArrayCodec<double[]> {
    
    /**
     * Mask for the lowest byte.
     */
    private static final long BYTE_MASK = 0xFFL;
    /**
     * Minimal number of bits per value.
     */
    private static final int MIN_BITS_PER_VALUE = 8;
    /**
     * Maximum number of bits per value.
     */
    private static final int MAX_BITS_PER_VALUE = 32;
    /**
     * Number of code bits per single <code>double</code> value.
     */
    protected int bitsPerValue;
    /**
     * Minimum value which will be encoded. All values smaller than this will have bits set to all zeroes.
     */
    protected double lowerBound;
    /**
     * Maximum value which will be encoded. All values greater than this will have bits set to all ones.
     */
    protected double upperBound;
    /**
     * Minimum difference between two values when encoding and decoding.
     */
    protected double step;
    
    /**
     * Fetches the minimum number of bits per value.
     * 
     * @return Minimum number of bits per value.
     */
    public static int getMinNumOfBitsPerValue() {
        
        return MIN_BITS_PER_VALUE;
    }
    
    /**
     * Fetches the maximum number of bits per value.
     * 
     * @return Maximum number of bits per value.
     */
    public static int getMaxNumOfBitsPerValue() {
        
        return MAX_BITS_PER_VALUE;
    }
    
    /**
     * Constructs a <code>AbstractDoubleArrayToBinaryCodec</code> object with provided coding parameters.
     * 
     * @param bitsPerValue number of code bits per single <code>double</code> value. Minimum value is
     *            {@value #MIN_BITS_PER_VALUE}, and maximum value is {@value #MAX_BITS_PER_VALUE}.
     * @param lowerBound minimum value to encode. All values smaller than this will have bits set to all zeroes.
     * @param upperBound maximum value to encode. All values greater than this will have bits set to all ones.
     * @throws IllegalArgumentException thrown if number of code bits is less than {@value #MIN_BITS_PER_VALUE} or
     *             greater than {@value #MAX_BITS_PER_VALUE}.
     */
    public AbstractDoubleArrayToBinaryCodec(int bitsPerValue, double lowerBound, double upperBound) {
        if (bitsPerValue < MIN_BITS_PER_VALUE || bitsPerValue > MAX_BITS_PER_VALUE) {
            throw new IllegalArgumentException("Valid range for number of bits is [" + MIN_BITS_PER_VALUE + ", "
                    + MAX_BITS_PER_VALUE + "]. Provided value was " + bitsPerValue + ".");
        }
        
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException(
                    "Upper bound must be greater than lower bound. Provided values were: lowerBound = " + lowerBound
                            + ", upperBound = " + upperBound + ".");
        }
        
        this.bitsPerValue = bitsPerValue;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        
        step = (upperBound - lowerBound) / Math.pow(2.0, bitsPerValue);
    }
    
    @Override
    public byte[] encode(double[] item) {
        
        int numOfBytes = (int) Math.ceil((item.length * bitsPerValue) / 8.0);
        byte[] output = new byte[numOfBytes];
        
        if (bitsPerValue == 8) {
            encode8Bits(item, output);
        } else if (bitsPerValue < 16) {
            encode9To15Bits(item, output);
        } else if (bitsPerValue == 16) {
            encode16Bits(item, output);
        } else if (bitsPerValue < 24) {
            encode17To23Bits(item, output);
        } else if (bitsPerValue == 24) {
            encode24Bits(item, output);
        } else if (bitsPerValue < 32) {
            encode25To31Bits(item, output);
        } else {
            encode32Bits(item, output);
        }
        
        return output;
    }
    
    /**
     * Used to encode and store values when each value is assigned exactly 8 bits.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode8Bits(double values[], byte[] bytes) {
        
        int i = 0;
        for (double value : values) {
            long encodedValue = encodeValue(value);
            
            bytes[i] = (byte) encodedValue;
            i++;
        }
    }
    
    /**
     * Used to encode and store values when each value is between 9 and 15 bits, inclusive.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode9To15Bits(double[] values, byte[] bytes) {
        
        int i = 0;
        int bitPosition = 0;
        for (double value : values) {
            int rot = bitsPerValue + bitPosition - 8;
            long encodedValue = encodeValue(value);
            
            bitPosition = rot;
            bytes[i] |= (byte) (encodedValue >>> rot);
            rot = 8 - rot;
            bytes[i + 1] |= (byte) ((encodedValue << rot) & BYTE_MASK);
            i++;
        }
    }
    
    /**
     * Used to encode and store values when each value is assigned exactly 16 bits.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode16Bits(double values[], byte[] bytes) {
        
        int i = 0;
        for (double value : values) {
            long encodedValue = encodeValue(value);
            
            bytes[i] = (byte) (encodedValue >>> 8L);
            bytes[i + 1] = (byte) (encodedValue & BYTE_MASK);
            i += 2;
        }
    }
    
    /**
     * Used to encode and store values when each value is between 17 and 23 bits, inclusive.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode17To23Bits(double[] values, byte[] bytes) {
        
        int i = 0;
        int bitPosition = 0;
        for (double value : values) {
            int rot = bitsPerValue + bitPosition - 8;
            long encodedValue = encodeValue(value);
            
            bytes[i] |= (byte) (encodedValue >>> rot);
            rot = bitsPerValue + bitPosition - 16;
            bitPosition = rot;
            bytes[i + 1] |= (byte) ((encodedValue >>> rot) & BYTE_MASK);
            rot = 8 - rot;
            bytes[i + 2] |= (byte) ((encodedValue << rot) & BYTE_MASK);
            i += 2;
        }
    }
    
    /**
     * Used to encode and store values when each value is assigned exactly 24 bits.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode24Bits(double values[], byte[] bytes) {
        
        int i = 0;
        for (double value : values) {
            long encodedValue = encodeValue(value);
            
            bytes[i] = (byte) (encodedValue >>> 16L);
            bytes[i + 1] = (byte) ((encodedValue >>> 8L) & BYTE_MASK);
            bytes[i + 2] = (byte) (encodedValue & BYTE_MASK);
            i += 3;
        }
    }
    
    /**
     * Used to encode and store values when each value is between 25 and 31 bits, inclusive.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode25To31Bits(double[] values, byte[] bytes) {
        
        int i = 0;
        int bitPosition = 0;
        for (double value : values) {
            int rot = bitsPerValue + bitPosition - 8;
            long encodedValue = encodeValue(value);
            
            bytes[i] |= (byte) (encodedValue >>> rot);
            rot = bitsPerValue + bitPosition - 16;
            bytes[i + 1] |= (byte) ((encodedValue >>> rot) & BYTE_MASK);
            rot = bitsPerValue + bitPosition - 24;
            bitPosition = rot;
            bytes[i + 2] |= (byte) ((encodedValue >>> rot) & BYTE_MASK);
            rot = 8 - rot;
            bytes[i + 3] |= (byte) ((encodedValue << rot) & BYTE_MASK);
            i += 3;
        }
    }
    
    /**
     * Used to encode and store values when each value is assigned exactly 24 bits.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode32Bits(double values[], byte[] bytes) {
        
        int i = 0;
        for (double value : values) {
            long encodedValue = encodeValue(value);
            
            bytes[i] = (byte) (encodedValue >>> 24L);
            bytes[i + 1] = (byte) ((encodedValue >>> 16L) & BYTE_MASK);
            bytes[i + 2] = (byte) ((encodedValue >>> 8L) & BYTE_MASK);
            bytes[i + 3] = (byte) (encodedValue & BYTE_MASK);
            i += 4;
        }
    }
    
    @Override
    public double[] decode(byte[] bytes) {
        
        int numOfValues = (int) Math.floor((bytes.length * 8.0) / bitsPerValue);
        double[] output = new double[numOfValues];
        
        if (bitsPerValue == 8) {
            decode8Bits(output, bytes);
        } else if (bitsPerValue < 16) {
            decode9To15Bits(output, bytes);
        } else if (bitsPerValue == 16) {
            decode16Bits(output, bytes);
        } else if (bitsPerValue < 24) {
            decode17To23Bits(output, bytes);
        } else if (bitsPerValue == 24) {
            decode24Bits(output, bytes);
        } else if (bitsPerValue < 32) {
            decode25To31Bits(output, bytes);
        } else {
            decode32Bits(output, bytes);
        }
        
        return output;
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is assigned exactly 8 bits.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode8Bits(double[] values, byte[] bytes) {
        
        for (int i = 0; i < values.length; i++) {
            values[i] = decodeValue(bytes[i]);
        }
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is between 9 and 15 bits, inclusive.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode9To15Bits(double[] values, byte[] bytes) {
        
        int j = 0;
        int bitPosition = 0;
        for (int i = 0; i < values.length; i++) {
            int rot = bitsPerValue - 8;
            int oldBitPosition = bitPosition;
            long temp = (bytes[j] << bitPosition) & BYTE_MASK;
            long valueBits = 0L;
            
            bitPosition = (rot + bitPosition) % 8;
            valueBits |= temp << rot;
            rot = 8 - rot - oldBitPosition;
            valueBits |= bytes[j + 1] >>> rot;
            values[i] = decodeValue(valueBits);
            j++;
        }
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is assigned exactly 16 bits.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode16Bits(double[] values, byte[] bytes) {
        
        int j = 0;
        for (int i = 0; i < values.length; i++) {
            values[i] = decodeValue((bytes[j] << 8) & bytes[j + 1]);
            j += 2;
        }
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is between 17 and 23 bits, inclusive.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode17To23Bits(double[] values, byte[] bytes) {
        
        int j = 0;
        int bitPosition = 0;
        for (int i = 0; i < values.length; i++) {
            int rot = bitsPerValue - 8;
            int oldBitPosition = bitPosition;
            long temp = (bytes[j] << bitPosition) & BYTE_MASK;
            long valueBits = 0L;
            
            bitPosition = (rot + bitPosition) % 8;
            valueBits |= temp << rot;
            rot = bitsPerValue - 16 + oldBitPosition;
            valueBits |= bytes[j + 1] << rot;
            rot = 8 - rot;
            valueBits |= bytes[j + 2] >>> rot;
            values[i] = decodeValue(valueBits);
            j += 2;
        }
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is assigned exactly 24 bits.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode24Bits(double[] values, byte[] bytes) {
        
        int j = 0;
        for (int i = 0; i < values.length; i++) {
            values[i] = decodeValue((bytes[j] << 16) & (bytes[j + 1] << 8) & bytes[j + 2]);
            j += 3;
        }
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is between 25 and 31 bits, inclusive.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode25To31Bits(double[] values, byte[] bytes) {
        
        int j = 0;
        int bitPosition = 0;
        for (int i = 0; i < values.length; i++) {
            int rot = bitsPerValue - 8;
            int oldBitPosition = bitPosition;
            long temp = (bytes[j] << bitPosition) & BYTE_MASK;
            long valueBits = 0L;
            
            bitPosition = (rot + bitPosition) % 8;
            valueBits |= temp << rot;
            rot = bitsPerValue - 24 + oldBitPosition;
            valueBits |= bytes[j + 1] << rot;
            rot = bitsPerValue - 16 + oldBitPosition;
            valueBits |= bytes[j + 2] << rot;
            rot = 8 - rot;
            valueBits |= bytes[j + 3] >>> rot;
            values[i] = decodeValue(valueBits);
            j += 3;
        }
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is assigned exactly 32 bits.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode32Bits(double[] values, byte[] bytes) {
        
        int j = 0;
        for (int i = 0; i < values.length; i++) {
            values[i] = decodeValue((bytes[j] << 24) & (bytes[j + 1] << 16) & (bytes[j + 2] << 8) & (bytes[j + 3]));
            j += 4;
        }
    }
    
    /**
     * Encodes the given <code>double</code> value into the bits which are stored in a <code>long</code> variable.
     * 
     * @param value value to encode.
     * @return Bits of the encoded value stored in <code>long</code> variable.
     */
    protected abstract long encodeValue(double value);
    
    /**
     * Decodes the given <code>long</code> value into the <code>double</code> value.
     * 
     * @param value value to decode.
     * @return <code>double</code> value which was decoded from the <code>long</code> value.
     */
    protected abstract double decodeValue(long value);
}
