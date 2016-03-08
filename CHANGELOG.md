# Development history

### v0.6.4
AbstractDoubleArrayChromosome and AbstractByteArrayChromosome now have a method for fetching a single value stored in
the internal array. Additionally, they now also have a forEach(Consumer<INTERNAL\_ARRAY\_ELEMENT\_TYPE>) method which
iterates every element of the internal array and performs the provided action.

### v0.6.3
Added GeneticAlgorithmException, all genetic algorithm exceptions are now derived from this exception.

### v0.6.2
Added TooFewParentsException and TooManyCrossoverPointsException. AbstractCrossoverOperator now has default behaviour
for doCrossover(SortedSet<C>) method and defines an abstract method which must be overridden in derived classes to
generate a child chromosome. Added methods for fetching array length in AbstractDoubleArrayChromosome and
AbstractByteArrayChromosome. Implemented DoubleArrayPointCrossover.

### v0.6.1
Removed annotations package and all its contents. Added MIN\_SELECTION\_SIZE constant in ISelection operator interface.
All chromosome implementations are now marked as final.

### v0.6.0
Added ArithmeticMeanCrossover operator.

### v0.5.3
Defined an annotation which indicates that a method should be always overridden. There is no annotation processor yet.

### v0.5.2
IChromosome interface now defines an additional method which is used to create an empty copy of the chromosome.

### v0.5.1
Added BitFlipMutation.

### v0.5.0
Added several mutation operators for arrays of doubles. Change in the mutation interface, mutationChance is now
mutationIntensity.

### v0.4.3
Minor changes in ByteArrayWrapperChromosome and DoubleArrayWrapperChromosome, added tests for both wrapper chromosomes.

### v0.4.2
Several changes in ByteArrayWrapperChromosome and DoubleArrayWrapperChromosome, there are no more public constructors.
Object can now only be created via static methods. Codec interfaces now extend Serializable interface in order to make
all codecs serializable. Added answer to the Question of Life, the Universe and Everything.

### v0.4.1
Added tests for DoubleArrayToNaturalBinaryCodec and DoubleArrayToGrayBinaryCodec, minor changes in
AbstractDoubleArrayToBinaryCodec. Test for AbstractDoubleArrayToBinaryCodec now generates random test array of
predefined size.

### v0.4.0
Redone encoding and decoding methods in AbstractDoubleArrayToBinaryCodec. Both methods are now fully tested and work
as expected. AbstractDoubleArrayToBinaryCodec is not fully tested.

### v0.3.9
AbstractDoubleArrayToBinaryCodec is now finished, but it is not tested yet.

### v0.3.8
Finished encoding in AbstractDoubleArrayToBinaryCodec. Added several missing package-info files.

### v0.3.7
Added new abstract class AbstractDoubleArrayToBinaryCodec which implements encoding and decoding methods and defines
abstract methods how to encode and decode a single double value into bytes. Moved all relevant code from
DoubleArrayToNaturalBinaryCodec to the new abstract class. DoubleArrayToNaturalBinaryCodec is now fully implemented,
however some methods are still missing in AbstractDoubleArrayToBinaryCodec. Added skeleton for
DoubleArrayToGrayBinaryCodec in order have link to it in documentation.

### v0.3.6
Created DoubleArrayToNaturalBinaryCodec, but it is not finished yet. It currently only encodes values if 8 bits per
value are specified.

### v0.3.5
Added package-info for package which contains operators. Added tests for DoubleArrayChromosome and ByteArrayChromosome.

### v0.3.4
All classes and interface now have documented version 1.0. The versions won't change until v1.0.0. Created
implementations of binary chromosomes: ByteArrayChromosome and ByteArrayWrapperChromosome. MutableContainer in
AbstractChromosomeTest is no longer serializable, it is instead marked as transient property. Some tests are
restructured slightly.

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
