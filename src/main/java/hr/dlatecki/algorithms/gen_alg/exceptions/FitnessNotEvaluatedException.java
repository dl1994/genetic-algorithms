package hr.dlatecki.algorithms.gen_alg.exceptions;

/**
 * Thrown to indicate that fitness value of the chromosome has not yet been evaluated.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 */
public class FitnessNotEvaluatedException extends RuntimeException {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 7406997263974322061L;
    
    /**
     * Constructs the <code>FitnessNotEvaluatedException</code> with no detail message.
     */
    public FitnessNotEvaluatedException() {
        super();
    }
    
    /**
     * Constructs the <code>FitnessNotEvaluatedException</code> with specified detail message.
     * 
     * @param message the detail message.
     */
    public FitnessNotEvaluatedException(String message) {
        super(message);
    }
}
