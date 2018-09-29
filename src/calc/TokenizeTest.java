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
public class TokenizeTest {
    private static final Calculator calc = new Calculator();
    private String fInput;
    private List<String> fExpected;

    public TokenizeTest(String input, String expected) {
        List<String> expectedList;
        expectedList = Arrays.asList(expected.split(" "));

        this.fInput = input;
        this.fExpected = expectedList;
    }

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"1 + 10", "1 + 10"},
                {"1+ 10", "1 + 10"},
                {"1 +10", "1 + 10"},
                {"1+10", "1 + 10"},
                {"(1+10) ", "( 1 + 10 )"},
                {"2 *( 1+10) ", "2 * ( 1 + 10 )"},
                {"(1 +2) /2 *( 1+10) ", "( 1 + 2 ) / 2 * ( 1 + 10 )"}
        });
    }

    @Test
    public void test() {
        assertEquals(fExpected, calc.tokenize(fInput));
    }
}

