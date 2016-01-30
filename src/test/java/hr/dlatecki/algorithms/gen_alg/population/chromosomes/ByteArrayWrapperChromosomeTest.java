// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.population.chromosomes;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.codecs.interfaces.IByteArrayCodec;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;

/**
 * Class which contains tests for <code>ByteArrayWrapperChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see ByteArrayWrapperChromosome
 */
public class ByteArrayWrapperChromosomeTest {
    
    /**
     * First string used in tests.
     */
    private static final String TEST_STRING_1 = "test1";
    /**
     * Second string used in tests.
     */
    private static final String TEST_STRING_2 = "test2";
    /**
     * Bytes of first test string.
     */
    private static final byte[] STRING_1_BYTES = new StringToByteCodec().encode(TEST_STRING_1);
    /**
     * Bytes of second test string.
     */
    private static final byte[] STRING_2_BYTES = new StringToByteCodec().encode(TEST_STRING_2);
    
    /**
     * Codec which is used to test <code>ByteArrayWrapperChromosome</code> with immutable items, in this case Java
     * Strings.
     * 
     * @author Domagoj Latečki
     * @version 1.0
     * @since 1.8
     */
    private static class StringToByteCodec implements IByteArrayCodec<String> {
        
        /**
         * Serial version UID.
         */
        private static final long serialVersionUID = -5713780938129999337L;
        
        @Override
        public byte[] encode(String item) {
            
            return item.getBytes();
        }
        
        @Override
        public String decode(byte[] bytes) {
            
            return new String(bytes);
        }
    }
    
    /**
     * Codec which is used to test <code>ByteArrayWrapperChromosome</code> with mutable items, in this case a container
     * class which is mutable.
     * 
     * @author Domagoj Latečki
     * @version 1.0
     * @since 1.8
     */
    private static class MutableStringContainerToByteCodec implements IByteArrayCodec<MutableStringContainer> {
        
        /**
         * Serial version UID.
         */
        private static final long serialVersionUID = -5713780938129999337L;
        
        @Override
        public byte[] encode(MutableStringContainer item) {
            
            return item.getString().getBytes();
        }
        
        @Override
        public MutableStringContainer decode(byte[] bytes) {
            
            return new MutableStringContainer(new String(bytes));
        }
    }
    
    /**
     * A mutable string container class.
     * 
     * @author Domagoj Latečki
     * @version 1.0
     * @since 1.8
     */
    private static class MutableStringContainer {
        
        /**
         * String stored in the object.
         */
        private String string;
        
        /**
         * Constructs a mutable string container with provided initial string.
         * 
         * @param string string which will be stored in the container.
         */
        public MutableStringContainer(String string) {
            this.string = string;
        }
        
        /**
         * Sets the string which is stored in the container.
         * 
         * @param string new string which will be stored in the container.
         */
        public void setString(String string) {
            
            this.string = string;
        }
        
        /**
         * Fetches the string stored in the container.
         * 
         * @return String stored in this object.
         */
        public String getString() {
            
            return string;
        }
        
        @Override
        public int hashCode() {
            
            final int prime = 31;
            int result = 1;
            
            result = prime * result + (string == null ? 0 : string.hashCode());
            
            return result;
        }
        
        @Override
        public boolean equals(Object obj) {
            
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            
            MutableStringContainer other = (MutableStringContainer) obj;
            
            if (string == null) {
                if (other.string != null) {
                    return false;
                }
            } else if (!string.equals(other.string)) {
                return false;
            }
            
            return true;
        }
    }
    
    /**
     * Tests the chromosome using a mutable item.
     */
    @Test
    public void testWithMutableItem() {
        
        MutableStringContainerToByteCodec codec = new MutableStringContainerToByteCodec();
        MutableStringContainer container = new MutableStringContainer(TEST_STRING_1);
        ByteArrayWrapperChromosome<MutableStringContainer> a = ByteArrayWrapperChromosome.fromMutable(container, codec);
        TestUtilities.assertArrayElementsEqual(STRING_1_BYTES, a.getBytes());
        Assert.assertNotSame(container, a.getItem());
        Assert.assertEquals(container, a.getItem());
        
        a.getBytes()[0] = -1;
        container.setString(TEST_STRING_2);
        
        TestUtilities.assertArrayElementsEqual(STRING_1_BYTES, a.getBytes());
        Assert.assertNotSame(container, a.getItem());
        Assert.assertEquals(new MutableStringContainer(TEST_STRING_1), a.getItem());
        
        container = new MutableStringContainer(TEST_STRING_2);
        a.setItem(container);
        
        TestUtilities.assertArrayElementsEqual(STRING_2_BYTES, a.getBytes());
        Assert.assertNotSame(container, a.getItem());
        Assert.assertEquals(container, a.getItem());
        
        @SuppressWarnings("unchecked")
        ByteArrayWrapperChromosome<MutableStringContainer> b =
                (ByteArrayWrapperChromosome<MutableStringContainer>) a.clone();
        TestUtilities.assertArrayElementsEqual(STRING_2_BYTES, b.getBytes());
        Assert.assertNotSame(container, b.getItem());
        Assert.assertEquals(container, b.getItem());
        Assert.assertNotSame(a.getItem(), b.getItem());
    }
    
    /**
     * Tests the chromosome using an immutable item.
     */
    @Test
    public void testWithImmutableItem() {
        
        StringToByteCodec codec = new StringToByteCodec();
        ByteArrayWrapperChromosome<String> a = ByteArrayWrapperChromosome.fromImmutable(TEST_STRING_1, codec);
        TestUtilities.assertArrayElementsEqual(STRING_1_BYTES, a.getBytes());
        Assert.assertSame(TEST_STRING_1, a.getItem());
        Assert.assertEquals(TEST_STRING_1, a.getItem());
        
        a.getBytes()[0] = -1;
        
        TestUtilities.assertArrayElementsEqual(STRING_1_BYTES, a.getBytes());
        Assert.assertSame(TEST_STRING_1, a.getItem());
        Assert.assertEquals(TEST_STRING_1, a.getItem());
        
        a.setItem(TEST_STRING_2);
        
        TestUtilities.assertArrayElementsEqual(STRING_2_BYTES, a.getBytes());
        Assert.assertSame(TEST_STRING_2, a.getItem());
        Assert.assertEquals(TEST_STRING_2, a.getItem());
        
        @SuppressWarnings("unchecked")
        ByteArrayWrapperChromosome<String> b = (ByteArrayWrapperChromosome<String>) a.clone();
        TestUtilities.assertArrayElementsEqual(STRING_2_BYTES, b.getBytes());
        Assert.assertSame(TEST_STRING_2, b.getItem());
        Assert.assertEquals(TEST_STRING_2, b.getItem());
        Assert.assertSame(a.getItem(), b.getItem());
    }
    
    /**
     * Tests the chromosome using a byte array.
     */
    @Test
    public void testWithByteArray() {
        
        StringToByteCodec codec = new StringToByteCodec();
        ByteArrayWrapperChromosome<String> a = ByteArrayWrapperChromosome.fromBytes(STRING_1_BYTES, codec);
        TestUtilities.assertArrayElementsEqual(STRING_1_BYTES, a.getBytes());
        Assert.assertNotSame(STRING_1_BYTES, a.getBytes());
        Assert.assertEquals(TEST_STRING_1, a.getItem());
        
        a.setBytes(STRING_2_BYTES);
        
        TestUtilities.assertArrayElementsEqual(STRING_2_BYTES, a.getBytes());
        Assert.assertNotSame(STRING_2_BYTES, a.getBytes());
        Assert.assertEquals(TEST_STRING_2, a.getItem());
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
        
        StringToByteCodec codec = new StringToByteCodec();
        ByteArrayWrapperChromosome<String> toSend = ByteArrayWrapperChromosome.fromBytes(STRING_1_BYTES, codec);
        Object recieved = TestUtilities.serializeDeserialize(toSend);
        Assert.assertTrue(recieved instanceof ByteArrayWrapperChromosome);
        TestUtilities.assertArrayElementsEqual(STRING_1_BYTES,
                ((ByteArrayWrapperChromosome<String>) recieved).getBytes());
        Assert.assertEquals(TEST_STRING_1, ((ByteArrayWrapperChromosome<String>) recieved).getItem());
    }
}
