package Struct;

import java.io.PrintWriter;
import java.util.Arrays;

public class ResizingAList implements List<Character> {
    private static final int DEFAULT_CAPACITY = 1;
    private char[] elements;
    private int capacity;
    private int cursor;
    private int length;

    public ResizingAList() {
        elements = new char[DEFAULT_CAPACITY];
        capacity = DEFAULT_CAPACITY;
        cursor = 0;
        length = 0;
    }
    public double getRate() {
        double temp = (double) length / capacity;
        return temp;
    }


    @Override
    public void insert(Character newElement) throws ListException {
        if (isFull()) {
            resize(capacity * 2);
        }

        if (length == 0) {
            elements[0] = newElement;
        } else {
            for (int i = length; i > cursor + 1; i--) {
                elements[i] = elements[i - 1];
            }
            elements[cursor + 1] = newElement;
            cursor++;
        }

        length++;
    }

    @Override
    public void remove() {
        if (isEmpty()) {
            System.out.println("Empty List");
            return;
        }

        if (cursor == length - 1) {
            elements[cursor] = '\u0000';
            gotoBeginning();
        } else {
            for (int i = cursor; i < length - 1; i++) {
                elements[i] = elements[i + 1];
            }
        }
        length--;

        if (length > 0 && length == capacity / 4) {
            resize(capacity / 2);
        }
    }

    private void resize(int newCapacity) {
        char[] newElements = new char[newCapacity];
        for (int i = 0; i < length; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
        capacity = newCapacity;
    }

    @Override
    public void replace(Character newElement) {
        if (!isEmpty()) {
            elements[cursor] = newElement;
        }
    }

    @Override
    public void clear() {
        Arrays.fill(elements, '\u0000');
        length = 0;
        cursor = 0;
        capacity = 1 ;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public boolean isFull() {
        return length == capacity;
    }

    @Override
    public boolean gotoBeginning() {
        if (length > 0) {
            cursor = 0;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean gotoEnd() {
        if (length > 0) {
            cursor = length - 1;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean gotoNext() {
        if (isEmpty()) {
            return false;
        }
        if (cursor == length - 1) {
            return false;
        } else {
            cursor++;
            return true;
        }
    }

    @Override
    public boolean gotoPrev() {
        if (isEmpty()) {
            return false;
        }
        if (cursor == 0) {
            return false;
        } else {
            cursor--;
            return true;
        }
    }

    @Override
    public Character getCursor() {
        if (!isEmpty()) {
            return elements[cursor];
        }
        return null;
    }

    @Override
    public void showStructure(PrintWriter pw) {
        if (isEmpty()) {
            pw.printf("Empty list {capacity = %d, length = %d, cursor = %d}", capacity, length, -1);
        } else {
            for (int i = 0; i < length; i++) {
                pw.print(elements[i] + " ");
            }
            pw.printf("{capacity = %d, length = %d, cursor = %d}", capacity, length, cursor);
        }
    }

    @Override
    public void moveToNth(int n) {
        try {
            if (n >= 0 && n < length) {
                char cursorValue = elements[cursor];
                remove();
                cursor = n - 1;
                insert(cursorValue);
            }
        } catch (ListException e) {
            System.out.println("Error");
        }
    }

    @Override
    public boolean find(Character searchElement) {
        if (length == 0) {
            return false;
        }
        while (cursor < length) {
            if (elements[cursor] == searchElement.charValue()) {
                return true;
            }
            cursor++;
        }
        cursor--;
        return false;
    }
}