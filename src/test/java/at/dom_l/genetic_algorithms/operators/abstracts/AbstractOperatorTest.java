package at.dom_l.genetic_algorithms.operators.abstracts;

import at.dom_l.genetic_algorithms.test_utils.TestUtilities;
import org.junit.Assert;
import org.junit.Test;
import java.util.Random;

/**
 * Class which contains tests for <code>AbstractOperator</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractOperator
 */
public class AbstractOperatorTest {
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractOperator a = new AbstractOperator(TestUtilities.RAND) {};
        
        Assert.assertEquals(TestUtilities.RAND, a.getRand());
    }
    
    /**
     * Tests the setter and getter for <code>Random</code> object.
     */
    @Test
    public void testSetterAndGetter() {
        
        AbstractOperator a = new AbstractOperator(TestUtilities.RAND) {};
        
        Random newRand = new Random();
        
        a.setRandom(newRand);
        
        Assert.assertEquals(newRand, a.getRand());
    }
}
