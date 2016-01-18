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
     * Lower bound to pass to the constructor in constructor test. Must be less than {@link #UPPER_BOUND}.
     */
    private static final double LOWER_BOUND = -1.0;
    /**
     * Upper bound to pass to the constructor in constructor test. Must be greater than {@link #LOWER_BOUND}.
     */
    private static final double UPPER_BOUND = 1.0;
    /**
     * Expected step value in the constructor test.
     */
    private static final double EXPECTED_STEP = (UPPER_BOUND - LOWER_BOUND) / Math.pow(2.0, BITS_PER_VALUE);
    /**
     * Array used to test encoding/decoding.
     */
    private static final double[] TEST_ARRAY =
            { UPPER_BOUND, LOWER_BOUND, UPPER_BOUND, LOWER_BOUND, UPPER_BOUND, LOWER_BOUND, UPPER_BOUND };
            
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
         * Encoded {@link #LOWER_BOUND}.
         */
        private long encodedLowerBound;
        /**
         * Encoded {@link #UPPER_BOUND}.
         */
        private long encodedUpperBound;
        
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
            
            encodedLowerBound = 0L;
            
            return encodedLowerBound;
        }
        
        @Override
        protected long calculateEncodedUpperBound(double upperBound, int bitsPerValue) {
            
            long encodedValue = 0L;
            
            for (int i = 0; i < bitsPerValue; i++) {
                encodedValue = encodedValue << 1L | 1;
            }
            
            encodedUpperBound = encodedValue;
            
            return encodedUpperBound;
        }
        
        @Override
        protected long encodeValue(double value, double lowerBound, double upperBound, double step, int bitsPerValue) {
            
            if (Math.abs(Math.abs(value) - Math.abs(LOWER_BOUND)) <= TestUtilities.PRECISION) {
                return encodedLowerBound;
            } else if (Math.abs(Math.abs(value) - Math.abs(UPPER_BOUND)) <= TestUtilities.PRECISION) {
                return encodedUpperBound;
            } else {
                throw new RuntimeException("Unexpected value was passed to encoding function. Value was: " + value);
            }
        }
        
        @Override
        protected double decodeValue(long value, double lowerBound, double upperBound, double step, int bitsPerValue) {
            
            if (value == encodedLowerBound) {
                return LOWER_BOUND;
            } else if (value == encodedUpperBound) {
                return UPPER_BOUND;
            } else {
                throw new RuntimeException("Unexpected value was passed to decoding function. Value was: " + value);
            }
        }
    }
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
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
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY, a.decode(a.encode(TEST_ARRAY)));
        
        a = new AbstractDoubleArrayToBinaryCodecExtender(16, LOWER_BOUND, UPPER_BOUND);
        
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY, a.decode(a.encode(TEST_ARRAY)));
        
        a = new AbstractDoubleArrayToBinaryCodecExtender(24, LOWER_BOUND, UPPER_BOUND);
        
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY, a.decode(a.encode(TEST_ARRAY)));
        
        a = new AbstractDoubleArrayToBinaryCodecExtender(32, LOWER_BOUND, UPPER_BOUND);
        
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY, a.decode(a.encode(TEST_ARRAY)));
    }
}
