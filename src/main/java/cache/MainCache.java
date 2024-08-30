package cache;

import java.util.HashMap;

import org.springframework.stereotype.Component;

@Component
public class MainCache {

	private final HashMap<String, Object> cacheMap = new HashMap<>();

	public void createCache(String cacheName, String keyName, Object keyValue, int maxSize) throws Exception {
		if (cacheMap.containsKey(cacheName))
			throw new Exception("Cache is already existing");
		cacheMap.put(cacheName, new LRUCacheImplementation<String, Object>(maxSize));

	}

	public boolean removeCache(String cacheName) {
		boolean result = false;
		if (cacheMap.containsKey(cacheName)) {
			cacheMap.remove(cacheName);
			result = true;
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public LRUCacheImplementation<String, Object> getCache(String cacheName) {

		return (LRUCacheImplementation<String, Object>) cacheMap.get(cacheName);
	}

	public HashMap<String, Object> getAllCache() {
		return cacheMap;
	}

}
