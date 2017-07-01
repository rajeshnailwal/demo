package practice.algorithm.code.cache.launcher;

import practice.algorithm.code.cache.manager.CacheManager;
import practice.algorithm.code.cache.manager.CacheManager.CacheType;

/**
 * 
 * @author rajesh nailwal
 *
 */
public class ApplicationLauncher {
	
	public static void main(String...strings){
		CacheManager cm = CacheManager.getCacheManager();
		ObjectToCache co = null;
		
		for(int i = 0; i < 50; i++){
			co = new ObjectToCache(Integer.toString(i));
			cm.addInCache(co.getId(), co, CacheType.LRU);
		}
		
		cm.getCachedObject("1", CacheType.LRU);
		cm.getCachedObject("10", CacheType.LRU);
		
		for(int i = 0; i < 50; i++){
			try {
				Thread.sleep(1000);
				co = new ObjectToCache(Integer.toString(51+i));
				cm.addInCache(co.getId(), co, CacheType.LRU);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public static class ObjectToCache {
		private String id;
		
		public ObjectToCache(String id){
			this.id = id;
		}
		
		public String getId(){
			return id;
		}
		
		public String toString(){
			return "Object id "+this.id;
		}
	}
}
