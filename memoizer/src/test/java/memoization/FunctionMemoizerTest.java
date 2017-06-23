package memoization;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

class FunctionMemoizerTest {

    @InjectMocks
    private FunctionMemoizer<String, Integer> functionMemoizer;

    @Spy
    private Function<String, Integer> stringToIntParser;

    @BeforeEach
    void setUp() {
        stringToIntParser = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };
        initMocks(this);
    }

    @Test
    void whenComputeWithStringValueThenParseToInteger() {
        assertThat(functionMemoizer.compute("10"))
                .isEqualTo(10);
    }

    @Test
    void whenCallComputeTwiceWithTheSameArgumentsThenCallFunctionOnce() {
        functionMemoizer.compute("5");
        functionMemoizer.compute("5");
        verify(stringToIntParser, times(1)).apply(anyString());
    }

    @Test
    void whenCallComputeTwiceWithDifferentArgumentsThenCallFunctionTwice() {
        functionMemoizer.compute("3");
        functionMemoizer.compute("4");
        verify(stringToIntParser, times(2)).apply(anyString());
    }
}
