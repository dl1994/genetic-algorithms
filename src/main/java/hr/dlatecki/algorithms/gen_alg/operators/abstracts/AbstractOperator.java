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
package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;

/**
 * An abstract class which offers a constructor which takes a <code>Random</code> object as a parameter.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 */
abstract class AbstractOperator {
    
    /**
     * Object used to generate random numbers.
     */
    protected Random rand;
    
    /**
     * Constructs the operator using the provided <code>Random</code> object.
     * 
     * @param rand object used to generate random numbers.
     */
    public AbstractOperator(Random rand) {
        this.rand = rand;
    }
    
    /**
     * Fetches the <code>Random</code> object which is used in this object.
     * 
     * @return <code>Random</code> object which is used in this object.
     */
    public final Random getRand() {
        
        return rand;
    }
    
    /**
     * Sets the <code>Random</code> object which will be used in this object.
     * 
     * @param rand object used to generate random numbers.
     */
    public final void setRandom(Random rand) {
        
        this.rand = rand;
    }
}
