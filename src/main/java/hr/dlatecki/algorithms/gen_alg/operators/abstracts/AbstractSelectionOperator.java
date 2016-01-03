package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;
import java.util.SortedSet;
import hr.dlatecki.algorithms.gen_alg.operators.interfaces.ISelectionOperator;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

/**
 * An abstract class for selection operator. It is recommended that all implementations of selection operator extend
 * this class instead of directly implementing <code>ISelectionOperator</code> interface.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @param <C> Type of chromosome which will be used in the selection operator.
 * @see IChromosome
 * @see ISelectionOperator
 */
public abstract class AbstractSelectionOperator<C extends IChromosome> extends AbstractOperator
        implements ISelectionOperator<C> {
        
    /**
     * Defines a number of chromosomes to be selected in this selection operator.
     */
    protected int selectionSize;
    
    /**
     * Constructs a selection operator with provided <code>Random</code> object and selection size. Selection size must
     * be a number greater than or equal to 1.
     * 
     * @param rand object used to generate random numbers.
     * @param selectionSize number of chromosomes to select. Must be a number greater than or equal to 1.
     */
    public AbstractSelectionOperator(Random rand, int selectionSize) {
        super(rand);
        this.selectionSize = selectionSize;
    }
    
    @Override
    public void setSelectionSize(int size) {
        
        selectionSize = size;
    }
    
    @Override
    public SortedSet<C> select(SortedSet<C> pool) {
        
        return select(pool, selectionSize);
    }
}
