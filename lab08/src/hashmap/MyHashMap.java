package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    private final static int DEFAULT_INITIAL_CAPACITY = 16;
    private final static double DEFAULT_LOAD_FACTOR = 0.75;
    private int capacity;
    private final double loadFactor;
    private int nodeCount = 0;
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;
        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param capacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int capacity, double loadFactor) {
        this.capacity = capacity;
        this.loadFactor = loadFactor;
        buckets = new Collection[this.capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!


    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {

        int hashCode = key.hashCode();
        int index = Math.floorMod(hashCode, capacity);
        Collection<Node> bucket = buckets[index];
        Node exist = bucket.stream()
                .filter(node -> node.key.equals(key))
                .findFirst()
                .orElse(null);
        if (exist != null) {
            exist.value = value;
        }
        else {
            Node newNode = new Node(key, value);
            bucket.add(newNode);
            nodeCount++;
            double currentFactor = ((double) nodeCount) / capacity;
            if (currentFactor > loadFactor) {
                resize();
            }
        }
    }

    private void resize() {
        int newCapacity = capacity * 2;
        Collection<Node>[] newBuckets = new Collection[newCapacity];
        for (int i = 0; i < newCapacity; i++) {
            newBuckets[i] = createBucket();
        }
        for (Collection<Node> bucket : buckets) {
            for (Node node : bucket) {
                int hashCode = node.key.hashCode();
                int index = Math.floorMod(hashCode, newCapacity);
                newBuckets[index].add(node);
            }
        }
        buckets = newBuckets;
        capacity = newCapacity;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        int hashCode = key.hashCode();
        int index = Math.floorMod(hashCode, capacity);
        Collection<Node> bucket = buckets[index];
        return bucket.stream()
                .filter(node -> node.key.equals(key))
                .findFirst()
                .map(node -> node.value)
                .orElse(null);
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        int hashCode = key.hashCode();
        int index = Math.floorMod(hashCode, capacity);
        Collection<Node> bucket = buckets[index];
        return bucket.stream()
                .filter(node -> node.key.equals(key))
                .findFirst()
                .orElse(null) != null;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return nodeCount;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        nodeCount = 0;
        capacity = DEFAULT_INITIAL_CAPACITY;
        buckets = new Collection[capacity];
        for (int i = 0; i < capacity; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for this lab.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for this lab. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
