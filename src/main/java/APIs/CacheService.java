package APIs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cache.LRUCacheImplementation;
import cache.MainCache;

@Service
public class CacheService {

	@Autowired
	private MainCache mainCache;

	public Object getValue(String cacheName, String keyName) throws Exception {
		LRUCacheImplementation<String, Object> cache = mainCache.getCache(cacheName);
		if (cache == null)
			throw new Exception("Cache is not found");
		return cache.get(keyName);
	}

	public void putValue(String cacheName, String keyName, Object value) throws Exception {
		LRUCacheImplementation<String, Object> cache = mainCache.getCache(cacheName);
		if (cache == null)
			throw new Exception("Cache is not found");
		cache.put(keyName, value);
	}

	public void removeValue(String cacheName, String keyName) throws Exception {
		LRUCacheImplementation<String, Object> cache = mainCache.getCache(cacheName);
		if (cache == null)
			throw new Exception("Cache is not found");
		cache.remove(keyName);
	}

	public Map<String, Object> getAllValues(String cacheName) throws Exception {
		LRUCacheImplementation<String, Object> cache = mainCache.getCache(cacheName);
		if (cache == null)
			throw new Exception("Cache is not found");
		return cache.getAll();
	}

	public Object getAllCache() {
		return mainCache.getAllCache();
	}

	public void createCache(String cacheName, String keyName, Object value, int maxSize) throws Exception {
		mainCache.createCache(cacheName, keyName, value, maxSize);
	}

	public Object getCache(String cacheName) {
		return mainCache.getCache(cacheName);
	}

	public void removeCache(String cacheName) {
		mainCache.removeCache(cacheName);
	}

}
