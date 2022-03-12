package xinwendewen.ui.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErrorRoundingAccumulatorTest {
    RoundingErrorAccumulator accumulator;

    @BeforeEach
    void setUp() {
        accumulator = new RoundingErrorAccumulator();
    }

    @Test
    void test_accumulated_error_greater_than_one() {
        Assertions.assertEquals(1, accumulator.round(1.4));
        Assertions.assertEquals(0, accumulator.compensate());
        Assertions.assertEquals(2, accumulator.round(2.4));
        Assertions.assertEquals(0, accumulator.compensate());
        Assertions.assertEquals(1, accumulator.round(1.4));
        Assertions.assertEquals(1, accumulator.compensate());
        Assertions.assertEquals(0, accumulator.compensate());
        Assertions.assertEquals(0.2, accumulator.getAndReset(), 0.001);
        Assertions.assertEquals(0, accumulator.compensate());
    }

    @Test
    void test_accumulated_error_equals_to_negative_one() {
        Assertions.assertEquals(2, accumulator.round(1.5));
        Assertions.assertEquals(0, accumulator.compensate());
        Assertions.assertEquals(2, accumulator.round(1.5));
        Assertions.assertEquals(-1, accumulator.compensate());
        Assertions.assertEquals(0, accumulator.currentError(), 0.001);
    }
}
