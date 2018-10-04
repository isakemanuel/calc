package calc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

import static java.lang.Double.NaN;
import static java.lang.Math.pow;


/*
 *   A calculator for rather simple arithmetic expressions
 *
 *   This is not the program, it's a class declaration (with methods) in it's
 *   own file (which must be named Calculator.java)
 *
 *   NOTE:
 *   - No negative numbers implemented
 */
class Calculator {

    // Here are the only allowed instance variables!
    // Error messages (more on static later)
    final static String MISSING_OPERAND = "Missing or bad operand";
    final static String DIV_BY_ZERO = "Division with 0";
    final static String MISSING_OPERATOR = "Missing operator or parenthesis";
    final static String OP_NOT_FOUND = "Operator not found";

    // Definition of operators
    final static String OPERATORS = "+-*/^";
    final static String NUMBERS = "0123456789";

    // Method used in REPL
    double eval(String expr) {
        if (expr.length() == 0) {
            return NaN;
        }
        List<String> tokens = tokenize(expr);
        List<String> postfix = infix2Postfix(tokens);
        double result = evalPostfix(postfix);
        return result; // result;
    }

    // ------  Evaluate RPN expression -------------------

    double evalPostfix(List<String> postfix){
        //double result = 0;

        Deque<String> expression = new ArrayDeque<String>(postfix);

        Deque<String> stack = new ArrayDeque<String>();

        while(!expression.isEmpty()){
            String token = expression.pop();

            if (OPERATORS.contains(token)){
                if (stack.size() >= 2){
                    double d1 = Double.valueOf(stack.pop());
                    double d2 = Double.valueOf(stack.pop());
                    String result = applyOperator(token, d1, d2) + "";
                    stack.push(result);
                }else{
                    throw new IllegalArgumentException(MISSING_OPERAND);
                }

            }else{
                stack.push(token);
            }
        }



        if(stack.size() != 1){
            throw new IllegalArgumentException(MISSING_OPERATOR);
        }

        return Double.valueOf(stack.pop());
    }

    double applyOperator(String op, double d1, double d2) {
        switch (op) {
            case "+":
                return d1 + d2;
            case "-":
                return d2 - d1;
            case "*":
                return d1 * d2;
            case "/":
                if (d1 == 0) {
                    throw new IllegalArgumentException(DIV_BY_ZERO);
                }
                return d2 / d1;
            case "^":
                return pow(d2, d1);
        }
        throw new RuntimeException(OP_NOT_FOUND);
    }

    // ------- Infix 2 Postfix ------------------------

    List<String> infix2Postfix(List<String> infixList){

        // TODO @OG: Write method to convert infix expression to postfix expression given a List<String> with each token as a String in the List.

        List<String> postfixList = new ArrayList<String>();
        Deque<String> stack = new ArrayDeque<>();

        for (String token: infixList) {

            if (isNum(token)) {
                postfixList.add(token);
            }
            else if (isOp(token)) {
                while (!stack.isEmpty()) {
                    if (!stack.peek().equals("(")) {
                        if (getPrecedence(token) < getPrecedence(stack.peek()) || (getPrecedence(token) == getPrecedence(stack.peek()) && getAssociativity(stack.peek()) == Assoc.LEFT)) {
                            postfixList.add(stack.pop());
                        }
                        else {
                            break;
                        }
                    }
                    else {
                        break;
                    }
                }
                stack.push(token);
            }
            else if (token.equals("(")) {
                stack.push(token);
            }
            else if (token.equals(")")) {
                while (!stack.peek().equals("(")) {
                    postfixList.add(stack.pop());
                }
                stack.pop();
            }
            else {
                // We just have this else argument incase we would recive som garbage we wouldn't like tot append it to postfix
            }
        }

        while (!stack.isEmpty()) {
            postfixList.add(stack.pop());
        }

        return postfixList;
    }

    boolean isNum(String token) {
        boolean bool = false;
        try {
            float f = Float.parseFloat(token);
            bool = true;
        }
        catch (Exception e) {
            bool = false;
        }
        finally {
            return bool;
        }
    }

    boolean isOp(String token) {
        return "+-*/^".contains(token);

    }

    int getPrecedence(String op) {
        if ("+-".contains(op)) {
            return 2;
        } else if ("*/".contains(op)) {
            return 3;
        } else if ("^".contains(op)) {
            return 4;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }

    enum Assoc {
        LEFT,
        RIGHT
    }

    Assoc getAssociativity(String op) {
        if ("+-*/".contains(op)) {
            return Assoc.LEFT;
        } else if ("^".contains(op)) {
            return Assoc.RIGHT;
        } else {
            throw new RuntimeException(OP_NOT_FOUND);
        }
    }


    // ---------- Tokenize -----------------------

    List<String> tokenize(String expression){

        String OPERATORSANDPAR = OPERATORS + "()";
        List<String> list = new ArrayList<>();
        char[] c = expression.toCharArray();
        StringBuilder a;

        for (int i = 0; i < c.length; i++){
            a = new StringBuilder();
            if(NUMBERS.contains(Character.toString(c[i]))){
                a.append(c[i]);
                while(i < (c.length - 1) && (c[i] == '.' || NUMBERS.contains(Character.toString(c[i + 1])))){
                    a.append(c[i + 1]);
                    i++;
                }
                list.add(a.toString());
            }
            else if(OPERATORSANDPAR.contains(Character.toString(c[i]))){
                list.add(Character.toString(c[i]));
            }

        }
        System.out.print(list);

        return list;
    }



}
