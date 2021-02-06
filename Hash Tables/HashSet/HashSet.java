package HashSet;

import java.util.*;

// HashSet could be implemented by using HashMap as wel.
// For example, HashMap<Key, ""> 

@SuppressWarnings("unchecked")
public class HashSet <K> implements Iterable<K>{
    private static final int DEFAULT_CAPACITY = 3;
    private static final double DEFAULT_LOAD_FACTOR = 0.75;
    private double maxLoadFactor = 0;
    private int capacity, threshold, size = 0;
    private LinkedList <Entry<K>>[] table;

    public HashSet() {
        this(DEFAULT_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public HashSet(int capacity) {
        this(capacity, DEFAULT_LOAD_FACTOR);
    }
    
    public HashSet(int capacity, double maxLoadFactor) {
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal capacity.");

        if (maxLoadFactor <= 0 || Double.isNaN(maxLoadFactor) || Double.isInfinite(maxLoadFactor))
            throw new IllegalArgumentException("Illegal maxLoadFactor.");

        this.maxLoadFactor = maxLoadFactor;
        this.capacity = Math.max(DEFAULT_CAPACITY, capacity);
        threshold = (int) (this.capacity * maxLoadFactor);
        table = new LinkedList[this.capacity];        
    }

    public void clear() {
        Arrays.fill(table, null);
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int normalizeIndex(int hashCode) {
        return (hashCode & 0x7FFFFFFF) % capacity;
    }

    public boolean contains(K key) {
        if (key == null) throw new IllegalArgumentException("Null Key.");
        int bucketIndex = normalizeIndex(key.hashCode());
        return bucketSeekEntry(key, bucketIndex) != null;
    }

    public Iterator<K> iterator() {
        return keySet().iterator();
    }

    private Entry<K> bucketSeekEntry(K key, int bucketIndex) {
        LinkedList<Entry<K>> bucket = table[bucketIndex];
        if (bucket == null) bucket = new LinkedList<>();
        for (Entry<K> entry: bucket)
            if (entry.key.equals(key)) return entry;
        return null;
    }

    public boolean add(K key) {
        if (key == null)  throw new IllegalArgumentException("Null Key.");
        if (contains(key)) return false;

        Entry<K> newEntry = new Entry<>(key);
        int bucketIndex = normalizeIndex(newEntry.hashCode);
        LinkedList<Entry<K>> bucket = table[bucketIndex];
        if (bucket == null) table[bucketIndex] = bucket = new LinkedList<>();
        bucket.add(newEntry);
        if (size++ > threshold) resizeTable();

        return true;
    }

    public boolean remove(K key) {
        if (key == null)  throw new IllegalArgumentException("Null Key.");
        if (!contains(key)) return false;
        int bucketIndex = normalizeIndex(key.hashCode());
        Entry<K> entry = bucketSeekEntry(key, bucketIndex);
        LinkedList<Entry<K>> links = table[bucketIndex];
        links.remove(entry);
        size--;
        return true;

    }

    private void resizeTable() {
        capacity *= 2;
        threshold = (int) (capacity * maxLoadFactor);
        LinkedList<Entry<K>>[] newTable = new LinkedList[capacity];

        for (int i=0; i<table.length; i++) {
            if (table[i] == null) continue;
            for(Entry<K> key: table[i]) {
                int bucketIndex = normalizeIndex(key.hashCode);
                LinkedList<Entry<K>> bucket = newTable[bucketIndex];
                if (bucket == null) bucket = new LinkedList<>();
                bucket.add(key);
            }

            // Helps out the Garbage Collector.
            table[i].clear();
            table[i] = null;
        }

        table = newTable;
    }

    private List<K> keySet() {
        ArrayList<K> keys = new ArrayList<>(size());
        for(LinkedList<Entry<K>> bucket: table) {
            if (bucket != null) {
                for(Entry<K> entry: bucket) keys.add(entry.key);
            }
        }
        return keys;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (int i=0; i<table.length; i++) {
            if (table[i] == null) continue;
            for (Entry<K> entry: table[i]) sb.append(entry + ", ");
        }

        //Delete Last two character
        sb.deleteCharAt(sb.length()-1);
        sb.deleteCharAt(sb.length()-1);

        sb.append("}");

        return sb.toString();

    }



}
