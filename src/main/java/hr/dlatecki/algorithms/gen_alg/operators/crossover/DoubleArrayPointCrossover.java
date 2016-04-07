/* 
 * The MIT License (MIT)
 * 
 * Copyright © 2016 Domagoj Latečki
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package hr.dlatecki.algorithms.gen_alg.operators.crossover;

import java.util.Iterator;
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
