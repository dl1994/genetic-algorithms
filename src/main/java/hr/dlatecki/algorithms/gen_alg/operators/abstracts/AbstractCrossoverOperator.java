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
import java.util.SortedSet;
import hr.dlatecki.algorithms.gen_alg.exceptions.IncompatibleParentsException;
import hr.dlatecki.algorithms.gen_alg.exceptions.TooFewParentsException;
import hr.dlatecki.algorithms.gen_alg.operators.interfaces.ICrossoverOperator;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

/**
 * An abstract class for crossover operator. It is recommended that all implementations of crossover operator extend
 * this class instead of directly implementing <code>ICrossoverOperator</code> interface.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> the type of chromosome which will be used in the crossover operator.
 * @see IChromosome
 * @see ICrossoverOperator
 */
public abstract class AbstractCrossoverOperator<C extends IChromosome> extends AbstractOperator
        implements ICrossoverOperator<C> {
        
    /**
     * Constructs a crossover operator with provided <code>Random</code> object.
     * 
     * @param rand object used to generate random numbers.
     */
    public AbstractCrossoverOperator(Random rand) {
        super(rand);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @throws IncompatibleParentsException thrown if provided parents are incompatible for crossover.
     * @throws TooFewParentsException thrown if less than 2 parents are provided for the crossover.
     */
    @Override
    public C doCrossover(SortedSet<C> parents) {
        
        if (parents.size() < 2) {
            throw new TooFewParentsException();
        }
        
        return createChild(parents);
    }
    
    /**
     * Creates a child chromosome using the given set of parent chromosomes. The parents are sorted by their fitness in
     * the descending order. The number of parents is guaranteed to be at least 2.
     * 
     * @param parents set of parents which is used to create a child chromosome.
     * @return Chromosome which was generated by the crossover.
     * @throws IncompatibleParentsException thrown if provided parents are incompatible for crossover.
     */
    protected abstract C createChild(SortedSet<C> parents);
}
