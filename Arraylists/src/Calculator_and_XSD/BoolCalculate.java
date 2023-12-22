package Calculator_and_XSD;
import Struct.LStack;
import java.util.Scanner;

public class BoolCalculate {
    //���㲼�����ʽ��ֵ
    //�����Զ������ջ
    static char boolCalculate(String expS) throws Exception {//������
        LStack<Character> stack = new LStack<>();//�洢���ֵ�ջ
        char[] exp = expS.toCharArray();
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == 'T' || exp[i] == 'F') {//����T\F��ѹջ
                stack.push(exp[i]);
            } else if (exp[i] != ' ') {
                char char1 = stack.pop();
                char temp;
                if (exp[i] == '!') {//ȡ�������ֻ��Ҫһ��Ԫ��
                    temp = logicCalculate(exp[i], char1, ' ');//�洢�м�������
                } else {
                    char char2 = stack.pop();//����ջ���ĵڶ���Ԫ��
                    temp = logicCalculate(exp[i], char1, char2);//��������������߼����㣬�洢�м�������
                }

                stack.push(temp);//���м���ѹջ
            }
        }

        return stack.topValue();
    }

    static char logicCalculate(char operator, char char1, char char2) {//�߼�����
        switch (operator) {
            case '!':  //������
                if (char1 == 'T') return 'F';
                else return 'T';
            case '&': //������
                if (char1 == 'T' && char2 == 'T') return 'T';
                else return 'F';
            case '|': //������
                if (char1 == 'F' && char2 == 'F') return 'F';
                else return 'T';
            case '^': //�������
                if ((char1 == 'T' && char2 == 'T') || (char1 == 'F' && char2 == 'F')) return 'F';
                else return 'T';
            default:
                return ' ';
        }
    }

    static boolean match(char[]exp){
        char stack[] = new char[exp.length];
        int top=-1;
        for (int i=0;i<exp.length;i++){
            if (exp[i]=='('){
                stack[++top]='(';//����'('����ջ
            }
            if (exp[i]==')'){
                if (top==-1){
                    return false;//ջ��˵��')'��'('�࣬��ƥ��
                }else {
                    --top;//ջ������ջ�е�һ��'('����
                }
            }
        }
        if (top==-1){
            return true;//ջ����˵���������Ŷ��������
        }
        return false;//�������Ų�ƥ��
    }

    //��׺ת��׺
    static String toPostfix(String nifix) throws Exception {
        LStack<Character> s1 = new LStack<>();//�洢�������ջ
        LStack<Character> s2 = new LStack<>();//�洢�м�����ջ
        char[] nifixExp = nifix.toCharArray();//���ַ���ת��Ϊ�ַ�����
        for (int i = 0; i < nifix.length(); i++) {
            if (nifixExp[i] != 'F' && nifixExp[i] != 'T' && nifixExp[i] != '!'
                    && nifixExp[i] != '&' && nifixExp[i] != '|' && nifixExp[i] != '^'
                    && nifixExp[i] != ' ' && nifixExp[i] != '(' && nifixExp[i] != ')') {
                throw new Exception("�������������");
            }
        }
        if (match(nifixExp)) {
            throw new Exception("���Ų�ƥ��");
        } else {
            for (int i = 0; i < nifixExp.length; i++) {//������ɨ��
                if (nifixExp[i] == 'T' || nifixExp[i] == 'F') {
                    s2.push(nifixExp[i]);//����T/F��ѹ��s2
                } else if (nifixExp[i] == '(') {
                    s1.push(nifixExp[i]);//������ֱ��ѹ��s1
                } else if (nifixExp[i] == ')') {
                    while (s1.topValue() != '(') {
                        s2.push(s1.pop());
                    }
                    s1.pop();//��������Ҳ����
                } else if (nifixExp[i] != ' ') {
                    /**
                     * ���������ʱ���Ƚ�����s1ջ������������ȼ���
                     * 1) ���s1Ϊ�գ���ջ�������Ϊ�����š�(������ֱ�ӽ����������ջ��
                     * 2) ���������ȼ���ջ��������ĸߣ�Ҳ�������ѹ��s1��
                     * 3) ���򣬽�s1ջ���������������ѹ�뵽s2�У��ٴ�ת��(1)��s1���µ�ջ���������Ƚϣ�
                     */
                    while (true) {
                        if (s1.isEmpty() || s1.topValue() == '('
                                || comparePriority(nifixExp[i], s1.topValue())) {
                            s1.push(nifixExp[i]);
                            break;
                        } else {
                            s2.push(s1.pop());
                        }
                    }
                }
            }
        }
        //��s1��ʣ��������һ�ε�����ѹ��s2
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        //���ε���s2�е�Ԫ�ز������
        // ���������Ϊ��׺���ʽ��Ӧ�ĺ�׺���ʽ
        char[] temp = new char[s2.getSize()];
        for (int i = 0; !s2.isEmpty(); i++) {
            temp[i] = s2.pop();
        }
        String string = "";
        for (int i = temp.length - 1; i >= 0; i--) {
            string += temp[i];
        }
        return string;

    }

    private static boolean comparePriority(char c1, char c2) {//�Ƚ���������ȼ�
        int priority1 = 0, priority2 = 0;
        switch (c1) {
            case '!':
                priority1 = 4;
                break;
            case '&':
                priority1 = 3;
                break;
            case '^':
                priority1 = 2;
                break;
            case '|':
                priority1 = 1;
                break;
        }
        switch (c2) {
            case '!':
                priority2 = 4;
                break;
            case '&':
                priority2 = 3;
                break;
            case '^':
                priority2 = 2;
                break;
            case '|':
                priority2 = 1;
                break;
        }
        return priority1 - priority2 > 0;//c1���ȼ��ϸ�
    }

    public static void main(String[] args) throws Exception {
        System.out.print("please input your nifix expression: ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();//next()�������ܴ��ո�
        char result = boolCalculate(toPostfix(input));
        System.out.println("the result of: " + input + " is " + result);
    }
}

