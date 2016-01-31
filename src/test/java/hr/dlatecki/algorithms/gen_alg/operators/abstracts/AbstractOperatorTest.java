// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;

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
