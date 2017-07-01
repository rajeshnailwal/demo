package practice.algorithm.code.cache;

/**
 * 
 * @author rajesh nailwal
 *
 */
public class Status {
	private boolean isWriteInProgress;
	private boolean isRefreshInProgress;
	
	public boolean isWriteInProgress() {
		return isWriteInProgress;
	}
	public void setWriteInProgress(boolean isWriteInProgress) {
		this.isWriteInProgress = isWriteInProgress;
	}
	public boolean isRefreshInProgress() {
		return isRefreshInProgress;
	}
	public void setRefreshInProgress(boolean isRefreshInProgress) {
		this.isRefreshInProgress = isRefreshInProgress;
	}	
}
