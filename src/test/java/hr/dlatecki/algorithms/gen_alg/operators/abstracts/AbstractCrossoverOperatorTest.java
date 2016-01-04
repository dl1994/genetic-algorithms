package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import java.util.SortedSet;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

/**
 * Class which contains tests for <code>AbstractCrossoverOperator</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractCrossoverOperator
 */
public class AbstractCrossoverOperatorTest {
    
    /**
     * <code>Random</code> object used in tests.
     */
    private static final Random RAND = new Random();
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractCrossoverOperator<IChromosome> a = new AbstractCrossoverOperator<IChromosome>(RAND) {
            
            @Override
            public IChromosome doCrossover(SortedSet<IChromosome> parents) {
                
                return null;
            }
        };
        
        Assert.assertEquals(RAND, a.getRand());
        Assert.assertNull(a.doCrossover(null));
    }
}
