### v0.3.4
All classes and interface now have documented version 1.0. The versions won't change until v1.0.0. Created
implementations of binary chromosomes: ByteArrayChromosome and ByteArrayWrapperChromosome.

### v0.3.3
Added test for serialization in AbstractChromosomeTest. Created class for various testing utilities. Removed package
hr.dlatecki.algorithms.gen\_alg.impl. Algorithms will be in hr.dlatecki.algorithms.gen\_alg package instead. Interfaces
for codecs now have their own sub-package. Chromosome implementation now have their own sub-package.

### v0.3.2
Altered DoubleArrayWrapperChromosome, constructor which takes item as one of the arguments is now private. The
chromosome which has an item as initial value can now be created using appropriate static methods.

### v0.3.1
Created DoubleArrayChromosome. DoubleArrayWrapperChromosome now extends DoubleArrayChromosome.

### v0.3.0
Created DoubleArrayWrapperChromosome and added an interface which specifies a codec for DoubleArrayWrapperChromosome.

### v0.2.4
All methods that are not supposed to be overridden in abstract classes are not marked as final.

### v0.2.3
IPopulationGenerator now specifies return type of generatePopulation(int) to Collection instead of SortedSet.
Both IPopulationGenerator and AbstractPopulationGenerator now have new version to indicate this change. Methods
equals(Object) and hashCode() in AbstractChromosome are now marked as final in order to insure they cannot be
overridden.

### v0.2.2
Added getter and setter for Random object in AbstractOperator. Created tests for all abstract classes. Mutation and
selection operators now throw exceptions if illegal argument is provided in constructor or setter.
AbstractSeectionOperator now specifies a single abstract method that needs to be overridden in order to perform
selection. AbstractPopulationGenerator now throws exception if requested population size is less than 0. Corrected
comparison method in AbstractChromosome.

### v0.2.1

Added tests for FitnessNotEvaluatedException. Updated documentation for all classes and packages, they now include
@since tags to indicate in which JDK version the specific class or package was added.

### v0.2.0

Added abstract classes for operators and population. Created package for exceptions and added exception for
non-evaluated fitness. Added package-info for all new packages.

### v0.1.1

Added package-info for all relevant packages.

### v0.1.0

Defined interfaces for operators and population.
