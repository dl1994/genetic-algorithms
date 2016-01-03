package hr.dlatecki.algorithms.gen_alg.operators.interfaces;

import java.util.SortedSet;
import hr.dlatecki.algorithms.gen_alg.operators.abstracts.AbstractSelectionOperator;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;

/**
 * Interface which defines methods for selection operator. It is recommended that all implementations of selection
 * operator extend <code>AbstractSelectionOperator</code> instead of directly implementing this interface.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> Type of chromosome which will be used in the selection operator.
 * @see IChromosome
 * @see AbstractSelectionOperator
 */
public interface ISelectionOperator<C extends IChromosome> {
    
    /**
     * Selects chromosomes from given set based on their fitness values. Chromosomes with higher fitness values have
     * higher chance to be selected. The provided set of chromosomes is expected to be sorted by fitness in descending
     * order. Number of selected chromosomes can be defined by invoking {@link #setSelectionSize(int)}. Alternatively,
     * {@link #select(SortedSet, int)} can be invoked if different number of selections is needed without changing the
     * preset selection size.
     * 
     * @param pool set from which chromosomes will be selected based on their fitness.
     * @return Set which contains selected chromosomes. The set is sorted by fitness of the chromosomes, in descending
     *         order.
     */
    public SortedSet<C> select(SortedSet<C> pool);
    
    /**
     * Selects chromosomes from given set based on their fitness values. Chromosomes with higher fitness values have
     * higher chance to be selected. The provided set of chromosomes is expected to be sorted by fitness in descending
     * order. Number of selected chromosomes is defined by the second argument, which must be greater than or equal to
     * 1.
     * 
     * @param pool set from which chromosomes will be selected based on their fitness.
     * @param size number of chromosomes to select. Must be a number greater than or equal to 1.
     * @return Set which contains selected chromosomes. The set is sorted by fitness of the chromosomes, in descending
     *         order.
     */
    public SortedSet<C> select(SortedSet<C> pool, int size);
    
    /**
     * Sets the number of chromosomes to be selected by the selection operator. Provided value must be a number greater
     * than or equal to 1.
     * 
     * @param size number of chromosomes to select. Must be a number greater than or equal to 1.
     */
    public void setSelectionSize(int size);
}
