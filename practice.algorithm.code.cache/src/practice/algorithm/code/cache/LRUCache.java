package practice.algorithm.code.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import practice.algorithm.code.cache.cacheable.Cacheable;
import practice.algorithm.code.cache.cacheable.ICacheable;

/**
 * 
 * @author rajesh nailwal
 *
 */
public class LRUCache extends LinkedHashMap<Object, ICacheable> implements ICache {
	
	private static final int cacheSizeLimit = 25;
	private static final int reductionThreshold = 15;
	
	private Status status = new Status();

	{
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true) {
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					try {
						System.out.println("Refresh called");
						refresh();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
				}				
			}
			
		}).start();
	}
	
	public LRUCache() {
		super(16, 0.75f, true);
	}
	
	@Override
	public void refresh() throws InterruptedException {
		synchronized (this) {
			System.out.println("LRU Cache size before refresh starts "+this.size());
			while(status.isWriteInProgress()) this.wait();
			
			status.setRefreshInProgress(true);
			
			int size = this.size();
			
			if(!this.isEmpty() && size > cacheSizeLimit){
				Iterator<Entry<Object, ICacheable>> iterator = this.entrySet().iterator();
				
				//Delete till last ten as cache is a LinkedHashMap with access order so it will automatically iterates over map in access order 
				int i = (size - cacheSizeLimit) + (cacheSizeLimit - reductionThreshold);
				
				while(iterator.hasNext()){
					Entry<Object, ICacheable> entry = iterator.next();
					ICacheable value = entry.getValue();
					
					if(i > 0){
						System.out.println("Object being deleted "+value.getObject());
						iterator.remove();
					}					
					--i;
				}
			}
			
			status.setRefreshInProgress(false);
			System.out.println("LRU Cache size after refresh completes "+this.size());
			this.notifyAll();
		}
	}

	@Override
	public ICacheable getObject(Object identifier) {
		ICacheable cachedObject = null;
		try {
			synchronized (this) {
				while(status.isRefreshInProgress() || status.isWriteInProgress()) this.wait();
				
				cachedObject = this.get(identifier);
				if(cachedObject != null) cachedObject.updateExpirationTime();
				
				this.notifyAll();
			}			
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		return cachedObject;
	}

	@Override
	public void addObject(Object identifier, Object object, int timeInMinutes) {
		try {
			synchronized (this) {
				while(status.isRefreshInProgress()) this.wait();		
				
				status.setWriteInProgress(true);
				
				this.put(identifier, new Cacheable(identifier, object, timeInMinutes));
				
				status.setWriteInProgress(false);
				this.notifyAll();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isRefreshInProgress() {
		return status.isRefreshInProgress();
	}

	@Override
	public boolean isWriteInProgress() {
		return status.isWriteInProgress();
	}
	
	
	
}
