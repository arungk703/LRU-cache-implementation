package cache;

import java.util.Map;

public interface LRUCache<K, V> {

	public V get(K key);

	public void put(K key, V value);

	public V remove(K key);

	public int size();

	public void clear();

	public Map<K, V> getAll();
}
