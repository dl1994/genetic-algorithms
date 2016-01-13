// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.codecs;

import hr.dlatecki.algorithms.gen_alg.codecs.interfaces.IByteArrayCodec;

public class DoubleArrayToNaturalBinaryCodec implements IByteArrayCodec<double[]> {
    
    /**
     * Number of code bits per single <code>double</code> value.
     */
    private int bitsPerNumber;
    /**
     * Minimum value which will be encoded. All values smaller than this will have bits set to all zeroes.
     */
    private double lowerBound;
    /**
     * Maximum value which will be encoded. All values greater than this will have bits set to all ones.
     */
    private double upperBound;
    /**
     * Minimum difference between two values when encoding and decoding.
     */
    private double step;
    
    /**
     * Constructs a <code>DoubleArrayToNaturalBinaryCodec</code> object with provided coding parameters.
     * 
     * @param bitsPerNumber number of code bits per single <code>double</code> value. Minimum value is 8, and maximum
     *            value is 32.
     * @param lowerBound minimum value to encode. All values smaller than this will have bits set to all zeroes.
     * @param upperBound maximum value to encode. All values greater than this will have bits set to all ones.
     * @throws IllegalArgumentException thrown if number of code bits is less than 8 or greater than 32.
     */
    public DoubleArrayToNaturalBinaryCodec(int bitsPerNumber, double lowerBound, double upperBound) {
        if (bitsPerNumber < 8 || bitsPerNumber > 32) {
            throw new IllegalArgumentException(
                    "Valid range for number of bits is [8, 32]. Provided value was " + bitsPerNumber + ".");
        }
        
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException(
                    "Upper bound must be greater than lower bound. Provided values were: lowerBound = " + lowerBound
                            + ", upperBound = " + upperBound + ".");
        }
        
        this.bitsPerNumber = bitsPerNumber;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        
        step = (upperBound - lowerBound) / Math.pow(2.0, bitsPerNumber);
    }
    
    @Override
    public byte[] encode(double[] values) {
        
        int numOfBytes = (int) Math.ceil((values.length * bitsPerNumber) / 8.0);
        byte[] output = new byte[numOfBytes];
        
        for (double value : values) {
            long encodedValue;
            
            if (value <= lowerBound) {
                encodedValue = 0L;
            } else if (value >= upperBound) {
                encodedValue = 0xFFFF_FFFFL;
            } else {
                encodedValue = (long) ((value - lowerBound) / step);
            }
            
            // TODO: byte filling...
        }
        
        return output;
    }
    
    @Override
    public double[] decode(byte[] bytes) {
        
        // TODO Auto-generated method stub
        return null;
    }
}
