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
        protected long encodeValue(double value) {
            
            // TODO Auto-generated method stub
            return 0;
        }
        
        @Override
        protected double decodeValue(long value) {
            
            // TODO Auto-generated method stub
            return 0;
        }
        
        /**
         * Fetches the number of bits per value from the superclass.
         * 
         * @return Bites per value from the superclass.
         */
        public int getBitsPerValue() {
            
            return bitsPerValue;
        }
        
        /**
         * Fetches the lower bound from the superclass.
         * 
         * @return Lower bound from the superclass.
         */
        public double getLowerBound() {
            
            return lowerBound;
        }
        
        /**
         * Fetches the upper bound from the superclass.
         * 
         * @return Lower upper from the superclass.
         */
        public double getUpperBound() {
            
            return upperBound;
        }
        
        /**
         * Fetches the step from the superclass.
         * 
         * @return Step from the superclass.
         */
        public double getStep() {
            
            return step;
        }
    }
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractDoubleArrayToBinaryCodecExtender a =
                new AbstractDoubleArrayToBinaryCodecExtender(BITS_PER_VALUE, LOWER_BOUND, UPPER_BOUND);
        Assert.assertEquals(BITS_PER_VALUE, a.getBitsPerValue());
        Assert.assertEquals(LOWER_BOUND, a.getLowerBound(), TestUtilities.PRECISION);
        Assert.assertEquals(UPPER_BOUND, a.getUpperBound(), TestUtilities.PRECISION);
        Assert.assertEquals(EXPECTED_STEP, a.getStep(), TestUtilities.PRECISION);
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
}
