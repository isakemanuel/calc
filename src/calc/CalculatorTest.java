package calc;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class CalculatorTest {


    Calculator calc = new Calculator();

    @Test
    public void evalPostfixSimple() {
        String[] tokenArray = {"2", "2", "3", "+", "-"};
        List<String> tokenList = new ArrayList<>(Arrays.asList(tokenArray));
        Assert.assertEquals(-3.0, calc.evalPostfix(tokenList), 0.0001);
    }

    @Test
    public void evalPostfixSimple2() {
        String[] tokenArray = {"2", "2", "+"};
        List<String> tokenList = new ArrayList<>(Arrays.asList(tokenArray));
        Assert.assertEquals(4.0, calc.evalPostfix(tokenList), 0.0001);
    }

    @Test
    public void evalPostfixWiki() {
        String[] tokenArray = {"15", "7", "1", "1", "+", "-", "/", "3", "*", "2", "1", "1", "+", "+", "-"};
        List<String> tokenList = new ArrayList<>(Arrays.asList(tokenArray));
        Assert.assertEquals(5.0, calc.evalPostfix(tokenList), 0.0001);
    }


}