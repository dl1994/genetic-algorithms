// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.population.chromosomes;

import java.util.Arrays;
import hr.dlatecki.algorithms.gen_alg.population.abstracts.AbstractChromosome;

/**
 * An implementation of the chromosome which is represented by an array of <code>double</code> values.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractChromosome
 */
public class DoubleArrayChromosome extends AbstractChromosome {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -3983347067165233461L;
    /**
     * Array of values that represent this chromosome.
     */
    protected double[] values;
    
    /**
     * Constructs a <code>DoubleArrayChromosome</code> using the provided array of values.
     * 
     * @param values values which will be assigned to the chromosome.
     * @param copyValues indicates if provided array of <code>double</code>s should be copied into a new array to use it
     *            this object.
     */
    protected DoubleArrayChromosome(double[] values, boolean copyValues) {
        if (copyValues) {
            this.values = Arrays.copyOf(values, values.length);
        } else {
            this.values = values;
        }
    }
    
    /**
     * Constructs a <code>DoubleArrayChromosome</code> using the provided array of values. Values are copied in order to
     * insure that they cannot be altered outside of this chromosome.
     * 
     * @param values values which will be assigned to the chromosome.
     */
    public DoubleArrayChromosome(double[] values) {
        this.values = Arrays.copyOf(values, values.length);
    }
    
    /**
     * Creates an array which will contain the values stored in this object.
     * 
     * @return Array which contains values stored in this object.
     */
    public double[] getValues() {
        
        return Arrays.copyOf(values, values.length);
    }
    
    /**
     * Copies the provided values into an array stored in this object.
     * 
     * @param values the new values which will be stored in this object.
     */
    public void setValues(double[] values) {
        
        if (values.length == this.values.length) {
            System.arraycopy(values, 0, this.values, 0, this.values.length);
        } else {
            this.values = Arrays.copyOf(values, values.length);
        }
    }
    
    @Override
    protected void deepCopyTo(AbstractChromosome target) throws CloneNotSupportedException {}
}
