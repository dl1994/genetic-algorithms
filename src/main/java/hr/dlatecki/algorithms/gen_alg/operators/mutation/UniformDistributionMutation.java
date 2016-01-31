// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.operators.mutation;

import java.util.Random;
import hr.dlatecki.algorithms.gen_alg.operators.abstracts.AbstractDistributionMutation;
import hr.dlatecki.algorithms.gen_alg.population.chromosomes.DoubleArrayChromosome;

/**
 * An implementation of mutation operator which uses the uniform distribution to generate mutated values. The new value
 * is calculated by adding a value which is randomly generated using the uniform distribution. Maximum and minimum
 * values are bounded by the provided upper and lower limits. If variance is provided instead, upper and lower limits
 * are calculated from provided variance and are symmetrical. Every value in the range which is defined by the bounds
 * has an equal chance of being selected as the value which will be added to the current value.<br>
 * <br>
 * More info about the uniform distribution can be found
 * <a href = "https://en.wikipedia.org/wiki/Uniform_distribution_(continuous)">here</a>.<br>
 * <br>
 * This mutation operator can only be used on chromosomes which extend <code>DoubleArrayChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> the type of chromosome which will be used in the mutation operator.
 * @see AbstractDistributionMutation
 * @see DoubleArrayChromosome
 */
public class UniformDistributionMutation<C extends DoubleArrayChromosome> extends AbstractDistributionMutation<C> {
    
    /**
     * Value of the square roof of 12. Used as a constant to slightly speed up the calculation of the new value.
     */
    private static final double SQRT_12 = Math.sqrt(12.0);
    /**
     * Lower limit of the distribution.
     */
    private final double lowerLimit;
    /**
     * Upper limit of the distribution.
     */
    private final double upperLimit;
    /**
     * Range of the distribution.
     */
    private final double range;
    
    /**
     * Constructs an uniform distribution mutation using the provided variance. Variance must be a positive number. The
     * ranges of the distribution will be symmetrical and are calculated from the provided variance value. Mutation
     * intensity affects the variance which will be used in the distribution.
     * 
     * @param rand object used to generate random numbers.
     * @param mutationIntensity intensity at which mutation will occur. Valid value range is [0, 1].
     * @param variance variance of the distribution. Must be a positive number.
     * @throws IllegalArgumentException thrown if any of the arguments has an illegal value.
     */
    public UniformDistributionMutation(Random rand, double mutationIntensity, double variance) {
        super(rand, mutationIntensity, variance);
        
        upperLimit = Math.sqrt(variance * 3.0);
        lowerLimit = -upperLimit;
        range = upperLimit - lowerLimit;
    }
    
    /**
     * Constructs an uniform distribution mutation using the provided lower and upper limits. Upper limit must be
     * greater than the lower limit. Mutation intensity affects the variance which will be used in the distribution.
     * 
     * @param rand object used to generate random numbers.
     * @param mutationIntensity intensity at which mutation will occur. Valid value range is [0, 1].
     * @param lowerLimit lower limit of the distribution.
     * @param upperLimit upper limit of the distribution. Must be greater than the lower limit.
     * @throws IllegalArgumentException thrown if provided lower limit is greater or equal to the upper limit.
     */
    public UniformDistributionMutation(Random rand, double mutationIntensity, double lowerLimit, double upperLimit) {
        super(rand, mutationIntensity, calculateVariance(lowerLimit, upperLimit));
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
        
        range = upperLimit - lowerLimit;
    }
    
    /**
     * Calculates the variance of the distribution using the provided lower and upper limits. Upper limit must be
     * greater than the lower limit.
     * 
     * @param lowerLimit lower limit of the distribution.
     * @param upperLimit upper limit of the distribution. Must be greater than the lower limit.
     * @return Variance calculated from provided limits.
     * @throws IllegalArgumentException thrown if provided lower limit is greater or equal to the upper limit.
     */
    private static double calculateVariance(double lowerLimit, double upperLimit) {
        
        if (lowerLimit >= upperLimit) {
            throw new IllegalArgumentException(
                    "Upper limit must be greather than the lower limit. Provided values were: lowerLimit = "
                            + lowerLimit + ", upperLimit = " + upperLimit + ".");
        }
        
        return Math.pow(upperLimit - lowerLimit, 2.0) / 12;
    }
    
    @Override
    protected double calculateNewValue(double oldValue, double variance) {
        
        double currentLowerLimit = SQRT_12 * variance / range * lowerLimit;
        double currentUpperLimit = SQRT_12 * variance / range * upperLimit;
        double currentRange = currentUpperLimit - currentLowerLimit;
        
        return oldValue + currentRange * rand.nextDouble() + currentLowerLimit;
    }
}
