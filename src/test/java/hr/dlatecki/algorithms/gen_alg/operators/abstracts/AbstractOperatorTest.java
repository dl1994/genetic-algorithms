/* 
 * The MIT License (MIT)
 * 
 * Copyright © 2016 Domagoj Latečki
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
