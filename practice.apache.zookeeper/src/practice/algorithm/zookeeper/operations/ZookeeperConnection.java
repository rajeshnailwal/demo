package practice.algorithm.zookeeper.operations;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

public class ZookeeperConnection {
	
	// declare zookeeper instance to access ZooKeeper ensemble
	private static ZooKeeper zoo;
	private static final CountDownLatch connectedSignal = new CountDownLatch(1);
	private static Semaphore sem = new Semaphore(1);
	
	public static ZooKeeper getConnection(String host) throws IOException, InterruptedException{
		if(zoo == null) {
			sem.acquire();
			try {
				if(zoo == null) {
					connect(host);
				}
			} finally {
				sem.release();
			}
		}
		return zoo;
	}
	
	// Method to connect zookeeper ensemble.
	private static ZooKeeper connect(String host) throws IOException,InterruptedException {
		
		zoo = new ZooKeeper(host,5000,new Watcher() {
			
			public void process(WatchedEvent we) {

				if (we.getState() == KeeperState.SyncConnected) {
					connectedSignal.countDown();
	            }
	         }
	    });
			
		connectedSignal.await();
		return zoo;
	}

	// Method to disconnect from zookeeper server
	public static void close() throws InterruptedException {
		if(zoo != null) zoo.close();
		zoo = null;
	}
	
}
