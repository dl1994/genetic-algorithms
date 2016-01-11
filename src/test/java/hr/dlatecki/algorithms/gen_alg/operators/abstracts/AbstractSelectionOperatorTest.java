// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import java.util.SortedSet;
import org.junit.Assert;
import org.junit.Test;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;
import hr.dlatecki.algorithms.gen_alg.test_utils.TestUtilities;

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
     * Class which extends <code>AbstractSelectionOperator</code>.
     * 
     * @author Domagoj Latečki
     * @version 1.0
     * @since 1.8
     */
    private static class AbstractSelectionOperatorExtender extends AbstractSelectionOperator<IChromosome> {
        
        /**
         * Constructor used to pass arguments to superclass constructor.
         * 
         * @param rand <code>Random</code> object to be passed to superclass constructor.
         * @param selectionSize selection size to be passed to superclass constructor.
         */
        public AbstractSelectionOperatorExtender(Random rand, int selectionSize) {
            super(rand, selectionSize);
        }
        
        @Override
        protected SortedSet<IChromosome> performSelection(SortedSet<IChromosome> pool, int size) {
            
            return null;
        }
    }
    
    /**
     * Tests the constructor.
     */
    @Test
    public void testConstructor() {
        
        AbstractSelectionOperator<IChromosome> a = new AbstractSelectionOperatorExtender(TestUtilities.RAND, SIZE);
        Assert.assertEquals(TestUtilities.RAND, a.getRand());
    }
    
    /**
     * Tests the selection methods.
     */
    @Test
    public void testSelection() {
        
        AbstractSelectionOperator<IChromosome> a =
                new AbstractSelectionOperator<IChromosome>(TestUtilities.RAND, SIZE) {
                    
                    @Override
                    protected SortedSet<IChromosome> performSelection(SortedSet<IChromosome> pool, int size) {
                        
                        Assert.assertEquals(SIZE, size);
                        
                        return null;
                    }
                };
                
        a.select(null);
        
        int expectedSize = SIZE * 2;
        
        a = new AbstractSelectionOperator<IChromosome>(TestUtilities.RAND, SIZE) {
            
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
        
        AbstractSelectionOperator<IChromosome> a = new AbstractSelectionOperatorExtender(TestUtilities.RAND, SIZE);
        
        a.setSelectionSize(0);
    }
}
