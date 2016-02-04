// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.population.abstracts;

import java.util.Arrays;

/**
 * An abstract chromosome which is represented by an array of <code>byte</code>s.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractChromosome
 */
public abstract class AbstractByteArrayChromosome extends AbstractChromosome {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 5882144342907451099L;
    /**
     * Array of bytes that represent this chromosome.
     */
    protected byte[] bytes;
    
    /**
     * An empty constructor used in {@link #newLikeThis()} method in derived classes.
     */
    protected AbstractByteArrayChromosome() {}
    
    /**
     * Constructs an <code>AbstractByteArrayChromosome</code> using the provided array of bytes.
     * 
     * @param bytes bytes which will be assigned to the chromosome.
     * @param copyBytes indicates if provided array of <code>byte</code>s should be copied into a new array to use it
     *            this object.
     */
    protected AbstractByteArrayChromosome(byte[] bytes, boolean copyBytes) {
        if (copyBytes) {
            this.bytes = Arrays.copyOf(bytes, bytes.length);
        } else {
            this.bytes = bytes;
        }
    }
    
    /**
     * Constructs an <code>AbstractByteArrayChromosome</code> using the provided array of bytes. Bytes are copied in
     * order to ensure that they cannot be altered outside of this chromosome.
     * 
     * @param bytes bytes which will be assigned to the chromosome.
     */
    public AbstractByteArrayChromosome(byte[] bytes) {
        this(bytes, true);
    }
    
    /**
     * Creates an array which will contain the bytes stored in this object.
     * 
     * @return Array which contains bytes stored in this object.
     */
    public byte[] getBytes() {
        
        return Arrays.copyOf(bytes, bytes.length);
    }
    
    /**
     * Copies the provided bytes into an array stored in this object.
     * 
     * @param bytes the new bytes which will be stored in this object.
     */
    public void setBytes(byte[] bytes) {
        
        if (bytes.length == this.bytes.length) {
            System.arraycopy(bytes, 0, this.bytes, 0, this.bytes.length);
        } else {
            this.bytes = Arrays.copyOf(bytes, bytes.length);
        }
    }
}
