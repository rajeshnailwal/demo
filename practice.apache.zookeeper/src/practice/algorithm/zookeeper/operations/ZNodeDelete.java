package practice.algorithm.zookeeper.operations;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZNodeDelete {
	private static ZooKeeper zk;
	
	// Method to check existence of znode and its status, if znode is available.
	public static void delete(String path) throws KeeperException,InterruptedException {
		zk.delete(path,zk.exists(path,true).getVersion());
	}

	public static void main(String[] args) throws InterruptedException,KeeperException {
		String path = "/MyFirstZnode"; //Assign path to the znode
		
		try {
			zk = ZookeeperConnection.getConnection("127.0.0.1:2181");
			delete(path); //delete the node with the specified path
	    } catch(Exception e) {
	    	System.out.println(e.getMessage()); // catches error messages
	    }
	}
}
