package HashSet;

public class Entry<K>{
    K key;
    int hashCode;
    public Entry(K key) {
        this.key = key;
        this.hashCode = key.hashCode();
    }

    public boolean equals(Entry<K> other) {
        if (other.hashCode != this.hashCode) return false;
        return (other.key.equals(this.key));
    }

    public String toString() {
        return this.key + "";
    }
}
