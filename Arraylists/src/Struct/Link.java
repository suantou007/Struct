package Struct;

public class Link <E> {
    //�����
    private E element;
    private Link next; //ָ�����һ�����
    //���췽��
    public Link(E element, Link next) {
        this.element = element;
        this.next = next;
    }

    public Link(Link next) {
        this.next = next;
    }
    //getter��setter
    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public Link getNext() {
        return next;
    }

    public void setNext(Link next) {
        this.next = next;
    }
}
