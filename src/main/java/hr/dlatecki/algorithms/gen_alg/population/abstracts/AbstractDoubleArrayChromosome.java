// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.population.abstracts;

import java.util.Arrays;

/**
 * An abstract chromosome which is represented by an array of <code>double</code>s.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractChromosome
 */
public abstract class AbstractDoubleArrayChromosome extends AbstractChromosome {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 7920710305208104502L;
    /**
     * Array of values that represent this chromosome.
     */
    protected double[] values;
    
    /**
     * An empty constructor used in {@link #newLikeThis()} method in derived classes.
     */
    protected AbstractDoubleArrayChromosome() {}
    
    /**
     * Constructs an <code>AbstractDoubleArrayChromosome</code> using the provided array of values.
     * 
     * @param values values which will be assigned to the chromosome.
     * @param copyValues indicates if provided array of <code>double</code>s should be copied into a new array to use it
     *            this object.
     */
    protected AbstractDoubleArrayChromosome(double[] values, boolean copyValues) {
        if (copyValues) {
            this.values = Arrays.copyOf(values, values.length);
        } else {
            this.values = values;
        }
    }
    
    /**
     * Constructs an <code>AbstractDoubleArrayChromosome</code> using the provided array of values. Values are copied in
     * order to insure that they cannot be altered outside of this chromosome.
     * 
     * @param values values which will be assigned to the chromosome.
     */
    public AbstractDoubleArrayChromosome(double[] values) {
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
    
    /**
     * Fetches the length of the internally stored array of <code>double</code>s.
     * 
     * @return Length of the internal array.
     */
    public int getArrayLangth() {
        
        return values.length;
    }
}
