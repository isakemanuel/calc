package calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class EvalTest {
    private static final Calculator calc = new Calculator();
    private String fInput;
    private double fExpected;

    public EvalTest(String input, double expected) {
        this.fInput = input;
        this.fExpected = expected;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{

                // A value
                {"123", 123},

                // Basic operations
                {"1 + 10", 11},
                {"1 + 0", 1},
                {"1 - 10", -9}, // Input may not be negative but output may
                {"10 - 1", 9},
                {"60 * 10", 600},
                {"60 * 0", 0},
                {"3 / 2", 1.5}, // See exception for div by zero
                {"1 / 2", 0.5},
                {"2 ^ 4 ", 16},
                {"2 ^ 0 ", 1},

                // Associativity
                {"10 - 5 - 2", 3}, // (10-5)-2
                {"20 / 2 / 2", 5}, // (20/2)/2
                {"4 ^ 2 ^ 2", 256}, // 4^(2^2)

                // Precedence
                {"3 * 10 + 2", 32},
                {"3 + 10 * 2", 23},
                {"30 / 3 + 2", 12},
                {"1 + 30 / 3", 11},
                {"3 * 2 ^ 2", 12},
                {"3 ^ 2 * 2", 18},

                // Parentheses
                {"10 - (5 - 2)", 7},
                {"20 / (10 / 2)", 4},
                {"(3 ^ 2) ^ 2", 81},
                {"3 * (10 + 2)", 36},
                {"30 / (3 + 2)", 6},
                {"(3 + 2) ^ 2", 25},
                {" 2 ^ (1 + 1)", 4},
                {" ((((1 + 1))) * 2)", 4},

                // Mix priority and right and left associativity
                {" 1 ^ 1 ^ 1 ^ 1  - 1", 0},
                {" 4 - 2 - 1 ^ 2 ", 1},
        });
    }

    @Test
    public void test() {
        assertEquals(fExpected, calc.eval(fInput), 0.01);
    }
}

