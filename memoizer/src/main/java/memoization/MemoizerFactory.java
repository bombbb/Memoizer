package memoization;

import java.util.function.BiFunction;
import java.util.function.Function;

public final class MemoizerFactory {

    private MemoizerFactory() {}

    public static <T, R> FunctionMemoizer<T, R> createMemoizerFor(Function<T, R> function) {
        return new FunctionMemoizer<>(function);
    }

    public static <T, U, R> BiFunctionMemoizer<T, U, R> createMemoizerFor(BiFunction<T, U, R> biFunction) {
        return new BiFunctionMemoizer<>(biFunction);
    }

}
