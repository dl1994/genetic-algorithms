// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.population.interfaces;

import java.io.Serializable;
import hr.dlatecki.algorithms.gen_alg.exceptions.FitnessNotEvaluatedException;
import hr.dlatecki.algorithms.gen_alg.population.abstracts.AbstractChromosome;

/**
 * Interface which defines methods for chromosome. Chromosome is a population unit used in genetic algorithms.
 * Chromosomes should be considered equal only if they are the same object. Otherwise, the genetic algorithms
 * implemented in {@link hr.dlatecki.algorithms.gen_alg} package may not work properly. Fitness of the chromosome is
 * determined by fitness tests. It is highly recommended not to directly implement this interface. Instead, extend the
 * <code>AbstractChromosome</code> class which already implements this interface and all of its methods.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see IFitnessTest
 * @see AbstractChromosome
 */
public interface IChromosome extends Comparable<IChromosome>, Cloneable, Serializable {
    
    /**
     * Fetches the current fitness of this chromosome.
     * 
     * @return Current fitness value of this chromosome.
     * @throws FitnessNotEvaluatedException thrown if fitness of this chromosome is not yet evaluated.
     */
    public double getFitness() throws FitnessNotEvaluatedException;
    
    /**
     * Sets the fitness value of this chromosome.
     * 
     * @param fitness new fitness value of this chromosome.
     */
    public void setFitness(double fitness);
    
    /**
     * Creates a copy of this chromosome. By convention, the returned object should be obtained by calling
     * <code>super.clone()</code>. If there are any non-primitive fields of mutable objects, they should be copied in
     * order to insure total independence of this object and the new object. See {@link Object#clone()} for more
     * details.
     * 
     * @return A clone of this chromosome.
     */
    public IChromosome clone();
}
