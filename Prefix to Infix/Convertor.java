import java.util.Stack;

public class Convertor {
    static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^") || token.equals("%");
    }

    public static void prefixToInfix(String Expression) {
        Stack<String> infixStack = new Stack<>();
        Stack<Integer> evalStack = new Stack<>();

        String[] tokens = Expression.split("\\s+");

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            if (isOperator(token)) {
            	int val1 = evalStack.pop();
                int val2 = evalStack.pop();
                int tempEval = 0;
                switch (token) {
                    case "+":
                        tempEval = val1 + val2;
                        break;
                    case "-":
                        tempEval = val1 - val2;
                        break;
                    case "*":
                        tempEval = val1 * val2;
                        break;
                    case "/":
                        tempEval = val1 / val2;
                        break;
                    case "^":
                        tempEval = (int) Math.pow(val1, val2);
                        break;
                    case "%":
                        tempEval = val1 % val2;
                        break;
                }
                
                String op1 = infixStack.pop();
                String op2 = infixStack.pop();
                
                String tempInfix = "(" + op1 + token + op2 + ")";
                
                infixStack.push(tempInfix);
                evalStack.push(tempEval);
            } else {
                infixStack.push(token);
                evalStack.push(Integer.parseInt(token));
            }
        }
        int result = evalStack.pop();
        String infix = infixStack.pop();
        System.out.println(Expression + " -> " + infix + " = " + result );
      //  return 	infix + " = " + result;
    }


    public static void main(String[] args) {
        String exp = "* + 3 5 + 19 6";
        prefixToInfix(exp);
    }
}
