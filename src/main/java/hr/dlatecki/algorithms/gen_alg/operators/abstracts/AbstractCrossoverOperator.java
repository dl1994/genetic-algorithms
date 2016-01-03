package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import hr.dlatecki.algorithms.gen_alg.operators.interfaces.ICrossoverOperator;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

/**
 * An abstract class for crossover operator. It is recommended that all implementations of crossover operator extend
 * this class instead of directly implementing <code>ICrossoverOperator</code> interface.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @param <C> Type of chromosome which will be used in the crossover operator.
 * @see IChromosome
 * @see ICrossoverOperator
 */
public abstract class AbstractCrossoverOperator<C extends IChromosome> extends AbstractOperator
        implements ICrossoverOperator<C> {
        
    /**
     * Constructs a crossover operator with provided <code>Random</code> object.
     * 
     * @param rand object used to generate random numbers.
     */
    public AbstractCrossoverOperator(Random rand) {
        super(rand);
    }
}
