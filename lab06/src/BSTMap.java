import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;
    private int size;
    private final Set<K> keys;
    private V removeValue;

    private class Node {
        K key;
        V value;
        Node left, right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
        keys = new TreeSet<>();
        removeValue = null;
    }

    @Override
    public void put(K key, V value) {
        keys.add(key);
        root = putNodeHelper(root, key, value);
    }

    private Node putNodeHelper(Node root, K key, V value) {
        if (root == null) {
            size++;
            return new Node(key, value);
        }
        if (root.key.equals(key)) {
            root.value = value;
            return root;
        }
        if (key.compareTo(root.key) < 0) {
            root.left = putNodeHelper(root.left, key, value);
        }
        if (key.compareTo(root.key) > 0) {
            root.right = putNodeHelper(root.right, key, value);
        }
        return root;
    }

    @Override
    public V get(K key) {
        return getNodeHelper(root, key);
    }

    private V getNodeHelper(Node root, K key) {
        if (root == null) {
            return null;
        }
        if (root.key.equals(key)) {
            return root.value;
        }
        if (key.compareTo(root.key) < 0) {
            return getNodeHelper(root.left, key);
        }
        if (key.compareTo(root.key) > 0) {
            return getNodeHelper(root.right, key);
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return checkKeyHelper(root, key);
    }

    private boolean checkKeyHelper(Node root, K key) {
        if (root == null) {
            return false;
        }
        if (root.key.equals(key)) {
            return true;
        }
        boolean left = checkKeyHelper(root.left, key);
        boolean right = checkKeyHelper(root.right, key);
        return left || right;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        keys.clear();
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        return keys;
    }

    @Override
    public V remove(K key) {
        root = removeNodeHelper(root, key);
        V temp = removeValue;
        if (temp != null) {
            size--;
        }
        removeValue = null;
        return temp;
    }

    private Node removeNodeHelper(Node root, K key) {
        if (root == null) {
            return null;
        }
        if (key.compareTo(root.key) < 0) {
            root.left = removeNodeHelper(root.left, key);
        }
        if (key.compareTo(root.key) > 0) {
            root.right = removeNodeHelper(root.right, key);
        }
        if (root.key.equals(key)) {
            removeValue = root.value;
            Node left = root.left;
            Node returnNode = root.right;
            Node currentNode = root.right;
            if (currentNode == null) {
                return left;
            }
            while (currentNode.left != null) {
                currentNode = currentNode.left;
            }
            currentNode.left = left;
            return returnNode;
        }
        return root;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTIterator();
    }

    private class BSTIterator implements Iterator<K> {

        private final Stack<Node> stack;

        public BSTIterator() {
            stack = new Stack<>();
            pushBack(root, stack);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public K next() {
            Node popNode = stack.pop();
            K returnKey = popNode.key;
            if (popNode.right != null) {
                pushBack(popNode.right, stack);
            }
            return returnKey;
        }

        private void pushBack(Node root, Stack<Node> stack) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
        }
    }
}
