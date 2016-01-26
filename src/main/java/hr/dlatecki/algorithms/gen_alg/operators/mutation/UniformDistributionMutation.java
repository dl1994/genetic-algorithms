// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.operators.mutation;

import java.util.Random;
import hr.dlatecki.algorithms.gen_alg.operators.abstracts.AbstractDistributionMutation;
import hr.dlatecki.algorithms.gen_alg.population.chromosomes.DoubleArrayChromosome;

public class UniformDistributionMutation<C extends DoubleArrayChromosome> extends AbstractDistributionMutation<C> {
    
    public UniformDistributionMutation(Random rand, double mutationChance, double delta) {
        super(rand, mutationChance, delta);
    }
    
    @Override
    protected double calculateNewValue(double oldValue) {
        
        return oldValue + 2.0 * delta * rand.nextDouble() - delta;
    }
}
