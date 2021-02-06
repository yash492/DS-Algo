package LinkedList;

public class DoublyLinkedList<T> {
    Node<T> head;
    Node<T> tail;
    int size = 0;

    DoublyLinkedList() {
        this.head = new Node<T>(null);
        this.tail = new Node<T>(null);
        head.next = tail;
        tail.prev = head;
    }

    public void add(T data) {
        Node<T> node = new Node<T>(data);
        if (data == null) {
            throw new IllegalArgumentException("Data can't be null.");
        }

        else {
            node.next = this.tail;
            node.prev = this.tail.prev;
            this.tail.prev = node;
            node.prev.next = node;
        }
        size++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) 
            throw new IndexOutOfBoundsException("Index " + index + ",Size " + size);

        if (data == null) 
            throw new IllegalArgumentException("Data can't be null.");

        else if (index == size) {
            add(data);
            return;
        }

        Node<T> node = new Node<T>(data);
        Node<T> previousNode = getNode(index);
        node.next = previousNode.next;
        node.prev = previousNode;
        previousNode.next = node;
        node.next.prev = node;
        size++;
    }

    public void remove(int index) {
        if (index < 0 || index > size-1) 
            throw new IndexOutOfBoundsException("Index " + index + ", Size " + size);

        if (size == 0) 
            throw new IllegalStateException("The list is empty.");

        Node<T> previousNode = getNode(index);
        Node<T> nextNode = previousNode.next.next;

        previousNode.next = nextNode;
        nextNode.prev = previousNode;

        size--;
    }

    public int size() {
        return size;
    }

    public T get(int index) {
        if (index < 0 || index > size-1) 
            throw new IndexOutOfBoundsException("Index " + index + ", Size " + size);

        Node<T> previousNode = getNode(index);
        return previousNode.next.data;
    }

    public void set(int index, T data) {
        if (index < 0 && index > size-1) 
            throw new IndexOutOfBoundsException("Index " + index + ", Size " + size);

        if (data == null) 
            throw new IllegalArgumentException("Data can't be null.");

        if (size == 0) 
            throw new IllegalStateException("The list is empty.");

        Node<T> previousNode = getNode(index);
        Node<T> node = new Node<T>(data);
        Node<T> nextNextNode = previousNode.next.next;
        previousNode.next = node;
        node.prev = previousNode;
        node.next = nextNextNode;
        nextNextNode.prev = node;

    }

    @Override
    public String toString() {
        StringBuilder print = new StringBuilder();
        print.append("[");

        Node<T> currentNode = this.head.next;
        int i = 0;
        while (currentNode.data != null) {
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
        Node<T> result = this.head;
        while(currentNode.next.data != null) {
            if (i++ == index) {
                result = currentNode;
                break;
            }
            currentNode = currentNode.next;
        }
        return result;
    }
} 