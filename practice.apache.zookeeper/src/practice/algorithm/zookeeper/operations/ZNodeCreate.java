package practice.algorithm.zookeeper.operations;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

public class ZNodeCreate {
	
	// create static instance for zookeeper class.
	private static ZooKeeper zk;

	// Method to create znode in zookeeper ensemble
	public static void create(String path, byte[] data) throws KeeperException,InterruptedException {
		zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}

	public static void main(String[] args) {
		// znode path
		String path = "/MyFirstZnode"; // Assign path to znode

		// data in byte array
		byte[] data = "My first zookeeper app".getBytes(); // Declare data
			
		try {
			zk = ZookeeperConnection.getConnection("127.0.0.1:2181");
			create(path, data); // Create the data to the specified path
			ZookeeperConnection.close();
	    } catch (Exception e) {
	    	System.out.println(e.getMessage()); //Catch error message
	    }
	}
}
