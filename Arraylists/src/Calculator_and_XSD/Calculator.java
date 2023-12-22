package Calculator_and_XSD;

import java.text.DecimalFormat;
import Struct.LStack;
public class Calculator {
    public static double calculate(String expression) {
        LStack<Double> operandStack = new LStack<Double>();
        LStack<Character> operatorStack = new LStack<Character>();
        boolean fushu = false;
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isDigit(ch) || ch == '.') {
                // 处理操作数
                String operand = "";
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    operand += expression.charAt(i);
                    i++;
                }
                i--; // 回退一个字符
                double value = Double.parseDouble(operand);
                if (fushu){
                    value=-value;
                    fushu=false;
                }
                operandStack.push(value);
            } else if (ch == '(') {
                // 左括号直接入栈
                operatorStack.push(ch);
            } else if (ch == ')') {
                // 右括号，进行计算直到遇到左括号
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    calculateOperation(operandStack, operatorStack);
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // 弹出左括号
                } else {
                    throw new IllegalArgumentException("括号匹配失败");
                }
            } else if (isOperator(ch)) {
                // 运算符，进行比较和计算
                if(ch=='-'){
                    if(Character.isDigit(expression.charAt(i+1))){
                        fushu=true;
                        continue;
                    }
                }
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && !hasHigherPrecedence(ch, operatorStack.peek())) {
                    //如果优先级更高就不进行计算，优先级一样或者更低，说明前面的符号已经可以开始计算了
                    calculateOperation(operandStack, operatorStack);
                }
                operatorStack.push(ch);
                //ch都不参与本次运算，是在确保运算顺序正确的情况下将ch入栈
            }
        }

        // 处理剩余的运算符
        while (!operatorStack.isEmpty()) {
            calculateOperation(operandStack, operatorStack);
        }

        if (operandStack.getSize()!= 1) {//运算结果
            throw new IllegalArgumentException("格式错误");
        }

        return operandStack.pop();
    }

    private static void calculateOperation(LStack<Double> operandStack, LStack<Character> operatorStack) {
        if (operandStack.isEmpty() || operatorStack.isEmpty()) {
            throw new IllegalArgumentException("表达式格式错误");
        }

        double operand2 = operandStack.pop();
        double operand1 = operandStack.pop();
        char operator = operatorStack.pop();

        double result;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                result = operand1 / operand2;
                break;
            case '^':
                result = Math.pow(operand1, operand2);
                break;
            default:
                throw new IllegalArgumentException("不支持的运算符");
        }

        operandStack.push(result);
    }

    private static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '^';
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return true;
        }
        if (op1 == '^' && (op2 == '+' || op2 == '-' || op2 == '*' || op2 == '/')) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String expression = "3.5 + -2.1 * (4 - 1)";

        try {
            double number = calculate(expression);
            DecimalFormat df = new DecimalFormat("#.0");
            String result = df.format(number);
            System.out.println("计算结果: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("表达式错误: " + e.getMessage());
        }
    }
}
