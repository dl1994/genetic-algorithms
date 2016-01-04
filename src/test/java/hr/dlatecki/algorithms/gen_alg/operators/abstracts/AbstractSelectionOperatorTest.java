package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import java.util.SortedSet;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

/**
 * Class which contains tests for <code>AbstractSelectionOperator</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractSelectionOperator
 */
public class AbstractSelectionOperatorTest {
    
    /**
     * Size of selection used in tests.
     */
    private static final int SIZE = 10;
    /**
     * <code>Random</code> object used in tests.
     */
    private static final Random RAND = new Random();
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractSelectionOperator<IChromosome> a = new AbstractSelectionOperator<IChromosome>(RAND, SIZE) {
            
            @Override
            protected SortedSet<IChromosome> performSelection(SortedSet<IChromosome> pool, int size) {
                
                return null;
            }
        };
        
        Assert.assertEquals(RAND, a.getRand());
    }
    
    /**
     * Tests the selection methods.
     */
    @Test
    public void testSelection() {
        
        AbstractSelectionOperator<IChromosome> a = new AbstractSelectionOperator<IChromosome>(RAND, SIZE) {
            
            @Override
            protected SortedSet<IChromosome> performSelection(SortedSet<IChromosome> pool, int size) {
                
                Assert.assertEquals(SIZE, size);
                
                return null;
            }
        };
        
        a.select(null);
        
        int expectedSize = SIZE * 2;
        
        a = new AbstractSelectionOperator<IChromosome>(RAND, SIZE) {
            
            @Override
            protected SortedSet<IChromosome> performSelection(SortedSet<IChromosome> pool, int size) {
                
                Assert.assertEquals(expectedSize, size);
                
                return null;
            }
        };
        
        a.select(null, expectedSize);
    }
    
    /**
     * Tests if <code>setSelectionSize</code> method throws exception if incorrect size is given.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckSelectionSizeThrowsException() {
        
        AbstractSelectionOperator<IChromosome> a = new AbstractSelectionOperator<IChromosome>(RAND, SIZE) {
            
            @Override
            protected SortedSet<IChromosome> performSelection(SortedSet<IChromosome> pool, int size) {
                
                return null;
            }
        };
        
        a.setSelectionSize(0);
    }
}
