package at.doml.genalg.population.chromosomes;

import at.doml.genalg.population.abstracts.AbstractChromosome;
import at.doml.genalg.population.abstracts.AbstractDoubleArrayChromosome;

/**
 * An implementation of <code>AbstractDoubleArrayChromosome</code>.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractDoubleArrayChromosome
 */
public final class DoubleArrayChromosome extends AbstractDoubleArrayChromosome {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -2583519091375253441L;
    
    /**
     * An empty constructor used in {@link #newLikeThis()} method.
     */
    protected DoubleArrayChromosome() {}
    
    /**
     * Constructs a <code>DoubleArrayChromosome</code> using the provided array of values. Values are copied in order to
     * insure that they cannot be altered outside of this chromosome.
     * 
     * @param values values which will be assigned to the chromosome.
     */
    public DoubleArrayChromosome(double[] values) {
        super(values);
    }
    
    @Override
    protected void deepCopyTo(AbstractChromosome target) throws CloneNotSupportedException {}
    
    @Override
    public DoubleArrayChromosome newLikeThis() {
        
        return new DoubleArrayChromosome();
    }
}
