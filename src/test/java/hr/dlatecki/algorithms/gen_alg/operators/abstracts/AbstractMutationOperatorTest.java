package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

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
     * Precision to use when comparing if double numbers are equal.
     */
    private static final double PRECISION = 10E-5;
    /**
     * <code>Random</code> object used in tests.
     */
    private static final Random RAND = new Random();
    
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
         * @param mutationChance value of mutation chance to be passed to superclass constructor.
         */
        public AbstractMutationOperatorExtender(Random rand, double mutationChance) {
            super(rand, mutationChance);
        }
        
        @Override
        public IChromosome mutate(IChromosome chromosome) {
            
            return null;
        }
        
        /**
         * Fetches the mutation chance stored in superclass.
         * 
         * @return Mutation chance stored in superclass.
         */
        public double getMutationChance() {
            
            return mutationChance;
        }
    }
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractMutationOperatorExtender a = new AbstractMutationOperatorExtender(RAND, MUTATION_CHANCE);
        Assert.assertEquals(RAND, a.getRand());
        Assert.assertEquals(MUTATION_CHANCE, a.getMutationChance(), PRECISION);
        
        a.mutate(null);
    }
    
    /**
     * Tests the setter for mutation chance.
     */
    @Test
    public void testSetter() {
        
        AbstractMutationOperatorExtender a = new AbstractMutationOperatorExtender(RAND, MUTATION_CHANCE);
        
        a.setMutationChance(0.0);
        a.setMutationChance(1.0);
        a.setMutationChance(MUTATION_CHANCE / 2.0);
        
        Assert.assertEquals(MUTATION_CHANCE / 2.0, a.getMutationChance(), PRECISION);
    }
    
    /**
     * Tests if setter for mutation chance throw exception if too small chance is specified.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetterThrowsExceptionForTooSmallNumber() {
        
        AbstractMutationOperatorExtender a = new AbstractMutationOperatorExtender(RAND, MUTATION_CHANCE);
        
        a.setMutationChance(-0.1);
    }
    
    /**
     * Tests if setter for mutation chance throw exception if too large chance is specified.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testSetterThrowsExceptionForTooLargeNumber() {
        
        AbstractMutationOperatorExtender a = new AbstractMutationOperatorExtender(RAND, MUTATION_CHANCE);
        
        a.setMutationChance(1.1);
    }
}
