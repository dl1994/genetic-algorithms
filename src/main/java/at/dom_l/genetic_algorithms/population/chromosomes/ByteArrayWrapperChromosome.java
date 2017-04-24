package at.dom_l.genetic_algorithms.population.chromosomes;

import at.dom_l.genetic_algorithms.population.abstracts.AbstractByteArrayChromosome;
import at.dom_l.genetic_algorithms.codecs.interfaces.IByteArrayCodec;
import at.dom_l.genetic_algorithms.population.abstracts.AbstractChromosome;

/**
 * An implementation of the chromosome which contains an array of <code>byte</code>s and a reference to the wrapped
 * object. The wrapped object is represented by the array. This class also provides methods for fetching the wrapped
 * object and an array which represents it. When a change occurs the array, a new object is wrapped which corresponds to
 * the array, and vice-versa. Essentially, the wrapped object is always represented by the array which was encoded from
 * it, and the array can always be decoded into an object with same attributes. Encoding and decoding process is
 * performed by the coder which is provided upon creation of the instance of this class.<br>
 * <br>
 * There are no public constructors for this class. To construct a chromosome using an item, use
 * {@link #fromMutable(Object, IByteArrayCodec)} or {@link #fromImmutable(Object, IByteArrayCodec)} static methods of
 * this class. To construct a chromosome using an array of <code>byte</code>s, use
 * {@link #fromBytes(byte[], IByteArrayCodec)} static method.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @param <I> type of the object to wrap.
 * @since 1.8
 * @see AbstractByteArrayChromosome
 */
public final class ByteArrayWrapperChromosome<I> extends AbstractByteArrayChromosome {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 7442518821699954977L;
    /**
     * Indicates if the currently stored item is immutable.
     */
    private boolean immutable;
    /**
     * Initial setting of the <code>immutable</code> flag.
     */
    private final boolean initialImmutable;
    /**
     * Item wrapped by this object.
     */
    private transient I item;
    /**
     * Codec which is used in item encoding/decoding.
     */
    private IByteArrayCodec<I> codec;
    
    /**
     * Constructs a <code>ByteArrayWrapperChromosome</code> object using the provided mutable item and codec. If a
     * chromosome for immutable item is needed, use {@link #fromImmutable(Object, IByteArrayCodec)} instead. The item
     * will be encoded into the array of <code>byte</code>s which will represent the encoded item. The encoding action
     * is performed by the provided codec. New item will be created and stored by decoding an array which was just
     * encoded from the provided item. This will ensure that the item wrapped in this object cannot be changed from
     * outside of this object.
     * 
     * @param item item to wrap in the chromosome object.
     * @param codec codec to use in item encoding/decoding process.
     * @return Constructed chromosome.
     */
    public static <I> ByteArrayWrapperChromosome<I> fromMutable(I item, IByteArrayCodec<I> codec) {
        
        return new ByteArrayWrapperChromosome<>(item, false, codec);
    }
    
    /**
     * Constructs a <code>ByteArrayWrapperChromosome</code> object using the provided immutable item and codec. If a
     * chromosome for mutable item is needed, use {@link #fromMutable(Object, IByteArrayCodec)} instead. The item will
     * be encoded into the array of <code>byte</code>s which will represent the encoded item. The encoding action is
     * performed by the provided codec. The item will be wrapped in this object.
     * 
     * @param item item to wrap in the chromosome object.
     * @param codec codec to use in item encoding/decoding process.
     * @return Constructed chromosome.
     */
    public static <I> ByteArrayWrapperChromosome<I> fromImmutable(I item, IByteArrayCodec<I> codec) {
        
        return new ByteArrayWrapperChromosome<>(item, true, codec);
    }
    
    /**
     * Constructs a <code>ByteArrayWrapperChromosome</code> object using the provided array of <code>byte</code>s and
     * codec. The array will be copied into this object, so outside changes to the array will have no effect on the
     * contents of this object. It will also be decoded into an item by the codec. The item will be wrapped in this
     * object.
     * 
     * @param bytes bytes of the chromosome.
     * @param codec codec to use in item encoding/decoding process.
     * @return Constructed chromosome.
     */
    public static <I> ByteArrayWrapperChromosome<I> fromBytes(byte[] bytes, IByteArrayCodec<I> codec) {
        
        return new ByteArrayWrapperChromosome<>(bytes, codec);
    }
    
    /**
     * A constructor used in {@link #newLikeThis()} method. Creates an empty <code>ByteArrayWrapperChromosome</code>.
     * 
     * @param immutable indicates if <code>item</code> is immutable.
     * @param codec codec to use in item encoding/decoding process.
     */
    protected ByteArrayWrapperChromosome(boolean immutable, IByteArrayCodec<I> codec) {
        this.immutable = initialImmutable = immutable;
        this.codec = codec;
    }
    
    /**
     * Constructs a <code>ByteArrayWrapperChromosome</code> object with provided codec. If the item which will be
     * wrapped in this object is meant to be immutable, then set the <code>immutable</code> flag to <code>true</code>.
     * When flag is set to <code>true</code> the provided item will be set as the wrapped item. If the flag is set to
     * <code>false</code>, then the new item will be created by decoding an array which was encoded from the provided
     * item. This will ensure that the item wrapped in this object cannot be changed from outside of this object.
     * 
     * @param bytes array of <code>byte</code>s to which will be passed to the superclass constructor.
     * @param immutable indicates if item which will be stored in this object is immutable.
     * @param codec codec to use in item encoding/decoding process.
     * @param copyBytes indicates if provided array of <code>byte</code>s should be copied into a new array to use it
     *            this object.
     */
    private ByteArrayWrapperChromosome(byte[] bytes, boolean immutable, IByteArrayCodec<I> codec, boolean copyBytes) {
        super(bytes, copyBytes);
        this.immutable = initialImmutable = immutable;
        this.codec = codec;
    }
    
    /**
     * Constructs a <code>ByteArrayWrapperChromosome</code> object using the provided item and codec. The item will be
     * encoded into the array of <code>byte</code>s which will represent the encoded item. The encoding action is
     * performed by the provided codec. If item is immutable, then set the <code>immutable</code> flag to
     * <code>true</code>. When flag is set to <code>true</code> the provided item will be set as the wrapped item. If
     * the flag is set to <code>false</code>, then the new item will be created by decoding an array which was encoded
     * from the provided item. This will ensure that the item wrapped in this object cannot be changed from outside of
     * this object.
     * 
     * @param item item to wrap and encode.
     * @param immutable indicates if <code>item</code> is immutable.
     * @param codec codec to use in item encoding/decoding process.
     */
    private ByteArrayWrapperChromosome(I item, boolean immutable, IByteArrayCodec<I> codec) {
        this(codec.encode(item), immutable, codec, false);
        this.item = immutable ? item : codec.decode(bytes);
    }
    
    /**
     * Constructs a <code>ByteArrayWrapperChromosome</code> object using the provided array of <code>byte</code>s and
     * codec. The array will be copied into this object, so outside changes to the array will have no effect on the
     * contents of this object. It will also be decoded into an item by the codec. The item will be wrapped in this
     * object.<br>
     * <br>
     * To construct a chromosome using an item, use {@link #fromMutable(Object, IByteArrayCodec)} or
     * {@link #fromImmutable(Object, IByteArrayCodec)} static method of this class.
     * 
     * @param bytes bytes of the chromosome.
     * @param codec codec to use in item encoding/decoding process.
     */
    private ByteArrayWrapperChromosome(byte[] bytes, IByteArrayCodec<I> codec) {
        this(bytes, true, codec, true);
        
        item = codec.decode(this.bytes);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    protected void deepCopyTo(AbstractChromosome target) {
        
        // Type is guaranteed to be DoubleArrayWrapperChromosome<I> because the target is cloned from this object.
        ((ByteArrayWrapperChromosome<I>) target).item = immutable ? item : codec.decode(bytes);
    }
    
    @Override
    public void setBytes(byte[] bytes) {
        
        super.setBytes(bytes);
        
        item = codec.decode(this.bytes);
        immutable = true;
    }
    
    /**
     * Fetches the item wrapped in this object. If item is not immutable, the new object will be returned which will be
     * identical to the stored object.
     * 
     * @return Object wrapped in this object.
     */
    public I getItem() {
        
        if (item == null) {
            item = codec.decode(bytes);
            immutable = true;
        }
        
        return immutable ? item : codec.decode(bytes);
    }
    
    /**
     * Sets the item wrapped in this object. If the object is not expected to be immutable, then a new object will be
     * created and wrapped in this object.
     * 
     * @param item object to wrap.
     */
    public void setItem(I item) {
        
        immutable = initialImmutable;
        bytes = codec.encode(item);
        
        this.item = immutable ? item : codec.decode(bytes);
    }
    
    @Override
    public ByteArrayWrapperChromosome<I> newLikeThis() {
        
        return new ByteArrayWrapperChromosome<>(initialImmutable, codec);
    }
}
