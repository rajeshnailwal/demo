package practice.apache.hadoop.connect;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hdfs.DistributedFileSystem;

public class ConnectTest {

	public static void main(String[] args) throws IOException, URISyntaxException {
		/*Path outputPath = new Path("/hadooptest/hadooptest_1.txt");
		String localInputPath = "C:\\Development\\eclipse_neon\\workspace\\practice.apache.hadoop\\LocalFile.txt";*/
		Configuration conf = new Configuration();
	    FileSystem fileSystem = FileSystem.get(new URI("hdfs://localhost:19000"), conf);
	    if(fileSystem instanceof DistributedFileSystem) {
	    	System.out.println("yes : hdfs file system is connected");
	    } else {
	    	System.out.println("Other type of file system "+fileSystem.getClass());
	    }
	}

}
