package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import org.junit.Assert;
import org.junit.Test;

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
     * <code>Random</code> object used in tests.
     */
    private static final Random RAND = new Random();
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractOperator a = new AbstractOperator(RAND) {};
        
        Assert.assertEquals(RAND, a.getRand());
    }
    
    /**
     * Tests the setter and getter for <code>Random</code> object.
     */
    @Test
    public void testSetterAndGetter() {
        
        AbstractOperator a = new AbstractOperator(RAND) {};
        
        Random newRand = new Random();
        
        a.setRandom(newRand);
        
        Assert.assertEquals(newRand, a.getRand());
    }
}
