package Struct;

public interface StackADT<E> {
    public void clear();//���ջ������Ԫ��
    public void push(E it);//ѹջ
    public E pop();//��ջ
    public E topValue();//����ջ��Ԫ��
    public boolean isEmpty();//�ж�ջ�Ƿ��
    public void print();//��ӡջ�е�����Ԫ��
}
