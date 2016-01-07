package hr.dlatecki.algorithms.gen_alg.population;

import java.util.Arrays;
import hr.dlatecki.algorithms.gen_alg.codecs.IDoubleArrayCodec;
import hr.dlatecki.algorithms.gen_alg.population.abstracts.AbstractChromosome;

/**
 * An implementation of the chromosome which contains an array of <code>double</code>s and a reference to the wrapped
 * object. The wrapped object is represented by the array. This class also provides methods for fetching the wrapped
 * object and an array which represents it. When a change occurs the an array, a new object is wrapped which corresponds
 * to the array, and vice-versa. Essentially, the wrapped object is always represented by the array which was encoded
 * from it, and the array can always be decoded into the object with same attributes. Encoding and decoding process is
 * performed by the coder which is provided upon creation of the instance of this class.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @param <I> type of the object to wrap.
 * @since 1.8
 */
public class DoubleArrayWrapperChromosome<I> extends AbstractChromosome {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 4428769866923824574L;
    /**
     * An array of <code>double</code>s which represents the item wrapped in this object.
     */
    private double[] values;
    /**
     * Indicates if the currently stored item is immutable.
     */
    private boolean immutable;
    /**
     * Initial setting of the <code>immutable</code> flag.
     */
    private boolean immutableHistory;
    /**
     * Item wrapped by this object.
     */
    private I item;
    /**
     * Codec which is used in item encoding/decoding.
     */
    private IDoubleArrayCodec<I> codec;
    
    /**
     * Creates a <code>DoubleArrayWrapperChromosome</code> object with provided codec. If the item which will be wrapped
     * in this object is meant to be immutable, then set the <code>immutable</code> flag to <code>true</code>. When flag
     * is set to <code>true</code> the reference to the wrapped item will be simply set to the provided item. If the
     * flag is set to <code>false</code>, then the new item will be created by decoding an array which was just created
     * by encoding the provided item. This will ensure that the item wrapped in this object cannot be changed from
     * outside of this object.
     * 
     * @param immutable indicates if item which will be stored in this object is immutable.
     * @param codec codec to use in item encoding/decoding process.
     */
    public DoubleArrayWrapperChromosome(boolean immutable, IDoubleArrayCodec<I> codec) {
        this.immutable = immutableHistory = immutable;
        this.codec = codec;
    }
    
    /**
     * Creates a <code>DoubleArrayWrapperChromosome</code> object using the provided item and codec. The item will be
     * encoded into the array of <code>double</code>s which will represent the encoded item. The encoding action will be
     * performed by provided codec. If item is immutable, then set the <code>immutable</code> flag to <code>true</code>.
     * When flag is set to <code>true</code> the reference to the wrapped item will be simply set to the provided item.
     * If the flag is set to <code>false</code>, then the new item will be created by decoding an array which was just
     * created by encoding the provided item. This will ensure that the item wrapped in this object cannot be changed
     * from outside of this object.
     * 
     * @param item item to wrap and encode.
     * @param immutable indicates if <code>item</code> is immutable.
     * @param codec codec to use in item encoding/decoding process.
     */
    public DoubleArrayWrapperChromosome(I item, boolean immutable, IDoubleArrayCodec<I> codec) {
        this(immutable, codec);
        
        values = codec.encode(item);
        
        this.item = immutable ? item : codec.decode(values);
    }
    
    /**
     * Creates a <code>DoubleArrayWrapperChromosome</code> object using the provided array of <code>double</code>s and
     * codec. The array will be copied into this object, so outside changes to the array will have no effect on the
     * contents of this object. It will also be decoded into an item by the codec. The item will be wrapped in this
     * object.
     * 
     * @param values values of the chromosome.
     * @param codec codec to use in item encoding/decoding process.
     */
    public DoubleArrayWrapperChromosome(double[] values, IDoubleArrayCodec<I> codec) {
        this(true, codec);
        this.values = Arrays.copyOf(values, values.length);
        
        item = codec.decode(this.values);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    protected void deepCopyTo(AbstractChromosome target) {
        
        // Type is guaranteed to be DoubleArrayWrapperChromosome<I> because the target is cloned from this object.
        ((DoubleArrayWrapperChromosome<I>) target).item = immutable ? item : codec.decode(values);
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
        
        item = codec.decode(this.values);
        immutable = true;
    }
    
    /**
     * Fetches the item wrapped in this object. If item is not immutable, the new object will be returned which will be
     * identical to the stored object.
     * 
     * @return Object wrapped in this object.
     */
    public I getItem() {
        
        return immutable ? item : codec.decode(values);
    }
    
    /**
     * Sets the item wrapped in this object. If the object is not expected to be immutable, then a new object will be
     * created and wrapped in this object.
     * 
     * @param item object to wrap.
     */
    public void setItem(I item) {
        
        immutable = immutableHistory;
        values = codec.encode(item);
        
        this.item = immutable ? item : codec.decode(values);
    }
}
