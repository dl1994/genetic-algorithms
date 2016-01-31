// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
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
    
    @Override
    public final Collection<C> generatePopulation(int size) {
        
        if (size < 0) {
            throw new IllegalArgumentException(
                    "Cannot generate population of negative size. Provided value was: " + size + ".");
        }
        
        return createPopulation(size);
    }
    
    /**
     * Creates the population of chromosomes of provided size. All chromosomes should have initial fitness value of 0.
     * Chromosomes are supposed to be generated randomly in order to cover as large search space as possible. The
     * <code>size</code> argument is guaranteed to be greater than or equal to 0. This is insured by
     * <code>AbstractPopulationGenerator</code>, which will call this method when {@link #generatePopulation(int)}
     * method is invoked externally.
     * 
     * @param size number of chromosomes to generate. Guaranteed to be greater than or equal to 0.
     * @return Collection of chromosomes which represents generated population.
     */
    protected abstract Collection<C> createPopulation(int size);
}
