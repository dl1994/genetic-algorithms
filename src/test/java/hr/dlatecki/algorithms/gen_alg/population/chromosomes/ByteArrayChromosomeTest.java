// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.population.chromosomes;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;

/**
 * Class which contains tests for <code>ByteArrayChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see ByteArrayChromosome
 */
public class ByteArrayChromosomeTest {
    
    /**
     * First array of <code>byte</code>s used in tests.
     */
    private static final byte[] ARRAY_1 = { 0, 1, 5 };
    /**
     * Second array of <code>byte</code>s used in tests.
     */
    private static final byte[] ARRAY_2 = { 7, 11, -5, -3, 1, 0 };
    /**
     * Third array of <code>byte</code>s used in tests. Must have same length as {@link #ARRAY_2}.
     */
    private static final byte[] ARRAY_3 = { 4, 0, 3, 2, 5, -100 };
    /**
     * Number used in tests. Must be different from first element of {@link #ARRAY_2}.
     */
    private static final byte NUMBER = 14;
    
    /**
     * Tests the constructor, getter and setter.
     */
    @Test
    public void testConstructorGetterAndSetter() {
        
        ByteArrayChromosome a = new ByteArrayChromosome(ARRAY_1);
        
        byte[] values = a.getBytes();
        
        Assert.assertNotSame(ARRAY_1, values);
        Assert.assertNotSame(values, a.getBytes());
        TestUtilities.assertArrayElementsEqual(ARRAY_1, values);
        
        a.setBytes(ARRAY_2);
        values = a.getBytes();
        
        Assert.assertNotSame(ARRAY_2, values);
        TestUtilities.assertArrayElementsEqual(ARRAY_2, values);
        
        ARRAY_2[0] = NUMBER;
        
        Assert.assertNotEquals(NUMBER, a.getBytes()[0]);
        
        a.setBytes(ARRAY_3);
        
        TestUtilities.assertArrayElementsEqual(ARRAY_3, a.getBytes());
        ByteArrayChromosome b = (ByteArrayChromosome) a.clone();
        TestUtilities.assertArrayElementsEqual(a.getBytes(), b.getBytes());
    }
    
    /**
     * Tests the serialization.
     * 
     * @throws IOException thrown if any stream is unable to read or write.
     * @throws ClassNotFoundException thrown if object in the stream cannot be deserialized.
     */
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        
        ByteArrayChromosome toSend = new ByteArrayChromosome(ARRAY_1);
        Object recieved = TestUtilities.serializeDeserialize(toSend);
        Assert.assertTrue(recieved instanceof ByteArrayChromosome);
        TestUtilities.assertArrayElementsEqual(ARRAY_1, ((ByteArrayChromosome) recieved).getBytes());
    }
}
