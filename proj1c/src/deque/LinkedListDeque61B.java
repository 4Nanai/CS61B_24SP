package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        public int iterPos;

        public LinkedListIterator() {
            iterPos = 0;
        }

        @Override
        public boolean hasNext() {
            return iterPos < _size;
        }

        @Override
        public T next() {
            T returnVal = get(iterPos);
            iterPos++;
            return returnVal;
        }
    }

    public class Node {
        T val;
        Node next;
        Node prev;
        public Node(T val, Node next, Node prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node _head;
    private int _size;

    public LinkedListDeque61B() {
        _head = null;
        _size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node newNode = new Node(x, null, null);

        if (_head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            newNode.next = _head;
            newNode.prev = _head.prev;
            if (_head.prev != null) _head.prev.next = newNode;
            _head.prev = newNode;
        }
        _head = newNode;
        _size++;
    }

    @Override
    public void addLast(T x) {
        Node newNode = new Node(x, null, null);
        if (_head == null) {
            newNode.next = newNode;
            newNode.prev = newNode;
        } else {
            newNode.next = _head;
            newNode.prev = _head.prev;
            if (_head.prev != null) _head.prev.next = newNode;
            _head.prev = newNode;
        }
        _head = newNode.next;
        _size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        if (_head == null) {
            return returnList;
        }
        Node cur = _head;
        do {
            returnList.add(cur.val);
            cur = cur.next;
        } while (cur != _head);
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return _head == null;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node returnNode = _head;
        _head = _head.next;
        _head.prev = returnNode.prev;
        returnNode.prev.next = _head;
        _size--;
        return returnNode.val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node returnNode = _head.prev;
        returnNode.prev.next = _head;
        _head.prev = returnNode.prev;
        _size--;
        return returnNode.val;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= _size) {
            return null;
        }
        Node cur = _head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }

    @Override
    public T getRecursive(int index) {
        return getRecursiveHelper(_head, index);
    }

    private T getRecursiveHelper(Node node, int index) {
        if (index < 0 || index >= _size) {
            return null;
        }
        if (index == 0) {
            return node.val;
        }
        return getRecursiveHelper(node.next, index - 1);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof LinkedListDeque61B<?> otherDeque) {
            if (size() != otherDeque.size()) {
                return false;
            }
            for (int i = 0; i < size(); i++) {
                if (!get(i).equals(otherDeque.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}
