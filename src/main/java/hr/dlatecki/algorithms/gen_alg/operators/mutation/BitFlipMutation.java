// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.operators.mutation;

import java.util.Random;
import hr.dlatecki.algorithms.gen_alg.operators.abstracts.AbstractMutationOperator;
import hr.dlatecki.algorithms.gen_alg.population.chromosomes.ByteArrayChromosome;

/**
 * An implementation of mutation operator which flips bits in order to mutate the chromosome. Bits are flipped with
 * provided probability rate.<br>
 * <br>
 * This mutation can only be used on chromosomes which extend the <code>ByteArrayChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @param <C> the type of chromosome which will be used in the mutation operator.
 * @see AbstractMutationOperator
 * @see ByteArrayChromosome
 */
public class BitFlipMutation<C extends ByteArrayChromosome> extends AbstractMutationOperator<C> {
    
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
