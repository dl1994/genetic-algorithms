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
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IPopulationGenerator;

/**
 * An abstract class which for population generator. It is recommended that all implementations of population generator
 * extend this class instead of directly implementing <code>IPopulationGenerator</code> interface.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> the type of chromosome which will be used in the population generator.
 * @see IChromosome
 * @see IPopulationGenerator
 */
public abstract class AbstractPopulationGenerator<C extends IChromosome> implements IPopulationGenerator<C> {
    
    /**
     * Object used to generate random numbers.
     */
    protected final Random rand;
    
    /**
     * Constructs the population generator using the provided <code>Random</code> object.
     * 
     * @param rand object used to generate random numbers.
     */
    public AbstractPopulationGenerator(Random rand) {
        this.rand = rand;
    }
    
    /**
     * {@inheritDoc}
     * 
     * @throws IllegalArgumentException thrown if specified size if not greater than or equal to 1.
     */
    @Override
    public final Collection<C> generatePopulation(int size) {
        
        if (size < 0) {
            throw new IllegalArgumentException("Population size must be at least 1. Provided value was: " + size + ".");
        }
        
        return createPopulation(size);
    }
    
    /**
     * Creates the population of chromosomes of provided size. All chromosomes should have initial fitness value of 0.
     * Chromosomes are supposed to be generated randomly in order to cover as large search space as possible. The
     * <code>size</code> argument is guaranteed to be greater than or equal to 1. This is insured by
     * <code>AbstractPopulationGenerator</code>, which will call this method when {@link #generatePopulation(int)}
     * method is invoked externally.
     * 
     * @param size number of chromosomes to generate. Guaranteed to be greater than or equal to 0.
     * @return Collection of chromosomes which represents generated population.
     */
    protected abstract Collection<C> createPopulation(int size);
}
