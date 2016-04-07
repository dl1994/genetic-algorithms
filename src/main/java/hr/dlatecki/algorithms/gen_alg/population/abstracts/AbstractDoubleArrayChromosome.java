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
package hr.dlatecki.algorithms.gen_alg.population.abstracts;

import java.util.Arrays;
import java.util.function.Consumer;

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
    private static final long serialVersionUID = 508198025622464779L;
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
    
    /**
     * Fetches a value with specified index stored in the internal array.
     * 
     * @param index index of the value which will be fetched.
     * @return Value from the internal array stored under specified index.
     * @throws IndexOutOfBoundsException thrown if provided <code>index</code> is outside of internal array bounds.
     */
    public double getValue(int index) {
        
        return values[index];
    }
    
    /**
     * Performs an action for each element of the internal array.
     * 
     * @param action action which will be performed for each element of the internal array.
     */
    public void forEach(Consumer<Double> action) {
        
        for (int i = 0; i < values.length; i++) {
            action.accept(values[i]);
        }
    }
}
