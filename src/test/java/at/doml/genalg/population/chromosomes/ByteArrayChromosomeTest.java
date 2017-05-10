package at.doml.genalg.population.chromosomes;

import at.doml.genalg.testutils.TestUtilities;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

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
     * Size of first array to use in tests.
     */
    private static final int ARRAY_1_SIZE = 500;
    /**
     * Size of second and third arrays to use in tests.
     */
    private static final int ARRAY_2_3_SIZE = 1_000;
    /**
     * First array of <code>byte</code>s used in tests.
     */
    private static final byte[] ARRAY_1 = new byte[ARRAY_1_SIZE];
    /**
     * Second array of <code>byte</code>s used in tests.
     */
    private static final byte[] ARRAY_2 = new byte[ARRAY_2_3_SIZE];
    /**
     * Third array of <code>byte</code>s used in tests.
     */
    private static final byte[] ARRAY_3 = new byte[ARRAY_2_3_SIZE];
    /**
     * Number used in tests. Must be different from first element of {@link #ARRAY_2}.
     */
    private static final byte NUMBER;
    
    static {
        TestUtilities.RAND.nextBytes(ARRAY_1);
        TestUtilities.RAND.nextBytes(ARRAY_2);
        TestUtilities.RAND.nextBytes(ARRAY_3);
        
        NUMBER = (byte) (ARRAY_2[0] >> 1);
    }
    
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
