package maps;

import java.util.*;

public class ArrayMap<K, V>  extends AbstractMap<K, V> {


    class ArrayMapEntry implements Map.Entry<K, V> {
        private K key;
        private V value;

        public ArrayMapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public String toString() {
            return "K: " + key + " V: " + value;
        }

        @Override
        public boolean equals(Object obj) {
            if (getClass() != obj.getClass())
                return false;
            ArrayMapEntry entry = ((ArrayMapEntry)obj);
            return (key.equals(entry.key) && value.equals(entry.value));
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
    protected ArrayList<ArrayMapEntry> arrayList;

    public ArrayMap() {
        arrayList = new ArrayList<>();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(arrayList);
    }

    @Override
    public V put(K key, V value) {
        V old = null;
        for(ArrayMapEntry entry : arrayList) {
            if(entry.key.equals(key)) {
                old = entry.getValue();
                entry.setValue(value);
            }
        }

        arrayList.add(new ArrayMapEntry(key, value));
        return old;
    }

    @Override
    public boolean containsKey(Object key) {
        for(ArrayMapEntry entry : arrayList) {
            if(entry.getKey().equals(key))
                return true;
        }
        return false;
    }

    @Override
    public V get(Object key) {
        return super.get(key);
    }

    @Override
    public int size() {
        return super.size();
    }
}
