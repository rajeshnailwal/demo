package practice.algorithm.code.cache.manager;

import java.util.HashMap;
import java.util.Map;

import practice.algorithm.code.cache.GeneralCache;
import practice.algorithm.code.cache.ICache;
import practice.algorithm.code.cache.LRUCache;
import practice.algorithm.code.cache.cacheable.ICacheable;

/**
 * 
 * @author rajesh nailwal
 *
 */
public class CacheManager {
	
	private static Map<CacheType, ICache> cacheCollection = new HashMap<CacheType, ICache>();
	private static CacheManager instance;
	
	static {
		cacheCollection.put(CacheType.GENERAL, new GeneralCache());
		cacheCollection.put(CacheType.LRU, new LRUCache());
	}
		
	public enum CacheType {GENERAL, LRU};
	
	private CacheManager(){}
	
	public static CacheManager getCacheManager(){
		if(instance == null){
			synchronized (CacheManager.class) {
				if(instance == null){
					instance = new CacheManager();
				}
			}
		}
		return instance;
	}
	
	private ICache getCache(CacheType cacheType){
		return cacheCollection.get(cacheType);
	}
	
	public void addInCache(Object identifier, Object object, CacheType cacheType){
		ICache cache = CacheManager.getCacheManager().getCache(cacheType);
		cache.addObject(identifier, object, 5);
	}
	
	public Object getCachedObject(Object identifier, CacheType cacheType){
		ICache cache = CacheManager.getCacheManager().getCache(cacheType);
		ICacheable cachedObject = cache.getObject(identifier);
		return cachedObject != null ? cachedObject.getObject() : null;
	}
}
