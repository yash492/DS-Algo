package SinglyLinkedList;

public class SinglyLinkedList<T> {
    Node<T> head;
    int size;

    SinglyLinkedList() {
        head = new Node<T>(null);
        size = 0;
    }

    public void add(T data) {

        if (data == null) 
            throw new IllegalArgumentException("Data can't be null.");

        Node<T> node = new Node<T>(data);
        Node<T> lastNode = this.head;
        while(lastNode.next != null) {
            lastNode = lastNode.next;
        }
        lastNode.next = node;
        size++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) 
            throw new IndexOutOfBoundsException("Index " + index + ", Size " + size);

        if (data == null) 
            throw new IllegalArgumentException("Data can't be null.");

        Node<T> node = new Node<T>(data);
        Node<T> previousNode = getNode(index);
        node.next = previousNode.next;
        previousNode.next = node;
        size++;
    }

    public T get(int index) {

        if (index < 0 || index > size-1) 
            throw new IndexOutOfBoundsException("Index " + index + ",Size " + size);

        return getNode(index).next.data;
    }

    public int size() {
        return size;
    }

    public void remove(int index) {

        if (index < 0 || index > size-1) 
            throw new IndexOutOfBoundsException("Index " + index + ",Size " + size);

        if (size == 0) 
            throw new IllegalStateException("The list is empty.");

        Node<T> previousNode = getNode(index);
        Node<T> nextNode = previousNode.next.next;
        previousNode.next = nextNode;
        size--;
    }

    public void set(int index, T data) {

        if (index < 0 && index > size-1) 
            throw new IndexOutOfBoundsException("Index " + index + ",Size " + size);

        if (data == null) 
            throw new IllegalArgumentException("Data can't be null.");

        if (size == 0) 
            throw new IllegalStateException("The list is empty.");

        Node<T> previousNode = getNode(index);
        Node<T> node = new Node<T>(data);
        Node<T> nextNode = previousNode.next.next;
        previousNode.next = node;
        node.next = nextNode;
    }

    @Override
    public String toString() {
        Node<T> currentNode = this.head.next;
        StringBuilder print = new StringBuilder();
        print.append("[");
        int i = 0;
        while(currentNode != null) {
            if (i++ < size - 1) 
                print.append(currentNode.data + ", ");

            else
                print.append(currentNode.data);

            currentNode = currentNode.next;
        }
        print.append("]"); 
        return print.toString();
    }

    private Node<T> getNode(int index) {
        int i = 0;
        Node<T> currentNode = this.head;
        while (currentNode.next != null) {
            if (i++ == index) {
                break;
            }
            currentNode = currentNode.next;
        }
        return currentNode;
    }
}