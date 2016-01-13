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
    public byte[] encode(double[] item) {
        
        int numOfBytes = (int) Math.ceil((item.length * bitsPerNumber) / 8.0);
        byte[] output = new byte[numOfBytes];
        
        if (bitsPerNumber == 8) {
            encode8Bits(item, output);
        } else if (bitsPerNumber < 16) {
            encode9To15Bits(item, output);
        } else if (bitsPerNumber == 16) {
            encode16Bits(item, output);
        } else if (bitsPerNumber < 24) {
            encode17To23Bits(item, output);
        } else if (bitsPerNumber == 24) {
            encode24Bits(item, output);
        } else if (bitsPerNumber < 32) {
            encode25To31Bits(item, output);
        } else {
            encode32Bits(item, output);
        }
        
        return output;
    }
    
    /**
     * Encodes the given <code>double</code> value into the bits which are stored in a <code>long</code> variable.
     * 
     * @param value value to encode.
     * @return Bits of the encoded value stored in <code>long</code> variable.
     */
    private long encodeValue(double value) {
        
        long encodedValue;
        
        if (value <= lowerBound) {
            encodedValue = 0L;
        } else if (value >= upperBound) {
            encodedValue = 0xFFFF_FFFFL;
        } else {
            encodedValue = (long) ((value - lowerBound) / step);
        }
        
        return encodedValue;
    }
    
    /**
     * Used to encode and store values when each value is assigned exactly 8 bits.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode8Bits(double values[], byte[] bytes) {
        
        int i = 0;
        for (double value : values) {
            long encodedValue = encodeValue(value);
            
            bytes[i] = (byte) encodedValue;
            i++;
        }
    }
    
    private void encode9To15Bits(double[] values, byte[] bytes) {
        // TODO: add code
    }
    
    /**
     * Used to encode and store values when each value is assigned exactly 16 bits.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode16Bits(double values[], byte[] bytes) {
        
        int i = 0;
        for (double value : values) {
            long encodedValue = encodeValue(value);
            
            bytes[i] = (byte) (encodedValue >>> 8L);
            bytes[i + 1] = (byte) (encodedValue & 0xFFL);
            i += 2;
        }
    }
    
    private void encode17To23Bits(double[] values, byte[] bytes) {
        // TODO: add code
    }
    
    /**
     * Used to encode and store values when each value is assigned exactly 24 bits.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode24Bits(double values[], byte[] bytes) {
        
        int i = 0;
        for (double value : values) {
            long encodedValue = encodeValue(value);
            
            bytes[i] = (byte) (encodedValue >>> 16L);
            bytes[i + 1] = (byte) ((encodedValue >>> 8L) & 0xFFL);
            bytes[i + 2] = (byte) (encodedValue & 0xFFL);
            i += 3;
        }
    }
    
    private void encode25To31Bits(double[] values, byte[] bytes) {
        // TODO: add code
    }
    
    /**
     * Used to encode and store values when each value is assigned exactly 24 bits.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode32Bits(double values[], byte[] bytes) {
        
        int i = 0;
        for (double value : values) {
            long encodedValue = encodeValue(value);
            
            bytes[i] = (byte) (encodedValue >>> 24L);
            bytes[i + 1] = (byte) ((encodedValue >>> 16L) & 0xFFL);
            bytes[i + 2] = (byte) ((encodedValue >>> 8L) & 0xFFL);
            bytes[i + 3] = (byte) (encodedValue & 0xFFL);
            i += 4;
        }
    }
    
    @Override
    public double[] decode(byte[] bytes) {
        
        // TODO Auto-generated method stub
        return null;
    }
}
