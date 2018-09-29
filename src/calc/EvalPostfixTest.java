package calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class EvalPostfixTest {
    private static final Calculator calc = new Calculator();
    private List<String> fInput;
    private double fExpected;

    public EvalPostfixTest(String input, double expected) {
        //String[] tokenArray = input;
        //List<String> tokenList = new ArrayList<>(Arrays.asList(tokenArray));
        List<String> inputList = Arrays.asList(input.split(" "));

        this.fInput = inputList;
        this.fExpected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1 2 +", 3.0},
                {"3 2 +", 5.0},
                {"5 2 -", 3.0},
                {"2 2 3 + -", -3.0},
                {"15 7 1 1 + - / 3 * 2 1 1 + + -", 5.0},
        });
    }

    @Test
    public void test() {
        assertEquals(fExpected, calc.evalPostfix(fInput), 0.01);
    }
}

