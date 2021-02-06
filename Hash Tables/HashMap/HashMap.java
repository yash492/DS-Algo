package HashMap;

import java.util.*;

@SuppressWarnings("unchecked")
public class HashMap<K, V> {

    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;

    private double maxLoadFactor;
    private int capacity, threshold, size = 0;
    private LinkedList<Entry<K, V>>[] table;

    public HashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }

    //Designated Constructor
    public HashMap(int capacity, double maxLoadFactor) {
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal capacity.");
        
        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
        throw new IllegalArgumentException("Illegal maxLoadFactor.");

        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        threshold = (int) (this.capacity * maxLoadFactor);
        table = new LinkedList[this.capacity];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // Converts hash value into index. Essentially, this strips the negative sign
    // and places the hash value in the domain [0, capacity].
    private int normalizeIndex(int keyHashCode) {
        return (keyHashCode & 0x7FFFFFFF) % capacity;
    }

    public void clear() {
        Arrays.fill(table, null);
        size = 0;
    }

    public boolean containsKey(K key) {
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(bucketIndex, key) != null;
    }

    public boolean containsValue(V value) {
        return values().contains(value);
    }

    public V put(K key, V value) {
        if (key == null)
            throw new IllegalArgumentException("Null Key.");
        Entry<K, V> newEntry = new Entry<>(key, value);
        int bucketIndex = normalizeIndex(newEntry.hashCode);
        return bucketInsertEntry(bucketIndex, newEntry);
    }

    public V get(K key) {
        if (key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null) return entry.value;
        return null;
    }

    public V remove(K key) {
        if (key == null) return null;
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketRemoveEntry(bucketIndex, key);
    }

    public List<K> keySet() {
        List<K> keys = new ArrayList<>(size());
        for (LinkedList<Entry<K, V>> bucket: table) {
            if (bucket != null) for(Entry<K, V> entry: bucket) keys.add(entry.key);
        }
        return keys;
    }

    public List<V> values() {

        List<V> values = new ArrayList<>(size());
        for (LinkedList<Entry<K, V>> bucket : table)
          if (bucket != null) for (Entry<K, V> entry : bucket) values.add(entry.value);
        return values;
      }

    private Entry<K, V> bucketSeekEntry(int bucketIndex, K key) {
        if (key == null) return null;
        LinkedList<Entry<K,V>> bucket = table[bucketIndex];
        if (bucket == null) return null;
        for(Entry<K, V> entry: bucket)
            if (entry.key.equals(key)) return entry;
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for(int i=0; i<capacity; i++) {
            if (table[i] == null) continue;
            for (Entry<K, V> entry: table[i]) sb.append(entry + ", ");
        }
        // To delete last two characters.
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);

        sb.append("}");
        return sb.toString();
    }

    private V bucketInsertEntry(int bucketIndex, Entry<K,V> entry) {

        LinkedList<Entry<K,V>> bucket = table[bucketIndex];
        if (bucket == null)
            table[bucketIndex] = bucket = new LinkedList<>();
        
        Entry<K, V> existentEntry = bucketSeekEntry(bucketIndex, entry.key);
        if (existentEntry == null) {
            bucket.add(entry);
            if (size++ > threshold) resizeTable();
            return null;
        }
        else {
            V oldVal = existentEntry.value;
            existentEntry.value = entry.value;
            return oldVal;
        }
    }

    private V bucketRemoveEntry(int bucketIndex, K key) {
        Entry<K, V> entry = bucketSeekEntry(bucketIndex, key);
        if (entry != null) {
            LinkedList<Entry<K, V>> links = table[bucketIndex];
            links.remove(entry);
            size--;
            return entry.value;
        }
        return null;
    }

    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);

        LinkedList<Entry<K, V>>[] newTable = new LinkedList[capacity];

        for (int i=0; i<table.length; i++) {
            if (table[i] != null) {
                for (Entry<K, V> entry: table[i]) {
                    int bucketIndex = normalizeIndex(entry.hashCode);
                    LinkedList<Entry<K, V>> bucket = newTable[bucketIndex];
                    if (bucket == null)
                        newTable[bucketIndex] = bucket = new LinkedList<>();
                    bucket.add(entry);
                }

                table[i].clear();
                table[i] = null;
            }
        }

        table = newTable;
    }


}