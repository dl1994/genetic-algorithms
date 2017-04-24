package hr.dlatecki.algorithms.gen_alg.population.chromosomes;

import hr.dlatecki.algorithms.gen_alg.codecs.interfaces.IDoubleArrayCodec;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;
import org.junit.Assert;
import org.junit.Test;
import java.io.IOException;

/**
 * Class which contains tests for <code>DoubleArrayWrapperChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see DoubleArrayWrapperChromosome
 */
public class DoubleArrayWrapperChromosomeTest {
    
    /**
     * First array of test integers.
     */
    private static final int[] TEST_ARRAY_1_INTS = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
    /**
     * Second array of test integers.
     */
    private static final int[] TEST_ARRAY_2_INTS = { 9, 8, 7, 6, 5, 4, 3, 2, 1 };
    /**
     * First array of test <code>double</code>s.
     */
    private static final double[] TEST_ARRAY_1_DOUBLES = new IntToDoubleCodec().encode(TEST_ARRAY_1_INTS);
    /**
     * Second array of test <code>double</code>s.
     */
    private static final double[] TEST_ARRAY_2_DOUBLES = new IntToDoubleCodec().encode(TEST_ARRAY_2_INTS);
    
    /**
     * Codec which is used to test <code>DoubleArrayWrapperChromosome</code> with mutable and immutable items, in this
     * case an array of integers.
     * 
     * @author Domagoj Latečki
     * @version 1.0
     * @since 1.8
     */
    private static class IntToDoubleCodec implements IDoubleArrayCodec<int[]> {
        
        /**
         * Serial version UID.
         */
        private static final long serialVersionUID = -6624307945336344687L;
        
        @Override
        public double[] encode(int[] item) {
            
            double[] output = new double[item.length];
            
            for (int i = 0; i < item.length; i++) {
                output[i] = item[i];
            }
            
            return output;
        }
        
        @Override
        public int[] decode(double[] values) {
            
            int[] output = new int[values.length];
            
            for (int i = 0; i < values.length; i++) {
                output[i] = (int) values[i];
            }
            
            return output;
        }
    }
    
    /**
     * Tests the chromosome using an immutable item.
     */
    @Test
    public void testWithMutableItem() {
        
        IntToDoubleCodec codec = new IntToDoubleCodec();
        DoubleArrayWrapperChromosome<int[]> a = DoubleArrayWrapperChromosome.fromMutable(TEST_ARRAY_1_INTS, codec);
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_DOUBLES, a.getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_INTS, a.getItem());
        Assert.assertNotSame(TEST_ARRAY_1_INTS, a.getItem());
        
        a.getValues()[0] = -1;
        
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_DOUBLES, a.getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_INTS, a.getItem());
        Assert.assertNotSame(TEST_ARRAY_1_INTS, a.getItem());
        
        a.setItem(TEST_ARRAY_2_INTS);
        
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_2_DOUBLES, a.getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_2_INTS, a.getItem());
        Assert.assertNotSame(TEST_ARRAY_2_INTS, a.getItem());
        
        @SuppressWarnings("unchecked")
        DoubleArrayWrapperChromosome<int[]> b = (DoubleArrayWrapperChromosome<int[]>) a.clone();
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_2_DOUBLES, b.getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_2_INTS, b.getItem());
        Assert.assertNotSame(TEST_ARRAY_2_INTS, b.getItem());
        Assert.assertNotSame(a.getItem(), b.getItem());
    }
    
    /**
     * Tests the chromosome using an immutable item.
     */
    @Test
    public void testWithImmutableItem() {
        
        IntToDoubleCodec codec = new IntToDoubleCodec();
        DoubleArrayWrapperChromosome<int[]> a = DoubleArrayWrapperChromosome.fromImmutable(TEST_ARRAY_1_INTS, codec);
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_DOUBLES, a.getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_INTS, a.getItem());
        Assert.assertSame(TEST_ARRAY_1_INTS, a.getItem());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_DOUBLES, a.getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_INTS, a.getItem());
        Assert.assertSame(TEST_ARRAY_1_INTS, a.getItem());
        
        a.setItem(TEST_ARRAY_2_INTS);
        
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_2_DOUBLES, a.getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_2_INTS, a.getItem());
        Assert.assertSame(TEST_ARRAY_2_INTS, a.getItem());
        
        @SuppressWarnings("unchecked")
        DoubleArrayWrapperChromosome<int[]> b = (DoubleArrayWrapperChromosome<int[]>) a.clone();
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_2_DOUBLES, b.getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_2_INTS, b.getItem());
        Assert.assertSame(TEST_ARRAY_2_INTS, b.getItem());
        Assert.assertSame(a.getItem(), b.getItem());
    }
    
    /**
     * Tests the chromosome using an array of values.
     */
    @Test
    public void testWithValueArray() {
        
        IntToDoubleCodec codec = new IntToDoubleCodec();
        DoubleArrayWrapperChromosome<int[]> a = DoubleArrayWrapperChromosome.fromValues(TEST_ARRAY_1_DOUBLES, codec);
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_DOUBLES, a.getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_INTS, a.getItem());
        Assert.assertNotSame(TEST_ARRAY_1_DOUBLES, a.getValues());
        
        a.setValues(TEST_ARRAY_2_DOUBLES);
        
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_2_DOUBLES, a.getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_2_INTS, a.getItem());
        Assert.assertNotSame(TEST_ARRAY_2_DOUBLES, a.getValues());
    }
    
    /**
     * Tests the serialization.
     * 
     * @throws IOException thrown if any stream is unable to read or write.
     * @throws ClassNotFoundException thrown if object in the stream cannot be deserialized.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testSerialization() throws IOException, ClassNotFoundException {
        
        IntToDoubleCodec codec = new IntToDoubleCodec();
        DoubleArrayWrapperChromosome<int[]> toSend =
                DoubleArrayWrapperChromosome.fromValues(TEST_ARRAY_1_DOUBLES, codec);
        Object recieved = TestUtilities.serializeDeserialize(toSend);
        Assert.assertTrue(recieved instanceof DoubleArrayWrapperChromosome);
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_DOUBLES,
                ((DoubleArrayWrapperChromosome<int[]>) recieved).getValues());
        TestUtilities.assertArrayElementsEqual(TEST_ARRAY_1_INTS,
                ((DoubleArrayWrapperChromosome<int[]>) recieved).getItem());
    }
}
