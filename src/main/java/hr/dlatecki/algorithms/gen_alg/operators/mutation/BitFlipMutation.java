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
package hr.dlatecki.algorithms.gen_alg.operators.mutation;

import java.util.Random;
import hr.dlatecki.algorithms.gen_alg.operators.abstracts.AbstractMutationOperator;
import hr.dlatecki.algorithms.gen_alg.population.abstracts.AbstractByteArrayChromosome;

/**
 * An implementation of mutation operator which flips bits in order to mutate the chromosome. Bits are flipped with
 * provided probability rate.<br>
 * <br>
 * This mutation operator can only be used on chromosomes which extend the <code>AbstractByteArrayChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> the type of chromosome which will be used in mutation operator.
 * @see AbstractMutationOperator
 * @see AbstractByteArrayChromosome
 */
public class BitFlipMutation<C extends AbstractByteArrayChromosome> extends AbstractMutationOperator<C> {
    
    /**
     * Constructs a bit flip mutation operator. Mutation intensity specifies the chance for each bit to be flipped.
     * Mutation intensity must be in range [0, 1].
     * 
     * @param rand object used to generate random numbers.
     * @param mutationIntensity intensity at which mutation will occur. Valid value range is [0, 1].
     * @throws IllegalArgumentException thrown if provided value for mutation chance is invalid.
     */
    public BitFlipMutation(Random rand, double mutationIntensity) {
        super(rand, mutationIntensity);
    }
    
    @Override
    public C mutate(C chromosome) {
        
        byte[] bytes = chromosome.getBytes();
        
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] ^= generateBitMask();
        }
        
        chromosome.setBytes(bytes);
        
        return chromosome;
    }
    
    /**
     * Generates a random bit mask. Probability of a single bit to be 1 is proportional to mutation intensity.
     * 
     * @return Generated bit mask.
     */
    private byte generateBitMask() {
        
        byte mask = 0;
        
        for (int i = 0; i < 8; i++) {
            if (rand.nextDouble() <= mutationIntensity) {
                mask |= 1;
            }
            
            mask = (byte) (mask << 1 & 0xFF);
        }
        
        return mask;
    }
}
