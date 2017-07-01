package practice.algorithm.code.cache;

import java.util.HashMap;
import java.util.Iterator;

import practice.algorithm.code.cache.cacheable.Cacheable;
import practice.algorithm.code.cache.cacheable.ICacheable;

/**
 * 
 * @author rajesh nailwal
 *
 */
public class GeneralCache extends HashMap<Object, ICacheable>implements ICache {

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
	
	@Override
	public void refresh() throws InterruptedException {
		synchronized (this) {
			while(status.isWriteInProgress()) this.wait();
			
			status.setRefreshInProgress(true);
			
			if(!this.isEmpty()){
				Iterator<Entry<Object, ICacheable>> iterator = this.entrySet().iterator();
				while(iterator.hasNext()){
					Entry<Object, ICacheable> entry = iterator.next();
					ICacheable value = entry.getValue();
					if(value != null && value.isExpired()) iterator.remove();
				}
			}
			
			status.setRefreshInProgress(false);	
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
