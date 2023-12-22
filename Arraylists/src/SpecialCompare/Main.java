package SpecialCompare;

import SpecialCompare.Singlelinkedlist;

import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        Singlelinkedlist Slt = new Singlelinkedlist();
        Consumer<Integer> myConsumer= new MyConsumer();
        Slt.loop3(myConsumer);
    }

    static class MyConsumer implements Consumer<Integer> {

        @Override
        public void accept(Integer integer) {
            luanlai666(integer);
        }
        public void luanlai666(int temp){

        }
    }
}