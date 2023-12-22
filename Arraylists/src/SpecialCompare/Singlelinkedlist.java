package SpecialCompare;

import org.w3c.dom.ls.LSOutput;

import java.util.Iterator;
import java.util.function.Consumer;

public class Singlelinkedlist implements Iterable<Integer>{

    node head= new node(Integer.MAX_VALUE,null);
    public void addfirst(int value) {
        node nd = new node(value,head.next);
        head.next=nd;

    }

    public void addlast(int value){
        node p=findlast();
        p.next=new node(value,null);
    }
    private node findlast(){
        node nd=head;
        while(nd.next!=null){
            nd=nd.next;
        }
        return nd;
    }

    public void loop1(Consumer<Integer> comsumer){
        node p=head.next;
        while(p!=null){
            comsumer.accept(p.value);
            p=p.next;
        }
    }
    public void loop2(Consumer<Integer> comsumer){
        for(node p=head;p!=null;p=p.next){
            comsumer.accept(p.value);
        }
    }

    public int get(int val){
        node p=findnode(val);
        if(p==null){
            throw new IllegalArgumentException(
                    String.format("index[%d] is illegal%n",val));
        }
        return p.value;
    }
    private node findnode(int index){
        int i=0;
        for(node p=head;p!=null;p=p.next,i++){
            if(i==index){
                return p;
            }
        }
        return null;
    }



    private static class node {

        int value;
        node next;
        public node() {
        }

        public node(int value, node next) {
            this.value = value;
            this.next = next;
        }
    }
    public void loop3(Consumer<Integer> consumer) {
        Iterator<Integer> iterator = iterator();

        while (iterator.hasNext()) {
            Integer value = iterator.next();
            consumer.accept(value);
        }
    }

    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {
            node cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != null;
            }

            @Override
            public Integer next() {
                int value = cur.value;
                cur = cur.next;
                return value;
            }
        };
    }
    public void insert(int index,int value){
        node p=findnode(index);
        node nd=new node(value,p.next);
        p.next=nd;
    }
}




