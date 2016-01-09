package hr.dlatecki.algorithms.gen_alg.population.chromosomes;

import java.util.Arrays;
import hr.dlatecki.algorithms.gen_alg.population.abstracts.AbstractChromosome;

/**
 * An implementation of the chromosome which is represented by an array of double values.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @since 1.8
 * @see AbstractChromosome
 */
public class DoubleArrayChromosome extends AbstractChromosome {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 8412545818730407936L;
    /**
     * Array of values that represent this chromosome.
     */
    protected double[] values;
    
    /**
     * This constructor is used only in derived classes so that they may have a constructor without parameters.
     */
    protected DoubleArrayChromosome() {}
    
    /**
     * Constructs a <code>DoubleArrayChromosome</code> using the provided array of values. Values are copied in order to
     * insure that they cannot be altered outside of this chromosome.
     * 
     * @param values values which will be assigned to the chromosome.
     */
    public DoubleArrayChromosome(double[] values) {
        this.values = Arrays.copyOf(values, values.length);
    }
    
    /**
     * Creates an array which will contain the values stored in this object.
     * 
     * @return Array which contains values stored in this object.
     */
    public double[] getValues() {
        
        return Arrays.copyOf(values, values.length);
    }
    
    /**
     * Copies the provided values into an array stored in this object.
     * 
     * @param values the new values which will be stored in this object.
     */
    public void setValues(double[] values) {
        
        if (values.length == this.values.length) {
            System.arraycopy(values, 0, this.values, 0, this.values.length);
        } else {
            this.values = Arrays.copyOf(values, values.length);
        }
    }
    
    @Override
    protected void deepCopyTo(AbstractChromosome target) throws CloneNotSupportedException {}
}
