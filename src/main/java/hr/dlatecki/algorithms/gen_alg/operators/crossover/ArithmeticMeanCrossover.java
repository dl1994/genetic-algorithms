/* 
 * The MIT License (MIT)
 * 
 * Copyright © 2016 Domagoj Latečki
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
