// Copyright (C) 2016 Domagoj Latečki
/* You may use, distribute and modify this code under the terms of the MIT license. */
package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;

/**
 * An abstract class which offers a constructor which takes a <code>Random</code> object as a parameter.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 */
abstract class AbstractOperator {
    
    /**
     * Object used to generate random numbers.
     */
    protected Random rand;
    
    /**
     * Constructs the operator using the provided <code>Random</code> object.
     * 
     * @param rand object used to generate random numbers.
     */
    public AbstractOperator(Random rand) {
        this.rand = rand;
    }
    
    /**
     * Fetches the <code>Random</code> object which is used in this object.
     * 
     * @return <code>Random</code> object which is used in this object.
     */
    public final Random getRand() {
        
        return rand;
    }
    
    /**
     * Sets the <code>Random</code> object which will be used in this object.
     * 
     * @param rand object used to generate random numbers.
     */
    public final void setRandom(Random rand) {
        
        this.rand = rand;
    }
}
