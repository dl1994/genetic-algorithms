### 0.2.2
Added getter and setter for Random object in AbstractOperator. Creates tests for all abstract classes. Mutation and
selection operators now throw exceptions if illegal argument is provided in constructor or setter.
AbstractSeectionOperator now specifies a single abstract method that needs to be overridden in order to perform
selection. AbstractPopulationGenerator now throws exception if requested population size is less than 0.

### 0.2.1

Added tests for FitnessNotEvaluatedException. Updated documentation for all classes and packages, they now include
@since tags to indicate in which JDK version the specific class or package was added.

### 0.2.0

Added abstract classes for operators and population. Created package for exceptions and added exception for
non-evaluated fitness. Added package-info for all new packages.

### 0.1.1

Added package-info for all relevant packages.

### 0.1.0

Defined interfaces for operators and population.
