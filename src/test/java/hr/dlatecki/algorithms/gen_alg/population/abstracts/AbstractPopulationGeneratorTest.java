// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
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
