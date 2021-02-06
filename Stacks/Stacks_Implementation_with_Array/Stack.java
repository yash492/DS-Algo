package Stacks_Implementation_with_Array;

import java.util.*;

public class Stack<E> implements Iterable<E> {

    ArrayList<E> list = new ArrayList<>();

    public Stack() {};

    public Stack(E value) {
        push(value);
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(E value) {
        list.add(value);
    }

    public E pop() {
        if (isEmpty()) throw new java.util.EmptyStackException();
        return list.remove(size()-1);
    }

    public E peek() {
        if (isEmpty()) throw new java.util.EmptyStackException();
        return list.get(size()-1);
    }

    @Override
    public Iterator<E> iterator() {
        return list.iterator();
    }

    @Override
    public String toString() {
        return list.toString();
    }

}
