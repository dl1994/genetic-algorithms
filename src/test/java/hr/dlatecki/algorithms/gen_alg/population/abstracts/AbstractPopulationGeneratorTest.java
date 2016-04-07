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
package hr.dlatecki.algorithms.gen_alg.population.abstracts;

import java.util.Collection;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;

/**
 * Class which contains tests for <code>AbstractPopulationGenerator</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractPopulationGenerator
 */
public class AbstractPopulationGeneratorTest {
    
    /**
     * Class which extends <code>AbstractPopulationGenerator</code> in order to gain access to protected parameters in
     * tests.
     * 
     * @author Domagoj Latečki
     * @version 1.0
     * @since 1.8
     */
    private static class AbstractPopulationGeneratorExtender extends AbstractPopulationGenerator<IChromosome> {
        
        /**
         * Constructor used to pass arguments to superclass constructor.
         * 
         * @param rand <code>Random</code> object to be passed to superclass constructor.
         */
        public AbstractPopulationGeneratorExtender(Random rand) {
            super(rand);
        }
        
        /**
         * Fetches the <code>Random</code> object stored in the superclass.
         * 
         * @return <code>Random</code> object stored in the superclass.
         */
        public Random getRand() {
            
            return rand;
        }
        
        @Override
        protected Collection<IChromosome> createPopulation(int size) {
            
            // TODO Auto-generated method stub
            return null;
        }
    }
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractPopulationGeneratorExtender a = new AbstractPopulationGeneratorExtender(TestUtilities.RAND);
        Assert.assertEquals(TestUtilities.RAND, a.getRand());
        
        a.generatePopulation(0);
    }
    
    /**
     * Tests if <code>generatePopulation(int)</code> method throws exception if negative population size is provided.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testGeneratePopulationThrowsException() {
        
        AbstractPopulationGeneratorExtender a = new AbstractPopulationGeneratorExtender(TestUtilities.RAND);
        
        a.generatePopulation(-1);
    }
}
