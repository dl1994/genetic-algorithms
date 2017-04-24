package at.dom_l.algorithms.gen_alg.exceptions;

/**
 * Thrown to indicate that an exception occurred in the genetic algorithm.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 */
public class GeneticAlgorithmException extends RuntimeException {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1417152389097202040L;
    
    /**
     * Constructs the <code>GeneticAlgorithmException</code> with no detail message.
     */
    public GeneticAlgorithmException() {
        super();
    }
    
    /**
     * Constructs the <code>GeneticAlgorithmException</code> with specified detail message.
     * 
     * @param message the detail message.
     */
    public GeneticAlgorithmException(String message) {
        super(message);
    }
    
    /**
     * Constructs the <code>GeneticAlgorithmException</code> with specified cause.
     * 
     * @param cause the exception which caused this exception.
     */
    public GeneticAlgorithmException(Throwable cause) {
        super(cause);
    }
    
    /**
     * Constructs the <code>GeneticAlgorithmException</code> with specified detail message and cause.
     * 
     * @param message the detail message.
     * @param cause the exception which caused this exception.
     */
    public GeneticAlgorithmException(String message, Throwable cause) {
        super(message, cause);
    }
}
