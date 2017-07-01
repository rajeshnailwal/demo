package practice.algorithm.code.cache;

import practice.algorithm.code.cache.cacheable.ICacheable;

/**
 * 
 * @author rajesh nailwal
 *
 */
public interface ICache {
	public void refresh() throws InterruptedException;
	public ICacheable getObject(Object identifier);
	public void addObject(Object identifier, Object object, int timeInMinutes);
	public boolean isRefreshInProgress();
	public boolean isWriteInProgress();
}
