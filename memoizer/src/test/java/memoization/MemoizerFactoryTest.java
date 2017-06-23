package memoization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.function.BiFunction;
import java.util.function.Function;

import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;


class MemoizerFactoryTest {

    @Spy
    private Function<Integer, Integer> function;

    @Spy
    private BiFunction<Integer, Integer, Integer> biFunction;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void shouldCreateMemoizerWithGivenFunction() {
        FunctionMemoizer<Integer, Integer> functionMemoizer = MemoizerFactory.createMemoizerFor(function);
        functionMemoizer.compute(1);
        verify(function).apply(1);
    }

    @Test
    void shouldCreateMemoizerWithGivenBiFunction() {
        BiFunctionMemoizer<Integer, Integer, Integer> biFunctionMemoizer
                = MemoizerFactory.createMemoizerFor(biFunction);
        biFunctionMemoizer.compute(1, 1);
        verify(biFunction).apply(1, 1);
    }
}
