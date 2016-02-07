// Copyright (C) Domagoj Latečki
// You may use, distribute and modify this code under the terms of the MIT license.
package hr.dlatecki.algorithms.gen_alg.operators.crossover;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;
import hr.dlatecki.algorithms.gen_alg.exceptions.TooManyCrossoverPointsException;
import hr.dlatecki.algorithms.gen_alg.operators.abstracts.AbstractCrossoverOperator;
import hr.dlatecki.algorithms.gen_alg.population.abstracts.AbstractDoubleArrayChromosome;

public class DoubleArrayPointCrossover<C extends AbstractDoubleArrayChromosome> extends AbstractCrossoverOperator<C> {
    
    private final int crossoverPoints;
    
    public DoubleArrayPointCrossover(Random rand, int crossoverPoints) {
        super(rand);
        
        if (crossoverPoints < 1) {
            throw new IllegalArgumentException("Number of crossover points cannot be less than 1");
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
        
        return null;
    }
    
    private int[] generateCrossoverPoints(int totalNumOfCrossoverPoints, int arrayLength) {
        
        int[] crossoverPoints = new int[totalNumOfCrossoverPoints];
        
        if (totalNumOfCrossoverPoints == arrayLength) {
            for (int i = 0; i < crossoverPoints.length; i++) {
                crossoverPoints[i] = i;
            }
        } else {
            generateRandomCrossoverPoints(crossoverPoints, arrayLength);
        }
        
        return crossoverPoints;
    }
    
    private void generateRandomCrossoverPoints(int[] crossoverPoints, int arrayLength) {
        
        SortedSet<Integer> points = new TreeSet<>();
        
        while (points.size() < crossoverPoints.length) {
            points.add(rand.nextInt(arrayLength));
        }
        
        int i = 0;
        for (int point : points) {
            crossoverPoints[i++] = point;
        }
    }
}
