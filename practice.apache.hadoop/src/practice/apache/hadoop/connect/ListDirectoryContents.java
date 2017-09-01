package practice.apache.hadoop.connect;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.FileUtil;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;

public class ListDirectoryContents {
	public static void main(String[] args) throws IOException, URISyntaxException {
		Path path = new Path("hdfs://localhost:19000/hadooptest");
		Configuration conf = new Configuration();
	    FileSystem fileSystem = FileSystem.get(new URI("hdfs://localhost:19000"), conf);
	    if(fileSystem instanceof DistributedFileSystem) {
	    	System.out.println("yes : hdfs file system is connected");
	    	
	    	if(fileSystem.exists(path)) {
	    		FileStatus[] fileStatuses = fileSystem.listStatus(path);
	    		for(FileStatus status : fileStatuses) {
	    			System.out.println(status.getPath().getName());
	    		}
	    		
	    		Path[] paths = FileUtil.stat2Paths(fileStatuses);
	    		for(Path filePath : paths) {
	    			System.out.println(filePath.getName());
	    		}
	    	}
	    } else {
	    	System.out.println("Other type of file system "+fileSystem.getClass());
	    }
	}
}
