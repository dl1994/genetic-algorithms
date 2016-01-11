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
     * First array of <code>double</code>s used in tests.
     */
    private static final double[] ARRAY_1 = { 1.0, 2.0, 3.0 };
    /**
     * Second array of <code>double</code>s used in tests.
     */
    private static final double[] ARRAY_2 = { 5.0, -2.0, 2.0, 1.0, 11.9, 7.7 };
    /**
     * Second array of <code>double</code>s used in tests. Must have same length as {@link #ARRAY_2}.
     */
    private static final double[] ARRAY_3 = { -5.0, -1.0, 5.0, 3.0, 1341.9, -9.7 };
    /**
     * Number used in tests. Must be different from first element of {@link #ARRAY_2}.
     */
    private static final double NUMBER = 0.0;
    
    /**
     * Tests the constructor, getter and setter.
     */
    @Test
    public void testConstructorGetterAndSetter() {
        
        DoubleArrayChromosome a = new DoubleArrayChromosome(ARRAY_1);
        
        double[] values = a.getValues();
        
        Assert.assertNotSame(ARRAY_1, values);
        Assert.assertNotSame(values, a.getValues());
        
        assertArrayElementsEqual(ARRAY_1, values);
        a.setValues(ARRAY_2);
        values = a.getValues();
        
        Assert.assertNotSame(ARRAY_2, values);
        
        assertArrayElementsEqual(ARRAY_2, values);
        
        ARRAY_2[0] = NUMBER;
        
        Assert.assertNotEquals(NUMBER, a.getValues()[0]);
        
        a.setValues(ARRAY_3);
        assertArrayElementsEqual(ARRAY_3, a.getValues());
        
        DoubleArrayChromosome b = (DoubleArrayChromosome) a.clone();
        
        assertArrayElementsEqual(a.getValues(), b.getValues());
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
        
        assertArrayElementsEqual(ARRAY_1, ((DoubleArrayChromosome) recieved).getValues());
    }
    
    /**
     * Tests if two arrays have equal elements.
     * 
     * @param expected array of expected values.
     * @param actual actual values.
     */
    private void assertArrayElementsEqual(double[] expected, double[] actual) {
        
        Assert.assertEquals(expected.length, actual.length);
        
        for (int i = 0; i < expected.length; i++) {
            Assert.assertEquals(expected[i], actual[i], TestUtilities.PRECISION);
        }
    }
}
