package Calculator_and_XSD;
import Struct.LStack;
import java.util.Scanner;

public class BoolCalculate {
    //计算布尔表达式的值
    //利用自定义的链栈
    static char boolCalculate(String expS) throws Exception {//主函数
        LStack<Character> stack = new LStack<>();//存储数字的栈
        char[] exp = expS.toCharArray();
        for (int i = 0; i < exp.length; i++) {
            if (exp[i] == 'T' || exp[i] == 'F') {//遇到T\F则压栈
                stack.push(exp[i]);
            } else if (exp[i] != ' ') {
                char char1 = stack.pop();
                char temp;
                if (exp[i] == '!') {//取非运算符只需要一个元素
                    temp = logicCalculate(exp[i], char1, ' ');//存储中间运算结果
                } else {
                    char char2 = stack.pop();//弹出栈顶的第二个元素
                    temp = logicCalculate(exp[i], char1, char2);//根据运算符进行逻辑运算，存储中间运算结果
                }

                stack.push(temp);//将中间结果压栈
            }
        }

        return stack.topValue();
    }

    static char logicCalculate(char operator, char char1, char char2) {//逻辑运算
        switch (operator) {
            case '!':  //非运算
                if (char1 == 'T') return 'F';
                else return 'T';
            case '&': //与运算
                if (char1 == 'T' && char2 == 'T') return 'T';
                else return 'F';
            case '|': //或运算
                if (char1 == 'F' && char2 == 'F') return 'F';
                else return 'T';
            case '^': //异或运算
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
                stack[++top]='(';//遇到'('则入栈
            }
            if (exp[i]==')'){
                if (top==-1){
                    return false;//栈空说明')'比'('多，不匹配
                }else {
                    --top;//栈不空则将栈中的一个'('弹出
                }
            }
        }
        if (top==-1){
            return true;//栈空则说明所有括号都被处理掉
        }
        return false;//否则括号不匹配
    }

    //中缀转后缀
    static String toPostfix(String nifix) throws Exception {
        LStack<Character> s1 = new LStack<>();//存储运算符的栈
        LStack<Character> s2 = new LStack<>();//存储中间结果的栈
        char[] nifixExp = nifix.toCharArray();//将字符串转化为字符数组
        for (int i = 0; i < nifix.length(); i++) {
            if (nifixExp[i] != 'F' && nifixExp[i] != 'T' && nifixExp[i] != '!'
                    && nifixExp[i] != '&' && nifixExp[i] != '|' && nifixExp[i] != '^'
                    && nifixExp[i] != ' ' && nifixExp[i] != '(' && nifixExp[i] != ')') {
                throw new Exception("输入符号有问题");
            }
        }
        if (match(nifixExp)) {
            throw new Exception("括号不匹配");
        } else {
            for (int i = 0; i < nifixExp.length; i++) {//从左到右扫描
                if (nifixExp[i] == 'T' || nifixExp[i] == 'F') {
                    s2.push(nifixExp[i]);//遇到T/F则压入s2
                } else if (nifixExp[i] == '(') {
                    s1.push(nifixExp[i]);//左括号直接压入s1
                } else if (nifixExp[i] == ')') {
                    while (s1.topValue() != '(') {
                        s2.push(s1.pop());
                    }
                    s1.pop();//把左括号也弹出
                } else if (nifixExp[i] != ' ') {
                    /**
                     * 遇到运算符时，比较其与s1栈顶运算符的优先级：
                     * 1) 如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈；
                     * 2) 否则，若优先级比栈顶运算符的高，也将运算符压入s1；
                     * 3) 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(1)与s1中新的栈顶运算符相比较；
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
        //把s1中剩余的运算符一次弹出并压入s2
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        //依次弹出s2中的元素并输出，
        // 结果的逆序即为中缀表达式对应的后缀表达式
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

    private static boolean comparePriority(char c1, char c2) {//比较运算符优先级
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
        return priority1 - priority2 > 0;//c1优先级较高
    }

    public static void main(String[] args) throws Exception {
        System.out.print("please input your nifix expression: ");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();//next()方法不能带空格
        char result = boolCalculate(toPostfix(input));
        System.out.println("the result of: " + input + " is " + result);
    }
}

