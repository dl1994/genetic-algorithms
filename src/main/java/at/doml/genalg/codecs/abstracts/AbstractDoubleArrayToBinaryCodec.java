package at.doml.genalg.codecs.abstracts;

import at.doml.genalg.codecs.interfaces.IByteArrayCodec;

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
     * Serial version UID.
     */
    private static final long serialVersionUID = -3583416420870841421L;
    /**
     * Mask for the single lowest byte.
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
    private final int bitsPerValue;
    /**
     * Minimum value which will be encoded. All values smaller than this will have bits set to all zeroes.
     */
    private final double lowerBound;
    /**
     * Maximum value which will be encoded. All values greater than this will have bits set to all ones.
     */
    private final double upperBound;
    /**
     * Minimum difference between two values when encoding and decoding.
     */
    private final double step;
    /**
     * Encoded <code>lowerBound</code>.
     */
    private final long encodedLowerBound;
    /**
     * Encoded <code>upperBound</code>.
     */
    private final long encodedUpperBound;
    /**
     * Used to mask first {@link #bitsPerValue} bits in <code>long</code> number.
     */
    private long bitMask;
    
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
     * Constructs an <code>AbstractDoubleArrayToBinaryCodec</code> object with provided coding parameters.
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
        encodedLowerBound = calculateEncodedLowerBound(lowerBound, bitsPerValue);
        encodedUpperBound = calculateEncodedUpperBound(upperBound, bitsPerValue);
        
        for (int i = 0; i < bitsPerValue; i++) {
            bitMask = bitMask << 1L | 1L;
        }
    }
    
    @Override
    public byte[] encode(double[] item) {
        
        int numOfBytes = (int) Math.ceil(item.length * bitsPerValue / 8.0);
        byte[] output = new byte[numOfBytes];
        
        int outputIndex = 0;
        int currentBitCapacity = 8;
        for (int i = 0; i < item.length; i++) {
            long encodedValue = encodeValue(item[i]);
            long mask = bitMask;
            int remainingBits = bitsPerValue;
            
            while (remainingBits != 0) {
                if (currentBitCapacity <= remainingBits) {
                    output[outputIndex] |= encodedValue >>> remainingBits - currentBitCapacity & BYTE_MASK;
                    outputIndex++;
                    remainingBits -= currentBitCapacity;
                    mask = mask >>> currentBitCapacity;
                    currentBitCapacity = 8;
                    encodedValue &= mask;
                } else {
                    output[outputIndex] |= encodedValue << currentBitCapacity - remainingBits & BYTE_MASK;
                    currentBitCapacity -= remainingBits;
                    remainingBits = 0;
                }
            }
        }
        
        return output;
    }
    
    @Override
    public double[] decode(byte[] bytes) {
        
        int numOfValues = (int) Math.floor(bytes.length * 8.0 / bitsPerValue);
        double[] output = new double[numOfValues];
        
        int byteIndex = 0;
        int currentBitPosition = 0;
        for (int i = 0; i < output.length; i++) {
            long valueToDecode = 0L;
            int remainingBits = (byte) bitsPerValue;
            
            while (remainingBits != 0) {
                if (8 - currentBitPosition <= remainingBits) {
                    remainingBits -= 8 - currentBitPosition;
                    valueToDecode |= (bytes[byteIndex] << currentBitPosition
                            & BYTE_MASK) >>> currentBitPosition << remainingBits;
                    currentBitPosition = 0;
                    byteIndex++;
                } else {
                    valueToDecode |= (bytes[byteIndex] & BYTE_MASK) >>> 8 - remainingBits - currentBitPosition;
                    currentBitPosition += remainingBits;
                    remainingBits = 0;
                }
            }
            
            output[i] = decodeValue(valueToDecode & bitMask);
        }
        
        return output;
    }
    
    /**
     * Encodes the given <code>double</code> value into the bits which are stored in a <code>long</code> variable.
     * 
     * @param value value to encode.
     * @return Bits of the encoded value stored in <code>long</code> variable.
     */
    private long encodeValue(double value) {
        
        if (value <= lowerBound) {
            return encodedLowerBound;
        } else if (value >= upperBound) {
            return encodedUpperBound;
        } else {
            return encodeValue(value, lowerBound, upperBound, step, bitsPerValue);
        }
    }
    
    /**
     * Decodes the given <code>long</code> value into the <code>double</code> value.
     * 
     * @param value value to decode.
     * @return <code>double</code> value which was decoded from the <code>long</code> value.
     */
    private double decodeValue(long value) {
        
        if (value == encodedLowerBound) {
            return lowerBound;
        } else if (value == encodedUpperBound) {
            return upperBound;
        } else {
            return decodeValue(value, lowerBound, upperBound, step, bitsPerValue);
        }
    }
    
    /**
     * Calculates the encoded value of <code>lowerBound</code>. For example, in natural binary code this value will be
     * encoded as all zeroes.
     * 
     * @param lowerBound lower bound to encode into <code>byte</code>s which represent the minimum value.
     * @param bitsPerValue number of bits per number to use in encoding. Returned value must have this number of bits
     *            set.
     * @return Encoded <code>lowerBound</code>.
     */
    protected abstract long calculateEncodedLowerBound(double lowerBound, int bitsPerValue);
    
    /**
     * Calculates the encoded value of <code>upperBound</code>. For example, in natural binary code this value will be
     * encoded as all ones.
     * 
     * @param upperBound upper bound to encode into <code>byte</code>s which represent the maximum value.
     * @param bitsPerValue number of bits per number to use in encoding. Returned value must have this number of bits
     *            set.
     * @return Encoded <code>upperBound</code>.
     */
    protected abstract long calculateEncodedUpperBound(double upperBound, int bitsPerValue);
    
    /**
     * Encodes the given <code>double</code> value into the bits which are stored in a <code>long</code> variable. The
     * <code>value</code> provided as an argument of this function will always be between <code>lowerBound</code> and
     * <code>upperBound</code>.
     * 
     * @param value value to encode.
     * @param lowerBound minimum value used in encoding/decoding.
     * @param upperBound maximum value used in encoding/decoding.
     * @param step distance between two values used in encoding/decoding.
     * @param bitsPerValue number of bits per value used in encoding/decoding.
     * @return Bits of the encoded value stored in <code>long</code> variable.
     */
    protected abstract long encodeValue(double value, double lowerBound, double upperBound, double step,
            int bitsPerValue);
            
    /**
     * Decodes the given <code>long</code> value into the <code>double</code> value. The <code>value</code> provided as
     * an argument of this function will never be equal to encoded <code>lowerBound</code> or encoded
     * <code>upperBound</code>.
     * 
     * @param value value to decode.
     * @param lowerBound minimum value used in encoding/decoding.
     * @param upperBound maximum value used in encoding/decoding.
     * @param step distance between two values used in encoding/decoding.
     * @param bitsPerValue number of bits per value used in encoding/decoding.
     * @return <code>double</code> value which was decoded from the <code>long</code> value.
     */
    protected abstract double decodeValue(long value, double lowerBound, double upperBound, double step,
            int bitsPerValue);
}
