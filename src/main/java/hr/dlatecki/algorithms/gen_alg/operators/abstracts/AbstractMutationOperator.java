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
 * @param <C> Type of chromosome which will be used in the mutation operator.
 * @see IChromosome
 * @see IMutationOperator
 */
public abstract class AbstractMutationOperator<C extends IChromosome> extends AbstractOperator
        implements IMutationOperator<C> {
        
    /**
     * Probability for mutation to occur.
     */
    protected double mutationChance;
    
    /**
     * Constructs a mutation operator with provided <code>Random</code> object and mutation chance. Mutation chance must
     * be in range [0, 1].
     * 
     * @param rand object used to generate random numbers.
     * @param mutationChance probability for mutation to occur. Valid value range is [0, 1].
     */
    public AbstractMutationOperator(Random rand, double mutationChance) {
        super(rand);
        this.mutationChance = mutationChance;
    }
    
    @Override
    public void setMutationChance(double chance) {
        
        mutationChance = chance;
    }
}
