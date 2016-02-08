// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.operators.crossover;

import java.util.Random;
import java.util.SortedSet;
import hr.dlatecki.algorithms.gen_alg.exceptions.IncompatibleParentsException;
import hr.dlatecki.algorithms.gen_alg.operators.abstracts.AbstractCrossoverOperator;
import hr.dlatecki.algorithms.gen_alg.population.abstracts.AbstractDoubleArrayChromosome;

/**
 * An implementation of a crossover operator used for chromosomes based on an array of <code>double</code>s. The
 * elements of the child's array are calculated in the following way:<br>
 * <br>
 * <table style = "text-align: center">
 * <tr>
 * <td colspan = "3"><code>numOfParents</code></td>
 * </tr>
 * <tr>
 * <td><code>childArray[i] = </code></td>
 * <td style = "font-size:26pt; text-align: right;">&Sigma;</td>
 * <td><code>(parentArray<sub>n</sub>[i])</code></td>
 * <td><code>/ numOfParents</code></td>
 * </tr>
 * <tr>
 * <td colspan = "3"><code>n = 1</code></td>
 * </tr>
 * </table>
 * <br>
 * <br>
 * This crossover operator can only be used on chromosomes which extend <code>AbstractDoubleArrayChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> the type of chromosome which will be used in the crossover operator.
 * @see AbstractDoubleArrayChromosome
 * @see AbstractCrossoverOperator
 */
public class ArithmeticMeanCrossover<C extends AbstractDoubleArrayChromosome> extends AbstractCrossoverOperator<C> {
    
    /**
     * Constructs an <code>ArithmeticMeanCrossover</code> operator.
     * 
     * @param rand object used to generate random numbers.
     */
    public ArithmeticMeanCrossover(Random rand) {
        super(rand);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @throws IncompatibleParentsException thrown if provided parents are incompatible for crossover.
     */
    @Override
    protected C createChild(SortedSet<C> parents) {
        
        @SuppressWarnings("unchecked")
        C child = (C) parents.first().newLikeThis();
        
        child.setValues(calculateChildValues(parents));
        
        return child;
    }
    
    /**
     * Calculates the values of the child array using the parent arrays.
     * 
     * @param parents set of parent chromosome from which values of the child array will be calculated.
     * @return Array which contains values for the child chromosome.
     * @throws IncompatibleParentsException thrown if provided parents are incompatible for crossover.
     */
    private double[] calculateChildValues(SortedSet<C> parents) {
        
        double[] childValues = null;
        
        for (C parent : parents) {
            double[] values = parent.getValues();
            
            if (childValues == null) {
                childValues = values;
            } else if (values.length != childValues.length) {
                throw new IncompatibleParentsException("One or more parents have arrays of different length.");
            } else {
                for (int i = 0; i < childValues.length; i++) {
                    childValues[i] += values[i];
                }
            }
        }
        
        int numOfParents = parents.size();
        
        for (int i = 0; i < childValues.length; i++) {
            childValues[i] /= numOfParents;
        }
        
        return childValues;
    }
}
