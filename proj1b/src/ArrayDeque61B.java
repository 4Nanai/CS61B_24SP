import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] array;
    private int size;
    private int head;
    private static final int UNLIMITED_CAPACITY = 16;

    public ArrayDeque61B() {
        array = (T[]) new Object[8];
        size = 0;
        head = 0;
    }

    @Override
    public void addFirst(T x) {
        if (size == 0) {
            array[head] = x;
            size++;
            return;
        }
        if (size < array.length) {
            int newHead = Math.floorMod(head - 1, array.length);
            array[newHead] = x;
            head = newHead;
            size++;
            return;
        }
        T[] newArray = (T[]) new Object[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = get(i);
        }
        head = 0;
        array = newArray;
        int newHead = Math.floorMod(head - 1, array.length);
        array[newHead] = x;
        head = newHead;
        size++;
    }

    @Override
    public void addLast(T x) {
        if (size == 0) {
            array[head] = x;
            size++;
            return;
        }
        if (size < array.length) {
            int addIndex = Math.floorMod(head + size, array.length);
            array[addIndex] = x;
            size++;
            return;
        }
        T[] newArray = (T[]) new Object[array.length * 2];
        for (int i = 0; i < array.length; i++) {
            newArray[i] = get(i);
        }
        head = 0;
        array = newArray;
        int addIndex = Math.floorMod(head + size, array.length);
        array[addIndex] = x;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> list = new ArrayList<>();
        for (int i = head; i < head + size; i++) {
            list.add(array[Math.floorMod(i, array.length)]);
        }
        return list;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T returnVal = array[head];
        if (array.length <= UNLIMITED_CAPACITY || size > array.length / 4) {
            head = Math.floorMod(head + 1, array.length);
            size--;
            return returnVal;
        }
        T[] newArray = (T[]) new Object[array.length / 2];
        for (int i = 0; i < size - 1; i++) {
            newArray[i] = get(i + 1);
        }
        head = 0;
        array = newArray;
        size--;
        return returnVal;
    }

    @Override
    public T removeLast() {
        T returnVal = get(size - 1);
        if (array.length <= UNLIMITED_CAPACITY || size > array.length / 4) {
            size--;
            return returnVal;
        }
        T[] newArray = (T[]) new Object[array.length / 2];
        for (int i = 0; i < size - 1; i++) {
            newArray[i] = get(i);
        }
        head = 0;
        array = newArray;
        size--;
        return returnVal;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[Math.floorMod(head + index, array.length)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
