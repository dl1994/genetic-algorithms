package at.doml.genalg.exceptions;

/**
 * Thrown to indicate that there are too few parent chromosomes provided.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see GeneticAlgorithmException
 */
public class TooFewParentsException extends GeneticAlgorithmException {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 258653111631295365L;
    
    /**
     * Constructs the <code>TooFewParentsException</code> with no detail message.
     */
    public TooFewParentsException() {
        super();
    }
    
    /**
     * Constructs the <code>TooFewParentsException</code> with specified detail message.
     * 
     * @param message the detail message.
     */
    public TooFewParentsException(String message) {
        super(message);
    }
}
