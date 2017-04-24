package at.dom_l.genetic_algorithms.operators.interfaces;

import at.dom_l.genetic_algorithms.operators.abstracts.AbstractSelectionOperator;
import at.dom_l.genetic_algorithms.population.interfaces.IChromosome;
import java.util.SortedSet;

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
     * Minimum selection size.
     */
    public static final int MIN_SELECTION_SIZE = 2;
    
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
     * {@value #MIN_SELECTION_SIZE}.
     * 
     * @param pool set from which chromosomes will be selected based on their fitness.
     * @param size number of chromosomes to select. Must be a number greater than or equal to
     *            {@value #MIN_SELECTION_SIZE}.
     * @return Set which contains selected chromosomes. The set is sorted by fitness of the chromosomes, in descending
     *         order.
     * @throws IllegalArgumentException thrown if provided size is less than {@value #MIN_SELECTION_SIZE}.
     */
    public SortedSet<C> select(SortedSet<C> pool, int size);
    
    /**
     * Sets the number of chromosomes to be selected by the selection operator. Provided value must be a number greater
     * than or equal to {@link #MIN_SELECTION_SIZE}.
     * 
     * @param size number of chromosomes to select. Must be a number greater than or equal to
     *            {@link #MIN_SELECTION_SIZE}.
     * @throws IllegalArgumentException thrown if provided size is less than {@link #MIN_SELECTION_SIZE}.
     */
    public void setSelectionSize(int size);
}
