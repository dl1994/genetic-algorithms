// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.population.chromosomes;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;

/**
 * Class which contains tests for <code>DoubleArrayChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see DoubleArrayChromosome
 */
public class DoubleArrayChromosomeTest {
    
    /**
     * Size of first array to use in tests.
     */
    private static final int ARRAY_1_SIZE = 500;
    /**
     * Size of second and third arrays to use in tests.
     */
    private static final int ARRAY_2_3_SIZE = 1_000;
    /**
     * Range of <code>double</code> values to use in tests. This value will be used as positive and negative bound.
     */
    private static final double RANGE = 100.0;
    /**
     * First array of <code>double</code>s used in tests.
     */
    private static final double[] ARRAY_1 = new double[ARRAY_1_SIZE];
    /**
     * Second array of <code>double</code>s used in tests.
     */
    private static final double[] ARRAY_2 = new double[ARRAY_2_3_SIZE];
    /**
     * Third array of <code>double</code>s used in tests.
     */
    private static final double[] ARRAY_3 = new double[ARRAY_2_3_SIZE];
    /**
     * Number used in tests. Must be different from first element of {@link #ARRAY_2}.
     */
    private static final double NUMBER;
    
    static {
        for (int i = 0; i < ARRAY_1_SIZE; i++) {
            ARRAY_1[i] = TestUtilities.RAND.nextDouble() * RANGE * 2.0 - RANGE;
        }
        
        for (int i = 0; i < ARRAY_2_3_SIZE; i++) {
            ARRAY_2[i] = TestUtilities.RAND.nextDouble() * RANGE * 2.0 - RANGE;
            ARRAY_3[i] = TestUtilities.RAND.nextDouble() * RANGE * 2.0 - RANGE;
        }
        
        NUMBER = ARRAY_2[0] / 2.0;
    }
    
    /**
     * Tests the constructor, getter and setter.
     */
    @Test
    public void testConstructorGetterAndSetter() {
        
        DoubleArrayChromosome a = new DoubleArrayChromosome(ARRAY_1);
        
        double[] values = a.getValues();
        
        Assert.assertNotSame(ARRAY_1, values);
        Assert.assertNotSame(values, a.getValues());
        TestUtilities.assertArrayElementsEqual(ARRAY_1, values);
        
        a.setValues(ARRAY_2);
        values = a.getValues();
        
        Assert.assertNotSame(ARRAY_2, values);
        TestUtilities.assertArrayElementsEqual(ARRAY_2, values);
        
        ARRAY_2[0] = NUMBER;
        
        Assert.assertNotEquals(NUMBER, a.getValues()[0]);
        
        a.setValues(ARRAY_3);
        
        TestUtilities.assertArrayElementsEqual(ARRAY_3, a.getValues());
        DoubleArrayChromosome b = (DoubleArrayChromosome) a.clone();
        TestUtilities.assertArrayElementsEqual(a.getValues(), b.getValues());
    }
    
    /**
     * Tests the serialization.
     * 
     * @throws IOException thrown if any stream is unable to read or write.
     * @throws ClassNotFoundException thrown if object in the stream cannot be deserialized.
     */
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        
        DoubleArrayChromosome toSend = new DoubleArrayChromosome(ARRAY_1);
        Object recieved = TestUtilities.serializeDeserialize(toSend);
        Assert.assertTrue(recieved instanceof DoubleArrayChromosome);
        TestUtilities.assertArrayElementsEqual(ARRAY_1, ((DoubleArrayChromosome) recieved).getValues());
    }
}
