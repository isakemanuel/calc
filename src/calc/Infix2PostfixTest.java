package calc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class Infix2PostfixTest {
    private static final Calculator calc = new Calculator();
    private List<String> fInput;
    private List<String> fExpected;

    public Infix2PostfixTest(String input, String expected) {
        List<String> expectedList = Arrays.asList(expected.split(" "));
        List<String> inputList = Arrays.asList(input.split(" "));

        this.fInput = inputList;
        this.fExpected = expectedList;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1 + 10", "1 10 +"},
                {"1 + 2 + 3", "1 2 + 3 +"},
                {"1 + 2 - 3", "1 2 + 3 -"},
                {"3 - 2 - 1", "3 2 - 1 -"},
                {"1 + 2 * 3", "1 2 3 * +"},
                {"1 / 2 + 3", "1 2 / 3 +"},
                {"20 / 4 / 2", "20 4 / 2 /"},
                {"4 ^ 3 ^ 2", "4 3 2 ^ ^"},
                {"4 ^ 3 * 2", "4 3 ^ 2 *"},
                {"( 1 + 2 ) * 3", "1 2 + 3 *"},
                {"2 ^ ( 1 + 1 )", "2 1 1 + ^"},
        });

    }

    @Test
    public void test() {
        assertEquals(fExpected, calc.infix2Postfix(fInput));
    }
}

