package memoization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.function.BiFunction;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class BiFunctionMemoizerTest {

    @InjectMocks
    private BiFunctionMemoizer<Integer, Integer, Integer> biFunctionMemoizer;

    @Spy
    private BiFunction<Integer, Integer, Integer> minOfTwoIntegers;

    @BeforeEach
    void setUp() {
        minOfTwoIntegers = new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer i1, Integer i2) {
                return Math.min(i1, i2);
            }
        };
        initMocks(this);
    }

    @Test
    void whenComputeWithTwoIntegersReturnSmallerValue() {
        assertThat(biFunctionMemoizer.compute(10, 5))
                .isEqualTo(5);
    }

    @Test
    void whenCallComputeTwiceWithTheSameArgumentsThenCallBiFunctionOnce() {
        biFunctionMemoizer.compute(1,2);
        biFunctionMemoizer.compute(1,2);
        verify(minOfTwoIntegers, times(1)).apply(anyInt(), anyInt());
    }

    @Test
    void whenCallComputeTwiceWithDifferentArgumentsThenCallBiFunctionTwice() {
        biFunctionMemoizer.compute(1,2);
        biFunctionMemoizer.compute(1,3);
        verify(minOfTwoIntegers, times(2)).apply(anyInt(), anyInt());
    }
}
