package hr.dlatecki.algorithms.gen_alg.population.abstracts;

import java.io.IOException;
import java.io.Serializable;
import java.util.SortedSet;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.exceptions.FitnessNotEvaluatedException;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;

/**
 * Class which contains tests for <code>AbstractChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractChromosome
 */
public class AbstractChromosomeTest {
    
    /**
     * Amount of chromosomes used in comparison test.
     */
    private static final int NUM_OF_CHROMOSOMES = 10;
    /**
     * Number used in tests.
     */
    private static final int NUMBER = 10;
    /**
     * Fitness value used in tests. Must be positive for tests to run correctly.
     */
    private static final double FITNESS = 50.0;
    
    /**
     * Class which represents a mutable container class. This class should be deep copied when cloning.
     * 
     * @author Domagoj Latečki
     * @version 1.0
     * @since 1.8
     */
    private static class MutableContainer implements Serializable {
        
        /**
         * Serial version UID.
         */
        private static final long serialVersionUID = 585747696495607118L;
        /**
         * Mutable number.
         */
        private int number;
        
        /**
         * Constructs the <code>MutableContainer</code> with provided number.
         * 
         * @param number number which will be stored in this object.
         */
        public MutableContainer(int number) {
            this.number = number;
        }
        
        /**
         * Sets the number.
         * 
         * @param number new number.
         */
        public void setNumber(int number) {
            
            this.number = number;
        }
        
        /**
         * Fetches the stored number.
         * 
         * @return Stored number.
         */
        public int getNumber() {
            
            return number;
        }
    }
    
    /**
     * Class which extends <code>AbstractChromosome</code> in order fully test all capabilities of the superclass.
     * 
     * @author Domagoj Latečki
     * @version 1.0
     * @since 1.8
     */
    private static class AbstractChromosomeExtender extends AbstractChromosome {
        
        /**
         * Serial version UID.
         */
        private static final long serialVersionUID = 6181514580043050935L;
        /**
         * Object which is a mutable container.
         */
        private MutableContainer contaianer;
        
        /**
         * Constructs the <code>AbstractChromosomeExtender</code> which will contain mutable container with provided
         * number.
         * 
         * @param number number which will be stored in mutable container.
         */
        public AbstractChromosomeExtender(int number) {
            contaianer = new MutableContainer(number);
        }
        
        @Override
        protected void deepCopyTo(AbstractChromosome target) {
            
            ((AbstractChromosomeExtender) target).contaianer = new MutableContainer(contaianer.getNumber());
        }
    }
    
    /**
     * Test the <code>clone()</code> method.
     */
    @Test
    public void testCloning() {
        
        AbstractChromosomeExtender original = new AbstractChromosomeExtender(NUMBER);
        AbstractChromosomeExtender clone = (AbstractChromosomeExtender) original.clone();
        Assert.assertEquals(NUMBER, original.contaianer.getNumber());
        Assert.assertEquals(NUMBER, clone.contaianer.getNumber());
        Assert.assertNotSame(original.contaianer, clone.contaianer);
        
        original.contaianer.setNumber(NUMBER * 2);
        
        Assert.assertEquals(NUMBER * 2, original.contaianer.getNumber());
        Assert.assertEquals(NUMBER, clone.contaianer.getNumber());
    }
    
    /**
     * Checks if cloning exception is caught and re-thrown as <code>RuntimeException</code>.
     */
    @Test(expected = RuntimeException.class)
    public void testCloningThrowsException() {
        
        AbstractChromosome a = new AbstractChromosome() {
            
            /**
             * Serial version UID.
             */
            private static final long serialVersionUID = 1L;
            
            @Override
            protected void deepCopyTo(AbstractChromosome target) throws CloneNotSupportedException {
                
                throw new CloneNotSupportedException();
            }
        };
        
        a.clone();
    }
    
    /**
     * Tests the getter and setter for fitness.
     */
    @Test
    public void testFitnessGetterAndSetter() {
        
        AbstractChromosomeExtender a = new AbstractChromosomeExtender(NUMBER);
        
        a.setFitness(FITNESS);
        
        Assert.assertEquals(FITNESS, a.getFitness(), TestUtilities.PRECISION);
    }
    
    /**
     * Tests if getter for fitness throws exception if fitness is not evaluated.
     */
    @Test(expected = FitnessNotEvaluatedException.class)
    public void testFitnessGetterThrowsException() {
        
        new AbstractChromosomeExtender(NUMBER).getFitness();
    }
    
    /**
     * Tests the comparison method. Chromosomes must be in descending order by fitness.
     */
    @Test
    public void testComparison() {
        
        SortedSet<AbstractChromosomeExtender> set = new TreeSet<>();
        AbstractChromosomeExtender chromosomes[] = new AbstractChromosomeExtender[NUM_OF_CHROMOSOMES];
        
        for (int i = 0; i < NUM_OF_CHROMOSOMES; i++) {
            chromosomes[i] = new AbstractChromosomeExtender(NUMBER);
            chromosomes[i].setFitness(FITNESS * i);
            set.add(chromosomes[i]);
        }
        
        int index = NUM_OF_CHROMOSOMES - 1;
        for (AbstractChromosomeExtender chromosome : set) {
            Assert.assertSame(chromosomes[index], chromosome);
            
            index--;
        }
        
        AbstractChromosomeExtender large = new AbstractChromosomeExtender(NUMBER);
        AbstractChromosomeExtender small = new AbstractChromosomeExtender(NUMBER);
        AbstractChromosomeExtender sameLage = new AbstractChromosomeExtender(NUMBER);
        
        large.setFitness(FITNESS * 2.0);
        small.setFitness(FITNESS / 2.0);
        sameLage.setFitness(FITNESS * 2.0);
        
        Assert.assertTrue(large.compareTo(small) < 0);
        Assert.assertTrue(small.compareTo(large) > 0);
        Assert.assertTrue(large.compareTo(null) > 0);
        Assert.assertTrue(large.compareTo(large) == 0);
        Assert.assertTrue(large.compareTo(sameLage) != 0);
    }
    
    /**
     * Tests the <code>equals(Object)</code> and <code>hashCode()</code> methods.
     */
    @Test
    public void testEqualsAndHashcode() {
        
        AbstractChromosomeExtender a = new AbstractChromosomeExtender(NUMBER);
        AbstractChromosomeExtender b = new AbstractChromosomeExtender(NUMBER);
        
        Assert.assertFalse(a.equals(b));
        Assert.assertTrue(a.equals(a));
        Assert.assertEquals(a.hashCode(), a.hashCode());
    }
    
    /**
     * Tests the serialization.
     * 
     * @throws IOException thrown if any stream is unable to read or write.
     * @throws ClassNotFoundException thrown if object in the stream cannot be deserialized.
     */
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        
        AbstractChromosome toSend = new AbstractChromosomeExtender(NUMBER);
        
        toSend.setFitness(FITNESS);
        
        Object recieved = TestUtilities.serializeDeserialize(toSend);
        Assert.assertTrue(recieved instanceof AbstractChromosome);
        Assert.assertTrue(recieved instanceof AbstractChromosomeExtender);
        Assert.assertEquals(FITNESS, ((AbstractChromosomeExtender) recieved).getFitness(), TestUtilities.PRECISION);
    }
}
