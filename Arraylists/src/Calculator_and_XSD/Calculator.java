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
                // ���������
                String operand = "";
                while (i < expression.length() && (Character.isDigit(expression.charAt(i)) || expression.charAt(i) == '.')) {
                    operand += expression.charAt(i);
                    i++;
                }
                i--; // ����һ���ַ�
                double value = Double.parseDouble(operand);
                if (fushu){
                    value=-value;
                    fushu=false;
                }
                operandStack.push(value);
            } else if (ch == '(') {
                // ������ֱ����ջ
                operatorStack.push(ch);
            } else if (ch == ')') {
                // �����ţ����м���ֱ������������
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    calculateOperation(operandStack, operatorStack);
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // ����������
                } else {
                    throw new IllegalArgumentException("����ƥ��ʧ��");
                }
            } else if (isOperator(ch)) {
                // ����������бȽϺͼ���
                if(ch=='-'){
                    if(Character.isDigit(expression.charAt(i+1))){
                        fushu=true;
                        continue;
                    }
                }
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(' && !hasHigherPrecedence(ch, operatorStack.peek())) {
                    //������ȼ����߾Ͳ����м��㣬���ȼ�һ�����߸��ͣ�˵��ǰ��ķ����Ѿ����Կ�ʼ������
                    calculateOperation(operandStack, operatorStack);
                }
                operatorStack.push(ch);
                //ch�������뱾�����㣬����ȷ������˳����ȷ������½�ch��ջ
            }
        }

        // ����ʣ��������
        while (!operatorStack.isEmpty()) {
            calculateOperation(operandStack, operatorStack);
        }

        if (operandStack.getSize()!= 1) {//������
            throw new IllegalArgumentException("��ʽ����");
        }

        return operandStack.pop();
    }

    private static void calculateOperation(LStack<Double> operandStack, LStack<Character> operatorStack) {
        if (operandStack.isEmpty() || operatorStack.isEmpty()) {
            throw new IllegalArgumentException("���ʽ��ʽ����");
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
                throw new IllegalArgumentException("��֧�ֵ������");
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
            System.out.println("������: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("���ʽ����: " + e.getMessage());
        }
    }
}
