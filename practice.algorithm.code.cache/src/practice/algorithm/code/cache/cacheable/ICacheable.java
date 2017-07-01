package practice.algorithm.code.cache.cacheable;

import java.util.Date;

/**
 * 
 * @author rajesh nailwal
 *
 */
public interface ICacheable {
	public Object getIdentifier();
	public Object getObject();
	public boolean isExpired();
	public void updateExpirationTime();
	public Date lastAccessed();
}
