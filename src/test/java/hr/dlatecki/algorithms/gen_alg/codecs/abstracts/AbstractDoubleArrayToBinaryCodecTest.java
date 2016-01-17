package hr.dlatecki.algorithms.gen_alg.codecs.abstracts;

import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;

/**
 * Class which contains tests for <code>AbstractDoubleArrayToBinaryCodec</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractDoubleArrayToBinaryCodec
 */
public class AbstractDoubleArrayToBinaryCodecTest {
    
    /**
     * Specifies the number of bits per <code>double</code> value to pass to the constructor in constructor test.
     */
    private static final int BITS_PER_VALUE = 8;
    /**
     * Lower precision to use in encoding/decoding tests when comparing <code>double</code> numbers.
     */
    public static final double LOWER_PRECISION = 10E-3;
    /**
     * Lower bound to pass to the constructor in constructor test. Must be less than {@link #UPPER_BOUND}.
     */
    private static final double LOWER_BOUND = -1.0;
    /**
     * Upper bound to pass to the constructor in constructor test. Must be greater than {@link #LOWER_BOUND}.
     */
    private static final double UPPER_BOUND = 1.0;
    /**
     * Input array in encoding/decoding tests.
     */
    private static final double[] INPUT_ARRAY =
            { 0.5, -0.5, 1.1, -1.1, 1.0, -1.0, 0.33, -0.33, 0.15, 0.17, -0.13, -0.98 };
    /**
     * Expected output array in encoding/decoding tests.
     */
    private static final double[] OUTPUT_ARRAY = new double[INPUT_ARRAY.length];
    /**
     * Expected step value in the constructor test.
     */
    private static final double EXPECTED_STEP = (UPPER_BOUND - LOWER_BOUND) / Math.pow(2.0, BITS_PER_VALUE);
    
    static {
        for (int i = 0; i < INPUT_ARRAY.length; i++) {
            double value = INPUT_ARRAY[i];
            
            if (value <= LOWER_BOUND) {
                OUTPUT_ARRAY[i] = LOWER_BOUND;
            } else if (value >= UPPER_BOUND) {
                OUTPUT_ARRAY[i] = UPPER_BOUND;
            } else {
                OUTPUT_ARRAY[i] = value;
            }
        }
    }
    
    /**
     * Class which extends <code>AbstractDoubleArrayToBinaryCodec</code> in order to gain access to protected parameters
     * in tests.
     * 
     * @author Domagoj Latečki
     * @version 1.0
     * @since 1.8
     */
    private static class AbstractDoubleArrayToBinaryCodecExtender extends AbstractDoubleArrayToBinaryCodec {
        
        /**
         * Constructor used to pass values to superclass constructor.
         * 
         * @param bitsPerNumber bits per value to pass to superclass constructor.
         * @param lowerBound value of lower bound to pass to superclass constructor.
         * @param upperBound value of upper bound to pass to superclass constructor.
         */
        public AbstractDoubleArrayToBinaryCodecExtender(int bitsPerNumber, double lowerBound, double upperBound) {
            super(bitsPerNumber, lowerBound, upperBound);
        }
        
        @Override
        protected long calculateEncodedLowerBound(double lowerBound, int bitsPerValue) {
            
            // TODO Auto-generated method stub
            return 0;
        }
        
        @Override
        protected long calculateEncodedUpperBound(double upperBound, int bitsPerValue) {
            
            // TODO Auto-generated method stub
            return 0;
        }
        
        @Override
        protected long encodeValue(double value, double lowerBound, double upperBound, double step, int bitsPerValue) {
            
            // TODO Auto-generated method stub
            return 0;
        }
        
        @Override
        protected double decodeValue(long value, double lowerBound, double upperBound, double step, int bitsPerValue) {
            
            // TODO Auto-generated method stub
            return 0;
        }
    }
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractDoubleArrayToBinaryCodecExtender a =
                new AbstractDoubleArrayToBinaryCodecExtender(BITS_PER_VALUE, LOWER_BOUND, UPPER_BOUND) {
                    
                    @Override
                    protected long encodeValue(double value, double lowerBound, double upperBound, double step,
                            int bitsPerValue) {
                            
                        Assert.assertEquals(BITS_PER_VALUE, bitsPerValue);
                        Assert.assertEquals(LOWER_BOUND, lowerBound, TestUtilities.PRECISION);
                        Assert.assertEquals(UPPER_BOUND, upperBound, TestUtilities.PRECISION);
                        Assert.assertEquals(EXPECTED_STEP, step, TestUtilities.PRECISION);
                        
                        return 0;
                    }
                };
    }
    
    /**
     * Tests if the constructor throws <code>IllegalArgumentException</code> if too few bits per value have been
     * specified.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionForTooFewBitsPerValue() {
        
        new AbstractDoubleArrayToBinaryCodecExtender(AbstractDoubleArrayToBinaryCodec.getMinNumOfBitsPerValue() - 1,
                LOWER_BOUND, UPPER_BOUND);
    }
    
    /**
     * Tests if the constructor throws <code>IllegalArgumentException</code> if too may bits per value have been
     * specified.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionForTooManyBitsPerValue() {
        
        new AbstractDoubleArrayToBinaryCodecExtender(AbstractDoubleArrayToBinaryCodec.getMaxNumOfBitsPerValue() + 1,
                LOWER_BOUND, UPPER_BOUND);
    }
    
    /**
     * Tests if the constructor throws <code>IllegalArgumentException</code> if specified lower bound is greater than
     * specified upper bound.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorThrowsExceptionForWrongBounds() {
        
        new AbstractDoubleArrayToBinaryCodecExtender(BITS_PER_VALUE, UPPER_BOUND, LOWER_BOUND);
    }
    
    /**
     * Tests the encoding and decoding when specified number of bits per value is 8, 16, 24 or 32.
     */
    @Test
    public void testEncodingAndDecodingForWholeByteSizes() {
        
        AbstractDoubleArrayToBinaryCodecExtender a =
                new AbstractDoubleArrayToBinaryCodecExtender(8, LOWER_BOUND, UPPER_BOUND);
        // TODO: finish
    }
}
