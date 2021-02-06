package HashMap;

public class Entry<K, V> {
    K key;
    V value;
    int hashCode;

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.hashCode = key.hashCode();
    }

    public boolean equals(Entry<K, V> other) {
        if (this.hashCode != other.hashCode) return false;
        return this.key.equals(other.key);
    }

    @Override
    public String toString() {
        return key + " : " + value;
    }
}