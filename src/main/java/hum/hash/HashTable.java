package hum.hash;

import java.util.TreeMap;

/**
 *  可以维护素数表来扩容
 * @author hum
 */
public class HashTable<K extends Comparable<K>, V> {

    private static final int UPPER_TOL = 10;
    private static final int LOWER_TOL = 2;
    private static final int INIT_CAPACITY = 7;

    private TreeMap<K, V>[] hashTable;
    private int size;
    private int M;

    public HashTable(int M) {
        this.M = M;
        size = 0;
        hashTable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashTable[i] = new TreeMap<>();
        }
    }

    public HashTable() {
        this(INIT_CAPACITY);
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public int getSize() {
        return size;
    }

    public void add(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            map.put(key, value);
        } else {
            map.put(key, value);
            size++;
            if (size >= UPPER_TOL * M) {
                resize(2 * M);
            }
        }
    }


    public V remove(K key) {
        V ret = null;
        TreeMap<K, V> map = hashTable[hash(key)];
        if (map.containsKey(key)) {
            ret = map.remove(key);
            size--;
            if (size < LOWER_TOL * M && M / 2 >= INIT_CAPACITY) {
                resize(M / 2);
            }
        }
        return ret;
    }

    private void resize(int newM) {
        TreeMap<K, V>[] newHashTable = new TreeMap[newM];
        for (int i = 0; i < newM; i++) {
            newHashTable[i] = new TreeMap<>();
        }

        int oldM = M;
        // 保证下边计算hash值正确
        this.M = newM;
        for (int i = 0; i < oldM; i++) {
            TreeMap<K, V> map = hashTable[i];
            for (K key : map.keySet()) {
                newHashTable[hash(key)].put(key, map.get(key));
            }
        }

        this.hashTable = newHashTable;
    }

    public void set(K key, V value) {
        TreeMap<K, V> map = hashTable[hash(key)];
        if (!map.containsKey(key)) {
            throw new IllegalArgumentException(key + " doesn't exist!");
        }

        map.put(key, value);
    }

    public boolean contains(K key) {
        return hashTable[hash(key)].containsKey(key);
    }

    public V get(K key) {
        return hashTable[hash(key)].get(key);
    }
}

