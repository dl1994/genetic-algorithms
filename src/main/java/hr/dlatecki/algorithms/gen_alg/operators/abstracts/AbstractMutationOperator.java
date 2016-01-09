package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import hr.dlatecki.algorithms.gen_alg.operators.interfaces.IMutationOperator;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

/**
 * An abstract class for mutation operator. It is recommended that all implementations of mutation operator extend this
 * class instead of directly implementing <code>IMutationOperator</code> interface.
 * 
 * @author Domagoj Latečki
 * @version 1.1
 * @since 1.8
 * @param <C> the type of chromosome which will be used in the mutation operator.
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
     * @throws IllegalArgumentException thrown if provided value for mutation chance is invalid.
     */
    public AbstractMutationOperator(Random rand, double mutationChance) {
        super(rand);
        
        setMutationChance(mutationChance);
    }
    
    @Override
    public final void setMutationChance(double chance) {
        
        if (chance < 0.0 || chance > 1.0) {
            throw new IllegalArgumentException("Mutation chance must be in range [0, 1].");
        }
        
        mutationChance = chance;
    }
}
