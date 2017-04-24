package hr.dlatecki.algorithms.gen_alg.population.abstracts;

import java.util.Arrays;
import java.util.function.Consumer;

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
    private static final long serialVersionUID = 7733663607007688849L;
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
    
    /**
     * Fetches the length of the internally stored array of <code>byte</code>s.
     * 
     * @return Length of the internal array.
     */
    public int getArrayLangth() {
        
        return bytes.length;
    }
    
    /**
     * Fetches a byte with specified index stored in the internal array.
     * 
     * @param index index of the byte which will be fetched.
     * @return Byte from the internal array stored under specified index.
     * @throws IndexOutOfBoundsException thrown if provided <code>index</code> is outside of internal array bounds.
     */
    public byte getByte(int index) {
        
        return bytes[index];
    }
    
    /**
     * Performs an action for each element of the internal array.
     * 
     * @param action action which will be performed for each element of the internal array.
     */
    public void forEach(Consumer<Byte> action) {
        
        for (int i = 0; i < bytes.length; i++) {
            action.accept(bytes[i]);
        }
    }
}
