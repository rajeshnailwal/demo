package practice.algorithm.code.cache.cacheable;

import java.util.Date;

/**
 * 
 * @author rajesh nailwal
 *
 */
public class Cacheable implements ICacheable {
	
	private Object idetifier;
	private Object cachedObject;
	private Date expirationTime;
	private int minutesToExpire;
	
	public Cacheable(Object identifier, Object cachedObject, int minutesToExpire) {
		this.idetifier = identifier;
		this.cachedObject = cachedObject;
		this.minutesToExpire = minutesToExpire;
		updateExpirationTime();
	}

	@Override
	public Object getIdentifier() {
		return idetifier;
	}
	
	@Override
	public Object getObject(){
		return cachedObject;
	}

	@Override
	public boolean isExpired() {
		return new Date().after(expirationTime);
	}
	
	@Override
	public void updateExpirationTime(){
		long current = System.currentTimeMillis();
		current += minutesToExpire * 60 * 1000;
		expirationTime = new Date(current);
	}
	
	@Override
	public Date lastAccessed(){
		return new Date(expirationTime.getTime() - minutesToExpire * 60 * 1000);
	}
	
}
