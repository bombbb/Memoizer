package memoization;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class FunctionMemoizer<T, R> {

    private final Map<T, R> cache = new ConcurrentHashMap<>();
    private final Function<T, R> function;

    public FunctionMemoizer(Function<T, R> function) {
        this.function = function;
    }

    public R compute(T x) {
        return cache.computeIfAbsent(x, this.function);
    }

}
