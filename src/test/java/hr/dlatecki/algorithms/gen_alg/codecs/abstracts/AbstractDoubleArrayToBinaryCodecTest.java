// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.codecs.abstracts;

import java.io.IOException;
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
     * Size of test array.
     */
    private static final int TEST_ARRAY_SIZE = 1_000;
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
     * Additional value used in encoding/decoding methods.
     */
    private static final double MIDDLE_VALUE = 0.0;
    /**
     * Array used to test encoding/decoding.
     */
    private static final double[] TEST_ARRAY = new double[TEST_ARRAY_SIZE];
    
    static {
        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            int num = TestUtilities.RAND.nextInt(3);
            
            if (num == 0) {
                TEST_ARRAY[i] = LOWER_BOUND;
            } else if (num == 1) {
                TEST_ARRAY[i] = MIDDLE_VALUE;
            } else {
                TEST_ARRAY[i] = UPPER_BOUND;
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
         * Serial version UID.
         */
        private static final long serialVersionUID = -5127874756776732529L;
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
                long output = 0L;
                boolean isOne = true;
                
                for (int i = 0; i < bitsPerValue; i++) {
                    output = output << 1L | (isOne ? 1L : 0L);
                    isOne = !isOne;
                }
                
                return output;
            }
        }
        
        @Override
        protected double decodeValue(long value, double lowerBound, double upperBound, double step, int bitsPerValue) {
            
            if (value == encodedLowerBound) {
                return LOWER_BOUND;
            } else if (value == encodedUpperBound) {
                return UPPER_BOUND;
            } else {
                long expected = 0L;
                boolean isOne = true;
                
                for (int i = 0; i < bitsPerValue; i++) {
                    expected = expected << 1L | (isOne ? 1L : 0L);
                    isOne = !isOne;
                }
                
                if (expected == value) {
                    return 0.0;
                } else {
                    throw new RuntimeException("Unexpected value was encountered while decoding with " + bitsPerValue
                            + " bits per value.");
                }
            }
        }
    }
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        new AbstractDoubleArrayToBinaryCodecExtender(BITS_PER_VALUE, LOWER_BOUND, UPPER_BOUND) {
            
            /**
             * Serial version UID.
             */
            private static final long serialVersionUID = 1L;
            
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
     * Tests the encoding and decoding methods.
     */
    @Test
    public void testEncodingAndDecoding() {
        
        for (int i = AbstractDoubleArrayToBinaryCodec.getMinNumOfBitsPerValue(), bound =
                AbstractDoubleArrayToBinaryCodec.getMaxNumOfBitsPerValue(); i <= bound; i++) {
            AbstractDoubleArrayToBinaryCodecExtender a =
                    new AbstractDoubleArrayToBinaryCodecExtender(i, LOWER_BOUND, UPPER_BOUND);
            TestUtilities.assertArrayElementsEqual(TEST_ARRAY, a.decode(a.encode(TEST_ARRAY)));
        }
    }
    
    /**
     * Tests the serialization.
     * 
     * @throws IOException thrown if any stream is unable to read or write.
     * @throws ClassNotFoundException thrown if object in the stream cannot be deserialized.
     */
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        
        AbstractDoubleArrayToBinaryCodec toSend =
                new AbstractDoubleArrayToBinaryCodecExtender(BITS_PER_VALUE, LOWER_BOUND, UPPER_BOUND);
        Object recieved = TestUtilities.serializeDeserialize(toSend);
        Assert.assertTrue(recieved instanceof AbstractDoubleArrayToBinaryCodec);
    }
}
