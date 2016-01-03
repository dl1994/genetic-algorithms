package hr.dlatecki.algorithms.gen_alg.operators.abstracts;

import java.util.Random;

/**
 * An abstract class which offers a constructor which takes a <code>Random</code> object as a parameter.
 * 
 * @author Domagoj Latečki
 * @version 1.0
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
}
