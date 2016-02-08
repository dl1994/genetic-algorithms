// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
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
