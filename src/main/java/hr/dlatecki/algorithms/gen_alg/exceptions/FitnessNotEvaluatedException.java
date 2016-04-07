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
package hr.dlatecki.algorithms.gen_alg.exceptions;

/**
 * Thrown to indicate that fitness value of the chromosome has not yet been evaluated.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see GeneticAlgorithmException
 */
public class FitnessNotEvaluatedException extends GeneticAlgorithmException {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 7406997263974322061L;
    
    /**
     * Constructs the <code>FitnessNotEvaluatedException</code> with no detail message.
     */
    public FitnessNotEvaluatedException() {
        super();
    }
    
    /**
     * Constructs the <code>FitnessNotEvaluatedException</code> with specified detail message.
     * 
     * @param message the detail message.
     */
    public FitnessNotEvaluatedException(String message) {
        super(message);
    }
}
