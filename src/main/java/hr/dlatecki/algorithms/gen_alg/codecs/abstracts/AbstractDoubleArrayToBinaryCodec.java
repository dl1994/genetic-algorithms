// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.codecs.abstracts;

import hr.dlatecki.algorithms.gen_alg.codecs.interfaces.IByteArrayCodec;

public abstract class AbstractDoubleArrayToBinaryCodec implements IByteArrayCodec<double[]> {
    
    /**
     * Mask for the lowers byte.
     */
    private static final long BYTE_MASK = 0xFFL;
    /**
     * Number of code bits per single <code>double</code> value.
     */
    protected int bitsPerValue;
    /**
     * Minimum value which will be encoded. All values smaller than this will have bits set to all zeroes.
     */
    protected double lowerBound;
    /**
     * Maximum value which will be encoded. All values greater than this will have bits set to all ones.
     */
    protected double upperBound;
    /**
     * Minimum difference between two values when encoding and decoding.
     */
    protected double step;
    
    /**
     * Constructs a <code>AbstractDoubleArrayToBinaryCodec</code> object with provided coding parameters.
     * 
     * @param bitsPerNumber number of code bits per single <code>double</code> value. Minimum value is 8, and maximum
     *            value is 32.
     * @param lowerBound minimum value to encode. All values smaller than this will have bits set to all zeroes.
     * @param upperBound maximum value to encode. All values greater than this will have bits set to all ones.
     * @throws IllegalArgumentException thrown if number of code bits is less than 8 or greater than 32.
     */
    public AbstractDoubleArrayToBinaryCodec(int bitsPerNumber, double lowerBound, double upperBound) {
        if (bitsPerNumber < 8 || bitsPerNumber > 32) {
            throw new IllegalArgumentException(
                    "Valid range for number of bits is [8, 32]. Provided value was " + bitsPerNumber + ".");
        }
        
        if (lowerBound >= upperBound) {
            throw new IllegalArgumentException(
                    "Upper bound must be greater than lower bound. Provided values were: lowerBound = " + lowerBound
                            + ", upperBound = " + upperBound + ".");
        }
        
        this.bitsPerValue = bitsPerNumber;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
        
        step = (upperBound - lowerBound) / Math.pow(2.0, bitsPerNumber);
    }
    
    @Override
    public byte[] encode(double[] item) {
        
        int numOfBytes = (int) Math.ceil((item.length * bitsPerValue) / 8.0);
        byte[] output = new byte[numOfBytes];
        
        if (bitsPerValue == 8) {
            encode8Bits(item, output);
        } else if (bitsPerValue < 16) {
            encode9To15Bits(item, output);
        } else if (bitsPerValue == 16) {
            encode16Bits(item, output);
        } else if (bitsPerValue < 24) {
            encode17To23Bits(item, output);
        } else if (bitsPerValue == 24) {
            encode24Bits(item, output);
        } else if (bitsPerValue < 32) {
            encode25To31Bits(item, output);
        } else {
            encode32Bits(item, output);
        }
        
        return output;
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
    
    /**
     * Used to encode and store values when each value is between 9 and 15 bits, inclusive.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode9To15Bits(double[] values, byte[] bytes) {
        
        int i = 0;
        int bitPosition = 0;
        for (double value : values) {
            int rot = bitsPerValue + bitPosition - 8;
            long encodedValue = encodeValue(value);
            
            bitPosition = rot;
            bytes[i] |= (byte) (encodedValue >>> rot);
            rot = 8 - rot;
            bytes[i + 1] |= (byte) ((encodedValue << rot) & BYTE_MASK);
            i++;
        }
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
            bytes[i + 1] = (byte) (encodedValue & BYTE_MASK);
            i += 2;
        }
    }
    
    /**
     * Used to encode and store values when each value is between 17 and 23 bits, inclusive.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode17To23Bits(double[] values, byte[] bytes) {
        
        int i = 0;
        int bitPosition = 0;
        for (double value : values) {
            int rot = bitsPerValue + bitPosition - 8;
            long encodedValue = encodeValue(value);
            
            bytes[i] |= (byte) (encodedValue >>> rot);
            rot = bitsPerValue + bitPosition - 16;
            bitPosition = rot;
            bytes[i + 1] |= (byte) ((encodedValue >>> rot) & BYTE_MASK);
            rot = 8 - rot;
            bytes[i + 2] |= (byte) ((encodedValue << rot) & BYTE_MASK);
            i += 2;
        }
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
            bytes[i + 1] = (byte) ((encodedValue >>> 8L) & BYTE_MASK);
            bytes[i + 2] = (byte) (encodedValue & BYTE_MASK);
            i += 3;
        }
    }
    
    /**
     * Used to encode and store values when each value is between 25 and 31 bits, inclusive.
     * 
     * @param values values to encode and store.
     * @param bytes array in which encoded bits will be written.
     */
    private void encode25To31Bits(double[] values, byte[] bytes) {
        
        int i = 0;
        int bitPosition = 0;
        for (double value : values) {
            int rot = bitsPerValue + bitPosition - 8;
            long encodedValue = encodeValue(value);
            
            bytes[i] |= (byte) (encodedValue >>> rot);
            rot = bitsPerValue + bitPosition - 16;
            bytes[i + 1] |= (byte) ((encodedValue >>> rot) & BYTE_MASK);
            rot = bitsPerValue + bitPosition - 24;
            bitPosition = rot;
            bytes[i + 2] |= (byte) ((encodedValue >>> rot) & BYTE_MASK);
            rot = 8 - rot;
            bytes[i + 3] |= (byte) ((encodedValue << rot) & BYTE_MASK);
            i += 3;
        }
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
            bytes[i + 1] = (byte) ((encodedValue >>> 16L) & BYTE_MASK);
            bytes[i + 2] = (byte) ((encodedValue >>> 8L) & BYTE_MASK);
            bytes[i + 3] = (byte) (encodedValue & BYTE_MASK);
            i += 4;
        }
    }
    
    @Override
    public double[] decode(byte[] bytes) {
        
        int numOfValues = (int) Math.floor((bytes.length * 8.0) / bitsPerValue);
        double[] output = new double[numOfValues];
        
        if (bitsPerValue == 8) {
            decode8Bits(output, bytes);
        } else if (bitsPerValue < 16) {
            decode9To15Bits(output, bytes);
        } else if (bitsPerValue == 16) {
            decode16Bits(output, bytes);
        } else if (bitsPerValue < 24) {
            decode17To23Bits(output, bytes);
        } else if (bitsPerValue == 24) {
            decode24Bits(output, bytes);
        } else if (bitsPerValue < 32) {
            decode25To31Bits(output, bytes);
        } else {
            decode32Bits(output, bytes);
        }
        
        return output;
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is assigned exactly 8 bits.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode8Bits(double[] values, byte[] bytes) {
        
        int i = 0;
        for (byte bits : bytes) {
            values[i] = decodeValue(bits);
            i++;
        }
    }
    
    private void decode9To15Bits(double[] values, byte[] bytes) {
    
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is assigned exactly 16 bits.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode16Bits(double[] values, byte[] bytes) {
        
        int i = 0;
        int bitCounter = 0;
        for (byte bits : bytes) {
            long valueBits = 0L;
            
            if (bitCounter == 0) {
                valueBits = bits << 8;
                bitCounter++;
            } else {
                valueBits |= bits;
                bitCounter = 0;
            }
            
            values[i] = decodeValue(valueBits);
            i++;
        }
    }
    
    private void decode17To23Bits(double[] values, byte[] bytes) {
    
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is assigned exactly 24 bits.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode24Bits(double[] values, byte[] bytes) {
        
        int i = 0;
        int bitCounter = 0;
        for (byte bits : bytes) {
            long valueBits = 0L;
            
            if (bitCounter == 0) {
                valueBits = bits << 16;
                bitCounter++;
            } else if (bitCounter == 1) {
                valueBits |= bits << 8;
                bitCounter++;
            } else {
                valueBits |= bits;
                bitCounter = 0;
            }
            
            values[i] = decodeValue(valueBits);
            i++;
        }
    }
    
    private void decode25To31Bits(double[] values, byte[] bytes) {
    
    }
    
    /**
     * Used to decode stored <code>byte</code>s into values when each value is assigned exactly 32 bits.
     * 
     * @param values in which decoded values will be stored.
     * @param bytes array to decode.
     */
    private void decode32Bits(double[] values, byte[] bytes) {
        
        int i = 0;
        int bitCounter = 0;
        for (byte bits : bytes) {
            long valueBits = 0L;
            
            if (bitCounter == 0) {
                valueBits = bits << 24;
                bitCounter++;
            } else if (bitCounter == 1) {
                valueBits |= bits << 16;
                bitCounter++;
            } else if (bitCounter == 2) {
                valueBits |= bits << 8;
                bitCounter++;
            } else {
                valueBits |= bits;
                bitCounter = 0;
            }
            
            values[i] = decodeValue(valueBits);
            i++;
        }
    }
    
    /**
     * Encodes the given <code>double</code> value into the bits which are stored in a <code>long</code> variable.
     * 
     * @param value value to encode.
     * @return Bits of the encoded value stored in <code>long</code> variable.
     */
    protected abstract long encodeValue(double value);
    
    /**
     * Decodes the given <code>long</code> value into the <code>double</code> value.
     * 
     * @param value value to decode.
     * @return <code>double</code> value which was decoded from the <code>long</code> value.
     */
    protected abstract double decodeValue(long value);
}
