// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.exceptions;

/**
 * Thrown to indicate that there have been too many crossover points specified in the point crossover operator.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 */
public class TooManyCrossoverPointsException extends RuntimeException {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -5945284115447011274L;
    
    /**
     * Constructs the <code>TooManyCrossoverPointsException</code> with no detail message.
     */
    public TooManyCrossoverPointsException() {
        super();
    }
    
    /**
     * Constructs the <code>TooManyCrossoverPointsException</code> with specified detail message.
     * 
     * @param message the detail message.
     */
    public TooManyCrossoverPointsException(String message) {
        super(message);
    }
}
