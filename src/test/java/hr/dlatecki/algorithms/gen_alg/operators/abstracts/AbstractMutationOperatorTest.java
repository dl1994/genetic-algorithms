package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;
import org.junit.Assert;
import org.junit.Test;
import java.util.Random;

/**
 * Class which contains tests for <code>AbstractMutationOperator</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractMutationOperator
 */
public class AbstractMutationOperatorTest {
    
    /**
     * Mutation chance used in tests.
     */
    private static final double MUTATION_CHANCE = 0.5;
    
    /**
     * Class which extends <code>AbstractMutationOperator</code> in order to gain access to protected parameters in
     * tests.
     * 
     * @author Domagoj Latečki
     * @version 1.0
     * @since 1.8
     */
    private static class AbstractMutationOperatorExtender extends AbstractMutationOperator<IChromosome> {
        
        /**
         * Constructor used to pass arguments to superclass constructor.
         * 
         * @param rand <code>Random</code> object to be passed to superclass constructor.
         * @param mutationIntensity value of mutation intensity to be passed to superclass constructor.
         */
        public AbstractMutationOperatorExtender(Random rand, double mutationIntensity) {
            super(rand, mutationIntensity);
        }
        
        @Override
        public IChromosome mutate(IChromosome chromosome) {
            
            return null;
        }
        
        /**
         * Fetches the mutation intensity stored in the superclass.
         * 
         * @return Mutation intensity stored in the superclass.
         */
        public double getMutationIntensity() {
            
            return mutationIntensity;
        }
    }
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractMutationOperatorExtender a = new AbstractMutationOperatorExtender(TestUtilities.RAND, MUTATION_CHANCE);
        Assert.assertEquals(TestUtilities.RAND, a.getRand());
        Assert.assertEquals(MUTATION_CHANCE, a.getMutationIntensity(), TestUtilities.PRECISION);
    }
    
    /**
     * Tests the setter for mutation chance.
     */
    @Test
    public void testSetter() {
        
        AbstractMutationOperatorExtender a = new AbstractMutationOperatorExtender(TestUtilities.RAND, MUTATION_CHANCE);
        
        a.setMutationIntensity(0.0);
        a.setMutationIntensity(1.0);
        a.setMutationIntensity(MUTATION_CHANCE / 2.0);
        
        Assert.assertEquals(MUTATION_CHANCE / 2.0, a.getMutationIntensity(), TestUtilities.PRECISION);
    }
    
    /**
     * Tests if setter for mutation chance throw exception if too small chance is specified.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetterThrowsExceptionForTooSmallNumber() {
        
        AbstractMutationOperatorExtender a = new AbstractMutationOperatorExtender(TestUtilities.RAND, MUTATION_CHANCE);
        
        a.setMutationIntensity(-0.1);
    }
    
    /**
     * Tests if setter for mutation chance throw exception if too large chance is specified.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetterThrowsExceptionForTooLargeNumber() {
        
        AbstractMutationOperatorExtender a = new AbstractMutationOperatorExtender(TestUtilities.RAND, MUTATION_CHANCE);
        
        a.setMutationIntensity(1.1);
    }
}
