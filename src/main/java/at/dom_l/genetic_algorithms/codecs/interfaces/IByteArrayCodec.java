package at.dom_l.genetic_algorithms.codecs.interfaces;

import java.io.Serializable;

/**
 * Interface which specifies methods for encoding and decoding an object into and from an array of <code>byte</code>s.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @param <I> the type of object to encode/decode.
 * @since 1.8
 */
public interface IByteArrayCodec<I> extends Serializable {
    
    /**
     * Encodes the object into an array of <code>byte</code>s.
     * 
     * @param item object to encode.
     * @return Array which represents the encoded item.
     */
    public byte[] encode(I item);
    
    /**
     * Decodes the array of <code>byte</code>s into an object.
     * 
     * @param bytes array to decode.
     * @return Object decoded from given array.
     */
    public I decode(byte[] bytes);
}
