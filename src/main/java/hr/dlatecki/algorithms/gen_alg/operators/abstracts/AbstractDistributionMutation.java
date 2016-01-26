// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import hr.dlatecki.algorithms.gen_alg.population.chromosomes.DoubleArrayChromosome;

public abstract class AbstractDistributionMutation<C extends DoubleArrayChromosome>
        extends AbstractMutationOperator<C> {
        
    protected double delta;
    
    public AbstractDistributionMutation(Random rand, double mutationChance, double delta) {
        super(rand, mutationChance);
        
        setDelta(delta);
    }
    
    @Override
    public C mutate(C chromosome) {
        
        double[] values = chromosome.getValues();
        
        for (int i = 0; i < values.length; i++) {
            if (rand.nextDouble() <= mutationChance) {
                values[i] = calculateNewValue(values[i]);
            }
        }
        
        chromosome.setValues(values);
        
        return chromosome;
    }
    
    public void setDelta(double delta) {
        
        if (delta <= 0) {
            throw new IllegalArgumentException("Delta must be a positive number. Provided value was: " + delta);
        }
        
        this.delta = delta;
    }
    
    protected abstract double calculateNewValue(double oldValue);
}
