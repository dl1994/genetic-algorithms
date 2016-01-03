package hr.dlatecki.algorithms.gen_alg.population.abstracts;

import java.util.Random;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IChromosome;
import hr.dlatecki.algorithms.gen_alg.population.interfaces.IPopulationGenerator;

/**
 * An abstract class which for population generator. It is recommended that all implementations of population generator
 * extend this class instead of directly implementing <code>IPopulationGenerator</code> interface.
 * 
 * @author Domagoj Latečki
 * @version 1.0
 * @param <C> Type of chromosome which will be used in the population generator.
 * @see IChromosome
 * @see IPopulationGenerator
 */
public abstract class AbstractPopulationGenerator<C extends IChromosome> implements IPopulationGenerator<C> {
    
    /**
     * Object used to generate random numbers.
     */
    protected Random rand;
    
    /**
     * Constructs the population generator using the provided <code>Random</code> object.
     * 
     * @param rand object used to generate random numbers.
     */
    public AbstractPopulationGenerator(Random rand) {
        this.rand = rand;
    }
}
