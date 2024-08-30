package APIs;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class CacheController {

	@Autowired
	private CacheService service;

	@PostMapping("/getValue")
	public ResponseEntity<?> getValue(@RequestBody CacheRequest request, HttpServletRequest httpRequest) {

		String cachename = request.getCacheName();
		String keyName = request.getKeyName();
		ResponseEntity<?> response;
		try {
			Object value = service.getValue(cachename, keyName);
			response = ResponseEntity.status(HttpStatus.OK).body(value);
		} catch (Exception e) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return response;
	}

	@PostMapping("getAllValues")
	public ResponseEntity<?> getAllValues(@RequestBody CacheRequest request) {
		String cacheName = request.getCacheName();
		Map<String, Object> allValues;
		ResponseEntity<?> response;
		try {
			allValues = service.getAllValues(cacheName);
			response = ResponseEntity.status(HttpStatus.OK).body(allValues);
		} catch (Exception e) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return response;

	}

	@PostMapping("/putValue")
	public ResponseEntity<?> putValue(@RequestBody CacheRequest request) {
		String cachename = request.getCacheName();
		String keyName = request.getKeyName();
		Object value = request.getValue();
		ResponseEntity<?> response;
		try {
			service.putValue(cachename, keyName, value);
			response = ResponseEntity.status(HttpStatus.OK).body("success");
		} catch (Exception e) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return response;
	}

	@PostMapping("/removeValue")
	public ResponseEntity<?> removeValue(@RequestBody CacheRequest request) {
		String cachename = request.getCacheName();
		String keyName = request.getKeyName();
		ResponseEntity<?> response;
		try {
			service.removeValue(cachename, keyName);
			response = ResponseEntity.status(HttpStatus.OK).body("success");
		} catch (Exception e) {
			response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		return response;
	}

	@PostMapping("/createCache")
	public ResponseEntity<?> createCache(@RequestBody CacheRequest request) {
		String cachename = request.getCacheName();
		String keyName = request.getKeyName();
		Object value = request.getValue();
		int maxSize = request.getMaxSize();
		if (maxSize == 0)
			maxSize = 50;
		ResponseEntity<?> response;
		try {
			service.createCache(cachename, keyName, value, maxSize);
			response = ResponseEntity.status(HttpStatus.OK).body("success");
		} catch (Exception e) {
			response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return response;
	}

	@PostMapping("getCache")
	public ResponseEntity<?> getCache(@RequestBody CacheRequest request) {
		String cacheName = request.getCacheName();
		Object cache = service.getCache(cacheName);
		ResponseEntity<?> response = ResponseEntity.status(HttpStatus.OK).body(cache);
		return response;

	}

	@PostMapping("removeCache")
	public ResponseEntity<?> removeCache(@RequestBody CacheRequest request) {
		String cacheName = request.getCacheName();
		service.removeCache(cacheName);
		ResponseEntity<?> response = ResponseEntity.status(HttpStatus.OK).body("success");
		return response;

	}

	@GetMapping("getAllCache")
	public ResponseEntity<?> getAllCache() {
		Object cache = service.getAllCache();
		ResponseEntity<?> response = ResponseEntity.status(HttpStatus.OK).body(cache);
		return response;

	}

}
