// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.population.interfaces;

/**
 * Interface which defines methods for fitness tests in genetic algorithms. Fitness tests evaluate chromosomes and set
 * their fitness values accordingly.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> the type of chromosome which will be evaluated in the fitness test.
 * @see IChromosome
 */
public interface IFitnessTest<C extends IChromosome> {
    
    /**
     * Evaluates given chromosome and sets its fitness accordingly.
     * 
     * @param chromosome chromosome to evaluate.
     */
    public void evaluate(C chromosome);
}
