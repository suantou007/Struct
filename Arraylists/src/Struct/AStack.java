package Struct;

public class AStack<E> implements StackADT<E> {
    //构造顺序栈
    private final int DEFAULT_SIZE = 10;
    private int size;//栈最多能容纳的元素个数
    private int top;//可插入位置的下标
    private E[] listArray;//存储栈中元素的数组

    //初始化
    private void setUp(int size) {
        this.size = size;
        top = 0;
        listArray = (E[]) new Object[size];
    }

    //构造器
    public AStack() {
        setUp(DEFAULT_SIZE);
    }

    public AStack(int size) {
        setUp(size);
    }

    @Override
    public void clear() {
        top = 0;
    }

    @Override
    public void push(E it) {
        if (top >= size) {
            System.out.println("stack overflow");
            return;
        }
        listArray[top++] = it;
    }

    @Override
    public E pop() {
        if (isEmpty()){return null;}
        return listArray[--top];//top-1才是存储最顶元素的位置
    }

    @Override
    public E topValue() {
        if (isEmpty()){return null;}
        return listArray[top-1];
    }

    @Override
    public boolean isEmpty() {
        return top==0;
    }

    @Override
    public void print() {
        if (isEmpty()){
            System.out.println("empty");
        }
        for (int i=top-1;i>=0;i--){
            System.out.println(listArray[i]);
        }
    }

    public static void main(String[] args) {
            AStack<Integer> myAStack=new AStack<>();
            myAStack.push(1);
            myAStack.push(2);
            myAStack.push(3);
            myAStack.push(4);
            myAStack.push(5);
            System.out.println("after pushing, the stack is: ");
            myAStack.print();
            System.out.print("top value: ");
            System.out.println(myAStack.topValue());
            System.out.println("after popping two elements: ");
            myAStack.pop();
            myAStack.pop();
            myAStack.print();
            System.out.print("top value: ");
            System.out.println(myAStack.topValue());
    }

}
