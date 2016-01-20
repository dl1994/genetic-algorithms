// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.codecs;

import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.codecs.abstracts.AbstractDoubleArrayToBinaryCodec;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;

/**
 * Class which contains tests for <code>DoubleArrayToNaturalBinaryCodec</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see DoubleArrayToNaturalBinaryCodec
 */
public class DoubleArrayToNaturalBinaryCodecTest {
    
    /**
     * Size of test arrays.
     */
    private static final int TEST_ARRAY_SIZE = 1_000;
    /**
     * Percentage of generated random values which will go outside of defined bounds.
     */
    private static final double OUT_OF_BOUNDS_PERC = 0.25;
    /**
     * Lower bound to use in the test. Must be less than {@link #UPPER_BOUND}.
     */
    private static final double LOWER_BOUND = -100.0;
    /**
     * Lower bound to use in the test. Must be greater than {@link #LOWER_BOUND}.
     */
    private static final double UPPER_BOUND = 100.0;
    /**
     * Input array used in the test.
     */
    private static final double[] INPUT_ARRAY = new double[TEST_ARRAY_SIZE];
    /**
     * Output array used in the test.
     */
    private static final double[] OUTPUT_ARRAY = new double[TEST_ARRAY_SIZE];
    
    static {
        double range = (UPPER_BOUND - LOWER_BOUND) * (1.0 + OUT_OF_BOUNDS_PERC);
        double modifiedBound = UPPER_BOUND * (1.0 + OUT_OF_BOUNDS_PERC);
        
        for (int i = 0; i < TEST_ARRAY_SIZE; i++) {
            INPUT_ARRAY[i] = (TestUtilities.RAND.nextDouble() * range - modifiedBound);
            
            if (INPUT_ARRAY[i] <= LOWER_BOUND) {
                OUTPUT_ARRAY[i] = LOWER_BOUND;
            } else if (INPUT_ARRAY[i] >= UPPER_BOUND) {
                OUTPUT_ARRAY[i] = UPPER_BOUND;
            } else {
                OUTPUT_ARRAY[i] = INPUT_ARRAY[i];
            }
        }
    }
    
    /**
     * Tests the functionality of the codec.
     */
    @Test
    public void testCodec() {
        
        for (int i = AbstractDoubleArrayToBinaryCodec.getMinNumOfBitsPerValue(), bound =
                AbstractDoubleArrayToBinaryCodec.getMaxNumOfBitsPerValue(); i <= bound; i++) {
            double expectedPrecision = (UPPER_BOUND - LOWER_BOUND) / Math.pow(2.0, i);
            
            DoubleArrayToNaturalBinaryCodec a = new DoubleArrayToNaturalBinaryCodec(i, LOWER_BOUND, UPPER_BOUND);
            TestUtilities.assertArrayElementsEqual(OUTPUT_ARRAY, a.decode(a.encode(INPUT_ARRAY)), expectedPrecision);
        }
    }
}
