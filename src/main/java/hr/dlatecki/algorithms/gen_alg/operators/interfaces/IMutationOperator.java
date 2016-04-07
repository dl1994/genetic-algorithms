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
package hr.dlatecki.algorithms.gen_alg.operators.interfaces;

import hr.dlatecki.algorithms.gen_alg.operators.abstracts.AbstractMutationOperator;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

/**
 * Interface which defines methods for mutation operator. It is recommended that all implementations of mutation
 * operator extend <code>AbstractMutationOperator</code> instead of directly implementing this interface.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> Type of chromosome which will be used in the mutation operator.
 * @see IChromosome
 * @see AbstractMutationOperator
 */
public interface IMutationOperator<C extends IChromosome> {
    
    /**
     * Performs mutation on given chromosome. The provided chromosome will be altered and returned as the result. If the
     * chromosome is meant to be immutable, new chromosome will be created and returned by this method.
     * 
     * @param chromosome chromosome on which the mutation will be performed.
     * @return Mutated version of the provided chromosome. This will be the same object which was provided as an
     *         argument of this method, unless the object is immutable. In that case, new object will be returned.
     */
    public C mutate(C chromosome);
    
    /**
     * Sets the probability for mutation to occur. Provided value must be in range [0, 1].
     * 
     * @param intensity intensity at which mutation will occur. Valid value range is [0, 1].
     * @throws IllegalArgumentException thrown if provided value is invalid.
     */
    public void setMutationIntensity(double intensity);
}
