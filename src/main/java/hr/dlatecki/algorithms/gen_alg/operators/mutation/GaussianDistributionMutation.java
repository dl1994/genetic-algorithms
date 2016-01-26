package hr.dlatecki.algorithms.gen_alg.operators.mutation;

import java.util.Random;
import hr.dlatecki.algorithms.gen_alg.operators.abstracts.AbstractDistributionMutation;
import hr.dlatecki.algorithms.gen_alg.population.chromosomes.DoubleArrayChromosome;

public class GaussianDistributionMutation<C extends DoubleArrayChromosome> extends AbstractDistributionMutation<C> {
    
    public GaussianDistributionMutation(Random rand, double mutationChance, double delta) {
        super(rand, mutationChance, delta);
    }
    
    @Override
    protected double calculateNewValue(double oldValue) {
        
        return oldValue + rand.nextGaussian() * delta;
    }
}
