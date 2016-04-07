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
package hr.dlatecki.algorithms.gen_alg.codecs;

import hr.dlatecki.algorithms.gen_alg.codecs.abstracts.AbstractDoubleArrayToBinaryCodec;

/**
 * An implementation of <code>AbstractDoubleArrayToBinaryCodec</code> which encodes <code>double</code>s with gray
 * binary code. This class extends the <code>DoubleArrayToNaturalBinaryCodec</code> due to similarities in conversion.
 * The values are converted to natural binary first and then to gray code. Values can range from specified
 * <code>lowerBound</code> to specified <code>upperBound</code>, with all zeroes representing <code>lowerBound</code>
 * and leading one with remaining zeroes representing <code>upperBound</code>. Minimum and maximum number of bits per
 * value is specified in <code>AbstractDoubleArrayToBinaryCodec</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractDoubleArrayToBinaryCodec
 * @see DoubleArrayToNaturalBinaryCodec
 */
public class DoubleArrayToGrayBinaryCodec extends DoubleArrayToNaturalBinaryCodec {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 3484670780845767156L;
    
    /**
     * Constructs a <code>DoubleArrayToGrayBinaryCodec</code> object with provided coding parameters.
     * 
     * @param bitsPerValue number of code bits per single <code>double</code> value. Minimum value is 8, and maximum
     *            value is 32.
     * @param lowerBound minimum value to encode. All values smaller than this will have bits set to all zeroes.
     * @param upperBound maximum value to encode. All values greater than this will have bits set to all ones.
     * @throws IllegalArgumentException thrown if number of code bits is less than 8 or greater than 32.
     */
    public DoubleArrayToGrayBinaryCodec(int bitsPerValue, double lowerBound, double upperBound) {
        super(bitsPerValue, lowerBound, upperBound);
    }
    
    @Override
    protected long calculateEncodedLowerBound(double lowerBound, int bitsPerValue) {
        
        return 0L;
    }
    
    @Override
    protected long calculateEncodedUpperBound(double upperBound, int bitsPerValue) {
        
        long encodedValue = 1L;
        
        for (int i = 0, bound = bitsPerValue - 1; i < bound; i++) {
            encodedValue = encodedValue << 1L;
        }
        
        return encodedValue;
    }
    
    @Override
    protected long encodeValue(double value, double lowerBound, double upperBound, double step, int bitsPerValue) {
        
        long binaryValue = super.encodeValue(value, lowerBound, upperBound, step, bitsPerValue);
        
        return binaryValue ^ binaryValue >>> 1;
    }
    
    @Override
    protected double decodeValue(long value, double lowerBound, double upperBound, double step, int bitsPerValue) {
        
        long binaryValue = value;
        
        for (long mask = binaryValue >>> 1L; mask != 0; mask = mask >>> 1L) {
            binaryValue = binaryValue ^ mask;
        }
        
        return super.decodeValue(binaryValue, lowerBound, upperBound, step, bitsPerValue);
    }
}
