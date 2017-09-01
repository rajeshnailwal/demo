package practice.apache.hadoop.connect;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.io.IOUtils;

public class ReadFileFromHadoop {

	public static void main(String[] args) throws IOException, URISyntaxException {
		Path path = new Path("hdfs://localhost:19000/hadooptest/Hadoop_File.txt");
		Configuration conf = new Configuration();
	    FileSystem fileSystem = FileSystem.get(new URI("hdfs://localhost:19000"), conf);
	    if(fileSystem instanceof DistributedFileSystem) {
	    	System.out.println("yes : hdfs file system is connected");
	    	
	    	if(fileSystem.exists(path)) {
	    		InputStream inputStream = null;
	    		System.out.println("yes : hdfs file exists");
	    		try {
	    			inputStream = fileSystem.open(path);
	    			IOUtils.copyBytes(inputStream, System.out, 4096, false);
	    		} finally {
	    			IOUtils.closeStream(inputStream);
	    		}
	    	}
	    } else {
	    	System.out.println("Other type of file system "+fileSystem.getClass());
	    }
	}

}
