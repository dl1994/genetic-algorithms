package at.doml.genalg.operators.abstracts;

import at.doml.genalg.population.interfaces.IChromosome;
import at.doml.genalg.testutils.TestUtilities;
import org.junit.Assert;
import org.junit.Test;
import java.util.SortedSet;

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
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractCrossoverOperator<IChromosome> a = new AbstractCrossoverOperator<IChromosome>(TestUtilities.RAND) {
            
            @Override
            public IChromosome createChild(SortedSet<IChromosome> parents) {
                
                return null;
            }
        };
        
        Assert.assertEquals(TestUtilities.RAND, a.getRand());
    }
}
