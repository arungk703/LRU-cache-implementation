package cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCacheImplementation<K, V> implements LRUCache<K, V> {

	private final Map<K, V> cache;
	private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private final int maxSize;

	public LRUCacheImplementation(int maxSize) {
		this.cache = new LinkedHashMap<K, V>(maxSize, 0.75f, true) {
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
				return size() > LRUCacheImplementation.this.maxSize;
			}
		};
		this.maxSize = maxSize;

	}

	@Override
	public V get(K key) {
		lock.readLock().lock();
		try {
			return cache.get(key);
		} finally {
			lock.readLock().unlock();
		}
	}

	@Override
	public void put(K key, V value) {
		lock.writeLock().lock();
		try {
			cache.put(key, value);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public V remove(K key) {
		lock.writeLock().lock();
		try {
			return cache.remove(key);
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public int size() {
		return this.maxSize;
	}

	@Override
	public void clear() {
		lock.writeLock().lock();
		try {
			cache.clear();
		} finally {
			lock.writeLock().unlock();
		}
	}

	@Override
	public Map<K, V> getAll() {
		return this.cache;
	}
}
