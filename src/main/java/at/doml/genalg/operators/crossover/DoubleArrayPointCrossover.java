package at.doml.genalg.operators.crossover;

import at.doml.genalg.exceptions.TooManyCrossoverPointsException;
import at.doml.genalg.operators.abstracts.AbstractCrossoverOperator;
import at.doml.genalg.population.abstracts.AbstractDoubleArrayChromosome;
import java.util.Iterator;
import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

public class DoubleArrayPointCrossover<C extends AbstractDoubleArrayChromosome> extends AbstractCrossoverOperator<C> {
    
    private final int crossoverPoints;
    
    public DoubleArrayPointCrossover(Random rand, int crossoverPoints) {
        super(rand);
        
        if (crossoverPoints < 1) {
            throw new IllegalArgumentException("Number of crossover points cannot be less than 1.");
        }
        
        this.crossoverPoints = crossoverPoints;
    }
    
    @Override
    protected C createChild(SortedSet<C> parents) {
        
        int numOfParents = parents.size();
        int arrayLength = parents.first().getArrayLangth();
        int totalNumOfCrossoverPoints = numOfParents * crossoverPoints;
        
        if (arrayLength < totalNumOfCrossoverPoints) {
            throw new TooManyCrossoverPointsException("Too many crossover points have been specified for array of size "
                    + arrayLength + ". With current number of parents (" + numOfParents
                    + "), maximum number of crossover points is: " + arrayLength / numOfParents + ".");
        }
        
        int[] crossoverPoints = generateCrossoverPoints(totalNumOfCrossoverPoints, arrayLength);
        double[] childArray = getChildValues(parents, crossoverPoints, arrayLength);
        
        return null;
    }
    
    private int[] generateCrossoverPoints(int totalNumOfCrossoverPoints, int arrayLength) {
        
        int[] crossoverPoints = new int[totalNumOfCrossoverPoints];
        
        if (totalNumOfCrossoverPoints == arrayLength) {
            for (int i = 0, length = crossoverPoints.length - 1; i < length; i++) {
                crossoverPoints[i] = i;
            }
        } else {
            generateRandomCrossoverPoints(crossoverPoints, arrayLength - 1);
        }
        
        return crossoverPoints;
    }
    
    private void generateRandomCrossoverPoints(int[] crossoverPoints, int maxCrossoverPoint) {
        
        SortedSet<Integer> points = new TreeSet<>();
        
        while (points.size() < crossoverPoints.length) {
            points.add(rand.nextInt(maxCrossoverPoint));
        }
        
        int i = 0;
        for (int point : points) {
            crossoverPoints[i++] = point;
        }
    }
    
    private double[] getChildValues(SortedSet<C> parents, int[] crossoverPoints, int arrayLength) {
        
        double[] childValues = new double[arrayLength];
        
        Iterator<C> parentIterator = parents.iterator();
        
        int start = 0;
        for (int i = 0; i < crossoverPoints.length; i++) {
            int end = crossoverPoints[i];
        }
        
        return childValues;
    }
}
