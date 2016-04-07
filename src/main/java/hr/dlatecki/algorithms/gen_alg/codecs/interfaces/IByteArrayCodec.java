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
package hr.dlatecki.algorithms.gen_alg.codecs.interfaces;

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
