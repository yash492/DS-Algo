package Stacks_Implementation_with_Linked_List;

import java.util.*;

public class Stack<E> implements Iterable<E>{

    LinkedList<E> list = new LinkedList<>();

    public Stack() {};

    public Stack(E value) {
        push(value);
    }

    public int size() {
        return size();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void push(E value) {
        list.addLast(value);
    }

    public E pop() {
        if (isEmpty()) throw new java.util.EmptyStackException();
        return list.removeLast();
      }
    

      public E peek() {
        if (isEmpty()) throw new java.util.EmptyStackException();
        return list.peekLast();
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