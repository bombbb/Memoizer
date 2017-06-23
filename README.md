# Memoizer
In computing, memoization or memoisation is an optimization technique used primarily to speed up computer programs by storing the results of expensive function calls and returning the cached result when the same inputs occur again.
[Source: wikipedia](https://en.wikipedia.org/wiki/Memoization)

Here are examples of Memoizer for java.util.function.Function and java.util.function.BiFunction classes since there is no implementation of memoization in Java API.
Mentioned technique should be use only with pure functions which computation is more expensive than extraction results from cache.

Usage of FunctionMemoizer:
```
//using factory
MemoizationFactory.createMemoizerFor(Math::sqrt).compute(64.0);

//using contructor
FunctionMemoizer<Integer, Integer> absFunction = new FunctionMemoizer<>(Math::abs);
absFunction.compute(-5);
```

Usage of BiFunctionMemoizer:
```
//using factory
MemoizationFactory.createMemoizerFor((BiFunction<Integer, Integer, Integer>) Math::max).compute(1,2);

//using constructor
BiFunctionMemoizer<Integer, Integer, Integer> maxBiFunction = new BiFunctionMemoizer<>(Math::max);
maxBiFunction.compute(1, 5);
```
