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
package hr.dlatecki.algorithms.gen_alg.population.chromosomes;

import hr.dlatecki.algorithms.gen_alg.population.abstracts.AbstractChromosome;
import hr.dlatecki.algorithms.gen_alg.population.abstracts.AbstractDoubleArrayChromosome;

/**
 * An implementation of <code>AbstractDoubleArrayChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractDoubleArrayChromosome
 */
public final class DoubleArrayChromosome extends AbstractDoubleArrayChromosome {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -2583519091375253441L;
    
    /**
     * An empty constructor used in {@link #newLikeThis()} method.
     */
    protected DoubleArrayChromosome() {}
    
    /**
     * Constructs a <code>DoubleArrayChromosome</code> using the provided array of values. Values are copied in order to
     * insure that they cannot be altered outside of this chromosome.
     * 
     * @param values values which will be assigned to the chromosome.
     */
    public DoubleArrayChromosome(double[] values) {
        super(values);
    }
    
    @Override
    protected void deepCopyTo(AbstractChromosome target) throws CloneNotSupportedException {}
    
    @Override
    public DoubleArrayChromosome newLikeThis() {
        
        return new DoubleArrayChromosome();
    }
}
