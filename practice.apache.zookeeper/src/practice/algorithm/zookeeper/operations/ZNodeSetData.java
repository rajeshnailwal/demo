package practice.algorithm.zookeeper.operations;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

public class ZNodeSetData {
	private static ZooKeeper zk;
	
	// Method to update the data in a znode. Similar to getData but without watcher.
	public static void update(String path, byte[] data) throws KeeperException,InterruptedException {
		zk.setData(path, data, zk.exists(path,true).getVersion());
	}

	public static void main(String[] args) throws InterruptedException,KeeperException {
		String path= "/MyFirstZnode";
		byte[] data = "Success".getBytes();//"Success".getBytes(); //Assign data which is to be updated.
			
		try {
			zk = ZookeeperConnection.getConnection("127.0.0.1:2181");
			update(path, data); // Update znode data to the specified path
	    } catch(Exception e) {
	    	System.out.println("Exception "+e.getMessage());
	    }
	}
}
