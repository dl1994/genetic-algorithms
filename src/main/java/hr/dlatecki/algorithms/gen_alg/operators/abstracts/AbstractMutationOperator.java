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
import hr.dlatecki.algorithms.gen_alg.operators.interfaces.IMutationOperator;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

/**
 * An abstract class for mutation operator. It is recommended that all implementations of mutation operator extend this
 * class instead of directly implementing <code>IMutationOperator</code> interface.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> the type of chromosome which will be used in the mutation operator.
 * @see IChromosome
 * @see IMutationOperator
 */
public abstract class AbstractMutationOperator<C extends IChromosome> extends AbstractOperator
        implements IMutationOperator<C> {
        
    /**
     * Intensity at which mutation will occur.
     */
    protected double mutationIntensity;
    
    /**
     * Constructs a mutation operator with provided <code>Random</code> object and mutation chance. Mutation intensity
     * must be in range [0, 1].
     * 
     * @param rand object used to generate random numbers.
     * @param mutationIntensity intensity at which mutation will occur. Valid value range is [0, 1].
     * @throws IllegalArgumentException thrown if provided value for mutation chance is invalid.
     */
    public AbstractMutationOperator(Random rand, double mutationIntensity) {
        super(rand);
        
        setMutationIntensity(mutationIntensity);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @throws IllegalArgumentException thrown if provided value is invalid.
     */
    @Override
    public final void setMutationIntensity(double intensity) {
        
        if (intensity < 0.0 || intensity > 1.0) {
            throw new IllegalArgumentException(
                    "Intensity must be in range [0, 1]. Provided value was: " + intensity + ".");
        }
        
        mutationIntensity = intensity;
    }
}
