package Struct;

public class LStack<E extends Comparable<E>> implements StackADT<E> {
    private Link<E> top; // 栈顶元素
    private int size; // 栈的大小

    // 初始化与构造器
    private void setUp() {
        top = null;
        size = 10;
    }

    public LStack() {
        setUp();
    }

    @Override
    public void clear() {
        top = null;
        size = 0;
    }

    @Override
    public void push(E it) {
        top = new Link<>(it, top);
        size++;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return null;
        }
        E it = top.getElement();
        top = top.getNext();
        size--;
        return it;
    }
    public E peek() {
        if (isEmpty()) {
            System.out.println("No top value");
            return null;
        }
        return top.getElement();
    }

    @Override
    public E topValue() {
        if (isEmpty()) {
            System.out.println("No top value");
            return null;
        }
        return top.getElement();
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }

    @Override
    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        Link<E> temp = top;
        while (temp != null) {
            System.out.println(temp.getElement());
            temp = temp.getNext();
        }
    }

    public int getSize() {
        return size;
    }
}