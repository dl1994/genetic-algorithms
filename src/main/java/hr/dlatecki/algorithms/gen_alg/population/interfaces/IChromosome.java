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
    public double getFitness();
    
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
    
    /**
     * Creates a new empty chromosome that is the same type as the chromosome it was created from.
     * 
     * @return A new chromosome of the same type as this chromosome.
     */
    public IChromosome newLikeThis();
}
