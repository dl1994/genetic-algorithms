// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.exceptions;

/**
 * Thrown to indicate that parents provided for crossover are incompatible.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 */
public class IncompatibleParentsException extends RuntimeException {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1245042011652944730L;
    
    /**
     * Constructs the <code>IncompatibleParentsException</code> with no detail message.
     */
    public IncompatibleParentsException() {
        super();
    }
    
    /**
     * Constructs the <code>IncompatibleParentsException</code> with specified detail message.
     * 
     * @param message the detail message.
     */
    public IncompatibleParentsException(String message) {
        super(message);
    }
}
