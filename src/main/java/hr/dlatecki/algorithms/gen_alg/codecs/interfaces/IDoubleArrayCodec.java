// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.codecs.interfaces;

import java.io.Serializable;

/**
 * Interface which specifies methods for encoding and decoding an object into and from an array of <code>double</code>s.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @param <I> the type of object to encode/decode.
 * @since 1.8
 */
public interface IDoubleArrayCodec<I> extends Serializable {
    
    /**
     * Encodes the object into an array of <code>double</code>s.
     * 
     * @param item object to encode.
     * @return Array which represents the encoded item.
     */
    public double[] encode(I item);
    
    /**
     * Decodes the array of <code>double</code>s into an object.
     * 
     * @param values array to decode.
     * @return Object decoded from given array.
     */
    public I decode(double[] values);
}
