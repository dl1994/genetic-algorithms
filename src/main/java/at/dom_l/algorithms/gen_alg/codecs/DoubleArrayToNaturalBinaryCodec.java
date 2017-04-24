package at.dom_l.algorithms.gen_alg.codecs;

import at.dom_l.algorithms.gen_alg.codecs.abstracts.AbstractDoubleArrayToBinaryCodec;

/**
 * An implementation of <code>AbstractDoubleArrayToBinaryCodec</code> which encodes <code>double</code>s with natural
 * binary code. Values can range from specified <code>lowerBound</code> to specified <code>upperBound</code>, with all
 * zeroes representing <code>lowerBound</code> and all ones representing <code>upperBound</code>. Minimum and maximum
 * number of bits per value is specified in <code>AbstractDoubleArrayToBinaryCodec</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractDoubleArrayToBinaryCodec
 * @see DoubleArrayToGrayBinaryCodec
 */
public class DoubleArrayToNaturalBinaryCodec extends AbstractDoubleArrayToBinaryCodec {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -3107399042761628480L;
    
    /**
     * Constructs a <code>DoubleArrayToNaturalBinaryCodec</code> object with provided coding parameters.
     * 
     * @param bitsPerValue number of code bits per single <code>double</code> value. Minimum value is 8, and maximum
     *            value is 32.
     * @param lowerBound minimum value to encode. All values smaller than this will have bits set to all zeroes.
     * @param upperBound maximum value to encode. All values greater than this will have bits set to all ones.
     * @throws IllegalArgumentException thrown if number of code bits is less than 8 or greater than 32.
     */
    public DoubleArrayToNaturalBinaryCodec(int bitsPerValue, double lowerBound, double upperBound) {
        super(bitsPerValue, lowerBound, upperBound);
    }
    
    @Override
    protected long calculateEncodedLowerBound(double lowerBound, int bitsPerValue) {
        
        return 0L;
    }
    
    @Override
    protected long calculateEncodedUpperBound(double upperBound, int bitsPerValue) {
        
        long encodedValue = 0L;
        
        for (int i = 0; i < bitsPerValue; i++) {
            encodedValue = encodedValue << 1L | 1L;
        }
        
        return encodedValue;
    }
    
    @Override
    protected long encodeValue(double value, double lowerBound, double upperBound, double step, int bitsPerValue) {
        
        return (long) ((value - lowerBound) / step);
    }
    
    @Override
    protected double decodeValue(long value, double lowerBound, double upperBound, double step, int bitsPerValue) {
        
        return value * step + lowerBound;
    }
}
