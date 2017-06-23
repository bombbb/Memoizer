package memoization;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import java.util.function.Function;

public class BiFunctionMemoizer<T, U, R> {

    private final Map<Tuple<T, U>, R> cache = new ConcurrentHashMap<>();
    private final BiFunction<T, U, R> biFunction;

    public BiFunctionMemoizer(BiFunction<T, U, R> biFunction) {
        this.biFunction = biFunction;
    }

    public R compute(T x, U y) {
        Tuple<T, U> bothArgs = new Tuple<>(x, y);
        Function<Tuple<T, U>, R> function = convertBiFunctionToFunctionWithTuple();
        return cache.computeIfAbsent(bothArgs, function);
    }

    private Function<Tuple<T, U>, R> convertBiFunctionToFunctionWithTuple() {
        return tuple -> biFunction.apply(tuple.getX(), tuple.getY());
    }

    private static class Tuple<T, U> {
        private final T x;
        private final U y;

        private Tuple(T x, U y) {
            this.x = x;
            this.y = y;
        }

        T getX() {
            return x;
        }

        U getY() {
            return y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            Tuple<?, ?> tuple = (Tuple<?, ?>) o;
            return Objects.equals(x, tuple.x) &&
                    Objects.equals(y, tuple.y);
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
