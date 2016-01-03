package hr.dlatecki.algorithms.gen_alg.population.abstracts;

import hr.dlatecki.algorithms.gen_alg.exceptions.FitnessNotEvaluatedException;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IFitnessTest;

/**
 * An abstract class which represents a chromosome. Chromosome is a population unit used in genetic algorithms from
 * {@link hr.dlatecki.algorithms.gen_alg.impl} package. Each chromosome has a fitness value. The fitness value is set by
 * fitness tests (see {@link IFitnessTest} for more details). It is highly recommended that any chromosome which is to
 * be used in the algorithms from previously specified package extends this class instead of directly implementing the
 * <code>IChromosome</code> interface.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @see IChromosome
 */
public abstract class AbstractChromosome implements IChromosome {
    
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 3164821181693556348L;
    /**
     * Fitness value of this chromosome.
     */
    private double fitness;
    /**
     * Flag which indicates if the fitness of this chromosome was evaluated.
     */
    private boolean fitnessEvaluated;
    
    public int compareTo(IChromosome other) {
        
        if (other == null) {
            return 1;
        }
        
        if (this == other) {
            return 0;
        }
        
        return fitness > other.getFitness() ? 1 : -1;
    }
    
    public double getFitness() throws FitnessNotEvaluatedException {
        
        if (!fitnessEvaluated) {
            throw new FitnessNotEvaluatedException();
        }
        
        return fitness;
    }
    
    public void setFitness(double fitness) {
        
        this.fitness = fitness;
        
        fitnessEvaluated = true;
    }
    
    @Override
    public IChromosome clone() {
        
        try {
            AbstractChromosome copy = (AbstractChromosome) super.clone();
            
            deepCopyTo(copy);
            
            return copy;
        } catch (CloneNotSupportedException ignorable) {
            // This exception will never be thrown by the Object superclass. This is insured by IChromosome interface,
            // which extends the Cloneable interface.
            throw new RuntimeException("It's not a bug. It's a feature!");
        }
    }
    
    /**
     * Performs a deep copy of this chromosome's fields into the target chromosome's fields. Deep copy should only be
     * performed on non-primitive mutable fields. If there are no such fields, this method can be left empty.
     * 
     * @param target target chromosome in which non-primitive mutable fields of this object will be deep copied.
     */
    protected abstract void deepCopyTo(IChromosome target);
}
